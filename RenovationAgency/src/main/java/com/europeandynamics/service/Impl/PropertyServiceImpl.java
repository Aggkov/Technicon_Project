/**
 * Implements all the methods that a customer can request
 *  about their Properties
 *  
 */

package com.europeandynamics.service.Impl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.europeandynamics.exceptions.BadRequestException;
import com.europeandynamics.exceptions.ResourceNotFoundException;
import com.europeandynamics.model.Property;
import com.europeandynamics.model.PropertyOwner;
import com.europeandynamics.model.enums.HttpStatus;
import com.europeandynamics.payload.request.PropertyRequest;
import com.europeandynamics.payload.response.PropertyResponse;
import com.europeandynamics.repository.PropertyOwnerRepository;
import com.europeandynamics.repository.PropertyRepository;
import com.europeandynamics.service.PropertyService;
import com.europeandynamics.utils.validator.PropertyValidator;

public class PropertyServiceImpl implements PropertyService {

    private final PropertyRepository propertyRepository;
    private final PropertyOwnerRepository propertyOwnerRepository;

    /**
     * @param propertyRepository The place where we save all the Properties
     * @param propertyOwnerRepository The place where we save all the Property Owners of the properties
     */
    public PropertyServiceImpl(PropertyRepository propertyRepository, PropertyOwnerRepository propertyOwnerRepository) {
        this.propertyRepository = propertyRepository;
        this.propertyOwnerRepository = propertyOwnerRepository;
    }

    /**
     * @param classType The list with all properties
     * @return Returns all the properties
     */
    public List<PropertyResponse> findAll(Class<Property> classType) {

        return propertyRepository.findAllProperties(classType);

    }

    /**
     * @param classType the list of all properties
     * @param id id of the property
     * @return Returns the properties found by a specific id
     */
    
    @Override
    public Property findById(String id, Class<Property> classType) {

        Optional<Property> property = Optional
                .ofNullable(propertyRepository.findById(id, classType).orElseThrow(() -> new ResourceNotFoundException(
                        classType.getSimpleName() + " with this id: " + id + " was not found", HttpStatus.NOT_FOUND)));

        return property.get();
    }

    
    /**
     * @param classType
     * @param id
     * @return Returns the list of the properties found by a vat number
     */
    
    @Override
    public List<PropertyResponse> findPropertiesByOwnerVatNumber(String id, Class<Property> classType) {

        List<PropertyResponse> propertiesByOwner = propertyRepository.findPropertiesByOwnerVatNumber(id, Property.class);
        if (propertiesByOwner.isEmpty()) {
        	throw new ResourceNotFoundException("Property Owner with this VatNumber: " + id + " was not found",
				HttpStatus.NOT_FOUND);
	}
        
        return propertiesByOwner;
    }

    /**
     * @param propertyRequest
     * @return Creates a new Property
     */
    
    public Property create(PropertyRequest propertyRequest) {
    	
    	PropertyValidator.checkForNulls(propertyRequest);
    	

    	 Optional<Property> optionalProperty = propertyRepository.findById(propertyRequest.getId(), Property.class);
         if (optionalProperty.isPresent()) {
             throw new BadRequestException("Property with id: " + propertyRequest.getId() + 
            		 " already exists ", HttpStatus.BAD_REQUEST);
         }

         Optional<PropertyOwner> optionalPropertyOwner = Optional.ofNullable(propertyOwnerRepository
        		 .findById(propertyRequest.getPropertyOwnerId(), PropertyOwner.class)
                 .orElseThrow(() -> new ResourceNotFoundException(
                         PropertyOwner.class.getName() + " with this id: " + propertyRequest.getPropertyOwnerId() +
                                 " was not found", HttpStatus.NOT_FOUND)));

         Property property = new Property();
         property.setId(propertyRequest.getId());
         property.setAddress(propertyRequest.getAddress());
         property.setType(propertyRequest.getType());
         property.setYearOfConstruction(propertyRequest.getYearOfConstruction());
         property.setPropertyOwner(optionalPropertyOwner.get());


         Property createdProperty = propertyRepository.create(property);
         return createdProperty;

	}
	
    /**
     * @param id
     * @param propertyRequest
     * Updates an already existing property
     */
    
	@Override
	public void update(String id , PropertyRequest propertyRequest) {
		
		Optional<Property> property = Optional.ofNullable(propertyRepository.findById(id, Property.class)
                .orElseThrow(() -> new ResourceNotFoundException
				(Property.class.getName() + " with this id: " + id + "was not found ", HttpStatus.NOT_FOUND)));

        Optional<PropertyOwner> propertyOwner = Optional.ofNullable(propertyOwnerRepository.findById(propertyRequest.getPropertyOwnerId(), PropertyOwner.class)
                .orElseThrow(() -> new ResourceNotFoundException
                (PropertyOwner.class.getName() +  "with this id: " + id + " was not found ", HttpStatus.NOT_FOUND)));
		
		if (property.isPresent()) {
			Property baseProperty = property.get();
			baseProperty.setAddress(propertyRequest.getAddress());
			baseProperty.setYearOfConstruction(propertyRequest.getYearOfConstruction());
			baseProperty.setType(propertyRequest.getType());
            baseProperty.setPropertyOwner(propertyOwner.get());
			
			propertyRepository.update(baseProperty);
		}
		
	}
	
/**
 * @param classType
 * @param id
 * @return Deletes an already existing property	
 */
    @Override
    public boolean deleteById(String id, Class<Property> classType) {

        Optional<Property> property = propertyRepository.findById(id, Property.class);

        if (property.isEmpty()) {
            throw new ResourceNotFoundException("Property with this id "  + id +   " was not found ", HttpStatus.NOT_FOUND);
        }
        Property foundProperty = property.get();
        foundProperty.setPropertyOwner(null);
        return propertyRepository.delete(foundProperty);

    }

}
