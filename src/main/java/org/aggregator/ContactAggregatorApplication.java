package org.aggregator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(scanBasePackages = {"org.config","org.controller", "org.service"})
public class ContactAggregatorApplication {
    public static void main(String[] args) {
        SpringApplication.run(ContactAggregatorApplication.class, args);
    }
}