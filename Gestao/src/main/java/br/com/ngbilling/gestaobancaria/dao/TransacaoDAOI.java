package br.com.ngbilling.gestaobancaria.dao;

import br.com.ngbilling.gestaobancaria.arquitetura.GenericDAOI;
import br.com.ngbilling.gestaobancaria.model.FormaPagamento;
import br.com.ngbilling.gestaobancaria.model.Transacao;
import jakarta.persistence.EntityManager;

public class TransacaoDAOI extends GenericDAOI<Transacao> {

	public TransacaoDAOI(EntityManager em) {
		super(em, Transacao.class);
	}

	public FormaPagamento buscarFormaPagamento(Character ch) {
		try {
			FormaPagamento formaPagamento = em.createQuery(
					"SELECT form FROM FormaPagamento as form WHERE form.chFormaPagamento = :chFormaPagamento",
					FormaPagamento.class).setParameter("chFormaPagamento", ch).getSingleResult();
			return formaPagamento;
		} catch (Exception e) {
			return null;
		}
	}

}
