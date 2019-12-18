package com.josephesteban.service.models;

import java.util.*;

public class Client {
	private UUID uuid;
	
	private String first_name;
	private String last_name;
	private Date birthdate;
	private int age;
	private final double ESPERANZA_VIDA = 76.5;
	//http://www.elperuano.pe/noticia-peru-mejora-calidad-vida-de-poblacion-87474.aspx
	
	public Client(String first_name, String last_name, Date birthdate, int age) {
		super();
		this.uuid = UUID.randomUUID();
		this.first_name = first_name;
		this.last_name = last_name;
		this.birthdate = birthdate;
		this.age = age;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public Date getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public UUID getUuid() {
		return uuid;
	}
	
	public Date getDied() {
		Double diferenceyears = (this.ESPERANZA_VIDA - this.getAge());
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.YEAR, diferenceyears.intValue());
		return calendar.getTime();
	}
	
	
}

