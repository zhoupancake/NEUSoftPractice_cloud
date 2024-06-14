package com.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.system.common.HttpResponseEntity;
import com.system.dto.ResponseAirDataEntity;
import com.system.entity.data.AirData;
import com.system.entity.data.City;
import com.system.service.AirDataService;
import com.system.service.CityServiceFeignClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/airData")
@Slf4j
@RequiredArgsConstructor
public class ActionController {
    private final AirDataService airDataService;
    private final CityServiceFeignClient cityService;

    @PostMapping("/selectAll/page")
    public HttpResponseEntity selectAll(@RequestBody Map<String, Object> map) {
        if((Integer)map.get("pageNum") < 1 || (Integer)map.get("pageSize") < 1)
            return HttpResponseEntity.error("pageNum and pageSize must be positive");
        QueryWrapper<AirData> queryWrapper = new QueryWrapper<>();
        Page<AirData> page = new Page<>((Integer)map.get("pageNum"), (Integer)map.get("pageSize"));
        Page<AirData> airDataPage = airDataService.page(page, queryWrapper);

        List<AirData> airDataList = airDataPage.getRecords();
        List<ResponseAirDataEntity> result = new ArrayList<>();
        boolean success = !airDataList.isEmpty();
        if(success)
            for(AirData airData: airDataList) {
                City city = cityService.getCityById(airData.getCityId());
                result.add(new ResponseAirDataEntity(airData, city));
            }
        return HttpResponseEntity.success("query successfully", result);
    }

    @GetMapping("/selectAll")
    public HttpResponseEntity selectAll() {
        QueryWrapper<AirData> queryWrapper = new QueryWrapper<>();
        List<AirData> airDataList = airDataService.list(queryWrapper);
        List<ResponseAirDataEntity> result = new ArrayList<>();
        boolean success = !airDataList.isEmpty();
        if(success)
            for(AirData airData: airDataList) {
                City city = cityService.getCityById(airData.getCityId());
                result.add(new ResponseAirDataEntity(airData, city));
            }
        return HttpResponseEntity.response(success,"query successfully", result);
    }

    @PostMapping("/selectByProvince")
    public HttpResponseEntity selectByProvince(@RequestBody Map<String, Object> map) {
        QueryWrapper<AirData> queryWrapper = new QueryWrapper<>();
        List<Integer> cityIdList = cityService.getCitiesIdByProvince((String)map.get("province"));
        queryWrapper.in("city_id", cityIdList);

        Page<AirData> page = new Page<>((Integer)map.get("pageNum"), (Integer)map.get("pageSize"));
        Page<AirData> airDataPage = airDataService.page(page, queryWrapper);

        List<AirData> airDataList = airDataPage.getRecords();
        List<ResponseAirDataEntity> result = new ArrayList<>();
        boolean success = !airDataList.isEmpty();
        if(success)
            for(AirData airData: airDataList) {
                City city = cityService.getCityById(airData.getCityId());
                result.add(new ResponseAirDataEntity(airData, city));
            }
        return HttpResponseEntity.success("query successfully", result);
    }

}
