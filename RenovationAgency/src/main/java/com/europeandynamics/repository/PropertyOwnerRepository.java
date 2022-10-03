package com.europeandynamics.repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import com.europeandynamics.model.PropertyOwner;
import com.europeandynamics.model.enums.RepairType;
import com.europeandynamics.payload.response.PropertyOwnerRepairsPaidResponse;
import com.europeandynamics.payload.response.PropertyOwnerResponse;

public interface PropertyOwnerRepository extends BaseRepository<PropertyOwner> {

	Optional<PropertyOwnerResponse> findByEmail(String email);
	
	List<PropertyOwnerResponse> findAllOwners(Class<PropertyOwner> classType);

	List<PropertyOwnerRepairsPaidResponse> amountPaidForRepairsByOwner();

//	Map<RepairType, Set<PropertyOwnerRepairsPaidResponse>> amountPaidForRepairsByOwner();
}
