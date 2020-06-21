package org.jnosql.demo.cosmosdb;

import com.mongodb.BasicDBObject;

/**
 * Address, stored as part of the Order document.
 */
public class Address extends BasicDBObject {

	private static final long serialVersionUID = 1L;

	public String getStreet() {
		return getString("street");
	}

	public void setStreet(String street) {
		put("street", street);
	}

	public String getCity() {
		return getString("city");
	}

	public void setCity(String city) {
		put("city", city);
	}

	public String getState() {
		return getString("state");
	}

	public void setState(String state) {
		put("state", state);
	}

	public String getZip() {
		return getString("zip");
	}

	public void setZip(String zip) {
		put("zip", zip);
	}
}
