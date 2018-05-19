# Microservice task


## Summary

**venues-search-service** is a microservice written using SpringBoot. 
It returns a list of recommended/popular venues near a specified name/locaton. It integrates the Foursquare Venues API. This service has a single end point
and it accepts a "name" query parameter to specified the name. I assume that "name" or "location" refer to the same thing when searching for nearby recommendations. 

## Technologies used

Java 8, Spring Boot, JUnit, Mockito, SpringBootTest.

## Build & run

Project is built using Spring Boot and Maven (Spring Boot Maven plugin). Maven POM file with all the dependencies is provided in the project folder.
To build the project, use standard **mvn install** when in the project folder. Spring Boot maven plugin builds an executable jar file which contains all the dependencies. 

The service can be started by running the executable jar file - either using **mvn spring-boot:run** command in the project folder, or using **java -jar <jar file name>** in the target folder.
NOTE: The executable jar file name is **venue-search-service.jar**. This jar file resides in the project's target folder.

Postman: I have also tested the service using Postman as a client. I have included in the project folder the exported Psotman "Collections" which shows the sample URL's I used.


The web application path is **/api/venues**. Example service endpoint URL is http://localhost:8080/api/venues?name=mayfair


## Design/Code

This application was created using Spring Boot 1.5.8. It hosts embedded Tomcat container and runs on port 8080 (which is configurable via application.properties file)
Standard Spring RestTemplate is used to make calls to the Foursquare Venues API. Results from the API call are Java POJOs (to be found in the project's *model* package). 
The service endpoint accepts a single request query parameter **name** which allows you to specify the name/location for recommended/popular venues. (example URL shown above).
Basic "exception handling" is provided and an "ExceptionResponse" object is returned to demonstrate that applicatkon specific information can be returned using this approach rather than standard response.

*Configuration:* The Foursquare API specific configuration is held in the **application.properties** file under resources folder.
I had to familiarise myself with the Foursquare Venue API. I decided to use their **Venues API** - https://api.foursquare.com/v2/venues/explore end point which returns **recommended/popular** places.

The main service end point is */api/venues?name=<name/locaton> and is mapped to a controller (GET) method (getRecommendedVenues in **VenueSearchServiceController**).

NOTE: I have included Spring Boot Actuator for demonstration purpose only.

## Tests 

Simple unit tests are provided to demonstrate use of Mockito. They are in no way meant to provide full coverage for this demo. 
I've also included an **Integration Test** (**VenueServiceControllerIntegrationTest file) to demonstrate how **Spring Boot Test** support should be used to write integration tests (mock or real).
This integration test utilises the embedded container and allows for easy injection of beans and environment specfic properties into the test. 
Typically, the integration tests" will be in a separate package in the project and they should only be invoked using a specfic maven profile (e.g. mvn -P integration-test) and they should not be run as standard unit tests during build process. 
This test purely exists for this demo and is run when doing maven build along with other unit tests.

## Considerations for production quality

Few points below to consider.
- "Mapper" objects can be used when returning POJOs (returned by foursquare API) back to the client These allow for selected data to be returned to the client for security reasons. Futhermore, "Lombok" plugin can be used for easier creation and use of the POJOs.
- As discussed below, **Integration Test** can be written which can be run using a Spring Profile.
- Basic Spring Boot security can be incorporated. Although OAUth or Kerberos tokens are typically used in production quality services.
- Application configuration can be externalized and Spring Profiles can be used to deliver environment specific properties.
- Service can incorporate further exception/error handling for request validation and other points of failure.
- No logging frameworks are used for DEMO purpose.