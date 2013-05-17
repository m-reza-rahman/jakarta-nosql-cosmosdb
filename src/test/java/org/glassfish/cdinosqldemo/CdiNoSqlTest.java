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

package org.glassfish.cdinosqldemo;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import java.util.regex.Pattern;
import javax.inject.Inject;
import org.bson.types.ObjectId;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.junit.InSequence;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class CdiNoSqlTest {

    @Inject
    @CustomersCollection
    private DBCollection customers;
    @Inject
    @OrdersCollection
    private DBCollection orders;
    private static String orderId;

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addClass(Address.class)
                .addClass(Customer.class)
                .addClass(Order.class)
                .addClass(OrderLine.class)
                .addClass(MongoDbFactory.class)
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Test
    @InSequence(1)
    public void testPersist() {
        System.out.println("\nTesting persist() of orders and customers.\n");

        Customer customer = new Customer();
        customer.setName("AMCE");
        customers.insert(customer);

        Order order = new Order();
        order.setCustomerId(customer.getId());
        order.setDescription("Pinball machine");
        Address address = new Address();
        address.setStreet("17 Jane St.");
        address.setCity("Pittsburgh");
        address.setState("PA");
        address.setZip("06758");
        order.setBillingAddress(address);
        address = new Address();
        address.setStreet("17 Jane St.");
        address.setCity("Philadelphia");
        address.setState("PA");
        address.setZip("54689");
        order.setShippingAddress(address);
        order.addOrderLine(new OrderLine("machine", 2999));
        order.addOrderLine(new OrderLine("shipping", 129));
        order.addOrderLine(new OrderLine("installation", 59));
        orders.insert(order);

        order = new Order();
        order.setCustomerId(customer.getId());
        order.setDescription("Foosball");
        address = new Address();
        address.setStreet("7 Bank St.");
        address.setCity("Miami");
        address.setState("FL");
        address.setZip("64849");
        order.setBillingAddress(address);
        address = new Address();
        address.setStreet("17 Jane St.");
        address.setCity("Jacksonville");
        address.setState("FL");
        address.setZip("75849");
        order.setShippingAddress(address);
        order.addOrderLine(new OrderLine("machine", 500));
        order.addOrderLine(new OrderLine("balls", 5));
        order.addOrderLine(new OrderLine("shipping", 60));
        orders.insert(order);

        customer = new Customer();
        customer.setName("Smith");
        customers.insert(customer);

        order = new Order();
        order.setCustomerId(customer.getId());
        order.setDescription("Pingpong table");
        address = new Address();
        address.setStreet("7 Bank St.");
        address.setCity("Los Angeles");
        address.setState("CA");
        address.setZip("13245");
        order.setBillingAddress(address);
        address = new Address();
        address.setStreet("17 Jane St.");
        address.setCity("Hollywood");
        address.setState("CA");
        address.setZip("78943");
        order.setShippingAddress(address);
        order.addOrderLine(new OrderLine("table", 300));
        order.addOrderLine(new OrderLine("balls", 5));
        order.addOrderLine(new OrderLine("rackets", 15));
        order.addOrderLine(new OrderLine("net", 2));
        order.addOrderLine(new OrderLine("shipping", 80));
        orders.insert(order);

        orderId = order.getId();
    }

    @Test
    @InSequence(2)
    public void testFind() {
        System.out.println("\nTesting find() by Id.\n");

        orders.setObjectClass(Order.class);
        Order order = (Order) orders.findOne(new ObjectId(orderId));

        System.out.println("\nFound order:" + order + " by its oid: " + orderId + "\n");
    }

    @Test
    @InSequence(3)
    public void testQuery() {
        System.out.println("\nTesting querying.\n");

        orders.setObjectClass(Order.class);

        DBObject query = new BasicDBObject("totalCost", new BasicDBObject("$gt", 1000));
        DBCursor cursor = orders.find(query);
        System.out.println("\nFound orders with cost > 1,000: " + cursor.toArray() + "\n");
        cursor.close();

        query = new BasicDBObject("description", Pattern.compile("^Pinball"));
        cursor = orders.find(query);
        System.out.println("\nFound orders for pinball: " + cursor.toArray() + "\n");
        cursor.close();

        query = new BasicDBObject("orderLines.description", "machine");
        cursor = orders.find(query);
        System.out.println("\nFound orders with a machine: " + cursor.toArray() + "\n");
        cursor.close();
    }

    @Test
    @InSequence(4)
    public void testUpdate() {
        System.out.println("\nTesting update of order.\n");

        orders.setObjectClass(Order.class);
        Order order = (Order) orders.findOne(new ObjectId(orderId));
        order.addOrderLine(new OrderLine("handling", 55));
        order.addOrderLine(new OrderLine("tax", 300));
        orders.update(new BasicDBObject("_id", new ObjectId(orderId)), order);
    }

    @Test
    @InSequence(5)
    public void testRemove() {
        System.out.println("\nTesting remove of order.\n");

        orders.remove(new BasicDBObject("_id", new ObjectId(orderId)));
    }
}
