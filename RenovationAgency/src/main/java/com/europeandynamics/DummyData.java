package com.europeandynamics;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import com.europeandynamics.model.Property;
import com.europeandynamics.model.PropertyOwner;
import com.europeandynamics.model.PropertyRepair;
import com.europeandynamics.model.enums.RepairStatus;
import com.europeandynamics.model.enums.RepairType;
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

		Property property = new Property("E91111", "Melinas Merkouri 31", LocalDate.of(2020, 9, 22), Type.APARTMENT);

		Property property1 = new Property("E91112", "Kolokotroni 52", LocalDate.of(2020, 9, 26), Type.MAISONETTE);

		Property property2 = new Property("E91113", "Karakatsani 59", LocalDate.of(2020, 10, 31), Type.DETACHED);

		Property property3 = new Property("E91114", "Veikou 22", LocalDate.of(2020, 1, 20), Type.MAISONETTE);

		Property property4 = new Property("E91115", "Kifisias 10", LocalDate.of(2020, 9, 12), Type.APARTMENT);

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

		PropertyRepair propertyRepair = new PropertyRepair("333", LocalDateTime.of(2022, 9, 20, 14, 30, 20),
				"Short Description", "Long text Description", RepairType.ELECTRICAL_WORK, RepairStatus.IN_PROGRESS,
				200.00);
		PropertyRepair propertyRepair2 = new PropertyRepair("334", LocalDateTime.of(2022, 9, 21, 14, 30, 20),
				"Short Description", "Long text Description", RepairType.ELECTRICAL_WORK, RepairStatus.IN_PROGRESS,
				299.00);
		PropertyRepair propertyRepair3 = new PropertyRepair("335", LocalDateTime.of(2022, 9, 21, 16, 30, 20),
				"Short Description", "Long text Description", RepairType.FRAMES, RepairStatus.DEFAULT_STANDBY, 400.00);
		PropertyRepair propertyRepair4 = new PropertyRepair("336", LocalDateTime.of(2022, 9, 22, 10, 30, 20),
				"Short Description", "Long text Description", RepairType.INSULATION, RepairStatus.PENDING, 300.00);
		PropertyRepair propertyRepair5 = new PropertyRepair("337", LocalDateTime.of(2022, 9, 22, 11, 30, 20),
				"Short Description", "Long text Description", RepairType.PAINTING, RepairStatus.COMPLETE, 150.00);
		PropertyRepair propertyRepair6 = new PropertyRepair("338", LocalDateTime.of(2022, 9, 23, 12, 30, 20),
				"Short Description", "Long text Description", RepairType.PLUMBING, RepairStatus.COMPLETE, 179.00);
		PropertyRepair propertyRepair7 = new PropertyRepair("339", LocalDateTime.of(2022, 9, 23, 19, 30, 20),
				"Short Description", "Long text Description", RepairType.PLUMBING, RepairStatus.COMPLETE, 120.00);
		PropertyRepair propertyRepair8 = new PropertyRepair("340", LocalDateTime.of(2022, 9, 23, 13, 30, 20),
				"Short Description", "Long text Description", RepairType.PLUMBING, RepairStatus.COMPLETE, 180.00);

	}

}
