package mx.com.yamil.hibernateapp;

import java.util.Arrays;
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
		
		System.out.println("CONSULTAS POR RANGOS");
//		clientes = em.createQuery("SELECT c FROM Cliente c WHERE c.id BETWEEN 2 AND 3",Cliente.class).getResultList();
		clientes = em.createQuery("SELECT c FROM Cliente c WHERE c.nombre BETWEEN 'J' AND 'Q'",Cliente.class).getResultList();
		clientes.forEach(c -> System.out.println(c));
		System.out.println("CONSULTAS CON ORDEN");
		clientes = em.createQuery("select c from Cliente c order by c.nombre desc",Cliente.class).getResultList();
		clientes.forEach(c -> System.out.println(c));
		
		System.out.println("TOTAL DE REGISTROS EN LA TABLA");
		long totalReg = em.createQuery("SELECT count(c) as total from Cliente c",Long.class).getSingleResult();
		System.out.println(totalReg);
		
		System.out.println("CONSULTAR ID MINIMO EN LA TABLA");
		long minId = em.createQuery("SELECT MIN(c.id) AS minimo FROM Cliente c",Long.class).getSingleResult();
		System.out.println(minId);
		
		System.out.println("CONSULTAR ID MAX EN LA TABLA");
		long maxId = em.createQuery("SELECT MAX(c.id) AS maximo FROM Cliente c",Long.class).getSingleResult();
		System.out.println(maxId);
		
		System.out.println("CONSULTAR POR NOMBRE Y SU LONGITUD");
		clientesList = em.createQuery("select c.nombre , length(c.nombre) from Cliente c", Object[].class).getResultList();
		clientesList.forEach(c -> {
			String nom = (String) c[0];
			int largo=(int) c[1];
			System.out.println("nombre: "+nom+" long: "+largo);
		});
		
		System.out.println("Consulta por el nombre mas corto");
		int minLargoNombre = em.createQuery("SELECT MIN(length(c.nombre)) from Cliente c", Integer.class).getSingleResult();
		System.out.println(minLargoNombre);
		
		System.out.println("Consulta por el nombre mas largo");
		int maxLargoNombre = em.createQuery("SELECT MAX(LENGTH(c.nombre)) FROM Cliente c", Integer.class).getSingleResult();
		System.out.println(maxLargoNombre);		
		
		System.out.println("consultas resumen funciones: avg, min, max, sum");
		Object[] estadisticas = em.createQuery("SELECT MIN(c.id), MAX(c.id), SUM(c.id), COUNT(c.id), AVG(LENGTH(c.nombre)) FROM Cliente c", Object[].class)
				.getSingleResult();
		
		long min = (long) estadisticas[0];
		long max = (long) estadisticas[1];
		long sum = (long) estadisticas[2];
		long count = (long) estadisticas[3];
		double avg = (double) estadisticas[4];
		System.out.println("min="+min+" max="+max+" sum="+sum+" count="+count);
		
		//SUBQUERIES
		System.out.println("Consulta con nombre mas corto y su longitud");
		clientesList = em.createQuery("SELECT c.nombre, LENGTH(c.nombre) FROM Cliente c "
				+ "WHERE LENGTH(c.nombre) = (SELECT min(length(c.nombre)) from Cliente c)", Object[].class)
				.getResultList();
		clientesList.forEach(c -> {
			String nom = (String) c[0];
			int largo = (int) c[1];
			System.out.println("nombre: "+nom+" largo="+largo);
		});
		
		System.out.println("Consulta para obtener el ultimo registro");
		Cliente ultimoCliente = em.createQuery("SELECT c FROM Cliente c WHERE c.id = (SELECT MAX(c.id) FROM Cliente c) ",Cliente.class)
				.getSingleResult();
		System.out.println(ultimoCliente);
		
		//WHERE IN (IN es para subconjuntos)
		System.out.println("Consulta where IN");
//		clientes = em.createQuery("select c from Cliente c where c.id in (1,2,10)",Cliente.class).getResultList();
		clientes = em.createQuery("select c from Cliente c where c.id in :ids",Cliente.class)
				.setParameter("ids", Arrays.asList(1l, 2l, 10l, 40l))
				.getResultList();
		clientes.forEach(c -> {
			System.out.println(c);
		});
		em.close();
	}	
}
