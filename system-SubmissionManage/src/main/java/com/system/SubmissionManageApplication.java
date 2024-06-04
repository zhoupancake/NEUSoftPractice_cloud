package com.system;

import com.system.config.RandomLoadBalancerConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.EnableFeignClients;


@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@LoadBalancerClient(name = "system-SubmissionManage", configuration = RandomLoadBalancerConfig.class)
public class SubmissionManageApplication {
    public static void main(String[] args) {
        SpringApplication.run(SubmissionManageApplication.class,args);
    }
}
