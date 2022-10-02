package com.europeandynamics.repository;

import java.util.List;
import java.util.Optional;

import com.europeandynamics.model.PropertyOwner;
import com.europeandynamics.payload.response.PropertyOwnerRepairsPaidResponse;
import com.europeandynamics.payload.response.PropertyOwnerResponse;

public interface PropertyOwnerRepository extends BaseRepository<PropertyOwner> {

	Optional<PropertyOwnerResponse> findByEmail(String email);
	
	List<PropertyOwnerResponse> findAllOwners(Class<PropertyOwner> classType);

	List<PropertyOwnerRepairsPaidResponse> amountPaidForRepairsByOwner();
}
