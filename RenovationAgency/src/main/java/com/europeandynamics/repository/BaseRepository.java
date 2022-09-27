package com.europeandynamics.repository;

import java.util.List;
import java.util.Optional;

public interface BaseRepository<T> {

	List<?> findAll(Class<T> classType);

	Optional<T> findById(String id, Class<T> classType);

	T create(final T entity);

	void update(T entity);

//	void deleteById(String id, Class<T> classType);

	void delete(T entity);

	boolean exists(T entity);

}
