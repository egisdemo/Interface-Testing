package configurations;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.ws.soap.SoapMessageFactory;
import org.springframework.ws.soap.saaj.SaajSoapMessageFactory;
import org.springframework.xml.xsd.SimpleXsdSchema;

import com.consol.citrus.dsl.endpoint.CitrusEndpoints;
import com.consol.citrus.endpoint.adapter.StaticEndpointAdapter;
import com.consol.citrus.http.client.HttpClient;
import com.consol.citrus.http.client.HttpsEndpointComponent;
import com.consol.citrus.http.message.HttpMessage;
import com.consol.citrus.http.server.HttpServer;
import com.consol.citrus.kafka.embedded.EmbeddedKafkaServer;
import com.consol.citrus.kafka.embedded.EmbeddedKafkaServerBuilder;
import com.consol.citrus.kafka.endpoint.KafkaEndpoint;
import com.consol.citrus.message.Message;
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
                .topics("nre.egis.dos.request", "nre.egis.dos.response", "nre.egis.adis.request", "nre.egis.adis.response", "pcqs.adis.request")
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
    
    @Bean
    public KafkaEndpoint CamelKafkaEndpoint() {
        return CitrusEndpoints.kafka()
                .asynchronous()
                .server("localhost:9092")
                .consumerProperties(camelKafka())
                .consumerGroup("adis.request")
                .offsetReset("earliest")
                .topic("pcqs.adis.request")
                .build();
    }
    
    private Map<String, Object>  camelKafka(){
    	Map<String, Object> kafkaProperties = new HashMap<>();
    	String bridgeEndpoint= "true";   	
    	
    	kafkaProperties.put("bridgeEndpoint", bridgeEndpoint);
    	
    	
    	return kafkaProperties;
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
	
	@Bean
    public HttpClient camelOrchestratorHTTPClient() {
        return CitrusEndpoints.http()
                            .client()
                            .requestUrl("http://localhost:8085")
                            .build();
    }
	
	@Bean
    public HttpClient mappingHTTPClient() {
        return CitrusEndpoints.http()
                            .client()
                            .requestUrl("http://localhost:8080")
                            .build();
    }
	
	/**
	 * HTTP server endpoints
	 */
	
	/*@Bean
    public HttpServer CCDHTTPServer() throws Exception {
        return CitrusEndpoints.http()
                .server()
                .port(8080)
                //.endpointAdapter(staticEndpointAdapter())
                .autoStart(true)
                .build();
    }*/
	
	@Bean
    public HttpServer TransformerServer() throws Exception {
        return CitrusEndpoints.http()
                .server()
                .port(8852)
                //.endpointAdapter(staticEndpointAdapter())
                .autoStart(true)
                .build();
    }
	
	@Bean
    public HttpServer mappingAPIRedisServer() throws Exception {
        return CitrusEndpoints.http()
                .server()
                .port(6379)
                //.endpointAdapter(staticEndpointAdapter())
                .autoStart(true)
                .build();
    }
	
	/*@Bean
    public StaticEndpointAdapter staticEndpointAdapter() {
        return new StaticEndpointAdapter() {
            @Override
            protected Message handleMessageInternal(Message message) {
                return new HttpMessage("<todo xmlns=\"http://citrusframework.org/samples/todolist\">" +
                            "<id>100</id>" +
                            "<title>todoName</title>" +
                            "<description>todoDescription</description>" +
                        "</todo>")
                		.
                        .status(HttpStatus.OK);
            }
        };
    }*/
	
	
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