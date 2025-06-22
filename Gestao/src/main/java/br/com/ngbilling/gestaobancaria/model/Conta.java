package br.com.ngbilling.gestaobancaria.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import br.com.ngbilling.gestaobancaria.arquitetura.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "conta")
public class Conta extends BaseEntity {
	@Id
	@Column(name = "cd_conta", nullable = false)
	private long cdConta;

	@Column(name = "saldo", nullable = false)
	private BigDecimal saldo;

	public long getCdConta() {
		return cdConta;
	}

	public void setCdConta(long cdConta) {
		this.cdConta = cdConta;
	}

	public BigDecimal getSaldo() {
		return saldo;
	}

	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}

	@Override
	public List<String> validarEntidade() {
		List<String> erros = new ArrayList<>();
		if (cdConta == 0L) {
			erros.add("Número da conta inválido");
		}
		if (saldo == null) {
			erros.add("Saldo é obrigatório");
		} else if (saldo.compareTo(BigDecimal.ZERO) < 0) {
			erros.add("Saldo precisa ser positivo");
		}
		return erros;
	}

	@Override
	public Object obteinId() {
		return this.cdConta;
	}

}
