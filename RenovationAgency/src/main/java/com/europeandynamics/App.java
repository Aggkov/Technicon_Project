package com.europeandynamics;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.jboss.logging.Logger;

import com.europeandynamics.exceptions.BadRequestException;
import com.europeandynamics.exceptions.InvalidEmailException;
import com.europeandynamics.exceptions.ResourceNotFoundException;
import com.europeandynamics.model.Property;
import com.europeandynamics.model.PropertyOwner;
import com.europeandynamics.model.PropertyRepair;
import com.europeandynamics.model.enums.RepairStatus;
import com.europeandynamics.model.enums.RepairType;
import com.europeandynamics.model.enums.Role;
import com.europeandynamics.model.enums.Type;
import com.europeandynamics.payload.request.PropertyOwnerRequest;
import com.europeandynamics.payload.request.PropertyRepairRequest;
import com.europeandynamics.payload.request.PropertyRequest;
import com.europeandynamics.payload.response.HighestProfitByRepairTypeResponse;
import com.europeandynamics.payload.response.PropertyOwnerRepairsPaidResponse;
import com.europeandynamics.payload.response.PropertyOwnerResponse;
import com.europeandynamics.payload.response.PropertyRepairResponse;
import com.europeandynamics.payload.response.PropertyResponse;
import com.europeandynamics.repository.Impl.PropertyOwnerRepositoryImpl;
import com.europeandynamics.repository.Impl.PropertyRepairRepositoryImpl;
import com.europeandynamics.repository.Impl.PropertyRepositoryImpl;
import com.europeandynamics.service.PropertyOwnerService;
import com.europeandynamics.service.PropertyRepairService;
import com.europeandynamics.service.PropertyService;
import com.europeandynamics.service.Impl.PropertyOwnerServiceImpl;
import com.europeandynamics.service.Impl.PropertyRepairServiceImpl;
import com.europeandynamics.service.Impl.PropertyServiceImpl;

public class App {

	public static void main(String[] args) {

		Logger logger = LoggerFactory.logger(App.class);

		PropertyOwnerService propertyOwnerService = new PropertyOwnerServiceImpl(new PropertyOwnerRepositoryImpl());

		PropertyService propertyService = new PropertyServiceImpl(new PropertyRepositoryImpl(),
				new PropertyOwnerRepositoryImpl());

		PropertyRepairService propertyRepairService = new PropertyRepairServiceImpl(new PropertyRepairRepositoryImpl(),
				new PropertyRepositoryImpl(), new PropertyOwnerRepositoryImpl());
		

//		  					PROPERTY OWNER SERVICE

		List<PropertyOwnerResponse> findAll = propertyOwnerService.findAll(PropertyOwner.class);
		logger.info("All owners " + findAll);

		try {
			logger.info(propertyOwnerService.findById("111111", PropertyOwner.class));

		} catch (ResourceNotFoundException ex) {
			logger.error(ex);
		}

        try {
            propertyOwnerService.create(new PropertyOwner("111111121", "Jack", "Jackson", "Athens", "6999999999",
                    "jack@email.com", "username9", "pass9", Role.USER));
            logger.info("owner created successfully");
        } catch (BadRequestException | InvalidEmailException ex) {
            logger.error(ex);
        }

//


        try {
        	PropertyOwnerResponse owner = propertyOwnerService.findByEmail("email2@email.com");
        	logger.info("Owner with this email was found " + owner);
        } catch (ResourceNotFoundException ex) {
        	logger.warn(ex);
		}
//
		try {
			propertyOwnerService.update("111111114",
					new PropertyOwnerRequest("Agiou Dimitriou 40", "some@email.com", "9876541231"));
			logger.info("owner updated successfully");
		} catch (ResourceNotFoundException ex) {
			logger.error(ex);
		}

//
//
		try {
			propertyOwnerService.deleteById("111111121", PropertyOwner.class);
			logger.info("owner with id 111111119 was deleted");
		} catch (ResourceNotFoundException ex) {
			logger.error(ex);
		}

//

//        // ------------------------ PROPERTY SERVICE------------------------------

		List<PropertyResponse> properties = propertyService.findAll(Property.class);
		logger.info("All properties: " + properties);
//
//      -----------------------------PROPERTY FIND BY VAT NUMBER--------------------------
        try {
		List<PropertyResponse> propertiesByVat = propertyService.findPropertiesByOwnerVatNumber("11", Property.class);
		logger.info("properties by vat: " + propertiesByVat);
        } catch (ResourceNotFoundException ex) {
        	logger.error(ex);
        }

		// ------------------------PROPERTY FIND BY ID-------------------------
		try {
        logger.info("property found: " + propertyService.findById("E91", Property.class));
		}catch (ResourceNotFoundException ex) {
        	logger.error(ex);
        }

		// -------------------------PROPERTY CREATE--------------------------------
		// if you put Null throws.
		try {
			Property property = propertyService.create(new PropertyRequest("E911119", "Marasliou 20",
					LocalDate.of(2030, 9, 22), Type.APARTMENT, "111111115"));
			logger.info("property created: " + property);
		} catch (BadRequestException ex) {
			logger.error(ex);
		}

		// ------------------------PROPERTY DELETE BY ID ---------------------------
		try {
			propertyService.deleteById("E", Property.class);
			logger.info("property deleted");
		} catch (ResourceNotFoundException ex) {
			logger.error(ex);
		}

//    --------------------------PROPERTY UPDATE----------------------------------
		try {
			propertyService.update("E91111",
					PropertyRequest.builder().address("Stadiou 100").yearOfConstruction(LocalDate.of(1990, 02, 15))
							.type(Type.APARTMENT).propertyOwnerId("111111112").build());

			logger.info("property successfully updated");
		} catch (ResourceNotFoundException ex) {
			logger.error(ex);
		}

		
								// PROPERTY REPAIR SERVICE

		logger.info("All repairs: " + propertyRepairService.findAllPropertyRepairs(PropertyRepair.class));

		try {
			PropertyRepair propertyRepair = propertyRepairService.findById("340", PropertyRepair.class);
			logger.info("found " + propertyRepair);
			logger.info(propertyRepair.getPropertyOwner());
		} catch (ResourceNotFoundException ex) {
			logger.error(ex);
		}

		String vatNumber = "111111113";
		try {
			List<PropertyRepair> propertyRepairs = propertyRepairService.propertyRepairsByOwnerVatNumber(vatNumber);
			logger.info("property repairs by a certain user: " + propertyRepairs);
		} catch (ResourceNotFoundException ex) {
			logger.error(ex);
		}

		List<PropertyRepairResponse> allRepairsByDate = propertyRepairService.findAllRepairsByDate(
				LocalDateTime.of(2022, 9, 20, 14, 30, 20), LocalDateTime.of(2022, 9, 21, 16, 30, 20),
				PropertyRepair.class);
		logger.info("repairs by date: ");
		for (PropertyRepairResponse propertyRepairResponse : allRepairsByDate) {
			logger.info(propertyRepairResponse + " \n");
		}

		try {
			propertyRepairService.create(new PropertyRepairRequest("343", LocalDateTime.of(2022, 10, 2, 10, 30, 20),
					"Short Description", RepairType.FRAMES, RepairStatus.COMPLETE, new BigDecimal("200"),
					"Long Description", "111111115", "E91119"));
			logger.info("property repair created");
		} catch (ResourceNotFoundException | BadRequestException | NumberFormatException ex) {
			logger.error(ex);
		}

//
		try {
			propertyRepairService.update("340", PropertyRepairRequest.builder()
					.repairStatus(RepairStatus.DEFAULT_STANDBY).costOfRepair(new BigDecimal("300"))
					.dateTimeOfRepair(LocalDateTime.of(2022, 10, 2, 10, 30, 20)).longDescription("Long Description")
					.shortDescription("Short Description").repairType(RepairType.ELECTRICAL_WORK).propertyId("E91113")
					.propertyOwnerId("111111112").build());

			logger.info("property repair successfully updated");
		} catch (ResourceNotFoundException ex) {
			logger.error(ex);
		}

		try {
			boolean result = propertyRepairService.deleteById("342", PropertyRepair.class);
			logger.info("property repair deleted " + result);
		} catch (ResourceNotFoundException ex) {
			logger.error(ex);
		}

//      
		List<HighestProfitByRepairTypeResponse> costPerRepairType = propertyRepairService.highestProfitRepairsByRepairType();
		logger.info(costPerRepairType);

		List<PropertyOwnerRepairsPaidResponse> eachOwnerSumPaid = new ArrayList<>(propertyOwnerService.amountPaidForRepairsByOwner());
		
		propertyOwnerService.amountPaidForRepairsByOwner();
		logger.info(eachOwnerSumPaid);

	}

}
