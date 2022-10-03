package com.europeandynamics.service.Impl;

import com.europeandynamics.exceptions.ResourceNotFoundException;
import com.europeandynamics.model.PropertyRepair;
import com.europeandynamics.payload.response.PropertyRepairResponse;
import com.europeandynamics.repository.PropertyRepairRepository;
import com.europeandynamics.service.PropertyRepairService;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class PropertyRepairServiceImpl implements PropertyRepairService {

	private final PropertyRepairRepository propertyRepairRepository;
	
	public PropertyRepairServiceImpl(PropertyRepairRepository propertyRepairRepository) {
		this.propertyRepairRepository = propertyRepairRepository;
	}


	@Override
	public PropertyRepair findById(String id, Class<PropertyRepair> classType) {
		Optional<PropertyRepair> propertyRepair = Optional.ofNullable(propertyRepairRepository.findById(id, classType).orElseThrow(
				() -> new ResourceNotFoundException(classType.getSimpleName() + " with this id: " + id + " was not found")));

		return propertyRepair.get();
	}

	public List<PropertyRepair> propertyRepairsByOwnerVatNumber(String id) {

		if(propertyRepairRepository.propertyRepairsByOwnerVatNumber(id).isEmpty()) {
			throw new ResourceNotFoundException("Couldn't find repairs for certain user");
		}
		return propertyRepairRepository.propertyRepairsByOwnerVatNumber(id);
	}

	@Override
	public List<PropertyRepairResponse> findAllRepairsByDate(LocalDateTime dateFrom, LocalDateTime dateTo, Class<PropertyRepair> classType) {
		return propertyRepairRepository.findAllRepairsByDate(dateFrom,dateTo,classType);
	}


	public PropertyRepair create(PropertyRepair entity) {
		
		return propertyRepairRepository.create(entity);
	}

	@Override
	public boolean deleteById(String id, Class<PropertyRepair> classType) {
		Optional<PropertyRepair> propertyRepair = propertyRepairRepository.findById(id, PropertyRepair.class);

		if (propertyRepair.isEmpty()) {
			throw new ResourceNotFoundException("Property Repair  +  with this id  + id +  was not found");
		}
		propertyRepair.get().setPropertyOwner(null);
		propertyRepair.get().setProperty(null);
		return propertyRepairRepository.delete(propertyRepair.get());
		
	}

	@Override
	public void delete(PropertyRepair entity) {
		
		
	}

}
