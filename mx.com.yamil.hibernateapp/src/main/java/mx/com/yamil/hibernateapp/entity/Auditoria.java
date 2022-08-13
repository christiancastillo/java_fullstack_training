package mx.com.yamil.hibernateapp.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

@Embeddable
public class Auditoria {
	
	@PrePersist
	public void prePersist() {
		System.out.println("inicializar algo justo antes del persist!!!!");
		this.creadoEn = LocalDateTime.now();
	}
	
	@PreUpdate
	public void preUpdate() {
		System.out.println("metodo PRE UPDATE antes de actualizar....");
		this.editadoEn = LocalDateTime.now();
	}
	///Las clases se pueden reutilizar, todos los atributos
	@Column(name="creado_en")
	private LocalDateTime creadoEn;
	
	@Column(name="editado_en")
	private LocalDateTime editadoEn;
	public void setCreadoEn(LocalDateTime creadoEn) {
		this.creadoEn = creadoEn;
	}

	public void setEditadoEn(LocalDateTime editadoEn) {
		this.editadoEn = editadoEn;
	}
	public LocalDateTime getCreadoEn() {
		return creadoEn;
	}

	public LocalDateTime getEditadoEn() {
		return editadoEn;
	}

	
}
