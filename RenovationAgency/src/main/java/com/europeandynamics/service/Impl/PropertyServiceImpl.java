package com.europeandynamics.service.Impl;

import java.util.List;
import java.util.Optional;

import com.europeandynamics.model.Property;
import com.europeandynamics.repository.PropertyRepository;
import com.europeandynamics.service.PropertyService;

public class PropertyServiceImpl implements PropertyService {

	private final PropertyRepository propertyRepository;
	
	public PropertyServiceImpl(PropertyRepository propertyRepository) {
		this.propertyRepository = propertyRepository;
	}

	@Override
	public List<?> findAll(Class<Property> classType) {
		
		return propertyRepository.findAll(classType);
	}

	@Override
	public Optional<Property> findById(String id, Class<Property> classType) {
		
		return propertyRepository.findById(id, classType);
	}

	@Override
	public Property create(Property entity) {
		
		return propertyRepository.create(entity);
	}

	@Override
	public void update(Property entity) {
		
		
	}

	@Override
	public void deleteById(String id, Class<Property> classType) {
		
		
	}

	@Override
	public void delete(Property entity) {
		
		
	}

}
