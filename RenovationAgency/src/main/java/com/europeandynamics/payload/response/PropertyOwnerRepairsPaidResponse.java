package com.europeandynamics.payload.response;

import lombok.Data;

@Data
public class PropertyOwnerRepairsPaidResponse {

    private String name;
    private String surname;
    private double amountPaidForRepairs;


    public PropertyOwnerRepairsPaidResponse(String name, String surname, double amountPaidForRepairs) {
        this.name = name;
        this.surname = surname;
        this.amountPaidForRepairs = amountPaidForRepairs;
    }
}
