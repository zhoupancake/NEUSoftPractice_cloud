package com.system.service;

import com.system.entity.data.City;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

@FeignClient(name="system-ZoneManage", url="localhost:8086") //  远程服务的名称
public interface CityServiceFeignClient {
    @PostMapping("/api/city/getCityById")
    public City getCityById(@RequestBody Integer id);

    @PostMapping("/api/city/getCityByLocation")
    public City getCityByLocation(@RequestBody Map<String, String> location);

    @PostMapping("/api/city/getCitiesByProvince")
    public List<Integer> getCitiesIdByProvince(@RequestBody String province);

    @PostMapping("/getCitiesByLikeName")
    public List<Integer> getCitiesByLikeName(@RequestBody String name);
}
