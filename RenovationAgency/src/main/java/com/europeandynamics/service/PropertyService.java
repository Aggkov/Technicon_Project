package com.europeandynamics.service;

import java.util.List;

import com.europeandynamics.model.Property;

public interface PropertyService extends BaseService<Property> {

	List<Property> findPropertiesByOwnerVatNumber(String id);

	void update(String id);

}
