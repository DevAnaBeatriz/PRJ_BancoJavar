package com.client.ana.javer.config;

import com.client.ana.javer.exceptions.FeignErrorDecoder;

import feign.RequestInterceptor;
import feign.auth.BasicAuthRequestInterceptor;
import feign.codec.ErrorDecoder;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Base64;

@Configuration
public class FeignClientConfig {

    private static final String USERNAME = "admin";
    private static final String PASSWORD = "ana123";

    @Bean
    public ErrorDecoder errorDecoder() {
        return new FeignErrorDecoder();
    }

    @Bean
    public BasicAuthRequestInterceptor basicAuthRequestInterceptor() {
        return new BasicAuthRequestInterceptor("admin", "ana123");
    }

    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> {
            String auth = USERNAME + ":" + PASSWORD;
            String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes());
            String authHeader = "Basic " + encodedAuth;

            requestTemplate.header("Authorization", authHeader);
        };
    }
}

