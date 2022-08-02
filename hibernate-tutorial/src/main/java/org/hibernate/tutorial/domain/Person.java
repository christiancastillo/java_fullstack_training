package org.hibernate.tutorial.domain;

import java.util.HashSet;
import java.util.Set;

public class Person {
	
	private Set events = new HashSet();
	private Long id;
	private int age;
	private String firstname;
	private String lastname;
	
	public Set getEvents() {
		return events;
	}
	public void setEvents(Set eventos) {
		this.events = eventos;
	}	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	
	
}
