package org.hibernate.tutorial.domain;

public class Cat extends Animal{
	private Long idCat;
	private String name;
	private String breed;
	private Integer age;
	
	
	public Cat(String name, String breed, Integer age) {
		this.name = name;
		this.breed = breed;
		this.age = age;
	}
	public Cat() {
	}
	public Long getIdCat() {
		return idCat;
	}
	public void setIdCat(Long idCat) {
		this.idCat = idCat;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBreed() {
		return breed;
	}
	public void setBreed(String breed) {
		this.breed = breed;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	
}