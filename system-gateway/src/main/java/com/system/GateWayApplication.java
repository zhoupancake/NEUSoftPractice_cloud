package com.system;

import com.system.util.TomcatUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Import;


@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@Import({TomcatUtil.class})
public class GateWayApplication extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(GateWayApplication.class,args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(GateWayApplication.class);
    }
}
