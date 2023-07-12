package com.micorserviceImpl.OrderService.config;

import com.micorserviceImpl.OrderService.external.decoder.CustomDecoder;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig{
    @Bean
    ErrorDecoder errorDecoder(){
        return new CustomDecoder();
    }
}
