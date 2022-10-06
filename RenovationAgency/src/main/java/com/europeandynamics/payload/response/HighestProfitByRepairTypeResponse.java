package com.europeandynamics.payload.response;

import java.math.BigDecimal;

import com.europeandynamics.model.enums.RepairType;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class HighestProfitByRepairTypeResponse {
	
	private RepairType repairType;
	
	private BigDecimal costOfRepair;
	
	
	
	

}
