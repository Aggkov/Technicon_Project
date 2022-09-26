package com.europeandynamics.repository;

import com.europeandynamics.model.PropertyOwner;

public interface PropertyOwnerRepository extends BaseRepository<PropertyOwner> {

    PropertyOwner findByEmail(String email);

}
