package com.europeandynamics;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

import com.europeandynamics.exceptions.BadRequestException;
import com.europeandynamics.exceptions.InvalidEmailException;
import com.europeandynamics.model.Property;
import com.europeandynamics.model.PropertyOwner;
import com.europeandynamics.model.enums.RepairType;
import com.europeandynamics.model.enums.Role;
import com.europeandynamics.model.enums.Type;
import com.europeandynamics.payload.request.PropertyOwnerRequest;
import com.europeandynamics.payload.request.PropertyRequest;
import com.europeandynamics.payload.response.PropertyOwnerRepairsPaidResponse;
import com.europeandynamics.payload.response.PropertyOwnerResponse;
import com.europeandynamics.payload.response.PropertyRepairResponse;
import com.europeandynamics.payload.response.PropertyResponse;
import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.jboss.logging.Logger;

import com.europeandynamics.exceptions.ResourceNotFoundException;
import com.europeandynamics.model.PropertyRepair;
import com.europeandynamics.repository.Impl.PropertyOwnerRepositoryImpl;
import com.europeandynamics.repository.Impl.PropertyRepairRepositoryImpl;
import com.europeandynamics.repository.Impl.PropertyRepositoryImpl;
import com.europeandynamics.service.PropertyOwnerService;
import com.europeandynamics.service.PropertyRepairService;
import com.europeandynamics.service.PropertyService;
import com.europeandynamics.service.Impl.PropertyOwnerServiceImpl;
import com.europeandynamics.service.Impl.PropertyRepairServiceImpl;
import com.europeandynamics.service.Impl.PropertyServiceImpl;

import javax.persistence.NoResultException;

public class App {

	public static void main(String[] args) {

		Logger logger = LoggerFactory.logger(App.class);
		PropertyOwnerService propertyOwnerService = new PropertyOwnerServiceImpl(new PropertyOwnerRepositoryImpl());
		PropertyService propertyService = new PropertyServiceImpl(new PropertyRepositoryImpl(),
				new PropertyOwnerRepositoryImpl());
		PropertyRepairService propertyRepairService = new PropertyRepairServiceImpl(new PropertyRepairRepositoryImpl());

		// TESTING PROPERTY REPAIR
//		try {
//			List<PropertyRepair> propertyRepairs = propertyRepairService.propertyRepairsByOwnerVatNumber("111111113");
//			logger.info("property repairs by a certain user: " + propertyRepairs);
//		} catch (ResourceNotFoundException ex) {
//			logger.warn(ex.getMessage() + "| " + ex.getHttpStatus());
//		}
//
//		List<PropertyRepairResponse> allRepairsByDate = propertyRepairService.findAllRepairsByDate(
//				LocalDateTime.of(2022, 9, 20, 14, 30, 20), LocalDateTime.of(2022, 9, 21, 14, 30, 20),
//				PropertyRepair.class);
//		logger.info("repairs by date: ");
//		for (PropertyRepairResponse propertyRepairResponse : allRepairsByDate) {
//			logger.info(propertyRepairResponse + " \n");
//		}
//
//		try {
//			boolean result = propertyRepairService.deleteById("338", PropertyRepair.class);
//			logger.info("property repair deleted " + result);
//		} catch (ResourceNotFoundException ex) {
//			logger.warn(ex.getMessage() + "| " + ex.getHttpStatus());
//		}

		// TESTING PROPERTY REPO
//
//		List<PropertyResponse> properties = propertyService.findAll(Property.class);
//		logger.info("All properties: " + properties);
//
//		List<?> propertiesByVat = propertyService.findPropertiesByOwnerVatNumber("111111115", Property.class);
//		logger.info("properties by vat: " + propertiesByVat);
//		logger.info("property found: " + propertyService.findById("E91116", Property.class));
//
//		try {
//			Property property = propertyService.create(new PropertyRequest("E91119", "Marasliou 20",
//					LocalDate.of(2000, 9, 22), Type.APARTMENT, "111111115"));
//			logger.info("property created: " + property);
//		} catch (BadRequestException ex) {
//			logger.warn(ex.getMessage() + "| " + ex.getHttpStatus());
//		}
//
//		try {
//			propertyService.deleteById("E91115", Property.class);
//			logger.info("property deleted");
//		} catch (ResourceNotFoundException ex) {
//			logger.warn(ex.getMessage() + "| " + ex.getHttpStatus());
//		}
//		try {
//			propertyService.update("E91118",
//					PropertyRequest.builder().address("Mpoumpulinas 30").yearOfConstruction(LocalDate.of(1970, 02, 15))
//							.type(Type.APARTMENT).propertyOwnerId("111111111").build());
//
//			logger.info("property successfully updated");
//		} catch (ResourceNotFoundException ex) {
//			logger.warn(ex.getMessage());
//		}

//		 TESTING PropertyOwner Service
//		List<PropertyOwnerResponse> findAll = propertyOwnerService.findAll(PropertyOwner.class);
//		logger.info("All owners " + findAll);

		List<PropertyOwnerRepairsPaidResponse> eachOwnerSumPaid = new ArrayList<>(propertyOwnerService.amountPaidForRepairsByOwner());
				propertyOwnerService.amountPaidForRepairsByOwner();
			logger.info(eachOwnerSumPaid);

//		try {
//			logger.info(propertyOwnerService.findById("111111112", PropertyOwner.class));
//
//		} catch (ResourceNotFoundException ex) {
//			logger.warn(ex.getMessage() + "| " + ex.getHttpStatus());
//		}
//
//		try {
//			PropertyOwner propertyOwner = propertyOwnerService.findById("1111111", PropertyOwner.class);
//			logger.info("property owner found: " + propertyOwner);
//		} catch (ResourceNotFoundException ex) {
//
//			logger.warn(ex.getMessage() + "| " + ex.getHttpStatus());
//		}

//		try {
//			propertyOwnerService.create(new PropertyOwner("111111120", "Jack", "Jackson", "Athens", "6999999999",
//					"marys@email.com", "username9", "pass9", Role.USER));
//			logger.info("owner created successfully");
//		} catch (BadRequestException | InvalidEmailException ex) {
//			logger.warn(ex.getMessage());
//		}
//
//		try {
//			PropertyOwnerResponse propertyOwnerFoundEmail = propertyOwnerService.findByEmail("emailemail.com");
//			logger.info(" found owner with email " + propertyOwnerFoundEmail);
//		} catch (InvalidEmailException ex) {
//			logger.warn(ex.getMessage() + "| " + ex.getHttpStatus());
//		}
//
//		try {
//			propertyOwnerService.update("111111114",
//					new PropertyOwnerRequest("Agiou Dimitriou 34", "nikou@email.com", "9876541231"));
//			logger.info("owner updated successfully");
//		} catch (ResourceNotFoundException ex) {
//			logger.warn(ex.getMessage() + "| " + ex.getHttpStatus());
//		}
//
//		try {
//			propertyOwnerService.deleteById("111111119", PropertyOwner.class);
//			logger.info("owner with id 111111119 was deleted");
//		} catch (ResourceNotFoundException ex) {
//			logger.warn(ex.getMessage() + "| " + ex.getHttpStatus());
//		}

	}

}
