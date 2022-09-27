package com.europeandynamics.service.Impl;

import com.europeandynamics.exceptions.ResourceNotFoundException;
import com.europeandynamics.model.Property;
import com.europeandynamics.repository.PropertyRepository;
import com.europeandynamics.service.PropertyService;

import java.util.List;
import java.util.Optional;

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
	public Property findById(String id, Class<Property> classType) {

		Optional<Property> property = Optional.ofNullable(propertyRepository.findById(id, classType).orElseThrow(
				() -> new ResourceNotFoundException(classType.getSimpleName() + " with this id: " + id + " was not found")));

		return property.get();
	}

	@Override
	public Property create(Property entity) {
		
		return propertyRepository.create(entity);
	}

	@Override
	public void deleteById(String id, Class<Property> classType) {
		
		
	}

	@Override
	public void delete(Property entity) {
		
		
	}

}