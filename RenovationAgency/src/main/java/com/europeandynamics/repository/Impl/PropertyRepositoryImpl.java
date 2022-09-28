package com.europeandynamics.repository.Impl;

import java.util.List;

import javax.persistence.EntityManager;

import com.europeandynamics.model.Property;
import com.europeandynamics.repository.AbstractRepository;
import com.europeandynamics.repository.PropertyRepository;

public class PropertyRepositoryImpl extends AbstractRepository<Property> implements PropertyRepository {

	@Override
	public List<Property> findPropertiesByOwnerVatNumber(String id, Class<Property> classType) {
		EntityManager em = emf.createEntityManager();

		em.getTransaction().begin();

		List<?> propertiesByVatNum = em
				.createQuery("SELECT propertyOwner,property FROM PropertyOwner propertyOwner"
						+ " JOIN propertyOwner.properties property WHERE propertyOwner.Id = ?1")
				.setParameter(1, id).getResultList();

//		List<Property> propertyList = propertiesByVatNum.stream().map(e -> )
//				.collect(Collectors.toCollection(() -> new ArrayList<Property>()));

		return null;

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
