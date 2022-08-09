package mx.com.yamil.hibernateapp.entity;

public class ClienteEntity {
/*Entidad cliente para Criteria HBM*/
	
	private long id;
	private String nombre;
	private String formaDePago;
	public long getId() {
		return id;
	}
	public String getNombre() {
		return nombre;
	}
	public String getFormaDePago() {
		return formaDePago;
	}
	public void setId(long id) {
		this.id = id;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public void setFormaDePago(String formaDePago) {
		this.formaDePago = formaDePago;
	}
	
}
