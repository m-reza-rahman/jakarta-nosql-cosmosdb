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

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import java.util.List;

/**
 * Order, stored as a root JSON object, nesting its order lines in the same
 * document.
 */
public class Order extends BasicDBObject {

    public String getId() {
        return getString("_id");
    }

    public String getDescription() {
        return getString("description");
    }

    public void setDescription(String description) {
        put("description", description);
    }

    public double getTotalCost() {
        if (get("totalCost") != null) {
            return getDouble("totalCost");
        }

        return 0;
    }

    public void setTotalCost(double totalCost) {
        put("totalCost", totalCost);
    }

    public List getOrderLines() {
        BasicDBList orderLines = (BasicDBList) get("orderLines");

        if (orderLines == null) {
            orderLines = new BasicDBList();
            put("orderLines", orderLines);
        }

        return orderLines;
    }

    public String getCustomerId() {
        return getString("customerId");
    }

    public void setCustomerId(String customerId) {
        put("customerId", customerId);
    }

    public Address getBillingAddress() {
        return (Address) get("billingAddress");
    }

    public void setBillingAddress(Address billingAddress) {
        put("billingAddress", billingAddress);
    }

    public Address getShippingAddress() {
        return (Address) get("shippingAddress");
    }

    public void setShippingAddress(Address shippingAddress) {
        put("shippingAddress", shippingAddress);
    }

    /**
     * Add the order line to the order, and set the back reference and update
     * the order cost.
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
