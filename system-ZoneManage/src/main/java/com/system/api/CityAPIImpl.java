package com.system.api;

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
@RequestMapping("/api")
@Slf4j
@RequiredArgsConstructor
public class CityAPIImpl  implements CityAPI{
    private final CityService cityService;
    @Override
    @PostMapping("/getCityById")
    public City getCityById(String id) {
        return cityService.getById(id);
    }

    @Override
    @PostMapping("/getCityByLocation")
    public City getCityByLocation(@RequestBody Map<String, String> location) {
        City result;
        List<City> administratorList = cityService.query()
                .eq("province", location.get("province"))
                .eq("name", location.get("city"))
                .list();
        if (administratorList.isEmpty()) {
            result = null;
        } else {
            result = administratorList.get(0);
        }
        return result;
    }
    }

