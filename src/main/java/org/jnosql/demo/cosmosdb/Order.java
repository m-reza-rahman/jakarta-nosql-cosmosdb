package org.jnosql.demo.cosmosdb;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import jakarta.nosql.mapping.Column;
import jakarta.nosql.mapping.Entity;
import jakarta.nosql.mapping.Id;

@Entity
public class Order {

  @Id private long id;

  @Column @NotBlank private String description;

  @Column @PositiveOrZero private double totalCost = 0;

  @Column @NotEmpty private List<OrderLine> orderLines = new ArrayList<>();

  @Column @NotNull private Customer customer;

  @Column @NotNull private Address address;

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

  public void setOrderLines(List<OrderLine> orderLines) {
    this.orderLines = orderLines;
  }

  public Customer getCustomer() {
    return customer;
  }

  public void setCustomer(Customer customer) {
    this.customer = customer;
  }

  public Address getAddress() {
    return address;
  }

  public void setAddress(Address address) {
    this.address = address;
  }

  /** Add the order line to the order, and set the back reference and update the order cost. */
  public void addOrderLine(OrderLine orderLine) {
    getOrderLines().add(orderLine);
    orderLine.setLineNumber(getOrderLines().size());
    setTotalCost(getTotalCost() + orderLine.getCost());
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();

    builder.append(
        "\tID="
            + getId()
            + ", description="
            + getDescription()
            + ", total cost="
            + getTotalCost()
            + "\n");
    builder.append("\tCustomer: " + getCustomer() + "\n");

    builder.append("\tOrder lines: " + getOrderLines().size() + "\n");
    getOrderLines().stream().forEach(l -> builder.append("\t" + l + "\n"));

    builder.append("\tAddress: " + getAddress());

    return builder.toString();
  }
}
