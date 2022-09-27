package com.europeandynamics.repository.Impl;

import java.util.Optional;

import com.europeandynamics.model.PropertyOwner;
import com.europeandynamics.repository.AbstractRepository;
import com.europeandynamics.repository.PropertyOwnerRepository;

public class PropertyOwnerRepositoryImpl extends AbstractRepository<PropertyOwner> implements PropertyOwnerRepository {

	@Override
	public Optional<PropertyOwner> findByEmail(String email) {
//		em.getTransaction().begin();

		Optional<PropertyOwner> propertyOwner = Optional.ofNullable((PropertyOwner) em
				.createQuery(
						"SELECT propertyOwner from PropertyOwner propertyOwner where " + "propertyOwner.email = ?1")
				.setParameter(1, email).getSingleResult());
//		em.getTransaction().commit();
//		em.close();

		return propertyOwner;
	}
}
