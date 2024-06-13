package com.system.service;

import com.system.entity.data.City;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

public interface CityServiceFeignClient {
    @PostMapping("/api/getCityById")
    public City getCityById(@RequestBody Integer id);

    @PostMapping("/api/getCityByLocation")
    public City getCityByLocation(@RequestBody Map<String, String> location);

    @PostMapping("/api/getCitiesByProvince")
    public List<Integer> getCitiesIdByProvince(@RequestBody String province);

    @PostMapping("/api/getCitiesSameProvince")
    public List<Integer> getCitiesSameProvince(@RequestBody Integer cityId);

}
