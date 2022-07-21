package mx.com.yamil.hibernateapp;

import java.util.List;

import jakarta.persistence.EntityManager;
import mx.com.yamil.hibernateapp.entity.Cliente;
import mx.com.yamil.hibernateapp.utilities.JpaUtilities;
import mx.com.yamil.hibernateapp.vo.ClienteVO;

public class HibernateQL {
	public static void main(String[] args) {
		EntityManager em = JpaUtilities.getEntityManager();
		
		System.out.println("==== CONSULTAR TODOS LOS REGISTROS ====");
		List<Cliente> clientes = em.createQuery("SELECT c FROM Cliente c", Cliente.class).getResultList();
		clientes.forEach(c -> {System.out.println(c);});
		
		//Devolver un solo objeto
		System.out.println("==== CONSULTA POR ID ====");
//		Cliente cliente = em.createQuery("Select c FROM Cliente c WHERE c.id=?1");
		Cliente cliente = em.createQuery("SELECT c FROM Cliente c WHERE c.id=:idCli",Cliente.class)
				.setParameter("idCli",1l)
				.getSingleResult();
		System.out.println(cliente);
		System.out.println("CONSULTA SOLO EL NOMBRE POR ID");
		//se ingresa el itpo de dato que esta en el entity
		String nombreCliente = em.createQuery("SELECT c.apellido FROM Cliente c WHERE c.id=:id",String.class)
				.setParameter("id", 2l)
				.getSingleResult();
		
		System.out.println(nombreCliente);
		System.out.println("CONSULTA POR CAMPO PERSONALIZADO");
		Object[] objCliente = em.createQuery("SELECT c.id, c.nombre, c.apellido FROM Cliente c WHERE c.id=:id",Object[].class)
				.setParameter("id", 2l)
				.getSingleResult();
		Long id = (Long) objCliente[0];
		String nombre = (String) objCliente[1];
		String apellido = (String) objCliente[2];
		System.out.println("Id: "+id+" \n nombre: "+nombre+"\n apellido: "+apellido);
		
		System.out.println("CONSULTA POR CAMPO PERSONALIZADO (LIST)");
		List<Object[]> clientesList = em.createQuery("SELECT c.id, c.nombre, c.apellido FROM Cliente c",Object[].class).getResultList(); 		
		for(Object[] reg : clientesList) {
			id = (Long) reg[0];
			nombre = (String) reg[1];
			apellido = (String) reg[2];
			System.out.println("Id: "+id+" \n nombre: "+nombre+"\n apellido: "+apellido);
		}
		
		System.out.println("CONSULTA POR CLIENTE Y POR FORMA DE PAGO");
		clientesList = em.createQuery("SELECT c, c.formaDePago FROM Cliente c", Object[].class)
				.getResultList();
		
		clientesList.forEach(c -> {
			Cliente cli = (Cliente) c[0];
			String formaPago = (String) c[1];
			System.out.println("formaPago: "+formaPago+" \n cliente: "+cli);
		});
		
		System.out.println("CONSULTA QUE DEVUELVA OBJETO DE CLASE ENTITY");
		clientes = em.createQuery("Select new Cliente(c.nombre, c.apellido) from Cliente c",Cliente.class).getResultList();
		clientes.forEach(c -> System.out.println(c));
		
		System.out.println("CONSULTA QUE DEVUELVA OBJETO DE CLASE VO");
		List<ClienteVO>clientesVO = em.createQuery("Select new mx.com.yamil.hibernateapp.vo.ClienteVO(c.nombre, c.apellido) from Cliente c",ClienteVO.class).getResultList();
		clientesVO.forEach(c -> System.out.println(c));
		
		System.out.println("CONSULTA QUE DEVUELVA NOMBRES");
		List<String> nombres = em.createQuery("SELECT c.nombre FROM Cliente c",String.class)
				.getResultList();
		nombres.forEach(c -> System.out.println(c));
		
		
		System.out.println("CONSULTA QUE DEVUELVA NOMBRES DISTINTOS");
		nombres = em.createQuery("SELECT DISTINCT(c.nombre) from Cliente c", String.class)
				.getResultList();
		nombres.forEach(c -> System.out.println(c));
		
		System.out.println("CONSULTA QUE DEVUELVA FORMA DE PAGO UNICA");
		List<String> formasPago = em.createQuery("SELECT distinct(c.formaDePago) from Cliente c",String.class).getResultList();
		formasPago.forEach(c -> System.out.println(c));
		
		System.out.println("CONSULTA QUE cuenta FORMA DE PAGO UNICA");
		long totalFormasPago = em.createQuery("SELECT count(distinct(c.formaDePago)) from Cliente c",Long.class).getSingleResult();
		System.out.println(totalFormasPago);
		
		System.out.println("CONSULTA CON NOMBRES Y APELLIDO CONCATENADOS");
//		nombres = em.createQuery("SELECT concat(c.nombre, c.apellido) FROM Cliente c",String.class).getResultList();
		nombres = em.createQuery("SELECT c.nombre || ' ' || c.apellido FROM Cliente c",String.class).getResultList();
		nombres.forEach(c -> System.out.println(c));
		
		System.out.println("CONSULTA CONVIERTE A MAYUSCULAS");
		nombres = em.createQuery("SELECT upper(concat(c.nombre, c.apellido)) FROM Cliente c",String.class).getResultList();
		nombres.forEach(c -> System.out.println(c));
		
		
		System.out.println("CONSULTA PARA BUSCAR POR NOMBRE");
		String par = "AF";
		clientes = em.createQuery("SELECT c from Cliente c where upper(c.nombre) like upper(:parametro)", Cliente.class)
				.setParameter("parametro", "%"+par+"%")
				.getResultList();
		clientes.forEach(c -> System.out.println(c));
		
		em.close();
	}	
}
