package com.system.api;

import com.system.entity.data.City;
import com.system.service.CityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.List.of;

/**
 * The task inner micro-service api impl
 */
@RestController
@RequestMapping("/api/city")
@Slf4j
@RequiredArgsConstructor
public class CityAPIImpl  implements CityAPI{
    private final CityService cityService;

    /**
     * get city by id
     * @param id the id of the city
     * @return the city object
     */
    @Override
    @PostMapping("/getCityById")
    public City getCityById(@RequestBody Integer id) {
        return cityService.getById(id);
    }

    /**
     * get city by the province and city name
     * @param location contains the province and city name
     * @return the city object
     */
    @Override
    @PostMapping("/getCityByLocation")
    public City getCityByLocation(@RequestBody Map<String, String> location) {
        City result;
        List<City> administratorList = cityService.query()
                .eq("province", location.get("province"))
                .eq("name", location.get("city"))
                .list();
        if (administratorList.isEmpty())
            result = null;
        else
            result = administratorList.get(0);
        return result;
    }

    /**
     * get cities id in the given province
     * @param province the name of the province
     * @return the integer list of the cities id
     */
    @Override
    @PostMapping("/getCitiesByProvince")
    public List<Integer> getCitiesIdByProvince(@RequestBody String province) {
        return cityService.query()
                .eq("province", province)
                .list()
                .stream()
                .map(City::getId)
                .toList();
    }

    /**
     * get cities id in the province except the given city
     * @param cityId the id of the city
     * @return the integer list of the cities id
     */
    @Override
    @PostMapping("/getCitiesSameProvince")
    public List<Integer> getCitiesSameProvince(@RequestBody Integer cityId) {
        City city = cityService.getById(cityId);
        if(city == null)
            return null;
        return cityService.query()
                .eq("province", city.getProvince())
                .ne("id", cityId)
                .list()
                .stream()
                .map(City::getId)
                .toList();
    }

    /**
     * get cities id in the province except the given city
     * @param name
     * @return
     */
    @Override
    @PostMapping("/getCitiesByLikeName")
    public List<Integer> getCitiesByLikeName(@RequestBody String name) {
        return cityService.query()
                .like("name", name)
                .list()
                .stream()
                .map(City::getId)
                .toList();
    }

    @Override
    @GetMapping("/getProvinceList")
    public List<String> getProvinceList() {
        List<City> cityList = cityService.list();
        return cityList.stream()
                .map(City::getProvince)
                .distinct()
                .collect(Collectors.toList());
    }
}

