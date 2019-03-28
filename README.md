# ccd-api Interface Tets

### Requirements

- JDK 1.8+:
- Maven 3.x+:
- Docker 18.x

### Required configurations

1. To match the current environment update the ENDPOINTS  in -/com/egis/ccd/interfacetesting/EndpointConfig.java (By default URL is set to http://localhost:8080 for the locally running CCD app server)
2. Start CCD app before running the tests

###Test data
1. Update the test in CCDTestData.class as configured in the test data base.

###Test
1. Run `mvn clean verify`



