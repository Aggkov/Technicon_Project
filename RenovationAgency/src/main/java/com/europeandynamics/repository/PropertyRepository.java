package com.europeandynamics.repository;

import com.europeandynamics.model.Property;
import com.europeandynamics.payload.response.PropertyResponse;

import java.util.List;

public interface PropertyRepository extends BaseRepository<Property> {

	List<PropertyResponse> findPropertiesByOwnerVatNumber(String id, Class<Property> classType);
	
	List<PropertyResponse> findAllProperties(Class<Property> classType);
}
