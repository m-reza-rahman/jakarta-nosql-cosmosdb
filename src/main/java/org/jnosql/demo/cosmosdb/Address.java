package org.jnosql.demo.cosmosdb;

import javax.validation.constraints.NotBlank;

import jakarta.nosql.mapping.Column;
import jakarta.nosql.mapping.Embeddable;

@Embeddable
public class Address {

  @Column @NotBlank private String street;

  @Column @NotBlank private String city;

  @Column @NotBlank private String state;

  @Column @NotBlank private String zip;

  public String getStreet() {
    return street;
  }

  public void setStreet(String street) {
    this.street = street;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  public String getZip() {
    return zip;
  }

  public void setZip(String zip) {
    this.zip = zip;
  }

  @Override
  public String toString() {
    return "Street=" + street + ", city=" + city + ", state=" + state + ", zip=" + zip;
  }
}
