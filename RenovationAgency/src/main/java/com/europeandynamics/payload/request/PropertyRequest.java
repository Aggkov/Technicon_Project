package com.europeandynamics.payload.request;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;

import com.europeandynamics.model.enums.Type;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PropertyRequest {
	
	@NotBlank
	private String Id;
	
	@NotBlank
	private String address;
	
	@NotBlank
	private LocalDate yearOfConstruction;
	
	@NotBlank
	private Type type;
	
	@NotBlank
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
