package mx.com.yamil.hibernateapp.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="direcciones")
public class Direccion {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	private String calle;
	private int numero;
	public Direccion() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Direccion(String calle, int numero) {
		super();
		this.calle = calle;
		this.numero = numero;
	}
	public long getId() {
		return id;
	}
	public String getCalle() {
		return calle;
	}
	public int getNumero() {
		return numero;
	}
	public void setId(long id) {
		this.id = id;
	}
	public void setCalle(String calle) {
		this.calle = calle;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	
	
}
