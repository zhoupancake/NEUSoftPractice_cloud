package com.system;

import com.system.config.RandomLoadBalancerConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;

@SpringBootApplication
@EnableDiscoveryClient
@LoadBalancerClient(name = "system-PermissionControl", configuration = RandomLoadBalancerConfig.class)
public class PermissionControlApplication {
    public static void main(String[] args) {
        SpringApplication.run(PermissionControlApplication.class,args);
    }
}
