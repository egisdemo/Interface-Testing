package com.egis.interfacetesting.pcqs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.testng.annotations.Test;

import com.consol.citrus.annotations.CitrusTest;
import com.consol.citrus.dsl.testng.TestNGCitrusTestDesigner;
import com.consol.citrus.http.client.HttpClient;
import com.consol.citrus.message.MessageType;

@Test
public class PCQSPersonSearchJSON extends TestNGCitrusTestDesigner{
	@Autowired
    private HttpClient PCQSHTTPClient;
	
	@CitrusTest()
    public void validataPCQSPersonSearchJSON() {	
        http()
            .client(PCQSHTTPClient)
            .send()
            .post("/validate/ccd")
            .contentType("application/json")
            .payload(new ClassPathResource("templates/PersonSearchRequest.json"));
        
        http()
	        .client(PCQSHTTPClient)
	        .receive()
	        .response(HttpStatus.OK)
	        .messageType(MessageType.JSON)
	        .payload(new ClassPathResource("templates/PersonSearchResponse.json"));
        
    }

}
