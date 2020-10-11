package org.jnosql.demo.cosmosdb;

import static jakarta.nosql.document.DocumentQuery.select;

import java.util.Optional;

import javax.enterprise.inject.se.SeContainer;
import javax.enterprise.inject.se.SeContainerInitializer;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import jakarta.nosql.document.DocumentQuery;
import jakarta.nosql.mapping.document.DocumentTemplate;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AzureGameStoreTest {

	private static SeContainer container;
	private static DocumentTemplate template;
	private long customerIdSequence = 0;
	private long orderIdSequence = 0;
	private long orderLineIdSequence = 0;
	private long orderId;

	@BeforeClass
	public static void init() {
		container = SeContainerInitializer.newInstance().initialize();
		template = container.select(DocumentTemplate.class).get();
	}

	@Test
	public void test_1_Persist() {
		System.out.println("\nTesting persist() of orders and customers.\n");

		Customer customer = new Customer(customerIdSequence++, "AMCE");

		Order order = new Order(orderIdSequence++);
		order.setCustomer(customer);
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
		order.addOrderLine(new OrderLine(orderLineIdSequence++, "machine", 2999));
		order.addOrderLine(new OrderLine(orderLineIdSequence++, "shipping", 129));
		order.addOrderLine(new OrderLine(orderLineIdSequence++, "installation", 59));
		template.insert(order);

		order = new Order(orderIdSequence++);
		order.setCustomer(customer);
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
		order.addOrderLine(new OrderLine(orderLineIdSequence++, "machine", 500));
		order.addOrderLine(new OrderLine(orderLineIdSequence++, "balls", 5));
		order.addOrderLine(new OrderLine(orderLineIdSequence++, "shipping", 60));
		template.insert(order);

		customer = new Customer(customerIdSequence++, "Smith");

		order = new Order(orderIdSequence++);
		order.setCustomer(customer);
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
		order.addOrderLine(new OrderLine(orderLineIdSequence++, "table", 300));
		order.addOrderLine(new OrderLine(orderLineIdSequence++, "balls", 5));
		order.addOrderLine(new OrderLine(orderLineIdSequence++, "rackets", 15));
		order.addOrderLine(new OrderLine(orderLineIdSequence++, "net", 2));
		order.addOrderLine(new OrderLine(orderLineIdSequence++, "shipping", 80));
		template.insert(order);

		orderId = order.getId();
	}

	@Test
	public void test_2_Find() {
		System.out.println("\nTesting find() by Id.\n");

		Optional<Order> order = template.find(Order.class, orderId);

		System.out.println("\nFound order:" + order + " by its id: " + orderId + "\n");
	}

	@Test
	public void test_3_Query() {
		System.out.println("\nTesting querying.\n");

		DocumentQuery query = select().from("Order").where("totalCost").gt(1000).build();
		System.out.println("\nFound orders with cost > 1,000: " + template.select(query).count() + "\n");

		query = select().from("Order").where("description").like("^Pinball").build();
		System.out.println("\nFound orders for pinball: " + template.select(query).count() + "\n");

		query = select().from("OrderLine").where("description").eq("machine").build();
		System.out.println("\nFound orders with a machine: " + template.select(query).count() + "\n");
	}

	@Test
	public void test_4_Update() {
		System.out.println("\nTesting update of order.\n");

		Order order = template.find(Order.class, orderId).get();

		order.addOrderLine(new OrderLine(orderLineIdSequence++, "handling", 55));
		order.addOrderLine(new OrderLine(orderLineIdSequence++, "tax", 300));
		template.update(order);
	}

	@Test
	public void test_5_testRemove() {
		System.out.println("\nTesting remove of order.\n");

		template.delete(Order.class, orderId);
	}

	@AfterClass
	public static void destroy() {
		container.close();
	}
}
