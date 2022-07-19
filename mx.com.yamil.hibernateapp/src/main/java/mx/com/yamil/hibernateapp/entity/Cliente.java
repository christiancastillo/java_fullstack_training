package mx.com.yamil.hibernateapp.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

//Definimos la entidad Cliente. Nota: Verificar que los atributos sean del mismo tipo que en la base de datos

@Entity
@Table(name="clientes") //definimos que tabla mapeamos
public class Cliente {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="idclientes")
	private long id;
	
	
	private String nombre;
	private String apellido; //los campos se mapean exactamente igual que en la tabla
	@Column(name="forma_pago")
	private String formaDePago;
	
	public Cliente(long id, String nombre, String apellido, String formaDePago) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.formaDePago = formaDePago;
	}
	public Cliente() {}		
	public long getId() {
		return id;
	}
	public String getNombre() {
		return nombre;
	}
	public String getApellido() {
		return apellido;
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
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public void setFormaDePago(String formaDePago) {
		this.formaDePago = formaDePago;
	}
	@Override
	public String toString() {
		return "Cliente [id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", formaDePago=" + formaDePago
				+ "]";
	}	
}