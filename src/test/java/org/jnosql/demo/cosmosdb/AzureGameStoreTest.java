package org.jnosql.demo.cosmosdb;

import java.util.regex.Pattern;

import org.bson.types.ObjectId;
import org.jboss.arquillian.junit.InSequence;
import org.junit.Test;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

public class AzureGameStoreTest {

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
