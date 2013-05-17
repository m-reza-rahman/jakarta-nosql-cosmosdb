package org.glassfish.cdinosqldemo;

import com.mongodb.BasicDBObject;

/**
 * OrderLine, stored as part of the Order document.
 */
public class OrderLine extends BasicDBObject {

    public OrderLine() {
    }

    public OrderLine(String description, double cost) {
        put("description", description);
        put("cost", cost);
    }

    public int getLineNumber() {
        if (get("lineNumber") != null) {
            return getInt("lineNumber");
        }

        return 0;
    }

    public void setLineNumber(int lineNumber) {
        put("lineNumber", lineNumber);
    }

    public String getDescription() {
        return getString("description");
    }

    public void setDescription(String description) {
        put("description", description);
    }

    public double getCost() {
        if (get("cost") != null) {
            return getDouble("cost");
        }

        return 0;
    }

    public void setCost(double cost) {
        put("cost", cost);
    }
}