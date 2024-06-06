package com.system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ZoneManageApplication {
    public static void main(String[] args) {
        SpringApplication.run(ZoneManageApplication.class,args);
    }
}
