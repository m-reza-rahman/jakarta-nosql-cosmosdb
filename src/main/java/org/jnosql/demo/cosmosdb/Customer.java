package org.jnosql.demo.cosmosdb;

import jakarta.validation.constraints.NotBlank;

import jakarta.nosql.Column;
import jakarta.nosql.Entity;
import jakarta.nosql.Id;

@Entity
public class Customer {

  @Id private long id;

  @Column @NotBlank private String name;

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
