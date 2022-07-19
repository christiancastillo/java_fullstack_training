package mx.com.yamil.hibernateapp.entity;

import java.util.List;
import java.util.Scanner;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import mx.com.yamil.hibernateapp.utilities.JpaUtilities;

public class HibernatePorId {
	public static void main(String[] args) {
		EntityManager em = JpaUtilities.getEntityManager();
		Scanner s = new Scanner(System.in);
		long id = s.nextLong();
//		Query query = em.createQuery("select c from Cliente c where c.id =?1", Cliente.class); //parametros //forma 1
		Cliente cliente = em.find(Cliente.class, id);
		System.out.println(cliente);		
//		query.setParameter(1, id); //forma 1

//		Cliente c = (Cliente) query.getSingleResult(); //devuelve tipo Object, si hay mas resultados entonces usar getresultlists
//		List<Cliente> c = query.getResultList(); //forma 1
		System.out.println(cliente);
		em.close();
		
	}
}
