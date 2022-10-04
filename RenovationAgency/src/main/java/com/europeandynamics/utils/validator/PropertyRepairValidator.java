package com.europeandynamics.utils.validator;

import com.europeandynamics.exceptions.BadRequestException;
import com.europeandynamics.model.enums.HttpStatus;
import com.europeandynamics.payload.request.PropertyRepairRequest;


public class PropertyRepairValidator {

    public static void validatePropertyRepair(PropertyRepairRequest propertyRepairRequest) {

        checkForNulls(propertyRepairRequest);

        InputValidator.checkPropertyRepairId(propertyRepairRequest.getPropertyRepairId());

        InputValidator.dateTimeOfRepairMustBeOnPresentOrFuture(propertyRepairRequest.getDateTimeOfRepair());

        InputValidator.checkRepairTypeEnum(propertyRepairRequest.getRepairType());

        InputValidator.checkCostOfRepair(propertyRepairRequest.getCostOfRepair());


    }

    public static void checkForNulls(PropertyRepairRequest propertyRepairRequest) {

        if(propertyRepairRequest.getPropertyRepairId() == null || propertyRepairRequest.getRepairType() == null ||
        propertyRepairRequest.getRepairStatus() == null || propertyRepairRequest.getDateTimeOfRepair() == null || propertyRepairRequest.getCostOfRepair() == null ||
        propertyRepairRequest.getShortDescription() == null || propertyRepairRequest.getLongDescription() == null || propertyRepairRequest.getPropertyOwnerId() == null ||
        propertyRepairRequest.getPropertyId() == null) {
            throw new BadRequestException("Fields cannot be null",HttpStatus.BAD_REQUEST);
        }
    }
}
