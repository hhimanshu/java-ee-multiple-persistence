[![Build Status](https://api.shippable.com/projects/54198afdac22859af7440514/badge?branchName=master)](https://app.shippable.com/projects/54198afdac22859af7440514/builds/latest)

This is a proof-of-concept to test persistence layer against different databases
It uses [H2 Database](http://www.h2database.com/html/main.html) in 2 modes

- In-memory: to test persisting entities in unit tests
- Mixed-Mode: to test when application is deployed in Wildfly 8 container
