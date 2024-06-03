package com.system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@SpringBootApplication
@EnableDiscoveryClient
public class ReportManageApplication {
    public static void main(String[] args) {
        SpringApplication.run(ReportManageApplication.class,args);
    }
}
