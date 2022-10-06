package com.europeandynamics.payload.response;

import java.time.LocalDate;

import com.europeandynamics.model.PropertyOwner;
import com.europeandynamics.model.enums.Type;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PropertyResponse {

    private String address;

    private LocalDate yearOfConstruction;

    private Type type;
    
    

	public PropertyResponse() {
	}
    
    public PropertyResponse(String address, LocalDate yearOfConstruction, Type type) {
        this.address = address;
        this.yearOfConstruction = yearOfConstruction;
        this.type = type;
    }



}
