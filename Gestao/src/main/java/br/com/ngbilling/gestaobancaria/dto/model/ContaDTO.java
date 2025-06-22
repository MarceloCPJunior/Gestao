package br.com.ngbilling.gestaobancaria.dto.model;

import java.math.BigDecimal;

import br.com.ngbilling.gestaobancaria.arquitetura.BaseEntity;
import br.com.ngbilling.gestaobancaria.interfaces.EntityDTO;
import br.com.ngbilling.gestaobancaria.model.Conta;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "DTO para representar uma conta bancária")
public class ContaDTO implements EntityDTO {

	@Schema(description = "Número da conta bancária", example = "123456789")
	private Long numeroConta;

	@Schema(description = "Saldo atual da conta", example = "1500.50")
	private BigDecimal saldo;

	public ContaDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ContaDTO(Long numeroConta, BigDecimal saldo) {
		super();
		this.numeroConta = numeroConta;
		this.saldo = saldo;
	}

	public Long getNumeroConta() {
		return numeroConta;
	}

	public void setNumeroConta(Long numeroConta) {
		this.numeroConta = numeroConta;
	}

	public BigDecimal getSaldo() {
		return saldo;
	}

	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}

	@Override
	public void fromEntity(BaseEntity entity) {
		Conta conta = (Conta) entity;
		this.numeroConta = conta.getCdConta();
		this.saldo = conta.getSaldo();
	}
}
