package com.example.ai_job_matching.config;


import org.apache.hc.core5.http.HttpHost;
import org.opensearch.client.RestClient;
import org.opensearch.client.json.jackson.JacksonJsonpMapper;
import org.opensearch.client.opensearch.OpenSearchClient;
import org.opensearch.client.opensearch.core.InfoResponse;
import org.opensearch.client.transport.rest_client.RestClientTransport;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class OpenSearchConfig {

   final HttpHost host = new HttpHost("http", "localhost", 9200);
    RestClient restClient = RestClient.builder(host).build();

    @Bean
    public OpenSearchClient openSearchClient(){

        RestClientTransport restClientTransport = new RestClientTransport(restClient, new JacksonJsonpMapper());

        return new OpenSearchClient(restClientTransport);
    }

    @Bean
    public CommandLineRunner commandRunner(OpenSearchClient openSearchClient) throws IOException {
        return args -> {
            InfoResponse info = openSearchClient.info();
            System.out.println(info.version().number());
        };

    }

}
