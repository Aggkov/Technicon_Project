package com.europeandynamics.repository.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;

import com.europeandynamics.model.Property;
import com.europeandynamics.model.PropertyOwner;
import com.europeandynamics.payload.PropertyOwnerResponse;
import com.europeandynamics.payload.PropertyResponse;
import com.europeandynamics.repository.AbstractRepository;
import com.europeandynamics.repository.PropertyRepository;

public class PropertyRepositoryImpl extends AbstractRepository<Property> implements PropertyRepository {

	@Override
	public List<PropertyResponse> findPropertiesByOwnerVatNumber(String id, Class<Property> classType) {
		EntityManager em = emf.createEntityManager();

		em.getTransaction().begin();

		List<PropertyResponse> propertiesByOwnerVatNum = em
				.createQuery("SELECT property FROM PropertyOwner propertyOwner"
						+ " JOIN propertyOwner.properties property WHERE propertyOwner.Id = ?1", classType)
				.setParameter(1, id).getResultList().stream()
				.map(e -> PropertyResponse.builder().address(e.getAddress())
						.yearOfConstruction(e.getYearOfConstruction()).type(e.getType()).build())

				.collect(Collectors.toCollection(ArrayList::new));

		return propertiesByOwnerVatNum;

	}
	
	public List<PropertyResponse> findAllProperties(Class<Property> classType) {
		EntityManager em = emf.createEntityManager();

		 
		List<PropertyResponse> resultList = em.createNamedQuery("Property.findAll", classType).getResultList()
        		.stream()
        		.map(e -> PropertyResponse.builder()
        				.address(e.getAddress())
        				.type(e.getType())
        				.yearOfConstruction(e.getYearOfConstruction())
        				.propertyOwner(e.getPropertyOwner())
        				.build())
        		
        		.collect(Collectors.toCollection(ArrayList::new));
		
		return resultList;
	}


}
