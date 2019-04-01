package com.egis.interfacetesting.ccd;

import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.soap.SoapMessageFactory;
import org.springframework.ws.soap.saaj.SaajSoapMessageFactory;
import org.springframework.xml.xsd.SimpleXsdSchema;

import com.consol.citrus.dsl.endpoint.CitrusEndpoints;
import com.consol.citrus.http.client.HttpClient;
import com.consol.citrus.ws.client.WebServiceClient;
import com.consol.citrus.xml.XsdSchemaRepository;

public class EndpointConfig {
	
	/**
	 * XSD schemas
	 * @return
	 */
	
	@Bean
    public SimpleXsdSchema todoListSchema() {
        return new SimpleXsdSchema(new ClassPathResource("schema/AdisSchema.xsd"));
    }

    @Bean
    public XsdSchemaRepository schemaRepository() {
        XsdSchemaRepository schemaRepository = new XsdSchemaRepository();
        schemaRepository.getSchemas().add(todoListSchema());
        return schemaRepository;
    }
    
    /**
     * HTTP clients setup
     * @return
     */
	
	@Bean
    public HttpClient CCDHTTPClient() {
        return CitrusEndpoints.http()
                            .client()
                            .requestUrl("http://localhost:8080")
                            .build();
    }
	
	@Bean
    public HttpClient validatorHTTPClient() {
        return CitrusEndpoints.http()
                            .client()
                            .requestUrl("http://localhost:8080")
                            .build();
    }
	
	
	/**
	 * SOAP Clients setup
	 * @return
	 */
	
	@Bean
    public SoapMessageFactory messageFactory() {
        return new SaajSoapMessageFactory();
    }
	
	@Bean
    public WebServiceClient adisSOAPClient() {
        return CitrusEndpoints.soap()
                            .client()
                            .defaultUri("http://localhost:8081/AdisFile/")
                            .build();
    }
	
	
	
	
}