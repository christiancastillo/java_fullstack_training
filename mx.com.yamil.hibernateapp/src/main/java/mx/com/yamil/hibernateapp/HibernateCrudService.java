package mx.com.yamil.hibernateapp;

import java.util.List;
import java.util.Optional;

import jakarta.persistence.EntityManager;
import mx.com.yamil.hibernateapp.entity.Cliente;
import mx.com.yamil.hibernateapp.service.ClienteService;
import mx.com.yamil.hibernateapp.service.ClienteServiceImpl;
import mx.com.yamil.hibernateapp.utilities.JpaUtilities;

public class HibernateCrudService {
	public static void main(String[] args) {
		EntityManager em = JpaUtilities.getEntityManager();
		
		ClienteService service = new ClienteServiceImpl(em);
		
		System.out.println("************** listar **************");
		List<Cliente> clientes = service.listar();
		clientes.forEach(c -> System.out.println(c));
		
		System.out.println("****obtener por ID****");
		Optional<Cliente> optionalCliente = service.porId(1l);
		optionalCliente.ifPresent(c -> System.out.println(c));
		
		System.out.println("****insertar un nuevo cliente****");
		Cliente cliente = new Cliente();
		cliente.setNombre("Manzana");
		cliente.setApellido("Verde");
		cliente.setFormaDePago("Efectivo");
		service.guardar(cliente);
		System.out.println("Cliente guardado con EXITO.");
		service.listar().forEach(c -> System.out.println(c));
		
		System.out.println("****editar cliente por ID****");
		Long id = cliente.getId();
		optionalCliente = service.porId(id);
		optionalCliente.ifPresent(c -> {
			c.setFormaDePago("Mercado Pago");
			service.guardar(c);
			System.out.println("Cliente EDITADO con exito!!");			
		});
		service.listar().forEach(c -> System.out.println(c));
		
		System.out.println("****Eliminar cliente****");
		id = cliente.getId();
		optionalCliente = service.porId(id);
		optionalCliente.ifPresent(c->{
			service.eliminar(c.getId());
			
		});
		
//		if (optionalCliente.isPresent()) { //metodo 2
//			service.eliminar(id);
//		}
		
		em.close();
	}
}
