package com.europeandynamics.service.Impl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.europeandynamics.exceptions.BadRequestException;
import com.europeandynamics.exceptions.ResourceNotFoundException;
import com.europeandynamics.model.Property;
import com.europeandynamics.payload.PropertyRequest;
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
			
			Property property = propertyRepository.create(entity);
			return property;
		}
		throw new BadRequestException("Property with id: " + entity.getId() + " already exists");
	}

	@Override
	public boolean deleteById(String id, Class<Property> classType) {

		Optional<Property> property = propertyRepository.findById(id, Property.class);

		if (property.isEmpty()) {
			throw new ResourceNotFoundException("Property with this id  + id +  was not found");
		}
		property.get().setPropertyOwner(null);
		return propertyRepository.delete(property.get());

	}
	
	@Override
	public void update(String id , PropertyRequest propertyRequest) {
		
		Optional<Property> property = Optional.ofNullable(propertyRepository.findById(id, Property.class).orElseThrow(() -> new ResourceNotFoundException
				("Property   with this id" + id + "was not found")));
		
		
		if (property.isPresent()) {
			Property baseProperty = property.get();
			baseProperty.setAddress(propertyRequest.getAddress());
			baseProperty.setYearOfConstruction(propertyRequest.getYearOfConstruction());
			baseProperty.setType(propertyRequest.getType());
			
			propertyRepository.update(baseProperty);
		}
		
	}


	@Override
	public void delete(Property entity) {

	}

	
}
