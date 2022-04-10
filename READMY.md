#Transaction Commission Calculation API

There is RESTful API with an endpoint for transaction commission calculation.

###How it works - Commission calculation rules

The **lowest** commission shall be used if there are **multiple** rules matching.

**Rule #1: Default pricing**

By default the price for every transaction is 0.5% but not less than 0.05€.

**Rule #2: Client with a discount**

Transaction price for the client with ID of 42 is 0.05€ (unless other rules set lower commission).

**Rule #3: High turnover discount**

Client after reaching transaction turnover of 1000.00€ (per month) gets a discount and transaction commission is 0.03€ for the following transactions.

##The following functionality was implemented here:
1. Business logic of rules
2. Support H2 database to store data in memory
3. Caching external API calls
4. Request data validation
5. Unit and Integration test
6. Possibility to read data for testing from CSV file
7. Basic Authentication
8. OpenApi documentation
9. Docker configuration

## How to run it
1. Using Spring Boot
    ```
   java -jar target/transaction-commission-0.0.1-SNAPSHOT.jar
2. Using Docker - unfortunatelly my docker image have a problem with Basic Authorization, so I suggest using Spring Boot from p1
   ```
   docker build -t transaction-commission:latest .  
   docker run -p 8080:8080 transaction-commission:latest

## How to use it
* After starting application is running on the port **8080**.
* Endpoint address is **localhost:8080/transaction/commission/**
* Request example:
    ```
  {
  "date": "2021-01-01",
  "amount": "100.00",
  "currency": "EUR",
  "client_id": 42
  }
* Endpoint accepted **POST** request
* Default username/password for Basic Authentication is **admin/password**

## How long it takes
I'm working on it more or less one working day (8h) with small breaks