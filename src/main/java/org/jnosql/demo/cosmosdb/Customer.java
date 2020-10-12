package org.jnosql.demo.cosmosdb;

import jakarta.nosql.mapping.Column;
import jakarta.nosql.mapping.Entity;
import jakarta.nosql.mapping.Id;

@Entity
public class Customer {

	@Id
	private long id;

	@Column
	private String name;

	public Customer() {
		super();
	}

	public Customer(long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "ID=" + id + ", name=" + name;
	}
}