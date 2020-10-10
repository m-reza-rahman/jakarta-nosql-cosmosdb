package org.jnosql.demo.cosmosdb;

import static jakarta.nosql.document.DocumentQuery.select;

import javax.enterprise.inject.se.SeContainer;
import javax.enterprise.inject.se.SeContainerInitializer;

import org.junit.Test;

import jakarta.nosql.document.DocumentQuery;
import jakarta.nosql.mapping.document.DocumentTemplate;

public class AzureGameStoreTest {

	private SeContainer container;
	private DocumentTemplate template;
	private long orderId;

	public void init() {
		container = SeContainerInitializer.newInstance().initialize();
		template = container.select(DocumentTemplate.class).get();
	}

	@Test
	public void testPersist() {
		System.out.println("\nTesting persist() of orders and customers.\n");

		Customer customer = new Customer();
		customer.setName("AMCE");
		template.insert(customer);

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
		template.insert(order);

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
		template.insert(order);

		customer = new Customer();
		customer.setName("Smith");
		template.insert(customer);

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
		template.insert(order);

		orderId = order.getId();
	}

	@Test
	public void testFind() {
		System.out.println("\nTesting find() by Id.\n");

		Order order = template.find(Order.class, orderId).get();

		System.out.println("\nFound order:" + order + " by its oid: " + orderId + "\n");
	}

	@Test
	public void testQuery() {
		System.out.println("\nTesting querying.\n");
		
		DocumentQuery query = select().from("Order").where("totalCost").gt(1000).build();		
		System.out.println("\nFound orders with cost > 1,000: " + template.select(query).count() + "\n");

		query = select().from("Order").where("description").like("^Pinball").build();		
		System.out.println("\nFound orders for pinball: " + template.select(query).count() + "\n");

		query = select().from("OrderLine").where("description").eq("machine").build();				
		System.out.println("\nFound orders with a machine: " + template.select(query).count() + "\n");
	}

	@Test
	public void testUpdate() {
		System.out.println("\nTesting update of order.\n");

		Order order = template.find(Order.class, orderId).get();

		order.addOrderLine(new OrderLine("handling", 55));
		order.addOrderLine(new OrderLine("tax", 300));
		template.update(order);
	}

	@Test
	public void testRemove() {
		System.out.println("\nTesting remove of order.\n");

		template.delete(Order.class, orderId);
	}

	public void destroy() {
		container.close();
	}
}
