package com.europeandynamics.payload;

import com.europeandynamics.model.PropertyOwner;
import com.europeandynamics.model.enums.Type;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Builder
public class PropertyRequest {

    private String Id;

    private String address;

    private LocalDate yearOfConstruction;

    private Type type;

    private String propertyOwnerId;

    public PropertyRequest() {
    }

    public PropertyRequest(String Id, String address, LocalDate yearOfConstruction, Type type, String propertyOwnerId) {
        this.Id = Id;
        this.address = address;
        this.yearOfConstruction = yearOfConstruction;
        this.type = type;
        this.propertyOwnerId = propertyOwnerId;
    }
}
