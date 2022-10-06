package com.europeandynamics.repository.Impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.europeandynamics.model.PropertyRepair;
import com.europeandynamics.payload.response.HighestProfitByRepairTypeResponse;
import com.europeandynamics.payload.response.PropertyOwnerResponse;
import com.europeandynamics.payload.response.PropertyRepairResponse;
import com.europeandynamics.repository.AbstractRepository;
import com.europeandynamics.repository.PropertyRepairRepository;

public class PropertyRepairRepositoryImpl extends AbstractRepository<PropertyRepair>
		implements PropertyRepairRepository {
	
	public List<PropertyRepair> findAll(Class<PropertyRepair> classType) {
		
		return em.createNamedQuery("PropertyRepair.findAll", PropertyRepair.class)
				.getResultList();
	}

	@Override
	public List<PropertyRepairResponse> findAllRepairsByDate(LocalDateTime dateFrom, LocalDateTime dateTo,
			Class<PropertyRepair> classType) {

		List<PropertyRepairResponse> repairsOnDateRange = em
				.createQuery("SELECT pr FROM PropertyRepair pr " + "WHERE pr.dateTimeOfRepair BETWEEN :dateFrom AND :dateTo",
						classType)
				.setParameter("dateFrom", dateFrom).setParameter("dateTo", dateTo)
				.getResultList()
				.stream()
				.map(e -> PropertyRepairResponse.builder().dateTimeOfRepair(e.getDateTimeOfRepair())
						.shortDescription(e.getShortDescription()).repairType(e.getRepairType())
						.repairStatus(e.getRepairStatus()).costOfRepair(e.getCostOfRepair())
						.longDescription(e.getLongDescription()).build())

				.collect(Collectors.toCollection(ArrayList::new));

		return repairsOnDateRange;
	}

	@Override
	public List<PropertyRepair> propertyRepairsByOwnerVatNumber(String vatNumber) {

		List<PropertyRepair> repairsByOwner = em
				.createQuery("SELECT pr " + "FROM PropertyRepair pr " + "JOIN FETCH pr.propertyOwner propertyOwner "
						+ "WHERE propertyOwner.Id = :Id", PropertyRepair.class)
				.setParameter("Id", vatNumber).getResultList();

		return repairsByOwner;
	}

	public List<HighestProfitByRepairTypeResponse> highestProfitRepairsByRepairType() {

		List<HighestProfitByRepairTypeResponse> repairsByOwner = new ArrayList<>();
		
		repairsByOwner = em.createQuery("SELECT "
				+ "new com.europeandynamics.payload.response.HighestProfitByRepairTypeResponse(pr.repairType,SUM(pr.costOfRepair) as totalCost) "
				+ "FROM PropertyRepair pr " +

				"GROUP BY pr.repairType " + 
				"ORDER BY totalCost DESC", HighestProfitByRepairTypeResponse.class)
				.setMaxResults(3)
				.getResultList()
				.stream()
				.sorted(Comparator.comparing(HighestProfitByRepairTypeResponse::getCostOfRepair).reversed())
				.collect(Collectors.toCollection(ArrayList::new));


		return repairsByOwner;
	}
}
