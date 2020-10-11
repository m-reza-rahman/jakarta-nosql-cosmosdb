package org.jnosql.demo.cosmosdb;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;

import org.eclipse.jnosql.diana.mongodb.document.MongoDBDocumentConfiguration;
import org.eclipse.jnosql.diana.mongodb.document.MongoDBDocumentConfigurations;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import jakarta.nosql.Settings;
import jakarta.nosql.document.DocumentCollectionManager;
import jakarta.nosql.document.DocumentCollectionManagerFactory;
import jakarta.nosql.document.DocumentConfiguration;

@ApplicationScoped
public class DocumentCollectionManagerProducer {

	@Inject
	@ConfigProperty(name = "document.settings.mongodb.url")
	private String url;

	private DocumentCollectionManagerFactory factory;

	@PostConstruct
	public void init() {
		DocumentConfiguration configuration = new MongoDBDocumentConfiguration();
		Settings settings = Settings.builder().put(MongoDBDocumentConfigurations.URL.get(), url).build();
		factory = configuration.get(settings);
	}

	@Produces
	public DocumentCollectionManager getManager() {
		return factory.get("azure-game-store-db");
	}
}