package com.egis.interfacetesting.encryption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.testng.annotations.Test;

import com.consol.citrus.annotations.CitrusTest;
import com.consol.citrus.dsl.testng.TestNGCitrusTestDesigner;
import com.consol.citrus.http.client.HttpClient;
import com.consol.citrus.message.MessageType;

@Test
public class EncryptionJSONrequest extends TestNGCitrusTestDesigner{
	@Autowired
    private HttpClient EncryptionHTTPClient;
	
	@CitrusTest()
    public void validataEncryptionActivityrequest() {	
        http()
            .client(EncryptionHTTPClient)
            .send()
            .post("/rest/encrypt")
            .contentType("application/json")
            .payload("{\"clientId\": 5688, \"payload\": \"This is the string I want to test\"}");     
       
        
        http()
	        .client(EncryptionHTTPClient)
	        .receive()
	        .response(HttpStatus.OK)
	        .messageType(MessageType.JSON);                  
    }

}
