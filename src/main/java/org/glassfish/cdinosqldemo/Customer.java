package org.glassfish.cdinosqldemo;

import com.mongodb.BasicDBObject;

/**
 * Customer, stored as a root JSON object.
 */
public class Customer extends BasicDBObject {

    public String getId() {
        return getString("_id");
    }

    public String getName() {
        return getString("name");
    }

    public void setName(String name) {
        put("name", name);
    }
}