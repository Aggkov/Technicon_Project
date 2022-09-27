package com.europeandynamics.service;

import java.util.Optional;

import com.europeandynamics.model.PropertyOwner;

public interface PropertyOwnerService extends BaseService<PropertyOwner> {

	Optional<PropertyOwner> findByEmail(String email);
}
