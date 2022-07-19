package mx.com.yamil.hibernateapp.entity;

import java.util.List;
import java.util.Scanner;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import mx.com.yamil.hibernateapp.utilities.JpaUtilities;

public class HibernateListarWhere {
	public static void main(String[]args) {
		EntityManager em = JpaUtilities.getEntityManager();
		Scanner s = new Scanner(System.in);
		String pago = s.next();
		Query query = em.createQuery("select c from Cliente c where c.formaDePago =?1", Cliente.class); //parametros
//		query.setMaxResults(1); //indica el maximo num de registros recuperados
		query.setParameter(1, pago);
	
//		Cliente c = (Cliente) query.getSingleResult(); //devuelve tipo Object, si hay mas resultados entonces usar getresultlists
		List<Cliente> c = query.getResultList();
		System.out.println(c);
		em.close();
	}
}
