package com.europeandynamics.repository.Impl;

import com.europeandynamics.model.Property;
import com.europeandynamics.payload.PropertyResponse;
import com.europeandynamics.repository.AbstractRepository;
import com.europeandynamics.repository.PropertyRepository;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PropertyRepositoryImpl extends AbstractRepository<Property> implements PropertyRepository {

	@SuppressWarnings("unchecked")
	@Override
	public List<PropertyResponse> findPropertiesByOwnerVatNumber(String id, Class<Property> classType) {
		EntityManager em = emf.createEntityManager();

		em.getTransaction().begin();

		List<PropertyResponse> propertiesByOwnerVatNum = em
				.createQuery("SELECT property FROM PropertyOwner propertyOwner"
						+ " JOIN propertyOwner.properties property WHERE propertyOwner.Id = ?1", classType)
				.setParameter(1, id).getResultList()
				.stream()
				.map(e -> PropertyResponse.builder()
						.address(e.getAddress())
						.yearOfConstruction(e.getYearOfConstruction())
						.type(e.getType())
						.build())

				.collect(Collectors.toCollection(ArrayList::new));

		return propertiesByOwnerVatNum;

	}
//				.stream()
//				.map(e -> (Property) e)
//				.collect(Collectors.toCollection(ArrayList::new));

//	List<Property> propertiesByVatNum(String queryString, Class<T> clazz) {
//		myEntityManager.createQuery(queryString);
//		return query.getResultList().stream().map(result -> clazz.cast(result)).collect(Collectors.toList());
//
//		em.getTransaction().commit();
//		em.close();
//
//		return propertiesByVatNum;

}
