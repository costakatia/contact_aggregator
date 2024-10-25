package org.aggregator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "org.aggregator")
public class ContactAggregatorApplication {
    public static void main(String[] args) {
        SpringApplication.run(ContactAggregatorApplication.class, args);
    }
}