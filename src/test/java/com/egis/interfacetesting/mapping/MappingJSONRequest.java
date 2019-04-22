package com.egis.interfacetesting.mapping;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.testng.annotations.Test;

import com.consol.citrus.annotations.CitrusTest;
import com.consol.citrus.dsl.testng.TestNGCitrusTestDesigner;
import com.consol.citrus.http.client.HttpClient;
import com.consol.citrus.kafka.endpoint.KafkaEndpoint;
import com.consol.citrus.message.MessageType;

import factories.adisTestData;

@Test
public class MappingJSONRequest extends TestNGCitrusTestDesigner{
	@Autowired
    private HttpClient mappingHTTPClient;
	
	
	
	@CitrusTest()
    public void TestPCQSAggregatorKAFKAIntegrationGetActivityJSON() {
		 variable("requestid", "citrus:randomNumber(6)");
		 
	        http()
	            .client(mappingHTTPClient)
	            .send()
	            .post("/mapping/")
	            .contentType("application/json")
	            .payload("{\"id\":\"dasdsadas\"}");
	        
	        http()
	        .client(mappingHTTPClient)
	        .receive()
	        .response(HttpStatus.OK)
	        .messageType(MessageType.JSON);
	        //.payload(new ClassPathResource("templates/ActivityRequest.json"));       
	        
	       /* receive(PCQSADISRequestKafkaEndpoint)
	        .messageType(MessageType.JSON)
	        .payload("{\"sourceSystem\":\"adis\"}");*/
    }

}
