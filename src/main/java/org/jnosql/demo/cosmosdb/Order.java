package org.jnosql.demo.cosmosdb;

import java.util.ArrayList;
import java.util.List;

import jakarta.nosql.mapping.Column;
import jakarta.nosql.mapping.Entity;
import jakarta.nosql.mapping.Id;

@Entity
public class Order {

	@Id
	private long id;

	@Column
	private String description;

	@Column
	private double totalCost = 0;

	@Column
	private List<OrderLine> orderLines = new ArrayList<>();

	@Column
	private Customer customer;

	@Column
	private Address billingAddress;

	@Column
	private Address shippingAddress;

	public Order() {
		super();
	}

	public Order(long id) {
		super();
		this.id = id;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}

	public List<OrderLine> getOrderLines() {
		return orderLines;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Address getBillingAddress() {
		return billingAddress;
	}

	public void setBillingAddress(Address billingAddress) {
		this.billingAddress = billingAddress;
	}

	public Address getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(Address shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	/**
	 * Add the order line to the order, and set the back reference and update the
	 * order cost.
	 */
	public void addOrderLine(OrderLine orderLine) {
		getOrderLines().add(orderLine);
		orderLine.setLineNumber(getOrderLines().size());
		setTotalCost(getTotalCost() + orderLine.getCost());
	}

	@Override
	public String toString() {
		return "Order(" + getDescription() + ", " + getTotalCost() + ")";
	}
}
