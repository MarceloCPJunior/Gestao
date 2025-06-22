package br.com.ngbilling.gestaobancaria.dao;

import br.com.ngbilling.gestaobancaria.arquitetura.GenericDAOI;
import br.com.ngbilling.gestaobancaria.dto.model.ContaDTO;
import br.com.ngbilling.gestaobancaria.model.Conta;
import jakarta.persistence.EntityManager;

public class ContaDAOI extends GenericDAOI<Conta> {

	public ContaDAOI(EntityManager em) {
		super(em, Conta.class);
	}

	public ContaDTO buscarContaToDTO(Long nrConta) {
		try {
			ContaDTO contaDTO = em
					.createQuery("SELECT new br.com.ngbilling.gestaobancaria.dto.model.ContaDTO(c.cdConta, c.saldo) "
							+ "FROM Conta c WHERE c.cdConta = :nrConta", ContaDTO.class)
					.setParameter("nrConta", nrConta).getSingleResult();
			return contaDTO;
		} catch (Exception e) {
		}
		return null;
	}
}
