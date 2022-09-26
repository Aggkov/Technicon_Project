package com.europeandynamics.service.Impl;

import com.europeandynamics.exceptions.ResourceNotFoundException;
import com.europeandynamics.model.PropertyOwner;
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
				.orElseThrow(() -> new ResourceNotFoundException(this.getClass() + " with this id " +  id + " was not found")));
	}

	@Override
	public PropertyOwner create(PropertyOwner entity) {

		// after validation is done
		return propertyOwnerRepository.create(entity);
	}

	@Override
	public void update(PropertyOwner entity) {

	}

	@Override
	public void deleteById(String id, Class<PropertyOwner> classType) {

	}

	@Override
	public void delete(PropertyOwner entity) {

	}

}
