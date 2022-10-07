# Technicon_Project

This project consists of three main Entities : Property-Owners, Properties and Property-Repairs.  
Three Service classes along with their respective repositories handle the main CRUD functionalities of these entities with the EntityManager interface.  

Technologies used for this project are   
Eclipse IDE  
Jpa with HIBERNATE as implementation  
MySQL Workbench  

Setup  
Inside java/resources create a new folder META-INF and inside this folder, create a new file called persistence.xml  
Inside persistence.xml file set up the xml Config and provide your username, password and the appropriate Database Driver. Then setup the database-schema-generation to "create" and run the DummyData class. Afterwards change the value to "update" and run the main App class.  

Repositories  
Repositories inherit from the abstract super-class AbstractRepository which is a Generic class.  


