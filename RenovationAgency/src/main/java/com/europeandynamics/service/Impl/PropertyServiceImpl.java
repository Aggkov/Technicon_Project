package com.europeandynamics.service.Impl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.europeandynamics.exceptions.BadRequestException;
import com.europeandynamics.exceptions.ResourceNotFoundException;
import com.europeandynamics.model.Property;
import com.europeandynamics.model.PropertyOwner;
import com.europeandynamics.payload.PropertyRequest;
import com.europeandynamics.payload.PropertyResponse;
import com.europeandynamics.repository.PropertyOwnerRepository;
import com.europeandynamics.repository.PropertyRepository;
import com.europeandynamics.service.PropertyService;

public class PropertyServiceImpl implements PropertyService {

    private final PropertyRepository propertyRepository;
    private final PropertyOwnerRepository propertyOwnerRepository;

    public PropertyServiceImpl(PropertyRepository propertyRepository, PropertyOwnerRepository propertyOwnerRepository) {
        this.propertyRepository = propertyRepository;
        this.propertyOwnerRepository = propertyOwnerRepository;
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
        if (propertiesByOwner.isEmpty()) {
            return Collections.emptyList();
        }
        return propertiesByOwner;
    }

    public Property create(PropertyRequest propertyRequest) {

    	 Optional<Property> optionalProperty = propertyRepository.findById(propertyRequest.getId(), Property.class);
         if (optionalProperty.isPresent()) {
             throw new BadRequestException("Property with id: " + propertyRequest.getId() + " already exists");
         }

         Optional<PropertyOwner> optionalPropertyOwner = Optional.ofNullable(propertyOwnerRepository.findById(propertyRequest.getPropertyOwnerId(), PropertyOwner.class)
                 .orElseThrow(() -> new ResourceNotFoundException(
                         PropertyOwner.class.getName() + " with this id: " + propertyRequest.getPropertyOwnerId() + " was not found")));

         Property property = new Property();
         property.setId(propertyRequest.getId());
         property.setAddress(propertyRequest.getAddress());
         property.setType(propertyRequest.getType());
         property.setYearOfConstruction(propertyRequest.getYearOfConstruction());
         property.setPropertyOwner(optionalPropertyOwner.get());


         Property createdProperty = propertyRepository.create(property);
         return createdProperty;

	}
	
	@Override
	public void update(String id , PropertyRequest propertyRequest) {
		
		Optional<Property> property = Optional.ofNullable(propertyRepository.findById(id, Property.class)
                .orElseThrow(() -> new ResourceNotFoundException
				(Property.class.getName() + " with this id" + id + "was not found")));

        Optional<PropertyOwner> propertyOwner = Optional.ofNullable(propertyOwnerRepository.findById(propertyRequest.getPropertyOwnerId(), PropertyOwner.class)
                .orElseThrow(() -> new ResourceNotFoundException
                (PropertyOwner.class.getName() +  "with this id" + id + "was not found")));
		
		if (property.isPresent()) {
			Property baseProperty = property.get();
			baseProperty.setAddress(propertyRequest.getAddress());
			baseProperty.setYearOfConstruction(propertyRequest.getYearOfConstruction());
			baseProperty.setType(propertyRequest.getType());
            baseProperty.setPropertyOwner(propertyOwner.get());
			
			propertyRepository.update(baseProperty);
		}
		
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
    public void delete(Property entity) {

    }


}
