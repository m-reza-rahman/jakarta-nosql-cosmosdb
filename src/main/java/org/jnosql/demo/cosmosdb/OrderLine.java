package org.jnosql.demo.cosmosdb;

import jakarta.nosql.mapping.Column;
import jakarta.nosql.mapping.Embeddable;

/**
 * OrderLine, stored as part of the Order document.
 */
@Embeddable
public class OrderLine {

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
