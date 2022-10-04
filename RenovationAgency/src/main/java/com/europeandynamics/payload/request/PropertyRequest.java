package com.europeandynamics.payload.request;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;

import com.europeandynamics.model.enums.Type;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
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
 

}
