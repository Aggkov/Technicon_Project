package com.europeandynamics.repository.Impl;

import com.europeandynamics.model.PropertyRepair;
import com.europeandynamics.payload.PropertyRepairResponse;
import com.europeandynamics.repository.AbstractRepository;
import com.europeandynamics.repository.PropertyRepairRepository;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PropertyRepairRepositoryImpl extends AbstractRepository<PropertyRepair>
		implements PropertyRepairRepository {

	@Override
	public List<PropertyRepairResponse> findAllRepairsByDate(LocalDateTime dateFrom, LocalDateTime dateTo, Class<PropertyRepair> classType) {

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		List<PropertyRepairResponse> repairsOnDateRange = em.createQuery("SELECT pr FROM PropertyRepair pr " +
				"WHERE pr.dateTimeOfRepair BETWEEN ?1 AND ?2", classType)
				.setParameter(1, dateFrom)
				.setParameter(2, dateTo)
				.getResultList()
				.stream()
				.map(e -> PropertyRepairResponse.builder()
						.dateTimeOfRepair(e.getDateTimeOfRepair())
						.shortDescription(e.getShortDescription())
						.repairType(e.getRepairType())
						.repairStatus(e.getRepairStatus())
						.costOfRepair(e.getCostOfRepair())
						.longDescription(e.getLongDescription()).build())
				.collect(Collectors.toCollection(ArrayList::new));

		return repairsOnDateRange;
	}
}
