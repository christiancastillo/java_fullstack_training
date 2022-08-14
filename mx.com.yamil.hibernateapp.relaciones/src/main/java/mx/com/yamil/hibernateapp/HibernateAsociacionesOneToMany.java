package mx.com.yamil.hibernateapp;

import jakarta.persistence.EntityManager;
import mx.com.yamil.hibernateapp.entity.Cliente;
import mx.com.yamil.hibernateapp.entity.Direccion;
import mx.com.yamil.hibernateapp.utilities.JpaUtilities;

public class HibernateAsociacionesOneToMany {

	public static void main(String[] args) {
		EntityManager em = JpaUtilities.getEntityManager();
		try {			
			em.getTransaction().begin();
			Cliente cliente = new Cliente("Christian Yamil", "Castillo");
			cliente.setFormaDePago("Debito");
			Direccion d1 = new Direccion("los olvidos", 1);
			Direccion d2 = new Direccion("fracc. bonanzas", 515);
			cliente.getDirecciones().add(d1);
			cliente.getDirecciones().add(d2);
			em.persist(cliente);
			
			em.getTransaction().commit();
			
			System.out.println("ELIMINANDO A CLIENTE==========");
			em.getTransaction().begin();
			cliente = em.find(Cliente.class, cliente.getId());
			cliente.getDirecciones().remove(d1);
			em.getTransaction().commit();
			
		} catch(Exception ex) {
			em.getTransaction().rollback();
			System.out.println(ex.getMessage());
		} finally {
			em.close();
		}

	}

}
