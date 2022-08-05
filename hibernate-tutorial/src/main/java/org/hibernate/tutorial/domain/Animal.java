package org.hibernate.tutorial.domain;

public class Animal {
	
	private Long idAnimal;
	private Integer canBark;
	private Integer isAlive;
	
	public Integer getIsAlive() {
		return isAlive;
	}
	public void setIsAlive(Integer isAlive) {
		this.isAlive = isAlive;
	}
	public Long getIdAnimal() {
		return idAnimal;
	}
	public void setIdAnimal(Long idAnimal) {
		this.idAnimal = idAnimal;
	}
	public Integer getCanBark() {
		return canBark;
	}
	public void setCanBark(Integer canBark) {
		this.canBark = canBark;
	}
	
	
}
