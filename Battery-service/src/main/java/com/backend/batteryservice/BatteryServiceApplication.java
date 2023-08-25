package com.backend.batteryservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class BatteryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BatteryServiceApplication.class, args);
    }

}
