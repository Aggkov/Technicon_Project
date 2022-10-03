package com.europeandynamics.repository.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.europeandynamics.model.PropertyOwner;
import com.europeandynamics.payload.response.PropertyOwnerRepairsPaidResponse;
import com.europeandynamics.payload.response.PropertyOwnerResponse;
import com.europeandynamics.payload.response.PropertyRepairResponse;
import com.europeandynamics.payload.response.PropertyResponse;
import com.europeandynamics.repository.AbstractRepository;
import com.europeandynamics.repository.PropertyOwnerRepository;

public class PropertyOwnerRepositoryImpl extends AbstractRepository<PropertyOwner> implements PropertyOwnerRepository {

	public List<PropertyOwnerRepairsPaidResponse> amountPaidForRepairsByOwner() {
		EntityManager em = emf.createEntityManager();

		em.getTransaction().begin();
		List<PropertyOwnerRepairsPaidResponse> query = em.createQuery("SELECT new com.europeandynamics.payload.response.PropertyOwnerRepairsPaidResponse(p.name, p.surname, SUM(pr.costOfRepair)) " +
				" FROM PropertyOwner p JOIN p.propertyRepairs pr GROUP BY p.Id ORDER BY pr.costOfRepair DESC " ,PropertyOwnerRepairsPaidResponse.class)
				.getResultList();

		return query;
	}

	@Override
	public Optional<PropertyOwnerResponse> findByEmail(String email) {
		EntityManager em = emf.createEntityManager();

		em.getTransaction().begin();
		Optional<PropertyOwnerResponse> propertyOwner = Optional.ofNullable(em
				.createQuery(
						"SELECT propertyOwner from PropertyOwner propertyOwner where " + "propertyOwner.email = ?1", PropertyOwner.class)
				.setParameter(1, email).getSingleResult())
				
				.map(e -> PropertyOwnerResponse.builder()
						.name(e.getName())
						.surname(e.getSurname())
						.address(e.getAddress())
						.phoneNumber(e.getPhoneNumber())
						.email(e.getEmail())
						.properties(e.getProperties())
						.propertyRepairs(e.getPropertyRepairs())
						.role(e.getRole())
						.build());
 
		em.close();

		return propertyOwner;
	}

	public List<PropertyOwnerResponse> findAllOwners(Class<PropertyOwner> classType) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		 
		List<PropertyOwnerResponse> resultList = em.createNamedQuery("PropertyOwner.findAll", classType).getResultList()
        		.stream()
        		.map(e -> PropertyOwnerResponse.builder()
        				.address(e.getAddress())
        				.name(e.getName())
        				.email(e.getEmail())
        				.surname(e.getSurname())
        				.phoneNumber(e.getPhoneNumber())
        				.username(e.getUsername())
        				.properties(e.getProperties())
        				.propertyRepairs(e.getPropertyRepairs())
        				.build())
        		
        		.collect(Collectors.toCollection(ArrayList::new));
		em.close();
		return resultList;
	}
	
}




