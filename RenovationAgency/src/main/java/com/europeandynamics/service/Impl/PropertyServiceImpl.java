package com.europeandynamics.service.Impl;

import java.util.List;
import java.util.Optional;

import com.europeandynamics.exceptions.ResourceNotFoundException;
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
	public Property findById(String id, Class<Property> classType) {

		Optional<Property> property = Optional
				.ofNullable(propertyRepository.findById(id, classType).orElseThrow(() -> new ResourceNotFoundException(
						classType.getSimpleName() + " with this id: " + id + " was not found")));

		return property.get();
	}

	@Override
	public List<Property> findPropertiesByOwnerVatNumber(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Property create(Property entity) {

		return propertyRepository.create(entity);
	}

	@Override
	public void deleteById(String id, Class<Property> classType) {

		Optional<Property> property = propertyRepository.findById(id, Property.class);

		if (property.isEmpty()) {
			throw new ResourceNotFoundException("Property   with this id" + id + "was not found");
		}

		propertyRepository.delete(property.get());

	}

	@Override
	public void delete(Property entity) {

	}

	@Override
	public void update(String id) {
		// TODO Auto-generated method stub

	}

}
