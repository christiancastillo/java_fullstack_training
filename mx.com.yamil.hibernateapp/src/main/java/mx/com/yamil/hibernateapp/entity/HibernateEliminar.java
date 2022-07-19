package mx.com.yamil.hibernateapp.entity;

import java.util.Scanner;

import jakarta.persistence.EntityManager;
import mx.com.yamil.hibernateapp.utilities.JpaUtilities;

public class HibernateEliminar {
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		System.out.println("Ingrese ID: ");
		long id = s.nextLong();
		EntityManager em = JpaUtilities.getEntityManager();
		
		try {
			Cliente cliente = em.find(Cliente.class, id);
			
			em.getTransaction().begin();
			em.remove(cliente); //elimina entidad, tiene que ser manejado por contexto JPA
			em.getTransaction().commit();
		} catch(Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			em.close();
		}
	}
}
