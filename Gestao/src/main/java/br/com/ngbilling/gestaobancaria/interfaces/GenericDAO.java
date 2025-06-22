package br.com.ngbilling.gestaobancaria.interfaces;

import java.util.List;

import br.com.ngbilling.gestaobancaria.arquitetura.BaseEntity;

public interface GenericDAO<T extends BaseEntity> {
	T save(T entity);
    T update(T entity);
    void delete(T entity);
    T findById(Object id);
    List<T> findAll();
}
