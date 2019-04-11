package com.egis.interfacetesting.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.testng.annotations.Test;

import com.consol.citrus.annotations.CitrusTest;
import com.consol.citrus.dsl.testng.TestNGCitrusTestDesigner;
import com.consol.citrus.http.client.HttpClient;
import com.consol.citrus.message.MessageType;

@Test
public class ValidatorJSONrequest extends TestNGCitrusTestDesigner{
	@Autowired
    private HttpClient validatorHTTPClient;
	
	@CitrusTest()
    public void validataCCDJSON() {	
        http()
            .client(validatorHTTPClient)
            .send()
            .post("/validate/ccd")
            .contentType("application/json")
            .payload(new ClassPathResource("templates/Applicant.json"));
        
        http()
	        .client(validatorHTTPClient)
	        .receive()
	        .response(HttpStatus.OK)
	        .messageType(MessageType.JSON);                  
    }

}
