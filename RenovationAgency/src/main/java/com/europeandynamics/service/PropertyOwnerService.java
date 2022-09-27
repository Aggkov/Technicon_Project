package com.europeandynamics.service;

import com.europeandynamics.model.PropertyOwner;
import com.europeandynamics.payload.PropertyOwnerRequest;

public interface PropertyOwnerService extends BaseService<PropertyOwner> {

	PropertyOwner findByEmail(String email);

	void update(String id, PropertyOwnerRequest propertyOwnerRequest);
}
