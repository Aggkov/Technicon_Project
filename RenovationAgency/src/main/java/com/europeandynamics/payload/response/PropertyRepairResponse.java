package com.europeandynamics.payload.response;


import com.europeandynamics.model.Property;
import com.europeandynamics.model.PropertyOwner;
import com.europeandynamics.model.enums.RepairStatus;
import com.europeandynamics.model.enums.RepairType;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class PropertyRepairResponse {

    private LocalDateTime dateTimeOfRepair;

    private String shortDescription;

    private RepairType repairType;

    private RepairStatus repairStatus;

    private double costOfRepair;

    private String longDescription;

    private PropertyOwner propertyOwner;
    
    public PropertyRepairResponse() {
	}

    public PropertyRepairResponse(LocalDateTime dateTimeOfRepair, String shortDescription, RepairType repairType,
                                  RepairStatus repairStatus, double costOfRepair,
                                  String longDescription, PropertyOwner propertyOwner) {
        this.dateTimeOfRepair = dateTimeOfRepair;
        this.shortDescription = shortDescription;
        this.repairType = repairType;
        this.repairStatus = repairStatus;
        this.costOfRepair = costOfRepair;
        this.longDescription = longDescription;
        this.propertyOwner = propertyOwner;
    }

	
}
