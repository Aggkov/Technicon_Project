package com.europeandynamics.repository;

import com.europeandynamics.model.PropertyRepair;
import com.europeandynamics.payload.PropertyRepairResponse;

import java.time.LocalDateTime;
import java.util.List;

public interface PropertyRepairRepository extends BaseRepository<PropertyRepair> {

    List<PropertyRepairResponse> findAllRepairsByDate(LocalDateTime from, LocalDateTime to, Class<PropertyRepair> classType);

}
