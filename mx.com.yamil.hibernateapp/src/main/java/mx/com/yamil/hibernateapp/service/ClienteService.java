package mx.com.yamil.hibernateapp.service;

import java.util.List;
import java.util.Optional;

import mx.com.yamil.hibernateapp.entity.Cliente;

//contiene la logica de negocio del controlador
//trabaja con varios DAOS
public interface ClienteService {
	List listar();
	Optional<Cliente> porId(long id);
	void guardar(Cliente cliente);
	void eliminar(long id);
}
