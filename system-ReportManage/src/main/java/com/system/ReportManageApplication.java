package com.system;

import com.system.config.RandomLoadBalancerConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.EnableFeignClients;


@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients
@LoadBalancerClient(name = "system-ReportManage", configuration = RandomLoadBalancerConfig.class)
public class ReportManageApplication {
    public static void main(String[] args) {
        SpringApplication.run(ReportManageApplication.class,args);
    }
}
