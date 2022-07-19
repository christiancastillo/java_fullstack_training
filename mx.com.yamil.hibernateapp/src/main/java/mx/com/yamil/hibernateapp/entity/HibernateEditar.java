package mx.com.yamil.hibernateapp.entity;

import javax.swing.JOptionPane;

import jakarta.persistence.EntityManager;
import mx.com.yamil.hibernateapp.utilities.JpaUtilities;

public class HibernateEditar {
	public static void main (String []args) {
		EntityManager em = JpaUtilities.getEntityManager();
		try {
			em.getTransaction().begin();
			
			long id = Long.valueOf(JOptionPane.showInputDialog("Ingrese el ID del cliente a modificar: "));
			Cliente c = em.find(Cliente.class,id); //hace la busqueda por ID
			String nombre = JOptionPane.showInputDialog("Ingrese el nombre: ",c.getNombre());
			String apellido = JOptionPane.showInputDialog("Ingrese el apellido: ",c.getApellido());
			String pago = JOptionPane.showInputDialog("Ingrese la forma de pago: ",c.getFormaDePago());
			
			c.setNombre(nombre);
			c.setApellido(apellido);
			c.setFormaDePago(pago);
			
			em.merge(c); //merge es el metodo para actualizar
			em.getTransaction().commit();
				
			System.out.println(c);			
		} catch(Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			em.close();
		}
	}
}
