package com.europeandynamics;

import java.time.LocalDate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import com.europeandynamics.model.Property;
import com.europeandynamics.model.PropertyOwner;

import com.europeandynamics.model.enums.Type;

import com.europeandynamics.model.enums.Role;

import com.europeandynamics.repository.PropertyOwnerRepository;
import com.europeandynamics.repository.Impl.PropertyOwnerRepositoryImpl;
import com.europeandynamics.utils.EntityManagerFactoryUtils;

public class App {

	public static void main(String[] args) {


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
		 
		
		
		


		
		em.getTransaction().commit();
		em.close();

		PropertyOwnerRepository prop = new PropertyOwnerRepositoryImpl();
		System.out.println(prop.findAll(PropertyOwner.class));

	
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
