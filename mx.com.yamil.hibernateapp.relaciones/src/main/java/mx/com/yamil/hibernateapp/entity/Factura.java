package mx.com.yamil.hibernateapp.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="facturas")
public class Factura {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;	
	private String descripcion;
	private long total;

	public Factura(String descripcion, long total) {
		super();
		this.descripcion = descripcion;
		this.total = total;
	}



	public Factura() {
		super();
		// TODO Auto-generated constructor stub
	}



	@ManyToOne
	@JoinColumn(name="id_cliente") //se define el nombre de la PK
	private Cliente cliente;

	public long getId() {
		return id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public long getTotal() {
		return total;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	
}
