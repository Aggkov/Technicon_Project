package com.europeandynamics.service.Impl;

import com.europeandynamics.exceptions.BadRequestException;
import com.europeandynamics.exceptions.ResourceNotFoundException;
import com.europeandynamics.model.PropertyOwner;
import com.europeandynamics.payload.PropertyOwnerRequest;
import com.europeandynamics.repository.PropertyOwnerRepository;
import com.europeandynamics.service.PropertyOwnerService;

import java.util.List;
import java.util.Optional;

public class PropertyOwnerServiceImpl implements PropertyOwnerService {

    private final PropertyOwnerRepository propertyOwnerRepository;

    public PropertyOwnerServiceImpl(PropertyOwnerRepository propertyOwnerRepository) {
        this.propertyOwnerRepository = propertyOwnerRepository;
    }

    @Override
    public List<?> findAll(Class<PropertyOwner> classType) {

        return propertyOwnerRepository.findAll(classType);

    }

    @Override
    public Optional<PropertyOwner> findById(String id, Class<PropertyOwner> classType) {

        return Optional.ofNullable(propertyOwnerRepository.findById(id, classType)
                .orElseThrow(() -> new ResourceNotFoundException("Property Owner " + " with this id " + id + " was not found")));
    }

    @Override
    public PropertyOwner findByEmail(String email) {
        try {
            PropertyOwner propertyOwner = propertyOwnerRepository.findByEmail(email);
            if (propertyOwner != null) {
                return propertyOwner;
            }
        } catch (RuntimeException ex) {
            ex.printStackTrace();
        }
        throw new ResourceNotFoundException(("Property Owner " + " with this email " + email + " was not found"));
    }


    @Override
    public PropertyOwner create(PropertyOwner entity) {

        if (findById(entity.getId(), PropertyOwner.class).isPresent() || findByEmail(entity.getEmail()) != null) {
            System.out.println(entity);
            throw new BadRequestException("This Property Owner already exists ");
        }

        return propertyOwnerRepository.create(entity);
    }

    @Override
    public void update(String id, PropertyOwnerRequest propertyOwnerRequest) {

        Optional<PropertyOwner> propertyOwner = findById(id, PropertyOwner.class);

        if (!propertyOwner.isPresent()) {
            throw new ResourceNotFoundException("Property Owner  +  with this id  + id +  was not found");
        }

        PropertyOwner foundOwner = propertyOwner.get();
        foundOwner.setAddress(propertyOwnerRequest.getAddress());
        foundOwner.setEmail(propertyOwnerRequest.getEmail());
        foundOwner.setPassword(propertyOwnerRequest.getPassword());

        propertyOwnerRepository.update(foundOwner);

    }

    @Override
    public void deleteById(String id, Class<PropertyOwner> classType) {

    }

    @Override
    public void delete(PropertyOwner entity) {

    }
}


