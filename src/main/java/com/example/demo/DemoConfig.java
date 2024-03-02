package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class DemoConfig {

    @Bean
    public RestClient restClient(RestClient.Builder restClientBuilder){
        return restClientBuilder.baseUrl("https://mermaid.ink/img/").build();
    }
}
