package com.europeandynamics.service;

import java.util.List;

import com.europeandynamics.model.Property;
import com.europeandynamics.payload.PropertyRequest;
import com.europeandynamics.payload.PropertyResponse;

public interface PropertyService extends BaseService<Property> {

	List<PropertyResponse> findPropertiesByOwnerVatNumber(String id, Class<Property> classType);
	
	List<PropertyResponse> findAll(Class<Property> classType);
	
	void update(String id, PropertyRequest propertyRequest);

}
