package com.europeandynamics.utils.validator;

import com.europeandynamics.exceptions.BadRequestException;
import com.europeandynamics.model.PropertyOwner;
import com.europeandynamics.model.enums.HttpStatus;
import com.europeandynamics.payload.request.PropertyOwnerRequest;


public class PropertyOwnerValidator {
	
	public static void validatePropertyOwner(PropertyOwner propertyOwner) {
		
		checkForNulls(propertyOwner);
		
		InputValidator.checkEmail(propertyOwner.getEmail());
		
	}

	
	public static void checkForNulls(PropertyOwner propertyOwner) {
		if(propertyOwner.getAddress() == null || propertyOwner.getEmail() == null || propertyOwner.getPassword() == null ) {
		            throw new BadRequestException("Fields cannot be null",HttpStatus.BAD_REQUEST);
				 }
	}
}