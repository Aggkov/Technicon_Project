package com.europeandynamics.repository.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;

import com.europeandynamics.model.PropertyOwner;
import com.europeandynamics.payload.PropertyOwnerResponse;
import com.europeandynamics.repository.AbstractRepository;
import com.europeandynamics.repository.PropertyOwnerRepository;

public class PropertyOwnerRepositoryImpl extends AbstractRepository<PropertyOwner> implements PropertyOwnerRepository {

	@Override
	public Optional<PropertyOwner> findByEmail(String email) {
		EntityManager em = emf.createEntityManager();

		em.getTransaction().begin();
		Optional<PropertyOwner> propertyOwner = Optional.ofNullable((PropertyOwner) em
				.createQuery(
						"SELECT propertyOwner from PropertyOwner propertyOwner where " + "propertyOwner.email = ?1")
				.setParameter(1, email).getSingleResult());
		em.getTransaction().commit();

		em.close();

		return propertyOwner;
	}


	public List<PropertyOwnerResponse> findAllOwners(Class<PropertyOwner> classType) {
		EntityManager em = emf.createEntityManager();

		 
		List<PropertyOwnerResponse> resultList = em.createNamedQuery("PropertyOwner.findAll", classType).getResultList()
        		.stream()
        		.map(e -> PropertyOwnerResponse.builder()
        				.address(e.getAddress())
        				.name(e.getName())
        				.email(e.getEmail())
        				.surname(e.getSurname())
        				.password(e.getPassword())
        				.phoneNumber(e.getPhoneNumber())
        				.username(e.getUsername())
        				.build())
        		
        		.collect(Collectors.toCollection(ArrayList::new));
		
		return resultList;
	}
	
}




