package org.jnosql.demo.cosmosdb;

import java.util.Collections;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

import org.eclipse.jnosql.diana.mongodb.document.MongoDBDocumentConfiguration;

import jakarta.nosql.Settings;
import jakarta.nosql.document.DocumentCollectionManager;
import jakarta.nosql.document.DocumentCollectionManagerFactory;
import jakarta.nosql.document.DocumentConfiguration;

@ApplicationScoped
public class DocumentCollectionManagerProducer {

	private static final String CUSTOMER_COLLECTION = "customers";
	private static final String ORDER_COLLECTION = "orders";

	private DocumentConfiguration configuration;

	private DocumentCollectionManagerFactory managerFactory;

	@PostConstruct
	public void init() {
		configuration = new MongoDBDocumentConfiguration();
		Map<String, Object> settings = Collections.singletonMap("mongodb-server-host-1", "localhost:27017");
		managerFactory = configuration.get(Settings.of(settings));
	}

	@Produces
	public DocumentCollectionManager getCustomerManager() {
		return managerFactory.get(CUSTOMER_COLLECTION);

	}

	@Produces
	public DocumentCollectionManager getOrderManager() {
		return managerFactory.get(ORDER_COLLECTION);

	}
}