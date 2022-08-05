package org.hibernate.tutorial.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Cat extends Animal{

	private Long idCat;
	private String name;
	private String breed;
	private Integer age;
	private Integer litterId;
	private Date dateOfBirth;	
	private Cat mother;
	private Set kittens = new HashSet();	
	
	
	public Cat(String name, String breed, Integer age) {
		this.name = name;
		this.breed = breed;
		this.age = age;
	}
	public Cat() {
	}	
	public Integer getLitterId() {
		return litterId;
	}
	public void setLitterId(Integer litterId) {
		this.litterId = litterId;
	}
	
	public Cat getMother() {
		return mother;
	}
	public void setMother(Cat mother) {
		this.mother = mother;
	}
	public Set getKittens() {
		return kittens;
	}
	public void setKittens(Set kittens) {
		this.kittens = kittens;
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}	
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
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
	
	//add kitten not needed by Hibernate
	public void addKitten(Cat kitten) {
		kitten.setMother(kitten);
		kitten.setLitterId(kittens.size());
		kitten.addKitten(kitten);
	}
	
	public boolean equals(Object other) {
		if (this == other) return true;
		if ( !(other instanceof Cat) ) return false;
		final Cat cat = (Cat) other;
		if ( !cat.getLitterId().equals( getLitterId() ) ) return false;
		if ( !cat.getMother().equals( getMother() ) ) return false;
		return true;
	}
	
	public int hashCode() {
		int result;
		result = getMother().hashCode();
		result = 29 * result + getLitterId();
		return result;
	}
}