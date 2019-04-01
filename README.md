# Interface Tets

### Requirements

- JDK 1.8+:
- Maven 3.x+:
- Docker 18.x

### Required configurations

1. To match the current environment update the ENDPOINTS  in -/com/egis/ccd/interfacetesting/EndpointConfig.java (By default URL is set to http://localhost:8080 for the locally running any app server)
2. Start SUT(system under test, ex-CCD app) before running the tests

###Test data
1. Update the test data in test APNPXXXTestData.java as configured in the SUT database.

###Test
1. Run all tests `mvn clean verify`
1. Run specific test method `mvn clean verify -Dtest=SearchByPersonTests -Dembedded`



