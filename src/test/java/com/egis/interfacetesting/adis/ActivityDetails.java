package com.egis.interfacetesting.adis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.testng.annotations.Test;

import com.consol.citrus.annotations.CitrusTest;
import com.consol.citrus.dsl.testng.TestNGCitrusTestDesigner;
import com.consol.citrus.ws.client.WebServiceClient;

@Test
public class ActivityDetails extends TestNGCitrusTestDesigner{
	
	@Autowired
	private WebServiceClient adisSOAPClient;
	
	@Test
    @CitrusTest
    public void ValidateActivityDetails() {      
        soap()
            .client(adisSOAPClient)
            .send()
            .soapAction("activityDetails")
            .payload(new ClassPathResource("templates/activityDetailsRequest.xml"));

        soap()
            .client(adisSOAPClient)
            .receive()
            .payload(new ClassPathResource("templates/activityDetailsResponse.xml"));
    }
}
