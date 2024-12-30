package com.subroute.config;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {


    //http://localhost:8080/swagger-ui.html

    @Bean
    public GroupedOpenApi publicApi() {

        return GroupedOpenApi.builder()
                .group("public-api")
                .pathsToMatch("/**")  // Adjust this to your API path
                .build();
    }
}