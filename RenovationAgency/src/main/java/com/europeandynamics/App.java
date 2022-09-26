package com.europeandynamics;

import java.time.LocalDate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import com.europeandynamics.model.Property;
import com.europeandynamics.model.PropertyOwner;
<<<<<<< HEAD
import com.europeandynamics.model.enums.Type;
=======
import com.europeandynamics.model.enums.Role;
>>>>>>> 48c7f6c56b36d93a5476ef1b23a05d4fbfd7c260
import com.europeandynamics.repository.PropertyOwnerRepository;
import com.europeandynamics.repository.Impl.PropertyOwnerRepositoryImpl;
import com.europeandynamics.utils.EntityManagerFactoryUtils;

public class App {

	public static void main(String[] args) {

<<<<<<< HEAD
		EntityManagerFactory emf = EntityManagerFactoryUtils.getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();

		/*
		 * PropertyOwnerRepository prop = new PropertyOwnerRepositoryImpl();
		 * System.out.println(prop.findAll(PropertyOwner.class));
		 */

//		System.out.println(PropertyOwner.class.getSimpleName());

		 //CREATE
		em.getTransaction().begin();
		
		  Property property = new Property("E91111", "Melinas Merkouri 31", LocalDate.of(2022,
				  9, 22), Type.APARTMENT);
				 
				  Property property1 = new Property("E91112", "Kolokotroni 52", LocalDate.of(2022,
						  9, 26), Type.MAISONETTE);
				  
				  Property property2 = new Property("E91113", "Karakatsani 59", LocalDate.of(2022,
						  10, 31), Type.DETACHED);
				
				  Property property3 = new Property("E91114", "Veikou 22", LocalDate.of(2022,
						  1, 20 ), Type.MAISONETTE);
				  
				  Property property4 = new Property("E91115", "Mpoumpoulina 21", LocalDate.of(2022,
						  9, 12), Type.APARTMENT);
		 
				  PropertyOwner propertyOwner = new PropertyOwner("111111111", "Kostas", "Chatzifotiadis", "Thessaloniki",
							"6999999991", "email1@email.com", "username3", "pass3");
					PropertyOwner propertyOwner1 = new PropertyOwner("111111112", "Aggelos", "Kovatsis", "Athens", "6999999992",
							"email2@email.com", "username3", "pass3");
					PropertyOwner propertyOwner2 = new PropertyOwner("111111113", "Alex", "Johnson", "Xanthi", "6999999993",
							"email3@email.com", "username3", "pass3");
					PropertyOwner propertyOwner3 = new PropertyOwner("111111114", "Lefteris", "Papageorgiou", "Kavala",
							"6999999994", "email4@email.com", "username3", "pass3");
					PropertyOwner propertyOwner4 = new PropertyOwner("111111115", "Nikos", "Matzablokos", "Larisa", "6999999995",
							"email5@email.com", "username3", "pass3");

		
		
		

		propertyOwner.addProperty(property);
		propertyOwner.addProperty(property1);
		propertyOwner.addProperty(property2);
		propertyOwner.addProperty(property3);
		propertyOwner.addProperty(property4);

		em.persist(propertyOwner);
		em.persist(propertyOwner1);
		em.persist(propertyOwner2);
		em.persist(propertyOwner3);
		em.persist(propertyOwner4);
		em.getTransaction().commit();
		em.close();
=======
		PropertyOwnerRepository prop = new PropertyOwnerRepositoryImpl();
		System.out.println(prop.findAll(PropertyOwner.class));

		prop.create(new PropertyOwner("111111116", "John", "Johnson", "Athens", "6999999996", "john@email.com",
				"username6", "pass6", Role.PROPERTY_OWNER));

		prop = new PropertyOwnerRepositoryImpl();
		System.out.println(prop.findAll(PropertyOwner.class));

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
>>>>>>> 48c7f6c56b36d93a5476ef1b23a05d4fbfd7c260

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
