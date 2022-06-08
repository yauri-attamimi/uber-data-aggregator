# uberdriver-crossdb-checker
Utility service that can be used to check / validate some Uber's driver data related to Payment and Trip records. All of these records currently are splitted around our different data sources such as `moovebackend DB`, `datapipeline DB`, `Uber API v1`, and `Google Sheet Files (represent the Uber Dashboard)`.

## Context Diagram

| ![context-diagram-v1](https://github.com/MooveAfrica/uberdriver-crossdb-checker/blob/develop/docs/images/UberDataComparator-v1-Context.png) |
| :--: |
| *Context Diagram v1* |

## Prerequisites 

1. Java 11
2. Maven

## Run the Service

1. Open your terminal
2. Install all dependencies
```mvn clean package```
3. Run the service 
```mvn spring-boot:run```
