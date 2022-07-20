package mx.com.yamil.hibernateapp.service;

import java.util.List;
import java.util.Optional;

import jakarta.persistence.EntityManager;
import mx.com.yamil.hibernateapp.entity.Cliente;
import mx.com.yamil.hibernateapp.repository.ClienteRepository;
import mx.com.yamil.hibernateapp.repository.CrudRepository;

public class ClienteServiceImpl implements ClienteService{
	private EntityManager em;
	private CrudRepository<Cliente> repository;
	
	

	public ClienteServiceImpl(EntityManager em) {
		this.em = em;
		this.repository = new ClienteRepository(em);
	}

	@Override
	public List listar() {
		// TODO Auto-generated method stub
		return repository.listar();
	}

	@Override
	public Optional<Cliente> porId(long id) {
		// TODO Auto-generated method stub
		return Optional.ofNullable(repository.porId(id));
	}

	@Override
	public void guardar(Cliente cliente) {
		// TODO Auto-generated method stub
		try {
			em.getTransaction().begin();
			repository.guardar(cliente);
			em.getTransaction().commit();
		} catch(Exception ex) {
			em.getTransaction().rollback();
		}
	}

	@Override
	public void eliminar(long id) {
		// TODO Auto-generated method stub
		try {
			em.getTransaction().begin();
			repository.eliminar(id);
			em.getTransaction().commit();
		} catch(Exception ex) {
			em.getTransaction().rollback();
		}		
	}

}
