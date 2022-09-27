package com.europeandynamics.repository;

import java.util.Optional;

import com.europeandynamics.model.PropertyOwner;

public interface PropertyOwnerRepository extends BaseRepository<PropertyOwner> {

	Optional<PropertyOwner> findByEmail(String email);

}
