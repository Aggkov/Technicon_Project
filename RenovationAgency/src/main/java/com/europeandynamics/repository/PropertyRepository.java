package com.europeandynamics.repository;

import java.util.List;

import com.europeandynamics.model.Property;

public interface PropertyRepository extends BaseRepository<Property> {

	List<Property> findPropertiesByOwnerVatNumber(String id, Class<Property> classType);
}
