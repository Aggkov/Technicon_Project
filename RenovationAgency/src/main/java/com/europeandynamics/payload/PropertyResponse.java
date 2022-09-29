package com.europeandynamics.payload;

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
    
    private PropertyOwner propertyOwner;

    public PropertyResponse(String address, LocalDate yearOfConstruction, Type type, PropertyOwner propertyOwner) {
        this.address = address;
        this.yearOfConstruction = yearOfConstruction;
        this.type = type;
        this.propertyOwner = propertyOwner;
    }
}
