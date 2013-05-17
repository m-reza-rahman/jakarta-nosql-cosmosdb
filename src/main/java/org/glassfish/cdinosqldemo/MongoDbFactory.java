package org.glassfish.cdinosqldemo;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

@ApplicationScoped
public class MongoDbFactory {

    private MongoClient mongoClient;

    @PostConstruct
    public void init() throws UnknownHostException {
        setupLogging();
        mongoClient = new MongoClient();
        cleanDatabase();
    }

    private void setupLogging() throws SecurityException {
        System.out.println("\nSetting up MongoDB logging.\n");

        System.setProperty("DEBUG.MONGO", "true");
        System.setProperty("DB.TRACE", "true");

        Logger mongoLogger = Logger.getLogger("com.mongodb");
        mongoLogger.setLevel(Level.FINEST);
    }

    @Produces
    @CustomersCollection
    public DBCollection getCustomersCollection() {
        DB db = mongoClient.getDB("cdi-nosql-demo");
        return db.getCollection("customers");
    }

    @Produces
    @OrdersCollection
    public DBCollection getOrdersCollection() {
        DB db = mongoClient.getDB("cdi-nosql-demo");
        return db.getCollection("orders");
    }

    @PreDestroy
    public void destroy() {
        // cleanDatabase();
        mongoClient.close();
    }

    private void cleanDatabase() {
        System.out.println("\nCleaning database.\n");
        mongoClient.dropDatabase("cdi-nosql-demo");
    }
}