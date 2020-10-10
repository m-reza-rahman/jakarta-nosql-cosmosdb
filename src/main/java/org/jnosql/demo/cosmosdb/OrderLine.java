package org.jnosql.demo.cosmosdb;

import jakarta.nosql.mapping.Column;
import jakarta.nosql.mapping.Entity;
import jakarta.nosql.mapping.Id;

@Entity
public class OrderLine {
	
	@Id("id")
	private long id;	

	@Column
	private String description;

	@Column
	private double cost;

	@Column
	private int lineNumber;

	public OrderLine() {
	}

	public OrderLine(String description, double cost) {
		this.description = description;
		this.cost = cost;
	}

	public int getLineNumber() {
		return lineNumber;
	}

	public void setLineNumber(int lineNumber) {
		this.lineNumber = lineNumber;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}
}
