package com.europeandynamics.service.Impl;

import com.europeandynamics.exceptions.ResourceNotFoundException;
import com.europeandynamics.model.Property;
import com.europeandynamics.model.PropertyOwner;
import com.europeandynamics.model.PropertyRepair;
import com.europeandynamics.repository.PropertyRepairRepository;
import com.europeandynamics.service.PropertyRepairService;

import java.util.List;
import java.util.Optional;

public class PropertyRepairServiceImpl implements PropertyRepairService {

	private final PropertyRepairRepository propertyRepairRepository;
	
	public PropertyRepairServiceImpl(PropertyRepairRepository propertyRepairRepository) {
		this.propertyRepairRepository = propertyRepairRepository;
	
	}

//	@Override
//	public List<?> findAll(Class<PropertyRepair> classType) {
//		
//		return  propertyRepairRepository.findAll(classType);
//	}

	@Override
	public PropertyRepair findById(String id, Class<PropertyRepair> classType) {
		Optional<PropertyRepair> propertyRepair = Optional.ofNullable(propertyRepairRepository.findById(id, classType).orElseThrow(
				() -> new ResourceNotFoundException(classType.getSimpleName() + " with this id: " + id + " was not found")));

		return propertyRepair.get();
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
