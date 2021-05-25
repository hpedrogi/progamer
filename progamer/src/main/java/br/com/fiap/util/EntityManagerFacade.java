package br.com.fiap.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerFacade {
	
	public static EntityManager get() {
		EntityManagerFactory factory = 
				Persistence.createEntityManagerFactory("progamer-persistence-unit");
		return factory.createEntityManager();
	}

}