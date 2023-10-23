package org.jnosql.demo.cosmosdb;

import org.eclipse.jnosql.mapping.Embeddable;

import jakarta.nosql.Column;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.PositiveOrZero;

@Embeddable
public class OrderLine {

  @Column
  @Min(1)
  private int lineNumber;

  @Column @NotEmpty private String description;

  @Column @PositiveOrZero private double cost;

  public OrderLine() {}

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

  @Override
  public String toString() {
    return "Line number=" + lineNumber + ", description=" + description + ", cost=" + cost;
  }
}
