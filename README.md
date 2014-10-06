[![Build Status](https://api.shippable.com/projects/54198afdac22859af7440514/badge?branchName=master)](https://app.shippable.com/projects/54198afdac22859af7440514/builds/latest)

###### Description ######
This project tests persistence layer/database layer against different databases.  
It uses [H2 Database](http://www.h2database.com/html/main.html) in 2 modes

- In-memory: to test persisting entities in unit tests
- Mixed-Mode: to test when application is deployed in Wildfly 8 container


######  Technologies used ######
- [Java 8](http://docs.oracle.com/javase/8/docs/api/)
- [Liquibase](http://www.liquibase.org/)
- [Java EE 7](http://docs.oracle.com/javaee/7/tutorial/doc/home.htm)
- [H2 Database](http://www.h2database.com/html/main.html)
