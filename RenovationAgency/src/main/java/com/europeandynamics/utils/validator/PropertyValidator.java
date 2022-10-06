package com.europeandynamics.utils.validator;

import com.europeandynamics.exceptions.BadRequestException;
import com.europeandynamics.model.enums.HttpStatus;
import com.europeandynamics.payload.request.PropertyRequest;

public class PropertyValidator {

	
	public static void validateProperty(PropertyRequest propertyRequest) {
		checkForNulls(propertyRequest);
	}
	
	public static void checkForNulls(PropertyRequest propertyRequest) {
		
		if (propertyRequest.getId()==(null) || 
				propertyRequest.getAddress() == (null) || 
				propertyRequest.getYearOfConstruction()== (null) ||
				propertyRequest.getType() == (null) ||
				propertyRequest.getPropertyOwnerId() == (null)) {
		            throw new BadRequestException("Property fields cannot be null",HttpStatus.BAD_REQUEST);
		        }
		
	}
	
}
