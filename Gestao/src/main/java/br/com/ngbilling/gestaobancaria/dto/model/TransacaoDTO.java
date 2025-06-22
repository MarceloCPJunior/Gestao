package br.com.ngbilling.gestaobancaria.dto.model;

import java.math.BigDecimal;

import br.com.ngbilling.gestaobancaria.arquitetura.BaseEntity;
import br.com.ngbilling.gestaobancaria.interfaces.EntityDTO;
import br.com.ngbilling.gestaobancaria.model.Transacao;
//import io.swagger.v3.oas.annotations.media.Schema;

//@Schema(description = "DTO para representar uma transação bancária")
public class TransacaoDTO implements EntityDTO {
	
//	@Schema(description = "Forma de pagamento (D=Débito, C=Crédito, P=PIX)", example = "D")
	private Character formaPagamento;
	
//	@Schema(description = "Número da conta bancária", example = "123456789")
	private Long numeroConta;
	
//	@Schema(description = "Valor da transação", example = "100.50")
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
