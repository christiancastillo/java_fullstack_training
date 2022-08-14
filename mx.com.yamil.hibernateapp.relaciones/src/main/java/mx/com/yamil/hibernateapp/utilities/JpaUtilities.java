package mx.com.yamil.hibernateapp.utilities;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;


public class JpaUtilities {
	private static final String entityUnitEjemploJPA = "ejemploJPA";
	private static final EntityManagerFactory entityManagerFactory = buildEntityManagerFactory(); //Solo se puede inicializar una sola vez
	 
	private static EntityManagerFactory buildEntityManagerFactory() {
		return Persistence.createEntityManagerFactory(entityUnitEjemploJPA); //Patron Singleton
	}
	
	public static EntityManager getEntityManager() {
		return entityManagerFactory.createEntityManager();
	}
}
