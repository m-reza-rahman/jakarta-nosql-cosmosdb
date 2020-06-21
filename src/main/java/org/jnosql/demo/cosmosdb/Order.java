/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright (c) 2013 Oracle and/or its affiliates. All rights reserved.
 *
 * The contents of this file are subject to the terms of either the GNU
 * General Public License Version 2 only ("GPL") or the Common Development
 * and Distribution License("CDDL") (collectively, the "License").  You
 * may not use this file except in compliance with the License.  You can
 * obtain a copy of the License at
 * http://glassfish.java.net/public/CDDL+GPL_1_1.html
 * or packager/legal/LICENSE.txt.  See the License for the specific
 * language governing permissions and limitations under the License.
 *
 * When distributing the software, include this License Header Notice in each
 * file and include the License file at packager/legal/LICENSE.txt.
 *
 * GPL Classpath Exception:
 * Oracle designates this particular file as subject to the "Classpath"
 * exception as provided by Oracle in the GPL Version 2 section of the License
 * file that accompanied this code.
 *
 * Modifications:
 * If applicable, add the following below the License Header, with the fields
 * enclosed by brackets [] replaced by your own identifying information:
 * "Portions Copyright [year] [name of copyright owner]"
 *
 * Contributor(s):
 * If you wish your version of this file to be governed by only the CDDL or
 * only the GPL Version 2, indicate your decision by adding "[Contributor]
 * elects to include this software in this distribution under the [CDDL or GPL
 * Version 2] license."  If you don't indicate a single choice of license, a
 * recipient has the option to distribute your version of this file under
 * either the CDDL, the GPL Version 2 or to extend the choice of license to
 * its licensees as provided above.  However, if you add GPL Version 2 code
 * and therefore, elected the GPL Version 2 license, then the option applies
 * only if the new code is made subject to such option by the copyright
 * holder.
 */

package org.jnosql.demo.cosmosdb;

import java.util.List;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;

/**
 * Order, stored as a root JSON object, nesting its order lines in the same
 * document.
 */
public class Order extends BasicDBObject {

	private static final long serialVersionUID = 1L;

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

	@SuppressWarnings("rawtypes")
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
	@SuppressWarnings("unchecked")
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
