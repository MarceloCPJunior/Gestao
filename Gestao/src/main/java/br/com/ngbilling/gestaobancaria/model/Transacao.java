package br.com.ngbilling.gestaobancaria.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import br.com.ngbilling.gestaobancaria.arquitetura.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "transacao")
public class Transacao extends BaseEntity {
	@Id
	@Column(name = "cd_transacao", nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cd_transacao_seq")
	@SequenceGenerator(name = "cd_transacao_seq", sequenceName = "cd_transacao_seq", allocationSize = 1)
	private long cdTransacao;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cd_conta", referencedColumnName = "cd_conta", nullable = false)
	private Conta conta;

	@Column(name = "valor", nullable = false)
	private BigDecimal valor;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cd_forma_pagamento", referencedColumnName = "cd_forma_pagamento", nullable = false)
	private FormaPagamento formaPagamento;

	public long getCdTransacao() {
		return cdTransacao;
	}

	public void setCdTransacao(long cdTransacao) {
		this.cdTransacao = cdTransacao;
	}

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public FormaPagamento getFormaPagamento() {
		return formaPagamento;
	}

	public void setFormaPagamento(FormaPagamento formaPagamento) {
		this.formaPagamento = formaPagamento;
	}

	@Override
	public List<String> validarEntidade() {
		List<String> erros = new ArrayList<>();
		if (conta == null) {
			erros.add("Nenhuma conta encontrada");
		} else if (conta.getCdConta() == 0) {
			erros.add("Conta é obrigatória");
		}
		if (valor == null || valor.compareTo(BigDecimal.ZERO) <= 0) {
			erros.add("Valor inválido");
		}
		if (formaPagamento == null || formaPagamento.getCdFormaPagamento() == 0) {
			erros.add("Forma de pagamento inválido");
		}
		return erros;
	}

	@Override
	public Object obteinId() {
		return this.cdTransacao;
	}
}
