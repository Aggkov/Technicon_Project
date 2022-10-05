package com.europeandynamics.repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import com.europeandynamics.model.PropertyRepair;
import com.europeandynamics.model.enums.RepairType;
import com.europeandynamics.payload.response.PropertyRepairResponse;

public interface PropertyRepairRepository extends BaseRepository<PropertyRepair> {

    List<PropertyRepairResponse> findAllRepairsByDate(LocalDateTime from, LocalDateTime to, Class<PropertyRepair> classType);

    List<PropertyRepair> propertyRepairsByOwnerVatNumber(String vatNumber);
    
    Map<RepairType, BigDecimal> highestProfitRepairsByRepairType();

}
