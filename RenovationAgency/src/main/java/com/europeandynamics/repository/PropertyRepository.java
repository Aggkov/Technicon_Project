package com.europeandynamics.repository;

import com.europeandynamics.model.Property;
import com.europeandynamics.payload.PropertyResponse;

import java.util.List;

public interface PropertyRepository extends BaseRepository<Property> {

	List<PropertyResponse> findPropertiesByOwnerVatNumber(String id, Class<Property> classType);
}
