package com.europeandynamics.service.Impl;

import java.util.List;
import java.util.Optional;

import javax.persistence.NoResultException;

import com.europeandynamics.exceptions.BadRequestException;
import com.europeandynamics.exceptions.ResourceNotFoundException;
import com.europeandynamics.model.PropertyOwner;
import com.europeandynamics.model.enums.HttpStatus;
import com.europeandynamics.payload.request.PropertyOwnerRequest;
import com.europeandynamics.payload.response.PropertyOwnerRepairsPaidResponse;
import com.europeandynamics.payload.response.PropertyOwnerResponse;
import com.europeandynamics.repository.PropertyOwnerRepository;
import com.europeandynamics.service.PropertyOwnerService;
import com.europeandynamics.payload.request.validator.InputValidator;

public class PropertyOwnerServiceImpl implements PropertyOwnerService {

	private final PropertyOwnerRepository propertyOwnerRepository;

	public PropertyOwnerServiceImpl(PropertyOwnerRepository propertyOwnerRepository) {
		this.propertyOwnerRepository = propertyOwnerRepository;
	}

	@Override
	public List<PropertyOwnerResponse> findAll(Class<PropertyOwner> classType) {

		return propertyOwnerRepository.findAllOwners(classType);

	}

	@Override
	public PropertyOwner findById(String id, Class<PropertyOwner> classType) {

		Optional<PropertyOwner> propertyOwner = Optional.ofNullable(
				propertyOwnerRepository.findById(id, classType).orElseThrow(() -> new ResourceNotFoundException(
						classType.getSimpleName() + " with this id " + id + " was not found", HttpStatus.NOT_FOUND)));

		return propertyOwner.get();
	}

	@Override
	public PropertyOwnerResponse findByEmail(String email) {

		InputValidator.checkEmail(email);

		try {
			Optional<PropertyOwnerResponse> propertyOwner = propertyOwnerRepository.findByEmail(email);
			return propertyOwner.get();

		} catch (NoResultException ex) {
			throw new ResourceNotFoundException("Property Owner " + " with this email " + email + " was not found ", HttpStatus.NOT_FOUND);
		}

	}

	public List<PropertyOwnerRepairsPaidResponse> amountPaidForRepairsByOwner() {

		return propertyOwnerRepository.amountPaidForRepairsByOwner();
	}

	public PropertyOwner create(PropertyOwner entity) {

		InputValidator.checkEmail(entity.getEmail());

		try {
			if(propertyOwnerRepository.findByEmail(entity.getEmail()).isPresent() ||
			propertyOwnerRepository.findById(entity.getId(), PropertyOwner.class).isPresent()) {

				throw new BadRequestException(entity.getClass().getSimpleName() + "with this identification already exists ", HttpStatus.BAD_REQUEST);
			}

		} catch (NoResultException | ResourceNotFoundException ex) {
			System.out.println(ex.getMessage());
		}
		return propertyOwnerRepository.create(entity);

	}

	

	@Override
	public void update(String id, PropertyOwnerRequest propertyOwnerRequest) {

		Optional<PropertyOwner> propertyOwner = Optional.ofNullable(propertyOwnerRepository
				.findById(id, PropertyOwner.class).orElseThrow(() -> new ResourceNotFoundException(
						"Owner scheduled for update with this id " + id + " was not found ", HttpStatus.NOT_FOUND)));

		if (propertyOwner.isPresent()) {
			PropertyOwner foundOwner = propertyOwner.get();
			foundOwner.setAddress(propertyOwnerRequest.getAddress());
			foundOwner.setEmail(propertyOwnerRequest.getEmail());
			foundOwner.setPassword(propertyOwnerRequest.getPassword());

			propertyOwnerRepository.update(foundOwner);
		}

	}

	@Override
	public boolean deleteById(String id, Class<PropertyOwner> classType) {
		Optional<PropertyOwner> propertyOwner = propertyOwnerRepository.findById(id, PropertyOwner.class);

		if (propertyOwner.isEmpty()) {
			throw new ResourceNotFoundException("Property Owner  +  with this id  + id +  was not found ", HttpStatus.NOT_FOUND);
		}

		return propertyOwnerRepository.delete(propertyOwner.get());
	}

}
