package mx.com.yamil.hibernateapp;

import java.util.Arrays;
import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.ParameterExpression;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import mx.com.yamil.hibernateapp.entity.Cliente;
import mx.com.yamil.hibernateapp.utilities.JpaUtilities;

public class HibernateCriteria {
	public static void main (String[] args) {
		EntityManager em = JpaUtilities.getEntityManager();
		CriteriaBuilder criteria = em.getCriteriaBuilder();
		CriteriaQuery<Cliente> query = criteria.createQuery(Cliente.class); //recibe el tipo de dato del resultado
		
		Root<Cliente> from = query.from(Cliente.class);
		
		query.select(from); //select c from Cliente c
		List<Cliente> clientes = em.createQuery(query).getResultList();
		clientes.forEach(c -> System.out.println(c));
		
		
		System.out.println("LISTAR WHERE EQUALS");
		query = criteria.createQuery(Cliente.class);
		from =query.from(Cliente.class);
		ParameterExpression<String> nombreParam = criteria.parameter(String.class, "nombre"); //forma 2
			
//		query.select(from).where(criteria.equal(from.get("nombre"), "Dafne")); //select c from Cliente c where c.nombre="Dafne"
		query.select(from).where(criteria.equal(from.get("nombre"), nombreParam)); //select c from Cliente c where c.nombre="Dafne" //forma 2
//		clientes = em.createQuery(query).getResultList();
		clientes = em.createQuery(query).setParameter("nombre", "Dafne").getResultList(); //forma 2
		clientes.forEach(c -> System.out.println(c));
		
		System.out.println("usando where like para buscar clientes por nombre");
		query = criteria.createQuery(Cliente.class); //crear query nueva
		from = query.from(Cliente.class); //hacia claase entity
		
		ParameterExpression<String> nombreParamL = criteria.parameter(String.class, "nombreParam");
		query.select(from).where(criteria.like(criteria.upper(from.get("nombre")), nombreParamL)); //atributo, valor
		
//		query.select(from).where(criteria.like(from.get("nombre"), "%a")); //atributo, valor
//		clientes = em.createQuery(query).getResultList();
		clientes = em.createQuery(query)
				.setParameter("nombreParam", "%a")
				.getResultList();
		clientes.forEach(c -> System.out.println(c));
		
		System.out.println("Ejemplo usando where between para rangos");
		query = criteria.createQuery(Cliente.class);
		from =query.from(Cliente.class);
		query.select(from).where(criteria.between(from.get("id"), 2l, 7l));		
		clientes = em.createQuery(query)
				.getResultList();
		System.out.println(clientes);
		
		//-------
		System.out.println("Ejemplo usando where IN ");
		query = criteria.createQuery(Cliente.class);
		from = query.from(Cliente.class);
		ParameterExpression<List> listParam = criteria.parameter(List.class, "nombres");
		query.select(from).where(from.get("nombre").in(listParam));
//		query.select(from).where(from.get("nombre").in("Yamil", "John", "Lou"));
//		query.select(from).where(from.get("nombre").in(Arrays.asList("Yamil", "John", "Lou")));
		clientes = em.createQuery(query).
				setParameter("nombres", Arrays.asList("Yamil", "John", "Lou"))
				.getResultList();
//		clientes = em.createQuery(query).getResultList();
		clientes.forEach(System.out::println);
		
		System.out.println("Ejemplo filtrar usando predicados mayor que o mayor igual que ");
		query = criteria.createQuery(Cliente.class);
		from = query.from(Cliente.class);
		query.select(from).where(criteria.ge(from.get("id"), 3l)); //mayor o igual que 3
		clientes = em.createQuery(query).getResultList();
		clientes.forEach(c -> System.out.println(c));

		query = criteria.createQuery(Cliente.class);
		from = query.from(Cliente.class);
		query.select(from).where(criteria.gt(criteria.length(from.get("nombre")), 5l)); //mayor o igual que 3
		clientes = em.createQuery(query).getResultList();
		clientes.forEach(c -> System.out.println(c));		
		
//		System.out.println("Conjuncion AND y disy OR");
//		from = query.from(Cliente.class);
//		Predicate predNombre = criteria.equal(from.get("nombre"), "Dafne");
//		Predicate porPago = criteria.equal(from.get("formaDePago"), "Debito");
//		query.select(from).where(predNombre,porPago);
//		clientes = em.createQuery(query).getResultList();
//		clientes.forEach(c -> System.out.println(c));
		
//		System.out.println("Consultas por ORDER BY");
//		query =criteria.createQuery(Cliente.class);
//		from = query.from(Cliente.class);
		
//		System.out.println("=========Consulta solo por el nombre de los clientes======");
//		CriteriaQuery<String> queryString = criteria.createQuery(String.class);
//		from = queryString.from(Cliente.class);
//		queryString.select(from.get("nombre"));
//		List<String> nombres = em.createQuery(queryString).getResultList();
//		nombres.forEach(System.out::println);
		
//		query.select(from).orderBy
		
		System.out.println("========== consulta solo por el nombre de los clientes unicos con distinct =========");
		CriteriaQuery<String> queryString = criteria.createQuery(String.class);
		from = queryString.from(Cliente.class);
		queryString.select(criteria.upper(from.get("nombre"))).distinct(true);
		List<String> nombres = em.createQuery(queryString).getResultList();
		nombres.forEach(System.out::println);
		
		System.out.println("======== consulta por nombre y apellido concatenado");
		queryString = criteria.createQuery(String.class);
		from = queryString.from(Cliente.class);
		
		queryString.select(criteria.concat(criteria.concat(from.get("nombre"), " "),from.get("apellido")));
		nombres = em.createQuery(queryString).getResultList();
		nombres.forEach(System.out::println);
		
		System.out.println("CONSULTA DE CAMPOS PERSONALIZADOS");
		CriteriaQuery<Object[]> queryObject = criteria.createQuery(Object[].class);
		from = queryObject.from(Cliente.class);
		queryObject.multiselect(from.get("id"),from.get("nombre"),from.get("apellido"));
		List<Object[]> registros = em.createQuery(queryObject).getResultList();
		registros.forEach(reg -> {
			Long id = (Long) reg[0];
			String nombre = (String) reg[1];
			String apellido = (String) reg[2];
			System.out.println("id = "+id+", nombre="+nombre+", apellido="+apellido);
		});

		System.out.println("CONSULTA DE CAMPOS PERSONALIZADOS CON WHERE");
		queryObject = criteria.createQuery(Object[].class);
		from = queryObject.from(Cliente.class);
		queryObject.multiselect(from.get("id"),from.get("nombre"),from.get("apellido")).where(criteria.like(from.get("nombre"),"%af%"));
		registros = em.createQuery(queryObject).getResultList();
		registros.forEach(reg -> {
			Long id = (Long) reg[0];
			String nombre = (String) reg[1];
			String apellido = (String) reg[2];
			System.out.println("id = "+id+", nombre="+nombre+", apellido="+apellido);
		});		
		
		
		em.close();
	}
}
