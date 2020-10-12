package org.jnosql.demo.cosmosdb;

import jakarta.nosql.document.DocumentCollectionManager;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;

@ApplicationScoped
public class DocumentCollectionManagerProducer {

	@Inject
	@ConfigProperty(name = "azureGameStoreDb")
	private DocumentCollectionManager manager;

	@Produces
	@ApplicationScoped
	public DocumentCollectionManager getManager() {
		return manager;
	}

	void close(@Disposes DocumentCollectionManager manager) {
		manager.close();
	}
}