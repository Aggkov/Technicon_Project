package com.europeandynamics.payload;

import com.europeandynamics.model.enums.Type;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class PropertyResponse {

    private String address;

    private LocalDate yearOfConstruction;

    private Type type;

    public PropertyResponse(String address, LocalDate yearOfConstruction, Type type) {
        this.address = address;
        this.yearOfConstruction = yearOfConstruction;
        this.type = type;
    }
}
