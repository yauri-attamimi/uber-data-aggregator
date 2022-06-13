# uberdriver-crossdb-checker

Collection of services which can be utilized to grab various Uber data from different data sources, particularly for payment and trip related information.
Currently, all of those data were spread around different data sources such as `moovebackend DB`, `datapipeline DB`, `Uber API`, and `CSV files hosted in our GDrive`, hence we have to grab those data and subsequently do a mix-and-match routine to investigate for some discrepancies between those data.

## Context Diagram

| ![context-diagram-v1](https://github.com/MooveAfrica/uberdriver-crossdb-checker/blob/develop/docs/images/UberDataComparator-v1-Context.png) |
| :--: |
| *Context Diagram v1* |

Please take a note that what being depicted on this context diagram doesn't represent the way how we deploy and run this service at the moment. 
It's merely used to give us an idea about each functional components, incl. on how we're going to handle it seriously for production uses in v1, i.e. by having different component deployed independently for scalability reason.  

## Prerequisites 

1. Java 11
2. Maven

## Run the Service

1. Open your terminal
2. Install all dependencies
```mvn clean package```
3. Run the service 
```mvn spring-boot:run```
