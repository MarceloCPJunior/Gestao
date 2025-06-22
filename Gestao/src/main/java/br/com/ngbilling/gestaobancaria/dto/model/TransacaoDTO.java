package br.com.ngbilling.gestaobancaria.dto.model;

import java.math.BigDecimal;

import br.com.ngbilling.gestaobancaria.arquitetura.BaseEntity;
import br.com.ngbilling.gestaobancaria.interfaces.EntityDTO;
import br.com.ngbilling.gestaobancaria.model.Transacao;

public class TransacaoDTO implements EntityDTO {
	private Character formaPagamento;
	private Long numeroConta;
	private BigDecimal valor;

	public Character getFormaPagamento() {
		return formaPagamento;
	}

	public void setFormaPagamento(Character formaPagamento) {
		this.formaPagamento = formaPagamento;
	}

	public Long getNumeroConta() {
		return numeroConta;
	}

	public void setNumeroConta(Long numeroConta) {
		this.numeroConta = numeroConta;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	@Override
	public void fromEntity(BaseEntity entity) {
		Transacao transacao = (Transacao) entity;
		this.formaPagamento = transacao.getFormaPagamento().getChFormaPagamento();
		this.numeroConta = transacao.getConta().getCdConta();
		this.valor = transacao.getValor();
	}
}
