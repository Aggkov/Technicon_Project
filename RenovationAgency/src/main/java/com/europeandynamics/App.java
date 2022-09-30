package com.europeandynamics;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.jboss.logging.Logger;

import com.europeandynamics.exceptions.InvalidEmailException;
import com.europeandynamics.exceptions.ResourceNotFoundException;
import com.europeandynamics.model.Property;
import com.europeandynamics.model.PropertyOwner;
import com.europeandynamics.model.PropertyRepair;
import com.europeandynamics.payload.PropertyOwnerResponse;
import com.europeandynamics.payload.PropertyRepairResponse;
import com.europeandynamics.repository.PropertyRepairRepository;
import com.europeandynamics.repository.PropertyRepository;
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
		PropertyService propertyService = new PropertyServiceImpl(new PropertyRepositoryImpl());
		PropertyRepairService propertyRepairService = new PropertyRepairServiceImpl(new PropertyRepairRepositoryImpl());

		// TESTING PROPERTY REPAIR
        PropertyRepairRepository propertyRepairRepository = new PropertyRepairRepositoryImpl();
//
//        List<PropertyRepairResponse> allRepairsByDate = propertyRepairRepository.findAllRepairsByDate(LocalDateTime.of(2022, 9, 20, 14,30,20), LocalDateTime.of(
//                2022, 9, 22, 11,30, 20), PropertyRepair.class);
//        logger.info("repairs by date: ");
//        for(PropertyRepairResponse propertyRepairResponse : allRepairsByDate) {
//             logger.info(propertyRepairResponse + " \n");
//        }
        
//        try {
//            boolean result = propertyRepairService.deleteById("340", PropertyRepair.class);
//            logger.info("property repair deleted " + result);
//        } catch (ResourceNotFoundException ex) {
//        	ex.getMessage();
//		}

		// TESTING PROPERTY REPO

		PropertyRepository propertyRepository = new PropertyRepositoryImpl();
		
//		List<PropertyResponse> properties = propertyService.findAll(Property.class);
//		logger.info("All properties: " + properties);
//		List<?> propertiesByVat = propertyService.findPropertiesByOwnerVatNumber("111111111",//				Property.class);//		logger.info("properties by vat: " + propertiesByVat);
		
//		try {
//			Property property = propertyService.create(new Property("E91121", "New address", LocalDate.of(2017, 9, 22), Type.APARTMENT));
//			logger.info("property created: " + property);
//		} catch (BadRequestException ex) {
//			logger.warn(ex.getMessage());
//		}
		
		/*
		 * try { propertyService.deleteById("E91118", Property.class);
		 * logger.info("property deleted"); } catch(ResourceNotFoundException ex) {
		 * logger.warn(ex.getMessage()); }
		 */

////		 TESTING PropertyOwner Service
//
		List<PropertyOwnerResponse> findAll = propertyOwnerService.findAll(PropertyOwner.class);
		logger.info("All owners " + findAll);
		 
        					;
//        resultList.stream().forEach(System.out::println);		
		
//		try {
//			PropertyOwner propertyOwner = propertyOwnerService.findById("11111111123", PropertyOwner.class);
//			logger.info("property owner found: " + propertyOwner);
//		} catch (ResourceNotFoundException ex) {
//
//			logger.warn(ex.getMessage());
//		}
//		

//
//        try {
//            propertyOwnerService.create(new PropertyOwner("111111118", "Mary", "Private", "Athens", "6999999998",
//                    "email8@email.com", "username8", "pass8", Role.USER));
//            logger.info("owner created successfully");
//
//        } catch (BadRequestException | InvalidEmailException | NoResultException ex) {
//            logger.warn(ex.getMessage());
//        }
//
//        try {
//            PropertyOwnerResponse propertyOwnerFoundEmail = propertyOwnerService.findByEmail("emailemail.com");
//            logger.info(" found owner with email " + propertyOwnerFoundEmail);
//        } catch (InvalidEmailException |
//
//                 NoResultException ex) {
//            logger.warn(ex.getMessage());
//        }
//
//        try {
//            propertyOwnerService.update("111111114",
//                    new PropertyOwnerRequest("UPDATED address", "updated@email.com", "987654"));
//            logger.info("owner updated successfully");
//        } catch (ResourceNotFoundException ex) {
//            logger.warn(ex.getMessage());
//        }
//
//        try {
//            propertyOwnerService.deleteById("111111116", com.europeandynamics.model.PropertyOwner.class);
//            logger.info("owner with id 111111117 was deleted");
//        } catch (ResourceNotFoundException ex) {
//            logger.warn(ex.getMessage());
//        }

		// CREATE
//		em.getTransaction().begin();
//		Property property = new Property("23456790", "address2", LocalDate.of(2022, 9, 22), Type.APARTMENT);
////
//		PropertyOwner propertyOwner = new PropertyOwner("121", "John3", "Johnson3", "address125", "phone3", "email3",
//				"username3", "pass3");
////
//		propertyOwner.addProperty(property);
//
//		em.persist(propertyOwner);
//		em.getTransaction().commit();
//		em.close();

		// READ
//		em.getTransaction().begin();

//		Property foundProperty = em.find(Property.class, "23456790");
//		System.out.println(foundProperty);

		// UPDATE
//		em.getTransaction().begin();
//		Property foundProperty2 = em.find(Property.class, "23456790");
//		foundProperty2.setType(Type.DETACHED);
//
//		em.merge(foundProperty2);
//		em.getTransaction().commit();
//		em.close();

		// READ ALL
//		List<Property> properties = em.createQuery("SELECT property from Property property").getResultList();
//
//		System.out.println(properties);

//			      .setParameter(1, "English")

	}

}
