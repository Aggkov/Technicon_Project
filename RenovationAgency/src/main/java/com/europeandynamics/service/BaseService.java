package com.europeandynamics.service;

import java.util.List;

public interface BaseService<T> {

	T findById(String id, Class<T> classType);

	boolean deleteById(String id, Class<T> classType);

}
