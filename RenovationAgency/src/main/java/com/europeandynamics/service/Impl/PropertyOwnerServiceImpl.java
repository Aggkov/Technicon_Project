/**
 * This is the implementation of The Property Owner Service. 
 * All the requirements of the Customer.
 */

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
import com.europeandynamics.utils.PassBasedEncryption;
import com.europeandynamics.utils.validator.InputValidator;
import com.europeandynamics.utils.validator.PropertyOwnerValidator;



public class PropertyOwnerServiceImpl implements PropertyOwnerService {

	private final PropertyOwnerRepository propertyOwnerRepository;

	public PropertyOwnerServiceImpl(PropertyOwnerRepository propertyOwnerRepository) {
		this.propertyOwnerRepository = propertyOwnerRepository;
	}

	
	/**
	 * @param classType
	 * @return A list of all property owners 
	 */
	@Override
	public List<PropertyOwnerResponse> findAll(Class<PropertyOwner> classType) {

		return propertyOwnerRepository.findAllOwners(classType);

	}

	/**
	 * @param classType 
	 * @param id
	 * @return Returns a specific owner finding them by Id
	 */
	@Override
	public PropertyOwner findById(String id, Class<PropertyOwner> classType) {

		Optional<PropertyOwner> propertyOwner = Optional
				.ofNullable(propertyOwnerRepository.findById(id, classType)
						.orElseThrow(() -> new ResourceNotFoundException(
								classType.getSimpleName() + " with this id " + id + " was not found",
								HttpStatus.NOT_FOUND)));

		return propertyOwner.get();
	}

	
	
	@Override
	public PropertyOwnerResponse findByEmail(String email) {

		InputValidator.checkEmail(email);

		try {
			Optional<PropertyOwnerResponse> propertyOwner = propertyOwnerRepository.findByEmail(email);
			return propertyOwner.get();

		} catch (NoResultException ex) {
			throw new ResourceNotFoundException("Property Owner " + " with this email " + email + " was not found ",
					HttpStatus.NOT_FOUND);
		}

	}

	/**
	 * @return The total amount paid for Repair by the Owners
	 */
	public List<PropertyOwnerRepairsPaidResponse> amountPaidForRepairsByOwner() {

		return propertyOwnerRepository.amountPaidForRepairsByOwner();
	}

	/**
	 * @param entity
	 * @return creates a new Property Owner
	 */
	
	public PropertyOwner create(PropertyOwner entity) {

		PropertyOwnerValidator.validatePropertyOwner(entity);

		try {
			if (propertyOwnerRepository.findById(entity.getId(), PropertyOwner.class).isPresent()) {
				throw new BadRequestException(
						entity.getClass().getSimpleName() + " with this identification already exists ",
						HttpStatus.BAD_REQUEST);

			}
			if (propertyOwnerRepository.findByEmail(entity.getEmail()).isPresent()) {
				throw new BadRequestException(
						entity.getClass().getSimpleName() + " with this identification already exists ",
						HttpStatus.BAD_REQUEST);

			}

		} catch (NoResultException | ResourceNotFoundException ex) {
		}
		entity.setPassword(PassBasedEncryption.passEncryptionGenerator(entity.getPassword()));
		return propertyOwnerRepository.create(entity);

	}

	/**
	 * @param id
	 * @param propertyOwnerRequest
	 * updates an already existing Property Owner
	 */
	
	@Override
	public void update(String id, PropertyOwnerRequest propertyOwnerRequest) {

		Optional<PropertyOwner> propertyOwner = Optional
				.ofNullable(propertyOwnerRepository.findById(id, PropertyOwner.class)
						.orElseThrow(() -> new ResourceNotFoundException(
								"Owner scheduled for update with this id " + id + " was not found ",
								HttpStatus.NOT_FOUND)));

		if (propertyOwner.isPresent()) {
			PropertyOwner foundOwner = propertyOwner.get();
			foundOwner.setAddress(propertyOwnerRequest.getAddress());
			foundOwner.setEmail(propertyOwnerRequest.getEmail());
			foundOwner.setPassword(propertyOwnerRequest.getPassword());

			propertyOwnerRepository.update(foundOwner);
		}

	}

	/**
	 * @param id
	 * @param classType
	 * @return Deletes a property Owner by a specific ID
	 */
	
	@Override
	public boolean deleteById(String id, Class<PropertyOwner> classType) {
		Optional<PropertyOwner> propertyOwner = propertyOwnerRepository.findById(id, PropertyOwner.class);

		if (propertyOwner.isEmpty()) {
			throw new ResourceNotFoundException("Property Owner with this id: " + id + " was not found",
					HttpStatus.NOT_FOUND);
		}

		return propertyOwnerRepository.delete(propertyOwner.get());
	}

}
