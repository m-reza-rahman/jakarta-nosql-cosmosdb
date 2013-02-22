/**
 * *****************************************************************************
 * Copyright (c) 2012 Oracle. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse
 * Public License v1.0 and Eclipse Distribution License v. 1.0 which accompanies
 * this distribution. The Eclipse Public License is available at
 * http://www.eclipse.org/legal/epl-v10.html and the Eclipse Distribution
 * License is available at http://www.eclipse.org/org/documents/edl-v10.php.
 * ****************************************************************************
 */
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