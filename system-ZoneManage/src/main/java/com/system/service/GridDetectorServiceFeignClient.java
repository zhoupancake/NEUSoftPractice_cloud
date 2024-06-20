package com.system.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient(name="system-PermissionControl", url="localhost:8081") //  远程服务的名称
public interface GridDetectorServiceFeignClient {
    @PostMapping("/api/getDetectorCities")
    public List<Integer> getDetectorCities();
}
