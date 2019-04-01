package com.egis.interfacetesting.adis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.testng.annotations.Test;

import com.consol.citrus.annotations.CitrusTest;
import com.consol.citrus.dsl.testng.TestNGCitrusTestDesigner;
import com.consol.citrus.ws.client.WebServiceClient;

@Test
public class PersonDetails extends TestNGCitrusTestDesigner{
	
	@Autowired
	private WebServiceClient adisSOAPClient;
	
	@Test
    @CitrusTest
    public void ValidatePersonDetails() {
        variable("firstName", adisTestData.ADIS_DATA_PERSONRESPONSE_FIRSTNAME);
        variable("lastName", adisTestData.ADIS_DATA_PERSONRESPONSE_LASTNAME);
        variable("aNumber", adisTestData.ADIS_DATA_PERSONRESPONSE_ANUMBER);

        soap()
            .client(adisSOAPClient)
            .send()
            .soapAction("personDetail")
            .payload(new ClassPathResource("templates/personDetailRequest.xml"));

        soap()
            .client(adisSOAPClient)
            .receive()
            .payload(new ClassPathResource("templates/personDetailResponse.xml"));
    }
}
