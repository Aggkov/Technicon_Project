package com.europeandynamics.repository.Impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import com.europeandynamics.model.PropertyRepair;
import com.europeandynamics.model.enums.RepairType;
import com.europeandynamics.payload.response.HighestProfitByRepairTypeResponse;
import com.europeandynamics.payload.response.PropertyRepairResponse;
import com.europeandynamics.repository.AbstractRepository;
import com.europeandynamics.repository.PropertyRepairRepository;

public class PropertyRepairRepositoryImpl extends AbstractRepository<PropertyRepair>
		implements PropertyRepairRepository {

	@Override
	public List<PropertyRepairResponse> findAllRepairsByDate(LocalDateTime dateFrom, LocalDateTime dateTo,
			Class<PropertyRepair> classType) {

		List<PropertyRepairResponse> repairsOnDateRange = em
				.createQuery("SELECT pr FROM PropertyRepair pr " + "WHERE pr.dateTimeOfRepair BETWEEN ?1 AND ?2",
						classType)
				.setParameter(1, dateFrom).setParameter(2, dateTo).getResultList().stream()
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

	public Map<RepairType, BigDecimal> highestProfitRepairsByRepairType() {

		Map<RepairType, BigDecimal> repairsByOwner = new LinkedHashMap<>();
		
		repairsByOwner = em.createQuery("SELECT "
				+ "new com.europeandynamics.payload.response.HighestProfitByRepairTypeResponse(pr.repairType,SUM(pr.costOfRepair)) "
				+ "FROM PropertyRepair pr " +

				"GROUP BY pr.repairType " + 
				"ORDER BY pr.costOfRepair ", HighestProfitByRepairTypeResponse.class)
//				.setMaxResults(3)
				.getResultList().stream()
				.sorted(Comparator.comparing(HighestProfitByRepairTypeResponse::getCostOfRepair))
				
				.collect(Collectors.toMap(HighestProfitByRepairTypeResponse::getRepairType,
						HighestProfitByRepairTypeResponse::getCostOfRepair,(o1, o2) -> o1));

		return repairsByOwner;
	}
}
