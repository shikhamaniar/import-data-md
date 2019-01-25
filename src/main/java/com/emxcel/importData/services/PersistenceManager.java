package com.emxcel.importData.services;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class PersistenceManager {
	
	private final EntityManagerFactory emf;
	private static final String PERSISTENCE_UNIT="import-data";

	public PersistenceManager() {
		emf=Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);

		// TODO Auto-generated constructor stub
	}

	public EntityManager getEntityManager() {
		// TODO Auto-generated method stub
		return emf.createEntityManager();
	}

	public void close() {
		// TODO Auto-generated method stub
		emf.close();
	}
}
