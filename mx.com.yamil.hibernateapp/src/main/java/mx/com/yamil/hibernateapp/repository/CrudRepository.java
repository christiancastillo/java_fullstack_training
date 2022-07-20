package mx.com.yamil.hibernateapp.repository;

import java.util.List;

public interface CrudRepository<T> {
	List listar();
	T porId(long id);
	void guardar(T t);
	void eliminar (long id);
}
