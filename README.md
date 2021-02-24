# Jakarta NoSQL with Cosmos DB

This repository shows how to use Cosmos DB with the Jakarta NoSQL specification. The repository hosts the demo for [this](abstract.md) talk. The slides for the talk are [here](https://speakerdeck.com/reza_rahman/jakarta-nosql-powered-by-cosmos-db-on-the-cloud).

We will be using the MongoDB API for Cosmos DB. You could also use the Cassandra or Gremlin API for Cosmos DB with Jakarta NoSQL.

## Setup
* Install Java SE 8 or Java SE 11 (we used [Azul Zulu Java 11 LTS](https://www.azul.com/downloads/zulu-community/)).
* Install the Eclipse IDE for Enterprise Java Developers from [here](https://www.eclipse.org/downloads/packages/). 
* You will need an Azure subscription. If you don't have one, you can get one for free for one year [here](https://azure.microsoft.com/en-us/free).
* Download this repository somewhere in your file system (easiest way might be to download as a zip and extract).

## Start Azure Cosmos DB
We first need to set up the database on Azure.
* Go to the [Azure portal](http://portal.azure.com).
* Select 'Create a resource'. In the search box, enter and select 'Azure Cosmos DB'. Hit create.
* Specify the account name to be azure-game-store-db-`<your suffix>` (the suffix could be your first name such as "reza"). Create a new resource group named azure-game-store-group-`<your suffix>` (the suffix could be your first name such as "reza"). Specify the API to be MongoDB. Hit 'Create'. It will take a moment for the database to deploy and be ready for use.
* In the portal, go to 'All resources'. Find and click on azure-game-store-db-`<your suffix>`. Open the connection string panel. Note down the primary connection string.

Once you are done exploring the demo, you should delete the azure-game-store-group-`<your suffix>` resource group. You can do this by going to the portal, going to resource groups, finding and clicking on azure-game-store-group-`<your suffix>` and hitting delete. This is especially important if you are not using a free subscription!

## Running the Application
The next step is to get the application up and running. Follow the steps below to do so.
* Start Eclipse.
* Get the application into the IDE. In order to do that, go to File -> Import -> Maven -> Existing Maven Projects. Then browse to where you have this repository code in your file system and select the root directory. Accept the rest of the defaults and finish.
* Once the application loads, open the [properties file](src/main/resources/META-INF/microprofile-config.properties) and replace `<Your Cosmos DB connection string`> with the connection string you noted down earlier.
* Do a full Maven build by righting click the application -> Run As -> Maven install.
* After the build - find, explore and run the [unit test](src/test/java/org/jnosql/demo/cosmosdb/AzureGameStoreTest.java). You can run the unit test in Eclipse by right clicking the unit test class -> Run As -> JUnit Test.
* You can take a look at the data through the [Azure portal](http://portal.azure.com). In the portal, go to 'All resources'. Find and click on azure-game-store-db-`<your suffix>`. Open the Data Explorer panel.

## To Do
* Incorporate Cassandra
* Incorporate Gremlin
