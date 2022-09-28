package com.europeandynamics;

import com.europeandynamics.model.Property;
import com.europeandynamics.payload.PropertyResponse;
import com.europeandynamics.repository.Impl.PropertyOwnerRepositoryImpl;
import com.europeandynamics.repository.Impl.PropertyRepositoryImpl;
import com.europeandynamics.repository.PropertyRepository;
import com.europeandynamics.service.Impl.PropertyOwnerServiceImpl;
import com.europeandynamics.service.PropertyOwnerService;
import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.jboss.logging.Logger;

import java.util.List;

public class App {

    public static void main(String[] args) {

        Logger logger = LoggerFactory.logger(App.class);

        // TESTING PROPERTY REPO
		PropertyRepository propertyRepository = new PropertyRepositoryImpl();
//		@SuppressWarnings("unchecked")
//		List<Property> properties = (List<Property>) propertyRepository.findAll(Property.class);
//		logger.info("All properties: " + properties);

		List<PropertyResponse> propertiesByVat = propertyRepository.findPropertiesByOwnerVatNumber("111111111", Property.class);
		logger.info("properties by vat: " + propertiesByVat);

//		 TESTING PropertyOwner Service
        PropertyOwnerService propertyOwnerService = new PropertyOwnerServiceImpl(new PropertyOwnerRepositoryImpl());

//        try {
//            PropertyOwner propertyOwner = propertyOwnerService.findById("111111111", PropertyOwner.class);
//            logger.info("property owner found: " + propertyOwner);
//        } catch (ResourceNotFoundException ex) {
//
//            logger.warn(ex.getMessage());
//        }
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
//            PropertyOwner propertyOwnerFoundEmail = propertyOwnerService.findByEmail("email@email.com");
//            logger.info(" found owner with email " + propertyOwnerFoundEmail);
//        } catch (ResourceNotFoundException | InvalidEmailException |
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

