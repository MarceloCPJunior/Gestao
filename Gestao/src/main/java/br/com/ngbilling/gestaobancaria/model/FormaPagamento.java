package br.com.ngbilling.gestaobancaria.model;

import java.math.BigDecimal;

import br.com.ngbilling.gestaobancaria.arquitetura.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "forma_pagamento")
public class FormaPagamento extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cd_forma_pagamento_seq")
	@SequenceGenerator(name = "cd_forma_pagamento_seq", sequenceName = "cd_forma_pagamento_seq", allocationSize = 1)
	@Column(name = "cd_forma_pagamento", nullable = false)
	private short cdFormaPagamento;

	@Column(name = "ch_forma_pagamento", nullable = false, unique = true)
	private Character chFormaPagamento;

	@Column(name = "vl_taxa")
	private BigDecimal vlTaxa;

	public short getCdFormaPagamento() {
		return cdFormaPagamento;
	}

	public void setCdFormaPagamento(short cdFormaPagamento) {
		this.cdFormaPagamento = cdFormaPagamento;
	}

	public Character getChFormaPagamento() {
		return chFormaPagamento;
	}

	public void setChFormaPagamento(Character chFormaPagamento) {
		this.chFormaPagamento = chFormaPagamento;
	}

	public BigDecimal getVlTaxa() {
		return vlTaxa;
	}

	public void setVlTaxa(BigDecimal vlTaxa) {
		this.vlTaxa = vlTaxa;
	}

}
