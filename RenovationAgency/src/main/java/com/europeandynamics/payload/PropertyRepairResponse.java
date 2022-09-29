package com.europeandynamics.payload;


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

    public PropertyRepairResponse(LocalDateTime dateTimeOfRepair, String shortDescription, RepairType repairType,
                                  RepairStatus repairStatus, double costOfRepair,
                                  String longDescription) {
        this.dateTimeOfRepair = dateTimeOfRepair;
        this.shortDescription = shortDescription;
        this.repairType = repairType;
        this.repairStatus = repairStatus;
        this.costOfRepair = costOfRepair;
        this.longDescription = longDescription;
    }

	public LocalDateTime getDateTimeOfRepair() {
		return dateTimeOfRepair;
	}

	public void setDateTimeOfRepair(LocalDateTime dateTimeOfRepair) {
		this.dateTimeOfRepair = dateTimeOfRepair;
	}

	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	public RepairType getRepairType() {
		return repairType;
	}

	public void setRepairType(RepairType repairType) {
		this.repairType = repairType;
	}

	public RepairStatus getRepairStatus() {
		return repairStatus;
	}

	public void setRepairStatus(RepairStatus repairStatus) {
		this.repairStatus = repairStatus;
	}

	public double getCostOfRepair() {
		return costOfRepair;
	}

	public void setCostOfRepair(double costOfRepair) {
		this.costOfRepair = costOfRepair;
	}

	public String getLongDescription() {
		return longDescription;
	}

	public void setLongDescription(String longDescription) {
		this.longDescription = longDescription;
	}
    
    
}
