package com.europeandynamics.repository;

import java.util.List;
import java.util.Optional;

import com.europeandynamics.model.PropertyOwner;
import com.europeandynamics.payload.PropertyOwnerResponse;

public interface PropertyOwnerRepository extends BaseRepository<PropertyOwner> {

	Optional<PropertyOwner> findByEmail(String email);
	
	public List<PropertyOwnerResponse> findAllOwners(Class<PropertyOwner> classType);

}
