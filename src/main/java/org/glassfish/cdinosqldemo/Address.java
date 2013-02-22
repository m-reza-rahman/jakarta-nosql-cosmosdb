/**
 * *****************************************************************************
 * Copyright (c) 2012 Oracle. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse
 * Public License v1.0 and Eclipse Distribution License v. 1.0 which accompanies
 * this distribution. The Eclipse Public License is available at
 * http://www.eclipse.org/legal/epl-v10.html and the Eclipse Distribution
 * License is available at http://www.eclipse.org/org/documents/edl-v10.php.
 * ****************************************************************************
 */
package org.glassfish.cdinosqldemo;

import com.mongodb.BasicDBObject;

/**
 * Address, stored as part of the Order document.
 */
public class Address extends BasicDBObject {

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