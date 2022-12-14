/**
 * A class that implements all the methods that are necessary 
 * for the Property Repair methods that the customer requests 
 */
package com.europeandynamics.service.Impl;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.europeandynamics.exceptions.BadRequestException;
import com.europeandynamics.exceptions.ResourceNotFoundException;
import com.europeandynamics.model.Property;
import com.europeandynamics.model.PropertyOwner;
import com.europeandynamics.model.PropertyRepair;
import com.europeandynamics.model.enums.HttpStatus;
import com.europeandynamics.payload.request.PropertyRepairRequest;
import com.europeandynamics.payload.response.HighestProfitByRepairTypeResponse;
import com.europeandynamics.payload.response.PropertyRepairResponse;
import com.europeandynamics.repository.PropertyOwnerRepository;
import com.europeandynamics.repository.PropertyRepairRepository;
import com.europeandynamics.repository.PropertyRepository;
import com.europeandynamics.service.PropertyRepairService;
import com.europeandynamics.utils.validator.PropertyRepairValidator;

public class PropertyRepairServiceImpl implements PropertyRepairService {

	private final PropertyRepairRepository propertyRepairRepository;
	private final PropertyRepository propertyRepository;
	private final PropertyOwnerRepository propertyOwnerRepository;
	
	
	public PropertyRepairServiceImpl(PropertyRepairRepository propertyRepairRepository, PropertyRepository propertyRepository,PropertyOwnerRepository propertyOwnerRepository) {
		this.propertyRepairRepository = propertyRepairRepository;
		this.propertyRepository = propertyRepository;
		this.propertyOwnerRepository = propertyOwnerRepository;
	}
	
	@Override
	public List<PropertyRepair> findAllPropertyRepairs(Class<PropertyRepair> classType) {
		
		return propertyRepairRepository.findAll(classType);
	}

	/**
	 * @param id The property Repair id
	 * @param classtype
	 * @return A specific Property Repair found by a specific Id
	 * 
	 */
	
	@Override
	public PropertyRepair findById(String id, Class<PropertyRepair> classType) {
		Optional<PropertyRepair> propertyRepair = Optional.ofNullable(propertyRepairRepository.findById(id, classType).orElseThrow(
				() -> new ResourceNotFoundException(classType.getSimpleName() + " with this id: " + id + " was not found ", HttpStatus.NOT_FOUND)));

		return propertyRepair.get();
	}

	/**
	 * @param id 
	 * @return A List of property Repairs based by an owner's Vat number
	 */
	
	public List<PropertyRepair> propertyRepairsByOwnerVatNumber(String id) {

		if(propertyRepairRepository.propertyRepairsByOwnerVatNumber(id).isEmpty()) {
			throw new ResourceNotFoundException("Couldn't find repairs for certain user ", HttpStatus.NOT_FOUND);
		}
		return propertyRepairRepository.propertyRepairsByOwnerVatNumber(id);
	}

	/**
	 * @param Class PropertyRepair
	 * @param dateFrom The date it started
	 * @param dateTo the date it ended
	 * @return Returns all the Repairs based on a given timeframe
	 */
	
	@Override
	public List<PropertyRepairResponse> findAllRepairsByDate(LocalDateTime dateFrom, LocalDateTime dateTo, Class<PropertyRepair> classType) {
		
		return propertyRepairRepository.findAllRepairsByDate(dateFrom,dateTo,classType);
	}

	/**
	 * @param propertyRepairRequest Payload
	 * @return Creates a new PropertyRepair
	 */
	@Override
	public PropertyRepair create(PropertyRepairRequest propertyRepairRequest) {

		PropertyRepairValidator.validatePropertyRepair(propertyRepairRequest);

		Optional<PropertyRepair> optionalPropertyRepair = propertyRepairRepository.findById(propertyRepairRequest.getPropertyRepairId(), PropertyRepair.class);
		if(optionalPropertyRepair.isPresent()) {
			throw new BadRequestException("PropertyRepair with id: " + propertyRepairRequest.getPropertyRepairId() + " already exists ", HttpStatus.BAD_REQUEST);
		}

		Optional<Property> optionalProperty = Optional.ofNullable(propertyRepository.findById(propertyRepairRequest.getPropertyId(), Property.class)
				.orElseThrow(() -> new ResourceNotFoundException(
						Property.class.getName() + " with this id: " + propertyRepairRequest.getPropertyId() +
								" was not found", HttpStatus.NOT_FOUND)));


		Optional<PropertyOwner> optionalPropertyOwner = Optional.ofNullable(propertyOwnerRepository.findById(propertyRepairRequest.getPropertyOwnerId(), PropertyOwner.class)
				.orElseThrow(() -> new ResourceNotFoundException(
						PropertyOwner.class.getName() + " with this id: " + propertyRepairRequest.getPropertyOwnerId() +
								" was not found", HttpStatus.NOT_FOUND)));

		PropertyRepair newPropertyRepair = new PropertyRepair();
		newPropertyRepair.setId(propertyRepairRequest.getPropertyRepairId());
		newPropertyRepair.setDateTimeOfRepair(propertyRepairRequest.getDateTimeOfRepair());
		newPropertyRepair.setShortDescription(propertyRepairRequest.getShortDescription());
		newPropertyRepair.setRepairType(propertyRepairRequest.getRepairType());
		newPropertyRepair.setRepairStatus(propertyRepairRequest.getRepairStatus());
		newPropertyRepair.setCostOfRepair(propertyRepairRequest.getCostOfRepair());
		newPropertyRepair.setLongDescription(propertyRepairRequest.getLongDescription());
		newPropertyRepair.setProperty(optionalProperty.get());
		newPropertyRepair.setPropertyOwner(optionalPropertyOwner.get());

		return propertyRepairRepository.create(newPropertyRepair);
	}

	/**
	 * @param propertyRepairRequest Payload
	 * @param repairId The id of the existing Repair
	 * @return Updates an already existing PropertyRepair
	 */
	@Override
	public void update(String repairId, PropertyRepairRequest propertyRepairRequest) {

		Optional<PropertyRepair> optionalPropertyRepair = Optional.ofNullable(propertyRepairRepository.findById(repairId, PropertyRepair.class)
				.orElseThrow(() -> new ResourceNotFoundException(PropertyRepair.class.getName() + " with this id: " + repairId +
						" was not found", HttpStatus.NOT_FOUND)));

		Optional<Property> optionalProperty = Optional.ofNullable(propertyRepository.findById(propertyRepairRequest.getPropertyId(), Property.class)
				.orElseThrow(() -> new ResourceNotFoundException(
						Property.class.getName() + " with this id: " + propertyRepairRequest.getPropertyId() +
								" was not found", HttpStatus.NOT_FOUND)));


		Optional<PropertyOwner> optionalPropertyOwner = Optional.ofNullable(propertyOwnerRepository.findById(propertyRepairRequest.getPropertyOwnerId(), PropertyOwner.class)
				.orElseThrow(() -> new ResourceNotFoundException(
						PropertyOwner.class.getName() + " with this id: " + propertyRepairRequest.getPropertyOwnerId() +
								" was not found", HttpStatus.NOT_FOUND)));


		PropertyRepair foundPropertyRepair = optionalPropertyRepair.get();
		foundPropertyRepair.setDateTimeOfRepair(propertyRepairRequest.getDateTimeOfRepair());
		foundPropertyRepair.setShortDescription(propertyRepairRequest.getShortDescription());
		foundPropertyRepair.setLongDescription(propertyRepairRequest.getLongDescription());
		foundPropertyRepair.setRepairStatus(propertyRepairRequest.getRepairStatus());
		foundPropertyRepair.setRepairType(propertyRepairRequest.getRepairType());
		foundPropertyRepair.setCostOfRepair(propertyRepairRequest.getCostOfRepair());
		foundPropertyRepair.setPropertyOwner(optionalPropertyOwner.get());
		foundPropertyRepair.setProperty(optionalProperty.get());

		propertyRepairRepository.update(foundPropertyRepair);


	}

	/**
	 * @param Class Property Repair
	 * @param Id of the Property Repair
	 * @return Deletes a Property Repair by a specific Id
	 */
	
	@Override
	public boolean deleteById(String id, Class<PropertyRepair> classType) {
		Optional<PropertyRepair> propertyRepair = propertyRepairRepository.findById(id, PropertyRepair.class);

		if (propertyRepair.isEmpty()) {
			throw new ResourceNotFoundException("Property Repair  with this id "   + id +   " was not found ", HttpStatus.NOT_FOUND);
		}
		propertyRepair.get().setPropertyOwner(null);
		propertyRepair.get().setProperty(null);
		return propertyRepairRepository.delete(propertyRepair.get());
		
	}


	
	@Override
	public List<HighestProfitByRepairTypeResponse> highestProfitRepairsByRepairType() {
		
		return propertyRepairRepository.highestProfitRepairsByRepairType();
				

				
	}


}
