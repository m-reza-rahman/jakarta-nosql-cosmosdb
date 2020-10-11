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
import org.eclipse.jnosql.diana.mongodb.document.MongoDBDocumentConfigurations;

@ApplicationScoped
public class DocumentCollectionManagerProducer {

	private DocumentCollectionManagerFactory factory;

	@PostConstruct
	public void init() {
		DocumentConfiguration configuration = new MongoDBDocumentConfiguration();
		Settings settings = Settings.builder().put(MongoDBDocumentConfigurations.URL.get(),
				"mongodb://azure-game-store-db-reza.mongo.cosmos.azure.com:10255/?ssl=true&replicaSet=globaldb&retrywrites=false&maxIdleTimeMS=120000&appName=@azure-game-store-db-reza@").build();
		factory = configuration.get(settings);
	}

	@Produces
	public DocumentCollectionManager getManager() {
		return factory.get("order");
	}
}