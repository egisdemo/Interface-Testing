package com.egis.interfacetesting.mapping;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.testng.annotations.Test;

import com.consol.citrus.annotations.CitrusTest;
import com.consol.citrus.dsl.testng.TestNGCitrusTestDesigner;
import com.consol.citrus.http.client.HttpClient;
import com.consol.citrus.http.server.HttpServer;
import com.consol.citrus.kafka.endpoint.KafkaEndpoint;
import com.consol.citrus.message.MessageType;

import factories.adisTestData;
import redis.embedded.util.JedisUtil;

@Test
public class MappingJSONRequest extends reddis{
	@Autowired
    private HttpClient mappingHTTPClient;
	
	@CitrusTest()
    public void TestMappingJSON() {
		
		 
	        http()
	            .client(mappingHTTPClient)
	            .send()
	            .post("/saveRefData")
	            .contentType("application/json")
	            .payload("{\r\n" + 
	            		"    \"id\":\"nationality_cd_AF\",\r\n" + 
	            		"    \"value\":\"AFGN\"\r\n" + 
	            		"}");
	        
	        http()
	        .client(mappingHTTPClient)
	        .receive()
	        .response(HttpStatus.OK)
	        .messageType(MessageType.JSON);
	        //.payload(new ClassPathResource("templates/ActivityRequest.json")); // 
	        
	        
	        
	       /* receive(PCQSADISRequestKafkaEndpoint)
	        .messageType(MessageType.JSON)
	        .payload("{\"sourceSystem\":\"adis\"}");*/
    }

}
