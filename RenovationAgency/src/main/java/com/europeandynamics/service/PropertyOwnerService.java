package com.europeandynamics.service;

import com.europeandynamics.model.PropertyOwner;

public interface PropertyOwnerService extends BaseService<PropertyOwner> {

    PropertyOwner findByEmail(String email);
}
