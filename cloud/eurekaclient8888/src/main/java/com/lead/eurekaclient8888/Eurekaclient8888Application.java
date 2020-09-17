package com.lead.eurekaclient8888;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author Administrator
 */
@SpringBootApplication
@EnableEurekaClient
public class Eurekaclient8888Application {

    public static void main(String[] args) {
        SpringApplication.run(Eurekaclient8888Application.class, args);
    }

}