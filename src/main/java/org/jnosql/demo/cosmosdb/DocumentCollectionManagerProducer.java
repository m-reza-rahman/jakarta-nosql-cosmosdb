package org.jnosql.demo.cosmosdb;

import java.util.Collections;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

import org.jnosql.diana.api.Settings;
import org.jnosql.diana.api.document.DocumentCollectionManager;
import org.jnosql.diana.api.document.DocumentCollectionManagerFactory;
import org.jnosql.diana.api.document.DocumentConfiguration;
import org.jnosql.diana.mongodb.document.MongoDBDocumentConfiguration;

@ApplicationScoped
public class DocumentCollectionManagerProducer {

	private static final String CUSTOMER_COLLECTION = "customers";
	private static final String ORDER_COLLECTION = "orders";

	@SuppressWarnings("rawtypes")
	private DocumentConfiguration configuration;

	@SuppressWarnings("rawtypes")
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