package configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.soap.SoapMessageFactory;
import org.springframework.ws.soap.saaj.SaajSoapMessageFactory;
import org.springframework.xml.xsd.SimpleXsdSchema;

import com.consol.citrus.dsl.endpoint.CitrusEndpoints;
import com.consol.citrus.http.client.HttpClient;
import com.consol.citrus.kafka.embedded.EmbeddedKafkaServer;
import com.consol.citrus.kafka.embedded.EmbeddedKafkaServerBuilder;
import com.consol.citrus.kafka.endpoint.KafkaEndpoint;
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
     * Kafka server    
     * @return
     */
    @Bean
    public EmbeddedKafkaServer embeddedKafkaServer() {
        return new EmbeddedKafkaServerBuilder()
                .kafkaServerPort(9092)
                .topics("nre.egis.dos.request", "nre.egis.dos.response", "nre.egis.adis.request", "nre.egis.adis.response")
                .build();
    }
    
    /**
     * Kafka cleints
     */
   /* @Bean
    public KafkaEndpoint PCQSDOSRequestKafkaEndpoint() {
        return CitrusEndpoints.kafka()
                .asynchronous()
                .server("localhost:9092")
                .topic("nre.egis.dos.request")
                .build();
    }*/
    
    @Bean
    public KafkaEndpoint PCQSADISRequestKafkaEndpoint() {
        return CitrusEndpoints.kafka()
                .asynchronous()
                .server("localhost:9092")
                .topic("nre.egis.adis.request")
                .build();
    }
    
   /* @Bean
    public KafkaEndpoint PCQSDOSRepsonseKafkaEndpoint() {
        return CitrusEndpoints.kafka()
                .asynchronous()
                .server("localhost:9092")
                .topic("nre.egis.dos.response")
                .build();
    }
    
    @Bean
    public KafkaEndpoint PCQSADISRepsonseKafkaEndpoint() {
        return CitrusEndpoints.kafka()
                .asynchronous()
                .server("localhost:9092")
                .topic("nre.egis.adis.response")
                .build();
    }*/
    
    
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
	
	@Bean
    public HttpClient PCQSHTTPClient() {
        return CitrusEndpoints.http()
                            .client()
                            .requestUrl("http://localhost:8080")
                            .build();
    }
	
	@Bean
    public HttpClient TransformerHTTPClient() {
        return CitrusEndpoints.http()
                            .client()
                            .requestUrl("http://localhost:8852")
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