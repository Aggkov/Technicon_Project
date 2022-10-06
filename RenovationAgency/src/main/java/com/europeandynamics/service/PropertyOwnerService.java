package com.europeandynamics.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.europeandynamics.model.PropertyOwner;
import com.europeandynamics.model.enums.RepairType;
import com.europeandynamics.payload.request.PropertyOwnerRequest;
import com.europeandynamics.payload.response.PropertyOwnerRepairsPaidResponse;
import com.europeandynamics.payload.response.PropertyOwnerResponse;


public interface PropertyOwnerService extends BaseService<PropertyOwner> {

	PropertyOwnerResponse findByEmail(String email);

	void update(String id, PropertyOwnerRequest propertyOwnerRequest);
	
	List<PropertyOwnerResponse> findAll(Class<PropertyOwner> classType);

	PropertyOwner create(PropertyOwner propertyOwner);

	List<PropertyOwnerRepairsPaidResponse> amountPaidForRepairsByOwner();

	
	
}
