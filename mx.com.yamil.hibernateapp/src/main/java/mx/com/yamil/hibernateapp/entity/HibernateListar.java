package mx.com.yamil.hibernateapp.entity;

import java.util.List;

import jakarta.persistence.EntityManager;
import mx.com.yamil.hibernateapp.utilities.JpaUtilities;

public class HibernateListar {
//Sentencia HQL Select
	public static void main(String [] args) {
		//https://www.udemy.com/course/master-completo-java-de-cero-a-experto/learn/lecture/28434306#overview 06:12
		EntityManager em =JpaUtilities.getEntityManager();
		List<Cliente> clientes = em.createQuery("SELECT c FROM Cliente c",Cliente.class).getResultList(); //Devuelve todos los objetos C de la clase Cliente
		clientes.forEach(c -> System.out.println(c));
		em.close();
	}
	
	
}
