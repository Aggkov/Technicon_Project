package com.europeandynamics;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.europeandynamics.model.Property;
import com.europeandynamics.model.PropertyOwner;
import com.europeandynamics.model.PropertyRepair;
import com.europeandynamics.model.enums.RepairStatus;
import com.europeandynamics.model.enums.RepairType;
import com.europeandynamics.model.enums.Role;
import com.europeandynamics.model.enums.Type;

public class DummyData {

	public static void main(String[] args) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Persistence");
		EntityManager em = emf.createEntityManager();

		// CREATE
		em.getTransaction().begin();

		Property property1 = new Property("E91111", "Melinas Merkouri 31", LocalDate.of(2020, 9, 22), Type.APARTMENT);

		Property property2 = new Property("E91112", "Kolokotroni 52", LocalDate.of(2020, 9, 26), Type.MAISONETTE);

		Property property3 = new Property("E91113", "Karakatsani 59", LocalDate.of(2020, 10, 31), Type.DETACHED);

		Property property4 = new Property("E91114", "Veikou 22", LocalDate.of(2020, 1, 20), Type.MAISONETTE);

		Property property5 = new Property("E91115", "Kifisias 10", LocalDate.of(2020, 9, 12), Type.APARTMENT);

		Property property6 = new Property("E91116", "Dimokratias 15", LocalDate.of(2018, 9, 12), Type.APARTMENT);

		Property property7 = new Property("E91117", "Kalirois 36", LocalDate.of(2018, 9, 12), Type.APARTMENT);

		Property property8 = new Property("E91118", "Egnatias 36", LocalDate.of(2015, 9, 12), Type.MAISONETTE);

		PropertyOwner propertyOwner1 = new PropertyOwner("111111111", "Kostas", "Chatzifotiadis", "Thessaloniki",
				"6999999991", "email1@email.com", "username1", "pass1", Role.ADMIN);
		PropertyOwner propertyOwner2 = new PropertyOwner("111111112", "Aggelos", "Kovatsis", "Athens", "6999999992",
				"email2@email.com", "username2", "pass2", Role.ADMIN);
		PropertyOwner propertyOwner3 = new PropertyOwner("111111113", "Alex", "Johnson", "Xanthi", "6999999993",
				"email3@email.com", "username3", "pass3", Role.PROPERTY_OWNER);
		PropertyOwner propertyOwner4 = new PropertyOwner("111111114", "Lefteris", "Papageorgiou", "Kavala",
				"6999999994", "email4@email.com", "username4", "pass4", Role.ADMIN);
		PropertyOwner propertyOwner5 = new PropertyOwner("111111115", "Nikos", "Nikolaou", "Larisa", "6999999995",
				"email5@email.com", "username5", "pass5", Role.CUSTOMER);

		PropertyRepair propertyRepair1 = new PropertyRepair("333", LocalDateTime.of(2022, 9, 20, 14, 30, 20),
				"Short Description", "Long text Description", RepairType.ELECTRICAL_WORK, RepairStatus.IN_PROGRESS,
				BigDecimal.valueOf(200.00));
		PropertyRepair propertyRepair2 = new PropertyRepair("334", LocalDateTime.of(2022, 9, 21, 14, 30, 20),
				"Short Description", "Long text Description", RepairType.ELECTRICAL_WORK, RepairStatus.IN_PROGRESS,
				BigDecimal.valueOf(299.00));
		PropertyRepair propertyRepair3 = new PropertyRepair("335", LocalDateTime.of(2022, 9, 21, 16, 30, 20),
				"Short Description", "Long text Description", RepairType.FRAMES, RepairStatus.DEFAULT_STANDBY, BigDecimal.valueOf(400.00));
		PropertyRepair propertyRepair4 = new PropertyRepair("336", LocalDateTime.of(2022, 9, 22, 10, 30, 20),
				"Short Description", "Long text Description", RepairType.INSULATION, RepairStatus.PENDING, BigDecimal.valueOf(300.00));
		PropertyRepair propertyRepair5 = new PropertyRepair("337", LocalDateTime.of(2022, 9, 22, 11, 30, 20),
				"Short Description", "Long text Description", RepairType.PAINTING, RepairStatus.COMPLETE, BigDecimal.valueOf(150.00));
		PropertyRepair propertyRepair6 = new PropertyRepair("338", LocalDateTime.of(2022, 9, 23, 12, 30, 20),
				"Short Description", "Long text Description", RepairType.PLUMBING, RepairStatus.COMPLETE, BigDecimal.valueOf(179.00));
		PropertyRepair propertyRepair7 = new PropertyRepair("339", LocalDateTime.of(2022, 9, 23, 19, 30, 20),
				"Short Description", "Long text Description", RepairType.PLUMBING, RepairStatus.COMPLETE, BigDecimal.valueOf(120.00));
		PropertyRepair propertyRepair8 = new PropertyRepair("340", LocalDateTime.of(2022, 9, 23, 13, 30, 20),
				"Short Description", "Long text Description", RepairType.PLUMBING, RepairStatus.COMPLETE, BigDecimal.valueOf(180.00));

		// Owner 1
		propertyOwner1.addProperty(property1);
		propertyOwner1.addProperty(property2);
		propertyOwner1.addProperty(property8);

		property1.addPropertyRepair(propertyRepair1);
		property1.addPropertyRepair(propertyRepair2);

		propertyOwner1.addPropertyRepair(propertyRepair1);
		propertyOwner1.addPropertyRepair(propertyRepair2);

		// Owner 2
		propertyOwner2.addProperty(property3);

		property3.addPropertyRepair(propertyRepair3);
		propertyOwner2.addPropertyRepair(propertyRepair3);

		// Owner 3
		propertyOwner3.addProperty(property4);
		propertyOwner3.addProperty(property7);

		property4.addPropertyRepair(propertyRepair4);
		property7.addPropertyRepair(propertyRepair5);
		property7.addPropertyRepair(propertyRepair6);
		property7.addPropertyRepair(propertyRepair7);

		propertyOwner3.addPropertyRepair(propertyRepair4);
		propertyOwner3.addPropertyRepair(propertyRepair5);
		propertyOwner3.addPropertyRepair(propertyRepair6);
		propertyOwner3.addPropertyRepair(propertyRepair7);

		// Owner 4
		propertyOwner4.addProperty(property5);

		property5.addPropertyRepair(propertyRepair8);
		propertyOwner4.addPropertyRepair(propertyRepair8);

		// Owner 5
		propertyOwner5.addProperty(property6);

		em.persist(propertyOwner1);
		em.persist(propertyOwner2);
		em.persist(propertyOwner3);
		em.persist(propertyOwner4);
		em.persist(propertyOwner5);

		em.getTransaction().commit();
		em.close();
		
		emf.close();

	}

}
