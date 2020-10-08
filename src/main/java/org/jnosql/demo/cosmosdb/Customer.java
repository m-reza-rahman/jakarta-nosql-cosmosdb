package org.jnosql.demo.cosmosdb;

import org.jnosql.artemis.Column;
import org.jnosql.artemis.Entity;
import org.jnosql.artemis.Id;

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