package mx.com.yamil.hibernateapp.repository;

import java.util.List;

import jakarta.persistence.EntityManager;
import mx.com.yamil.hibernateapp.entity.Cliente;

public class ClienteRepository implements CrudRepository<Cliente>{
	private EntityManager em;
	public ClienteRepository(EntityManager em) {
		this.em = em;
	}
	@Override
	public List listar() {
		// TODO Auto-generated method stub
		return em.createQuery("SELECT c from Cliente c",Cliente.class).getResultList();
	}

	@Override
	public Cliente porId(long id) {
		// TODO Auto-generated method stub
		return em.find(Cliente.class, id);
	}

	@Override
	public void guardar(Cliente t) {
		// TODO Auto-generated method stub
		if (t.getId() > 0) {
			em.merge(t);
		} else {
			em.persist(t);	
		}		
	}

	@Override
	public void eliminar(long id) {
		// Cliente cliente
		Cliente cliente = porId(id);
		em.remove(cliente);
		
	}

}
