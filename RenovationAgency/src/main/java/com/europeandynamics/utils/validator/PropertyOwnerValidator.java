package com.europeandynamics.utils.validator;

import com.europeandynamics.exceptions.BadRequestException;
import com.europeandynamics.model.enums.HttpStatus;
import com.europeandynamics.payload.request.PropertyOwnerRequest;


public class PropertyOwnerValidator {
	
	public static void validatePropertyOwner(PropertyOwnerRequest propertyOwnerRequest) {
		
		checkForNulls(propertyOwnerRequest);
		
		InputValidator.checkEmail(propertyOwnerRequest.getEmail());
		
	}

	
	public static void checkForNulls(PropertyOwnerRequest propertyOwnerRequest) {
		if(propertyOwnerRequest.getAddress() == null || propertyOwnerRequest.getEmail() == null || propertyOwnerRequest.getPassword() == null ) {
		            throw new BadRequestException("Fields cannot be null",HttpStatus.BAD_REQUEST);
				 }
	}
}