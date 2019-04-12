package com.egis.interfacetesting.camel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.testng.annotations.Test;

import com.consol.citrus.annotations.CitrusTest;
import com.consol.citrus.dsl.testng.TestNGCitrusTestDesigner;
import com.consol.citrus.http.server.HttpServer;
import com.consol.citrus.kafka.endpoint.KafkaEndpoint;

@Test
public class CamelJSONrequest extends TestNGCitrusTestDesigner{
		
	@Autowired
    private HttpServer TransformerServer;
		
	@Autowired
    private KafkaEndpoint CamelKafkaEndpoint;
	
	@CitrusTest()
    public void validataCamelCCDActivityrequestSON() {
		
		send(CamelKafkaEndpoint)
        .payload(new ClassPathResource("templates/ActivityRequest.json"));
		
		http()
		.server(TransformerServer)
		.receive()
		.post("/transform/api/v1/ccd/to/searchByNameAndDOB")
		.timeout(60000)		
		.payload("{\r\n" + 
				"   \"idType\": \"anumber\",\r\n" + 
				"   \"id\": \"A21123123\",\r\n" + 
				"   \"systems\": [\r\n" + 
				"       {\r\n" + 
				"           \"sourceSystem\": \"adis\"\r\n" + 
				"       },\r\n" + 
				"       {\r\n" + 
				"           \"sourceSystem\": \"dos\"\r\n" + 
				"       }\r\n" + 
				"   ]\r\n" + 
				"}");	
    }

}
