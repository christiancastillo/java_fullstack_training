package mx.com.yamil.hibernateapp;

import java.util.List;

import jakarta.persistence.EntityManager;
import mx.com.yamil.hibernateapp.entity.Cliente;
import mx.com.yamil.hibernateapp.utilities.JpaUtilities;

public class HibernateListar {
	public static void main(String[] args) {
		EntityManager em = JpaUtilities.getEntityManager();
		List<Cliente> clientes = em.createQuery("Select c from Cliente c").getResultList(); //funcion para hacer queries en la BD desde Hibernate; selecciona todos los objetos C de la clase Cliente
		clientes.forEach(c -> System.out.println(c));
		em.close();
	}
}
