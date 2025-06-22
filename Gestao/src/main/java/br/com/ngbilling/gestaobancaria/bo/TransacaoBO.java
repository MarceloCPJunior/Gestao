package br.com.ngbilling.gestaobancaria.bo;

import java.math.BigDecimal;
import java.util.List;

import br.com.ngbilling.gestaobancaria.dao.ContaDAOI;
import br.com.ngbilling.gestaobancaria.dao.TransacaoDAOI;
import br.com.ngbilling.gestaobancaria.dto.model.ContaDTO;
import br.com.ngbilling.gestaobancaria.dto.model.TransacaoDTO;
import br.com.ngbilling.gestaobancaria.dto.response.ResponseDTO;
import br.com.ngbilling.gestaobancaria.model.Conta;
import br.com.ngbilling.gestaobancaria.model.FormaPagamento;
import br.com.ngbilling.gestaobancaria.model.Transacao;
import br.com.ngbilling.gestaobancaria.util.JPAUtil;
import br.com.ngbilling.gestaobancaria.util.JSONUtil;
import jakarta.persistence.EntityManager;

public class TransacaoBO {
	public ResponseDTO<ContaDTO> criarTransacao(String jsonRequest, String ambiente) {
		ResponseDTO<ContaDTO> response = new ResponseDTO<>();

		try (EntityManager em = JPAUtil.getEntityManager(ambiente)) {
			TransacaoDTO transacaoDTO = (TransacaoDTO) JSONUtil.jsonToEntityDTO(jsonRequest, new TransacaoDTO());

			TransacaoDAOI transacaoDao = new TransacaoDAOI(em);
			ContaDAOI contaDAO = new ContaDAOI(em);

			Transacao transacao = gerarTransacao(em, transacaoDao, contaDAO, transacaoDTO);

			validar(transacao, response);

			transacionar(transacao.getConta(), transacao);

			transacao.setConta(contaDAO.update(transacao.getConta()));
			transacaoDao.save(transacao);

			ContaDTO contaDTO = new ContaDTO();
			contaDTO.fromEntity(transacao.getConta());

			response.setResponse(contaDTO);
			response.setInSuccess(true);
		} catch (Exception e) {
			response.addErro("Erro ao tentar transacionar: " + e.getMessage());
		}

		return response;
	}

	private void transacionar(Conta conta, Transacao transacao) throws Exception {
		BigDecimal taxa = BigDecimal.ZERO;
		if (transacao.getFormaPagamento().getVlTaxa() != null) {
			taxa = transacao.getValor().multiply(transacao.getFormaPagamento().getVlTaxa());
		}
		BigDecimal novoSaldo = conta.getSaldo().subtract(transacao.getValor().add(taxa));
		if (novoSaldo.compareTo(BigDecimal.ZERO) < 0) {
			throw new Exception("Saldo insuficiente!");
		}
		conta.setSaldo(novoSaldo);
	}

	private Transacao gerarTransacao(EntityManager em, TransacaoDAOI dao, ContaDAOI contaDAO, TransacaoDTO dto)
			throws Exception {
		Transacao transacao = new Transacao();
		FormaPagamento formaPagamento = dao.buscarFormaPagamento(dto.getFormaPagamento());
		Conta conta = dto.getNumeroConta() != null ? contaDAO.findById(dto.getNumeroConta()) : null;

		transacao.setConta(conta);
		transacao.setFormaPagamento(formaPagamento);
		transacao.setValor(dto.getValor());
		return transacao;
	}

	private void validar(Transacao transacao, ResponseDTO<ContaDTO> response) throws Exception {
		List<String> erros = transacao.validarEntidade();
		if (!erros.isEmpty()) {
			response.setErros(erros);
			throw new Exception("Problema ao validar informações");
		}
	}
}
