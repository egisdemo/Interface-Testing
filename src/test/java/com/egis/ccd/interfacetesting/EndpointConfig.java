package com.egis.ccd.interfacetesting;

import org.springframework.context.annotation.Bean;

import com.consol.citrus.dsl.endpoint.CitrusEndpoints;
import com.consol.citrus.http.client.HttpClient;

public class EndpointConfig {
	@Bean
    public HttpClient CCDClient() {
        return CitrusEndpoints.http()
                            .client()
                            .requestUrl("http://localhost:8080")
                            .build();
    }
}