package com.europeandynamics.payload.request;


import java.time.LocalDate;

import com.europeandynamics.model.enums.Type;

import lombok.Builder;
import lombok.Data;

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
