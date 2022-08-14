package mx.com.yamil.hibernateapp;

import jakarta.persistence.EntityManager;
import mx.com.yamil.hibernateapp.entity.Cliente;
import mx.com.yamil.hibernateapp.entity.Direccion;
import mx.com.yamil.hibernateapp.utilities.JpaUtilities;

public class HibernateAsociacionesOneToManyFind {

	public static void main(String[] args) {
		EntityManager em = JpaUtilities.getEntityManager();
		try {			
			em.getTransaction().begin();
			Cliente cliente = em.find(Cliente.class, 1l);
			Direccion d1 = new Direccion("los olvidos", 1);
			Direccion d2 = new Direccion("fracc. bonanzas", 515);
			cliente.getDirecciones().add(d1);
			cliente.getDirecciones().add(d2);
			em.merge(cliente); //actualiza
			
			em.getTransaction().commit();
			em.getTransaction().begin();
			d2 = em.find(Direccion.class, 2l);
			cliente.getDirecciones().remove(d2);
			em.getTransaction().commit();
		} catch(Exception ex) {
			em.getTransaction().rollback();
			System.out.println(ex.getMessage());
		} finally {
			em.close();
		}
	}

}
