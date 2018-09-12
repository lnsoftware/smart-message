package com.mysoft.smart.message;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class SmartMessageEurekaApplication {

    public static void main(String[] args) {
        SpringApplication.run(SmartMessageEurekaApplication.class, args);
    }
}
