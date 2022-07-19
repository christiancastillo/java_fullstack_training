package mx.com.yamil.hibernateapp.entity;

import javax.swing.JOptionPane;

import jakarta.persistence.EntityManager;
import mx.com.yamil.hibernateapp.utilities.JpaUtilities;

public class HibernateCrear {
	public static void main(String[] args) {
		
		EntityManager em = JpaUtilities.getEntityManager();
		try {
			String nombre = JOptionPane.showInputDialog("Ingrese nombre: ");
			String apellido = JOptionPane.showInputDialog("Ingrese apellido: ");
			String pago = JOptionPane.showInputDialog("Ingrese forma de pago: ");
			
			em.getTransaction().begin();
			Cliente c = new Cliente();
			c.setNombre(nombre);
			c.setApellido(apellido);
			c.setFormaDePago(pago);
			em.persist(c);
			
			System.out.println("El id generado es... "+c.getId());
			c = em.find(Cliente.class, c.getId());
			System.out.println(c);
			
			
			em.getTransaction().commit();
		}catch(Exception ex) {
			em.getTransaction().rollback();
			ex.printStackTrace();
		}finally {
			em.close();
		}
	}	
}
