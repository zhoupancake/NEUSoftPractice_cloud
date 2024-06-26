package com.system.api;

import com.system.entity.data.City;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

public interface CityAPI {
    @PostMapping("/api/city/getCityById")
    public City getCityById(@RequestBody Integer id);

    @PostMapping("/api/city/getCityByLocation")
    public City getCityByLocation(@RequestBody Map<String, String> location);

    @PostMapping("/api/city/getCitiesByProvince")
    public List<Integer> getCitiesIdByProvince(@RequestBody String province);

    @PostMapping("/api/city/getCitiesSameProvince")
    public List<Integer> getCitiesSameProvince(@RequestBody Integer cityId);

    @PostMapping("/api/city/getCitiesByLikeName")
    public List<Integer> getCitiesByLikeName(@RequestBody String name);

    @GetMapping("/api/city/getProvinceList")
    public List<String> getProvinceList();
}
