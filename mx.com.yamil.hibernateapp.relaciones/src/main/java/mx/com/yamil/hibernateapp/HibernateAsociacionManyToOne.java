package mx.com.yamil.hibernateapp;

import jakarta.persistence.EntityManager;
import mx.com.yamil.hibernateapp.entity.Cliente;
import mx.com.yamil.hibernateapp.entity.Factura;
import mx.com.yamil.hibernateapp.utilities.JpaUtilities;

public class HibernateAsociacionManyToOne {
	public static void main(String[] args) {
		EntityManager em = JpaUtilities.getEntityManager();
		
		try {
			em.getTransaction().begin();
			
			Cliente cliente = new Cliente("Cata","Edu");
			cliente.setFormaDePago("Paypal");
			em.persist(cliente);
			Factura factura = new Factura("compra de oficina",1000l);
			factura.setCliente(cliente); //se pasa un objeto que exista, no uno nuevo
			em.persist(factura);
			
			
			em.getTransaction().commit();
		} catch (Exception e){
			em.getTransaction().rollback();
			System.out.println(e.getMessage());
		} finally {
			em.close();
		}
	}
}
