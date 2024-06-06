package com.system.api;

import com.system.entity.data.City;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

public interface CityAPI {
    @PostMapping("/getCityById")
    public City getCityById(@RequestBody String id);

    @PostMapping("/getCityByLocation")
    public City getCityByLocation(@RequestBody Map<String, String> location);
}
