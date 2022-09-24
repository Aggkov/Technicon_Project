package com.europeandynamics;

import java.time.LocalDate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import com.europeandynamics.model.Property;
import com.europeandynamics.model.enums.Type;
import com.europeandynamics.utils.EntityManagerFactoryUtils;

public class DummyData {

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
		  
		  Property property4 = new Property("E91115", "", LocalDate.of(2022,
				  9, 12), Type.APARTMENT);
		
		
	}

}
