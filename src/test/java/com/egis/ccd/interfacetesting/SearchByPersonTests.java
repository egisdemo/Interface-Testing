package com.egis.ccd.interfacetesting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.testng.annotations.Test;

import com.consol.citrus.annotations.CitrusTest;
import com.consol.citrus.dsl.testng.TestNGCitrusTestDesigner;
import com.consol.citrus.http.client.HttpClient;
import com.consol.citrus.message.MessageType;

@Test
public class SearchByPersonTests extends TestNGCitrusTestDesigner{
	
	@Autowired
    private HttpClient CCDClient;
	
	@CitrusTest()
    public void WhenSearchingByProvidingAValidNumber() {	
        http()
            .client(CCDClient)
            .send()
            .get(CCDTestData.CCD_GET_URI_SEARCHBYNUMBER)
            .contentType(CCDTestData.CCD_CONTENTTYPE_APPLICATION_JSON)
            .queryParam("aNumber", CCDTestData.CCD_DATA_VALIDNUMBER);
            

        http()
            .client(CCDClient)
            .receive()
            .response(HttpStatus.OK)
            .messageType(MessageType.JSON)
            .payload(CCDTestData.CCD_GET_RESPONE_PAYLOAD);
    }

    @CitrusTest
    public void WhenSearchingByProvidingAValidNameAndDOB() {
    	load("classpath:testdata/ccd_data.properties");
        http()
            .client(CCDClient)
            .send()
            .get(CCDTestData.CCD_GET_URI_SEARCHBYNAMEANDDOB)
            .contentType(CCDTestData.CCD_CONTENTTYPE_APPLICATION_JSON)
            .queryParam("firstName", CCDTestData.CCD_DATA_FIRSTNAME_VALID)
            .queryParam("lastName", CCDTestData.CCD_DATA_LASTNAMEE_VALID)
            .queryParam("startDob", CCDTestData.CCD_DATA_STARTDOBE_VALID)
            .queryParam("endDob", CCDTestData.CCD_DATA_ENDDOBE_VALID);

        http()
            .client(CCDClient)
            .receive()
            .response(HttpStatus.OK)
            .messageType(MessageType.JSON);    
      }

    @CitrusTest
    public void WhenSearchingByProvidingInValidNumber() {
    	 http()
	         .client(CCDClient)
	         .send()
	         .get(CCDTestData.CCD_GET_URI_SEARCHBYNUMBER)
	         .contentType(CCDTestData.CCD_CONTENTTYPE_APPLICATION_JSON)
	         .queryParam("aNumber", CCDTestData.CCD_DATA_ANUMBER_INVALID);

    	 http()
    	    .client(CCDClient)
    	    .receive()
    	    .response(HttpStatus.INTERNAL_SERVER_ERROR)
    	    .messageType(MessageType.JSON);
      }

    @CitrusTest
    public void WhenSearchingByProvidingInValidFirstName() {
    	http()
        .client(CCDClient)
        .send()
        .get("/api/v1/applicant/searchByNameAndDOB")
        .contentType("application/json")
        .queryParam("firstName", CCDTestData.CCD_DATA_FIRSTNAME_INVALID)
        .queryParam("lastName", CCDTestData.CCD_DATA_LASTNAMEE_VALID)
        .queryParam("startDob", CCDTestData.CCD_DATA_STARTDOBE_VALID)
        .queryParam("endDob", CCDTestData.CCD_DATA_ENDDOBE_VALID);

    http()
        .client(CCDClient)
        .receive()
        .response(HttpStatus.INTERNAL_SERVER_ERROR)
        .messageType(MessageType.JSON);
      }

}
