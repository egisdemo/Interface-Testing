package com.egis.interfacetesting.pcqs;

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
public class PCQSPersonSearchJSON extends TestNGCitrusTestDesigner{
	@Autowired
    private HttpClient PCQSHTTPClient;
	
	@Autowired
    private KafkaEndpoint PCQSADISRequestKafkaEndpoint;
	
	@CitrusTest()
    public void TestPCQSAggregatorKAFKAIntegrationGetActivityJSON() {
		 variable("requestid", "citrus:randomNumber(6)");
		 
	        http()
	            .client(PCQSHTTPClient)
	            .send()
	            .post("query-aggregator/v1/egis/${requestid}/getActivity")
	            .contentType("application/json")
	            .payload(new ClassPathResource("templates/ActivityRequest.json"));
	        
	        http()
	        .client(PCQSHTTPClient)
	        .receive()
	        .response(HttpStatus.OK)
	        .messageType(MessageType.JSON);
	        //.payload(new ClassPathResource("templates/ActivityRequest.json"));       
	        
	        receive(PCQSADISRequestKafkaEndpoint)
	        .messageType(MessageType.JSON)
	        .payload("{\"sourceSystem\":\"adis\"}");
    }

}
