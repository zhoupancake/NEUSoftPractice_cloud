package com.system.service;

import com.system.entity.data.City;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@FeignClient(name="system-ZoneManage", url="localhost:8086") //  远程服务的名称
public interface CityServiceFeignClient {
    @PostMapping("/getCityById")
    public City getCityById(@RequestBody String id);

    @PostMapping("/getCityByLocation")
    public City getCityByLocation(@RequestBody Map<String, String> location);

}
