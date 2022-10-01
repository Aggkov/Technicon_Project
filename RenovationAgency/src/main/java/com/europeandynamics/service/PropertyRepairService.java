package com.europeandynamics.service;

import com.europeandynamics.model.PropertyRepair;
import com.europeandynamics.payload.response.PropertyRepairResponse;

import java.time.LocalDateTime;
import java.util.List;

public interface PropertyRepairService extends BaseService<PropertyRepair> {

     List<PropertyRepair> propertyRepairsByOwnerVatNumber(String id);

     List<PropertyRepairResponse> findAllRepairsByDate(LocalDateTime dateFrom, LocalDateTime dateTo, Class<PropertyRepair> classType);

}
