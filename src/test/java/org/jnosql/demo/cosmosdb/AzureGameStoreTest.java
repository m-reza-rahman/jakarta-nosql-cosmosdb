package org.jnosql.demo.cosmosdb;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;
import jakarta.validation.ConstraintViolationException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import jakarta.nosql.document.DocumentTemplate;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AzureGameStoreTest {

  private static final Logger logger = Logger.getLogger(AzureGameStoreTest.class.getName());

  private static SeContainer container;
  private static DocumentTemplate template;

  private static long customerIdSequence = 1;
  private static long orderIdSequence = 1;

  private static long orderId;

  @BeforeClass
  public static void init() {
    logger.setLevel(Level.INFO);

    container = SeContainerInitializer.newInstance().initialize();
    template = container.select(DocumentTemplate.class).get();

    template.delete(Order.class);
  }

  @Test
  public void test_1_testPersist() {
    logger.info("Testing persist() of orders and customers.");

    Customer customer1 = new Customer(customerIdSequence++, "AMCE");

    Order order1 = new Order(orderIdSequence++);
    order1.setCustomer(customer1);
    order1.setDescription("Pinball machine");
    Address address1 = new Address();
    address1.setStreet("17 Jane St.");
    address1.setCity("Pittsburgh");
    address1.setState("PA");
    address1.setZip("06758");
    order1.setAddress(address1);
    order1.addOrderLine(new OrderLine("machine", 2999));
    order1.addOrderLine(new OrderLine("shipping", 129));
    order1.addOrderLine(new OrderLine("installation", 59));
    template.insert(order1);

    Order order2 = new Order(orderIdSequence++);
    order2.setCustomer(customer1);
    order2.setDescription("Foosball");
    Address address2 = new Address();
    address2.setStreet("7 Bank St.");
    address2.setCity("Miami");
    address2.setState("FL");
    address2.setZip("64849");
    order2.setAddress(address2);
    order2.addOrderLine(new OrderLine("machine", 500));
    order2.addOrderLine(new OrderLine("balls", 5));
    order2.addOrderLine(new OrderLine("shipping", 60));
    template.insert(order2);

    Customer customer2 = new Customer(customerIdSequence++, "Smith");

    Order order3 = new Order(orderIdSequence++);
    order3.setCustomer(customer2);
    order3.setDescription("Pingpong table");
    Address address3 = new Address();
    address3.setStreet("10 Hill St.");
    address3.setCity("Los Angeles");
    address3.setState("CA");
    address3.setZip("13245");
    order3.setAddress(address3);
    order3.addOrderLine(new OrderLine("table", 300));
    order3.addOrderLine(new OrderLine("balls", 5));
    order3.addOrderLine(new OrderLine("rackets", 15));
    order3.addOrderLine(new OrderLine("net", 2));
    order3.addOrderLine(new OrderLine("shipping", 80));
    template.insert(order3);

    orderId = order3.getId();

    assertEquals(3, template.count(Order.class));
    logger.info("Saved orders: " + template.count(Order.class));
  }

  @Test
  public void test_2_testFind() {
    logger.info("Testing find() by Id.");

    Optional<Order> order = template.find(Order.class, orderId);

    assertTrue(order.isPresent());
    logger.info("Found order:\n" + order.get());
  }

  @Test
  public void test_3_testQuery() {
    logger.info("Testing querying.");

    List<Order> results = template.select(Order.class).where("totalCost").gt(1000).result(); 
    assertEquals(1, results.size());
    logger.info("Found orders with cost > 1,000: " + results.size());

    results = template.select(Order.class).where("description").like("^Pinball").result();
    assertEquals(1, results.size());
    logger.info("Found orders for pinball: " + results.size());

    results = template.select(Order.class).where("orderLines.description").eq("machine").result();    
    assertEquals(2, results.size());
    logger.info("Found orders with a machine: " + results.size());
  }

  @Test
  public void test_4_testUpdate() {
    logger.info("Testing update of order.");

    Order order = template.find(Order.class, orderId).get();

    order.addOrderLine(new OrderLine("handling", 55));
    order.addOrderLine(new OrderLine("tax", 300));

    template.update(order);

    assertEquals(757, template.find(Order.class, orderId).get().getTotalCost(), 0);
    logger.info("Updated order:\n" + order);
  }

  @Test
  public void test_5_testRemove() {
    logger.info("Testing remove of order.");
    assertEquals(3, template.count(Order.class));

    template.delete(Order.class, orderId);

    assertEquals(2, template.count(Order.class));
    logger.info("Deleted order with ID: " + orderId);
  }

  @Test
  public void test_6_testValidity() {
    logger.info("Testing validity of order.");

    Order order = new Order(orderIdSequence++);

    try {
      template.insert(order);
      fail("Invalid order inserted.");
    } catch (ConstraintViolationException e) {
      logger.info("Invalid order rejected:\n" + order + "\nReason: " + e.getMessage());
    }
  }

  @AfterClass
  public static void destroy() {
    container.close();
  }
}
