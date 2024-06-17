package com.system.controller;

import com.system.common.HttpResponseEntity;
import com.system.entity.data.City;
import com.system.service.CityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/city")
@Slf4j
@RequiredArgsConstructor
public class ActionController {
    private final CityService cityService;
    @GetMapping("selectAll")
    public HttpResponseEntity selectAll(){
        List<City> cities = cityService.query().list();
        return HttpResponseEntity.success("query ",cities);
    }

    @PostMapping("selectByProvince")
    public HttpResponseEntity selectByProvince(@RequestBody String province){
        List<City> cities = cityService.query().eq("province",province).list();
        return HttpResponseEntity.response(!cities.isEmpty(), "query ",cities);
    }
}
