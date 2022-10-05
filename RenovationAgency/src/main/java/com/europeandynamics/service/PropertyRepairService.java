package com.europeandynamics.service;

import com.europeandynamics.model.PropertyRepair;
import com.europeandynamics.model.enums.RepairType;
import com.europeandynamics.payload.request.PropertyRepairRequest;
import com.europeandynamics.payload.response.PropertyRepairResponse;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface PropertyRepairService extends BaseService<PropertyRepair> {

     List<PropertyRepair> propertyRepairsByOwnerVatNumber(String id);

     List<PropertyRepairResponse> findAllRepairsByDate(LocalDateTime dateFrom, LocalDateTime dateTo, Class<PropertyRepair> classType);

     PropertyRepair create(PropertyRepairRequest propertyRepairRequest);

     void update(String id, PropertyRepairRequest propertyRepairRequest);
     
     Map<RepairType, BigDecimal> highestProfitRepairsByRepairType();

}
