package com.europeandynamics.service.Impl;

import java.util.List;
import java.util.Optional;

import com.europeandynamics.exceptions.BadRequestException;
import com.europeandynamics.exceptions.ResourceNotFoundException;
import com.europeandynamics.model.PropertyOwner;
import com.europeandynamics.payload.PropertyOwnerRequest;
import com.europeandynamics.repository.PropertyOwnerRepository;
import com.europeandynamics.service.PropertyOwnerService;
import com.europeandynamics.validator.InputValidator;

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
	public PropertyOwner findById(String id, Class<PropertyOwner> classType) {

		Optional<PropertyOwner> propertyOwner = Optional.ofNullable(
				propertyOwnerRepository.findById(id, classType).orElseThrow(() -> new ResourceNotFoundException(
						classType.getSimpleName() + " with this id " + id + " was not found")));

		return propertyOwner.get();
	}

	@Override
	public PropertyOwner findByEmail(String email) {

		InputValidator.checkEmail(email);

		Optional<PropertyOwner> propertyOwner = Optional
				.ofNullable(propertyOwnerRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException(
						"Property Owner " + " with this email " + email + " was not found")));

		return propertyOwner.get();

	}

	@Override
	public PropertyOwner create(PropertyOwner entity) {

		InputValidator.checkEmail(entity.getEmail());

		if (!propertyOwnerRepository.findById(entity.getId(), PropertyOwner.class).isPresent()
				|| propertyOwnerRepository.findByEmail(entity.getEmail()).isPresent()) {
			return propertyOwnerRepository.create(entity);
		}
		throw new BadRequestException("This Property Owner already exists ");

	}

	@Override
	public void update(String id, PropertyOwnerRequest propertyOwnerRequest) {

		Optional<PropertyOwner> propertyOwner = propertyOwnerRepository.findById(id, PropertyOwner.class);

		if (!propertyOwner.isEmpty()) {
			PropertyOwner foundOwner = propertyOwner.get();
			foundOwner.setAddress(propertyOwnerRequest.getAddress());
			foundOwner.setEmail(propertyOwnerRequest.getEmail());
			foundOwner.setPassword(propertyOwnerRequest.getPassword());

			propertyOwnerRepository.update(foundOwner);
		}

		throw new ResourceNotFoundException("Property Owner  +  with this id  " + id + " was not found");

	}

	@Override
	public void deleteById(String id, Class<PropertyOwner> classType) {
		Optional<PropertyOwner> propertyOwner = propertyOwnerRepository.findById(id, PropertyOwner.class);

		if (propertyOwner.isEmpty()) {
			throw new ResourceNotFoundException("Property Owner  +  with this id  + id +  was not found");
		}

		propertyOwnerRepository.delete(propertyOwner.get());
	}

	@Override
	public void delete(PropertyOwner entity) {

	}
}
