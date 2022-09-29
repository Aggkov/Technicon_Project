package com.europeandynamics.repository.Impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;

import com.europeandynamics.model.PropertyRepair;
import com.europeandynamics.model.enums.RepairStatus;
import com.europeandynamics.model.enums.RepairType;
import com.europeandynamics.payload.PropertyRepairResponse;
import com.europeandynamics.repository.AbstractRepository;
import com.europeandynamics.repository.PropertyRepairRepository;

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

//	@Override
//	public List<?> findAll(Class<PropertyRepair> classType) {
//		// TODO Auto-generated method stub
//		return null;
//	}
}
