# FreightmateDemo

### Application development environment

The application is developed using
	
	1. Java8
	2. SpringBoot
	3. Junit + Mockito

### How to run the application with maven build (Recommended)

To run the application the following steps need to processed
	
	./mvnw clean install	 -DskipTests  -- if test cases run not required
	./mvnw clean install              -- with test case
	
The above command will build the application

	java -jar target/connote-1.0.0.jar

The above command start the application on `8080` port.

### Testing application with sample data

	curl --location --request POST 'http://localhost:8080/consignments/connotenumber' --header 'Content-Type: application/json' --data-raw '{ "carrierName":"FreightMateCourierCo","accountNumber":"123ABC","digits":10,"lastUsedIndex":19321,"rangeStart":19000,"rangeEnd":20000}'

### How to run without compile (Not Recommended)	

A pre-compiled jar file is given in `lib` directory. The application can be run using that jar too. It is not a 
recommended way as there may some conflict with java 8 version.

	java -jar lib/connote-1.0.0.jar


### Assumption for the application
The application is developed based on two assumptions

	1. As per requirement, prefix is not a part of request. So prefix is computed from carrierName with a assumption that carrierName will be provided in Camel Case.
	
	2. All parameters in JSON body is required. 
	
	 	