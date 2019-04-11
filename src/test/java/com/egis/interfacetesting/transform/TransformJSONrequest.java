package com.egis.interfacetesting.transform;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.testng.annotations.Test;

import com.consol.citrus.annotations.CitrusTest;
import com.consol.citrus.dsl.testng.TestNGCitrusTestDesigner;
import com.consol.citrus.http.client.HttpClient;
import com.consol.citrus.message.MessageType;

@Test
public class TransformJSONrequest extends TestNGCitrusTestDesigner{
	@Autowired
    private HttpClient TransformerHTTPClient;
	
	@CitrusTest()
    public void validataTranformCCDActivityrequestSON() {	
        http()
            .client(TransformerHTTPClient)
            .send()
            .post("/api/v1/ccd/from")
            .contentType("application/json")
            .payload(new ClassPathResource("templates/Applicant.json"));     
       
        
        http()
	        .client(TransformerHTTPClient)
	        .receive()
	        .response(HttpStatus.OK)
	        .messageType(MessageType.JSON);                  
    }

}
