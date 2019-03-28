package com.egis.ccd.interfacetesting;

import com.consol.citrus.dsl.testng.TestNGCitrusTestDesigner;

public class CCDTestData extends TestNGCitrusTestDesigner{
	public static final String CCD_DATA_VALIDNUMBER = "A123456789";
	public static final String CCD_GET_URI_SEARCHBYNUMBER = "/api/v1/applicant/searchByANumber";
	public static final String CCD_CONTENTTYPE_APPLICATION_JSON = "application/json";
	public static final String CCD_GET_RESPONE_PAYLOAD = "{\"anumber\":\"A123456789\",\"firstName\":\"Scott\",\"lastName\":\"Tiger\",\"middleName\":\"Irwin\",\"dob\":\"1980-02-02\",\"nationality\":\"USA\",\"birthplace\":\"VA\",\"applicantOver16\":true,\"applicantStatus\":\"Active\",\"paApplicantId\":\"212345\",\"crossRefId\":\"312345\",\"followToJoin\":true,\"totalApplicants\":2,\"issued\":true,\"refused\":false,\"traveling\":true,\"createdDate\":null,\"willJoinApplicant\":true,\"passportNumber\":\"512345678\",\"aNumber\":\"A123456789\",\"identFin\":\"612345678\",\"visaControlNo\":\"712345678\"}";
	public static final String CCD_GET_URI_SEARCHBYNAMEANDDOB = "/api/v1/applicant/searchByNameAndDOB";
	public static final String CCD_DATA_FIRSTNAME_VALID = "Scott";
	public static final String CCD_DATA_LASTNAMEE_VALID = "Tiger";
	public static final String CCD_DATA_STARTDOBE_VALID = "1980-01-02";
	public static final String CCD_DATA_ENDDOBE_VALID = "1980-02-02";
	public static final String CCD_DATA_FIRSTNAME_INVALID = "INVALID";
	public static final String CCD_DATA_ANUMBER_INVALID = "A123456755";
}
