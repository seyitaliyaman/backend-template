package com.project.template.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Logger;
import feign.Retryer;
import feign.codec.Decoder;
import feign.codec.Encoder;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public  class FeignClientConfiguration {


    @Bean
    public Encoder encoder(ObjectMapper objectMapper){
        return new JacksonEncoder(objectMapper);
    }

    @Bean
    public Decoder decoder(ObjectMapper objectMapper){
        return new JacksonDecoder(objectMapper);
    }

    @Bean
    public Retryer retryerLevel(){
        return Retryer.NEVER_RETRY;
    }

    @Bean
    public Logger.Level loggerLevel(){
        return Logger.Level.NONE;
    }
}
