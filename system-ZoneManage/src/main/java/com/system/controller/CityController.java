package com.system.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.system.common.HttpResponseEntity;
import com.system.entity.data.City;
import com.system.service.CityService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/city")
@Slf4j
@RequiredArgsConstructor
public class CityController {
    private final CityService cityService;
    @PostMapping("/addCity")
    public HttpResponseEntity addCity(@RequestBody City city) {
        boolean success = cityService.save(city);
        return HttpResponseEntity.response(success, "create city ", null);
    }

    @PostMapping("/modifyCity")
    public HttpResponseEntity modifyCity(@RequestBody City city) {
        boolean success = cityService.updateById(city);
        return HttpResponseEntity.response(success, "modify city", null);
    }

    @PostMapping("/deleteCity")
    public HttpResponseEntity deleteCityById(@RequestBody City city) {
        boolean success = cityService.removeById(city);
        return HttpResponseEntity.response(success, "delete city ", null);
    }

    @PostMapping("/getCityById")
    public City getCityById(String id) {
        return cityService.getById(id);
    }

    @PostMapping("/getCityByLocation")
    public City getCityByLocation(@RequestBody Map<String, String> location) {
        City result;
        List<City> administratorList = cityService.query()
                .eq("province", location.get("province"))
                .eq("name", location.get("city"))
                .list();
        System.out.println(administratorList);
        if (administratorList.isEmpty())
            result = null;
        else
            result = administratorList.get(0);
        return result;
    }

    @PostMapping("/queryCityList")
    public HttpResponseEntity queryCityList(@RequestBody Map<String, Object> map) {
        Integer pageNum = (Integer) map.get("pageNum");
        Integer pageSize = (Integer) map.get("pageSize");
        Page<City> page = new Page<>(pageNum, pageSize);
        cityService.query().eq("status", "1")
                .like("username", map.get("username")).page(page);
        List<City> cityList = page.getRecords();
        boolean success = !cityList.isEmpty();
        return HttpResponseEntity.response(success, "查询", cityList);
    }
}
