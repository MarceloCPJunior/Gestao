package br.com.ngbilling.gestaobancaria.arquitetura;

import java.util.List;

import br.com.ngbilling.gestaobancaria.interfaces.GenericDAO;
import jakarta.persistence.EntityManager;

public abstract class GenericDAOI<T extends BaseEntity> implements GenericDAO<T> {

	private final Class<T> entityClass;
	protected EntityManager em;

	public GenericDAOI(EntityManager em, Class<T> entityClass) {
		this.em = em;
		this.entityClass = entityClass;
	}

	@Override
	public T save(T entity) {
		em.getTransaction().begin();
		em.persist(entity);
		em.getTransaction().commit();
		return entity;
	}

	@Override
	public T update(T entity) {
		em.getTransaction().begin();
		T merged = em.merge(entity);
		em.getTransaction().commit();
		return merged;
	}

	@Override
	public void delete(T entity) {
		em.getTransaction().begin();
		em.remove(em.contains(entity) ? entity : em.merge(entity));
		em.getTransaction().commit();
	}

	@Override
	public T findById(Object id) {
		return em.find(entityClass, id);
	}

	@Override
	public List<T> findAll() {
		return em.createQuery("FROM " + entityClass.getSimpleName(), entityClass).getResultList();
	}

	public BaseEntity verificarDuplicidade(BaseEntity entidade) throws Exception {
		BaseEntity existente = findById(entidade.obteinId());
		if (existente != null) {
			throw new Exception("Já existe uma " + entidade.getClass().getSimpleName() + " com esse número");
		}
		return existente;
	}
}
