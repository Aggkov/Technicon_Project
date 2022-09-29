package com.europeandynamics.service.Impl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.europeandynamics.exceptions.BadRequestException;
import com.europeandynamics.exceptions.ResourceNotFoundException;
import com.europeandynamics.model.Property;
import com.europeandynamics.payload.PropertyResponse;
import com.europeandynamics.repository.PropertyRepository;
import com.europeandynamics.service.PropertyService;

public class PropertyServiceImpl implements PropertyService {

	private final PropertyRepository propertyRepository;

	public PropertyServiceImpl(PropertyRepository propertyRepository) {
		this.propertyRepository = propertyRepository;
	}

	public List<PropertyResponse> findAll(Class<Property> classType) {

		 return propertyRepository.findAllProperties(classType);
	
	}
	
	@Override
	public Property findById(String id, Class<Property> classType) {

		Optional<Property> property = Optional
				.ofNullable(propertyRepository.findById(id, classType).orElseThrow(() -> new ResourceNotFoundException(
						classType.getSimpleName() + " with this id: " + id + " was not found")));

		return property.get();
	}

	@Override
	public List<PropertyResponse> findPropertiesByOwnerVatNumber(String id, Class<Property> classType) {
		
		List<PropertyResponse> propertiesByOwner = propertyRepository.findPropertiesByOwnerVatNumber(id, Property.class);
		if(propertiesByOwner.isEmpty()) {
			return Collections.emptyList();
		}
		return propertiesByOwner;
	}

	@Override
	public Property create(Property entity) {
		
		if(propertyRepository.findById(entity.getId(), Property.class).isEmpty()) {
			
			return propertyRepository.create(entity);
		}
		throw new BadRequestException("Property with id: " + entity.getId() + " already exists");
	}

	@Override
	public void deleteById(String id, Class<Property> classType) {

		Property property = findById(id, Property.class);

		if (property == null) {
			throw new ResourceNotFoundException("Property   with this id" + id + "was not found");
		}

		propertyRepository.delete(property);

	}
	
	@Override
	public void update(String id
//			PropertyRequest propertyRequest
			) {
		
		Property property = findById(id, Property.class);

		if (property == null) {
			throw new ResourceNotFoundException("Property   with this id" + id + "was not found");
		}
	}


	@Override
	public void delete(Property entity) {

	}

	
}
