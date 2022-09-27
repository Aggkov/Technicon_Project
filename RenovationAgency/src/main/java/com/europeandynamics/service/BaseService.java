package com.europeandynamics.service;

import java.util.List;

public interface BaseService<T> {

	List<?> findAll(Class<T> classType);

	T findById(String id, Class<T> classType);

	T create(final T entity);



	void deleteById(String id, Class<T> classType);

	void delete(T entity);

}
