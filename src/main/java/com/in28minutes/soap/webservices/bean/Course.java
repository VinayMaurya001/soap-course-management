package com.in28minutes.soap.webservices.bean;

import java.math.BigInteger;

public class Course {
	private BigInteger id;
	private String name;
	private String description;

	public Course(BigInteger id, String name, String description) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
	}

	public Course(Integer id, String name, String description) {
		super();
		this.id = new BigInteger(id.toString());
		this.name = name;
		this.description = description;

		// TODO Auto-generated constructor stub
	}

	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return String.format("Course [id=%s, name=%s, description=%s]", id, name, description);
	}
}
