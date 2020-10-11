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

<<<<<<< HEAD
=======
	private static final String CUSTOMER_COLLECTION = "customers";
	private static final String ORDER_COLLECTION = "orders";

>>>>>>> branch 'master' of https://github.com/m-reza-rahman/jakarta-nosql-cosmosdb.git
	private DocumentConfiguration configuration;

	private DocumentCollectionManagerFactory managerFactory;

	@PostConstruct
	public void init() {
		configuration = new MongoDBDocumentConfiguration();
		Map<String, Object> settings = Collections.singletonMap("document.settings.jakarta.nosql.host",
				"mongodb://azure-game-store-db-reza:n8OCHCrMcVWXfhVz3IEuzJKOdWffqff9n6jf1PvKyyQAFBRuCgmZYUyB3PwM8Cc0BO1eZgOZt7SxyuiCe4W8pw==@azure-game-store-db-reza.mongo.cosmos.azure.com:10255/?ssl=true&replicaSet=globaldb&retrywrites=false&maxIdleTimeMS=120000&appName=@azure-game-store-db-reza@");
		managerFactory = configuration.get(Settings.of(settings));
	}

	@Produces
	public DocumentCollectionManager getManager() {
		return managerFactory.get("document");
	}
<<<<<<< HEAD
=======

	@Produces
	public DocumentCollectionManager getOrderManager() {
		return managerFactory.get(ORDER_COLLECTION);

	}
>>>>>>> branch 'master' of https://github.com/m-reza-rahman/jakarta-nosql-cosmosdb.git
}