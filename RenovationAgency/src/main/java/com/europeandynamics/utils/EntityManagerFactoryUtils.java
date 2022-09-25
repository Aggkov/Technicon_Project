package com.europeandynamics.utils;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerFactoryUtils {

	public static EntityManagerFactory getEntityManagerFactory() {
		return Persistence.createEntityManagerFactory("NewPersistenceUnit");
	}

}
