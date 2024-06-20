package com.system.controller;

import com.system.common.HttpResponseEntity;
import com.system.entity.data.City;
import com.system.service.CityService;
import com.system.service.GridDetectorServiceFeignClient;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.TreeSet;

@RestController
@RequestMapping("/city")
@Slf4j
@RequiredArgsConstructor
public class ActionController {
    private final CityService cityService;
    @Resource
    private final GridDetectorServiceFeignClient gridDetectorService;
    @GetMapping("/selectAll")
    public HttpResponseEntity selectAll(){
        List<City> cities = cityService.query().list();
        return HttpResponseEntity.success("query ",cities);
    }

    @PostMapping("/selectByProvince")
    public HttpResponseEntity selectByProvince(@RequestBody String province){
        List<City> cities = cityService.query().eq("province",province).list();
        return HttpResponseEntity.response(!cities.isEmpty(), "query ",cities);
    }

    @GetMapping("/administrator/getProvince")
    public HttpResponseEntity getProvince(){
        List<Integer> cities = gridDetectorService.getDetectorCities();
        TreeSet<String> provinceSet = new TreeSet<>();
        for (Integer cityId : cities) {
            City city = cityService.getById(cityId);
            provinceSet.add(city.getProvince());
        }
        List<String> provinceList = provinceSet.stream().toList();
        Map<String, Object> result = Map.of("provinces",provinceList, "rate", (provinceList.size() / 34.0));
        return HttpResponseEntity.response(!provinceList.isEmpty(), "query ",result);
    }

    @GetMapping("/administrator/getBigCity")
    public HttpResponseEntity getBigCity(){
        List<Integer> cities = gridDetectorService.getDetectorCities();
        List<String> cityList = cities.stream().map(cityId -> cityService.getById(cityId).getName()).toList();
        Map<String, Object> result = Map.of("cities", cityList, "rate", (cityList.size() / (double)cityService.count()));
        return HttpResponseEntity.response(!cityList.isEmpty(), "query ",result);
    }
}
