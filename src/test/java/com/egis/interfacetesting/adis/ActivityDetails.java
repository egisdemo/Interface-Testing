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
            .soapAction("http://egis.com/services/adisapi/getActivityDetails")
            .payload(new ClassPathResource("templates/activityDetailsRequest.xml"));

        soap()
            .client(adisSOAPClient)
            .receive()
            .xsd("AdisSchema")
            .payload("<ns3:activityResponse xmlns:ns3=\"http://egis.com/adisapi\">\r\n" + 
            		"<activity>Crosssing</activity>\r\n" + 
            		"<result>Border</result>\r\n" + 
            		"<ns3:personFull>\r\n" + 
            		"<firstName>JohnWS</firstName>\r\n" + 
            		"<id>A4237898</id>\r\n" + 
            		"<idType>Anumber</idType>\r\n" + 
            		"<lastName>DoeWS</lastName>\r\n" + 
            		"<middleName>MWS</middleName>\r\n" + 
            		"<dob>2019-04-24+05:30</dob>\r\n" + 
            		"</ns3:personFull>\r\n" + 
            		"<ns3:biographicData>\r\n" + 
            		"<ns3:biometric>\r\n" + 
            		"<image>FingerPrints</image>\r\n" + 
            		"<imageType>FINGERPRINTS</imageType>\r\n" + 
            		"</ns3:biometric>\r\n" + 
            		"<citizenship>USA</citizenship>\r\n" + 
            		"<classofAdmission>USA</classofAdmission>\r\n" + 
            		"<countryOfBirth>USA</countryOfBirth>\r\n" + 
            		"<countryOfResidence>USA</countryOfResidence>\r\n" + 
            		"<driverLicense>VA-1243123</driverLicense>\r\n" + 
            		"<gender>Female</gender>\r\n" + 
            		"<licensePlateNumber>UUN234212312</licensePlateNumber>\r\n" + 
            		"<nationality>USA</nationality>\r\n" + 
            		"<ssn>xxx-xx-xxxx</ssn>\r\n" + 
            		"<ns3:travelData>\r\n" + 
            		"<admitUntilDate>03/24/2020</admitUntilDate>\r\n" + 
            		"<airplaceCarrierCode>UAL</airplaceCarrierCode>\r\n" + 
            		"<arrivateDate>2019-04-24+05:30</arrivateDate>\r\n" + 
            		"<departureDate>2019-04-24+05:30</departureDate>\r\n" + 
            		"<destinationAddress>26948 Loudon Cir, Chicago IL</destinationAddress>\r\n" + 
            		"<inspectorComments>Watchlist</inspectorComments>\r\n" + 
            		"<passengerNameRecord>JohnWS DoeWS</passengerNameRecord>\r\n" + 
            		"<passengerStatus>Single</passengerStatus>\r\n" + 
            		"<passportInfo>USA Passport</passportInfo>\r\n" + 
            		"<vesselPortandName>Baltimore Port</vesselPortandName>\r\n" + 
            		"<visaInfo>UK Visa</visaInfo>\r\n" + 
            		"</ns3:travelData>\r\n" + 
            		"<ns3:travelDocumentInformation>\r\n" + 
            		"<docType>passport</docType>\r\n" + 
            		"<docNumber>2342342</docNumber>\r\n" + 
            		"<countryCode>USA</countryCode>\r\n" + 
            		"<dateOfIssuance>10/21/1998</dateOfIssuance>\r\n" + 
            		"</ns3:travelDocumentInformation>\r\n" + 
            		"<vehicleId>34892348</vehicleId>\r\n" + 
            		"</ns3:biographicData>\r\n" + 
            		"</ns3:activityResponse>\r\n");
    }
}
