package com.system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;


@SpringBootApplication
//annotation to enable nacos discovery client
@EnableDiscoveryClient
//annotation to enable feign client(provider)
@EnableFeignClients
public class AirDataManageApplication {
    public static void main(String[] args) {
        SpringApplication.run(AirDataManageApplication.class,args);
    }
}
