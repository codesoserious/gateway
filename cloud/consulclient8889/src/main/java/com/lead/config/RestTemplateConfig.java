package com.lead.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author Administrator
 */
@Configuration
public class RestTemplateConfig {
    /**
     * LoadBalanced 注解作用是将restTemplate变为带有ribbon的restTemplate
     *
     * @return
     */
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
