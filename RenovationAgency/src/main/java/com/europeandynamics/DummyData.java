package com.europeandynamics;

import java.time.LocalDate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import com.europeandynamics.model.Property;
import com.europeandynamics.model.PropertyOwner;
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

		// CREATE
		em.getTransaction().begin();

		Property property = new Property("E91111", "Melinas Merkouri 31", LocalDate.of(2022, 9, 22), Type.APARTMENT);

		Property property1 = new Property("E91112", "Kolokotroni 52", LocalDate.of(2022, 9, 26), Type.MAISONETTE);

		Property property2 = new Property("E91113", "Karakatsani 59", LocalDate.of(2022, 10, 31), Type.DETACHED);

		Property property3 = new Property("E91114", "Veikou 22", LocalDate.of(2022, 1, 20), Type.MAISONETTE);

		Property property4 = new Property("E91115", "", LocalDate.of(2022, 9, 12), Type.APARTMENT);

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

	}

}
