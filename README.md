# Jakarta NoSQL with Cosmos DB

This repository shows how to use Cosmos DB with the Jakarta NoSQL specification. The repository hosts the demo for [this](abstract.md) talk.

## Setup
* Install JDK 8 (we used [Azul Zulu Java 8 LTS](https://www.azul.com/downloads/zulu-community/)).
* Install the Eclipse IDE for Enterprise Java Developers from [here](https://www.eclipse.org/downloads/packages/). 
* You will need a GitHub account.
* You will need an Azure subscription. If you don't have one, you can get one for free for one year [here](https://azure.microsoft.com/en-us/free).
* Download this repository somewhere in your file system (easiest way might be to download as a zip and extract).

## Start Azure Cosmos DB
* Go to the [Azure portal](http://portal.azure.com).
* Select 'Create a resource'. In the search box, enter and select 'Azure Cosmos DB'. Hit create.
* Specify the account name to be azure-game-store-db-`<your suffix>` (the suffix could be your first name such as "reza"). Create a new resource group named azure-game-store-group-`<your suffix>` (the suffix could be your first name such as "reza"). Specify the API to be MongoDB. Hit 'Create'. It will take a moment for the database to deploy and be ready for use.
* In the portal, go to 'All resources'. Find and click on azure-cafe-db-`<your suffix>`. Open the connection security panel. Enable access to Azure services, disable SSL connection enforcement and then hit Save.

Once you are done exploring the demo, you should delete the azure-cafe-group-`<your suffix>` resource group. You can do this by going to the portal, going to resource groups, finding and clicking on azure-cafe-group-`<your suffix>` and hitting delete. This is especially important if you are not using a free subscription! If you do keep these resources around (for example to begin your own prototype), you should in the least use your own passwords and make the corresponding changes in the demo code.
