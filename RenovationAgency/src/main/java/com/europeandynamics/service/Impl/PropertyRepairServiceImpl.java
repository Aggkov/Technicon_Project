package com.europeandynamics.service.Impl;

import java.util.List;
import java.util.Optional;

import com.europeandynamics.model.PropertyRepair;
import com.europeandynamics.repository.PropertyRepairRepository;
import com.europeandynamics.service.PropertyRepairService;

public class PropertyRepairServiceImpl implements PropertyRepairService {

	private final PropertyRepairRepository propertyRepairRepository;
	
	public PropertyRepairServiceImpl(PropertyRepairRepository propertyRepairRepository) {
		this.propertyRepairRepository = propertyRepairRepository;
	
	}

	@Override
	public List<?> findAll(Class<PropertyRepair> classType) {
		
		return  propertyRepairRepository.findAll(classType);
	}

	@Override
	public Optional<PropertyRepair> findById(String id, Class<PropertyRepair> classType) {
		
		return propertyRepairRepository.findById(id, classType);
	}

	@Override
	public PropertyRepair create(PropertyRepair entity) {
		
		return propertyRepairRepository.create(entity);
	}

	@Override
	public void update(PropertyRepair entity) {
		
		
	}

	@Override
	public void deleteById(String id, Class<PropertyRepair> classType) {
		
		
	}

	@Override
	public void delete(PropertyRepair entity) {
		
		
	}

}
