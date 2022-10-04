package com.europeandynamics.payload.response;

import com.europeandynamics.model.enums.RepairType;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class PropertyOwnerRepairsPaidResponse {

    private String name;
    private String surname;
    private BigDecimal amountPaidForRepairs;

    
    public PropertyOwnerRepairsPaidResponse(String name, String surname,BigDecimal amountPaidForRepairs) {
        this.name = name;
        this.surname = surname;
        this.amountPaidForRepairs = amountPaidForRepairs;
    }



}
