package mx.com.yamil.hibernateapp.vo;

import java.io.Serializable;

public class ClienteVO implements Serializable {
	
	private static final long serialVersionUID = 2390168262054072712L;
	
	private String nombre;
	private String apellido;
	
	public ClienteVO() {
	}

	public ClienteVO(String nombre, String apellido) {
		this.nombre = nombre;
		this.apellido = apellido;
	}

	public String getNombre() {
		return nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	
	
	
	
}
