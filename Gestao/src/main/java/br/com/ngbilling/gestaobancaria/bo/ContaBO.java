package br.com.ngbilling.gestaobancaria.bo;

import java.util.List;

import br.com.ngbilling.gestaobancaria.dao.ContaDAOI;
import br.com.ngbilling.gestaobancaria.dto.model.ContaDTO;
import br.com.ngbilling.gestaobancaria.dto.response.ResponseDTO;
import br.com.ngbilling.gestaobancaria.model.Conta;
import br.com.ngbilling.gestaobancaria.util.JPAUtil;
import br.com.ngbilling.gestaobancaria.util.JSONUtil;
import jakarta.persistence.EntityManager;

public class ContaBO {
	public ResponseDTO<ContaDTO> criarConta(String jsonRequest, String ambiente) {
		ResponseDTO<ContaDTO> response = new ResponseDTO<>();

		try (EntityManager em = JPAUtil.getEntityManager(ambiente)) {
			ContaDTO contaDTO = (ContaDTO) JSONUtil.jsonToEntityDTO(jsonRequest, new ContaDTO());
			response.setResponse(contaDTO);

			ContaDAOI dao = new ContaDAOI(em);
			Conta conta = dtoToEntity(em, contaDTO);

			dao.verificarDuplicidade(conta);

			validar(conta, response);

			dao.save(conta);

			response.setInSuccess(true);
		} catch (Exception e) {
			response.addErro("Erro ao criar conta: " + e.getMessage());
		}

		return response;
	}

	public ResponseDTO<ContaDTO> consultarConta(Long nrConta, String ambiente) {
		ResponseDTO<ContaDTO> response = new ResponseDTO<>();

		try (EntityManager em = JPAUtil.getEntityManager(ambiente)) {
			ContaDAOI dao = new ContaDAOI(em);

			ContaDTO contaDTO = dao.buscarContaToDTO(nrConta);

			if (contaDTO == null) {
				throw new Exception("Nenhuma conta encontrada.");
			} else {
				response.setInSuccess(true);
				response.setResponse(contaDTO);
			}
		} catch (Exception e) {
			response.addErro("Erro ao buscar conta: " + e.getMessage());
		}
		return response;
	}

	private Conta dtoToEntity(EntityManager em, ContaDTO dto) throws Exception {
		try {
			Conta conta = new Conta();
			conta.setCdConta(dto.getNumeroConta());
			conta.setSaldo(dto.getSaldo());
			return conta;
		} catch (Exception e) {
			throw new Exception("Informações inválidas");
		}
	}

	private void validar(Conta conta, ResponseDTO<?> response) throws Exception {
		List<String> erros = conta.validarEntidade();
		if (!erros.isEmpty()) {
			response.setErros(erros);
			throw new Exception("Problema ao validar informações");
		}
	}
}
