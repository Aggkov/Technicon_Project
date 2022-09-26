package com.europeandynamics;

import com.europeandynamics.exceptions.BadRequestException;
import com.europeandynamics.exceptions.ResourceNotFoundException;
import com.europeandynamics.model.PropertyOwner;
import com.europeandynamics.model.enums.Role;
import com.europeandynamics.payload.PropertyOwnerRequest;
import com.europeandynamics.repository.Impl.PropertyOwnerRepositoryImpl;
import com.europeandynamics.service.Impl.PropertyOwnerServiceImpl;
import com.europeandynamics.service.PropertyOwnerService;

public class App {

	public static void main(String[] args) {

//
//		EntityManagerFactory emf = EntityManagerFactoryUtils.getEntityManagerFactory();
//		EntityManager em = emf.createEntityManager();

		PropertyOwnerService propertyOwnerService = new PropertyOwnerServiceImpl(new PropertyOwnerRepositoryImpl());
		// FINDBYID
		try {
			System.out.println(propertyOwnerService.findById("2313124", PropertyOwner.class));

		} catch (RuntimeException ex) {
			System.out.println(ex.getMessage());
		}
		try {
			System.out.println(propertyOwnerService.findByEmail("2113123123"));
		}
		catch (ResourceNotFoundException ex) {
			System.out.println(ex.getMessage());
		}

		// CREATE
		try {
			propertyOwnerService.create(new PropertyOwner("111111111", "Kostas", "Chatzifotiadis", "Thessaloniki",
					"6999999991", "email1@email.com", "username1", "pass1", Role.ADMIN));
		}catch (BadRequestException ex) {
			System.out.println(ex.getMessage());
		}
		// UPDATE
		try {
			propertyOwnerService.update("111111115", new PropertyOwnerRequest("updated address",
					"updated email", "updated password"));
		} catch (ResourceNotFoundException ex) {
			System.out.println(ex.getMessage());
		}

		/*
		 * PropertyOwnerRepository prop = new PropertyOwnerRepositoryImpl();
		 * System.out.println(prop.findAll(PropertyOwner.class));
		 */

//		System.out.println(PropertyOwner.class.getSimpleName());


//		PropertyOwnerRepository prop = new PropertyOwnerRepositoryImpl();
//		System.out.println(prop.findAll(PropertyOwner.class));
//
//
//		prop = new PropertyOwnerRepositoryImpl();
//		System.out.println(prop.findAll(PropertyOwner.class));

//		EntityManagerFactory emf = EntityManagerFactoryUtils.getEntityManagerFactory();
//		EntityManager em = emf.createEntityManager();

//		PropertyOwnerRepository prop = new PropertyOwnerRepositoryImpl();

//		System.out.println(PropertyOwner.class.getSimpleName());

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
