package org.jnosql.demo.cosmosdb;

import jakarta.nosql.mapping.Column;
import jakarta.nosql.mapping.Entity;
import jakarta.nosql.mapping.Id;

/**
 * Customer, stored as root.
 */
@Entity
public class Customer {

	@Id("id")
	private long id;

	@Column
	private String name;

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}