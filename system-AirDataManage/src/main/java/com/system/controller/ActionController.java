package com.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.system.common.HttpResponseEntity;
import com.system.dto.ResponseAirDataEntity;
import com.system.entity.data.AirData;
import com.system.entity.data.City;
import com.system.entity.data.Report;
import com.system.entity.data.Submission;
import com.system.mapper.AirDataMapper;
import com.system.service.AirDataService;
import com.system.service.CityServiceFeignClient;
import com.system.util.AQIUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/airData")
@Slf4j
@RequiredArgsConstructor
public class ActionController {
    private final AirDataService airDataService;
    private final CityServiceFeignClient cityService;

    @PostMapping("/administrator/selectAll/page")
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

    @GetMapping("/administrator/selectAll")
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
        return HttpResponseEntity.response(success,"query ", result);
    }

    @PostMapping("/administrator/selectByProvince")
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
        return HttpResponseEntity.success("query ", result);
    }

    @PostMapping("/administrator/queryAirDataList")
    public HttpResponseEntity queryAirDataList(@RequestBody Map<String, Object> map) throws ParseException {
        if((Integer) map.get("pageNum") < 1 || (Integer) map.get("pageSize") < 1)
            return HttpResponseEntity.error("pageNum and pageSize must be positive");
        QueryWrapper<AirData> queryWrapper = new QueryWrapper<>();
        if(map.containsKey("id") && map.get("id") != null&& !map.get("id").equals(""))
            queryWrapper.like("id", map.get("id"));
        if(map.containsKey("city") && map.get("city") != null && !map.get("city").equals("")){
            if(map.containsKey("province") && map.get("province") != null && !map.get("province").equals("")){
                City city = cityService.getCityByLocation(Map.of("province", (String) map.get("province"),
                        "city", (String) map.get("city")));
                if(city == null)
                    return HttpResponseEntity.error("the selected city is not exist");
                queryWrapper.eq("city_id", city.getId());
            }
            else{
                List<Integer> citiesList = cityService.getCitiesByLikeName((String) map.get("city"));
                if(citiesList.isEmpty())
                    return HttpResponseEntity.error("the selected city is not exist in the air data list");
                for (Integer cityId : citiesList)
                    queryWrapper.or().eq("city_id", cityId);
            }
        }
        else{
            if(map.containsKey("province") && map.get("province") != null && !map.get("province").equals("")){
                List<Integer> citiesList = cityService.getCitiesIdByProvince((String) map.get("province"));
                for (Integer cityId : citiesList)
                    queryWrapper.or().eq("city_id", cityId);
            }
        }
        if(map.containsKey("location") && map.get("location") != null && !map.get("location").equals(""))
            queryWrapper.like("location", map.get("location"));
        if(map.containsKey("startTime") && map.get("startTime") != null && !map.get("startTime").equals("")) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date startDate = dateFormat.parse(String.valueOf(map.get("startTime")));
            Timestamp startTime = new Timestamp(startDate.getTime());
            queryWrapper.lambda().ge(AirData::getDate,startTime);
        }
        if(map.containsKey("endTime") && map.get("endTime") != null && !map.get("endTime").equals("")) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date startDate = dateFormat.parse(String.valueOf(map.get("endTime")));
            Timestamp endTime = new Timestamp(startDate.getTime());
            queryWrapper.lambda().le(AirData::getDate,endTime);
        }
        if(map.containsKey("aqiLevel") && map.get("aqiLevel") != null && !map.get("aqiLevel").equals("")) {
            if((Integer) map.get("aqiLevel") < 1)
                return HttpResponseEntity.error("aqiLevel must be positive");
            queryWrapper.ge("aqi_level", (Integer) map.get("aqiLevel"));
        }
        if(map.containsKey("pm25Level") && map.get("pm25Level") != null && !map.get("pm25Level").equals("")) {
            if((Integer) map.get("aqiLevel") < 1)
                return HttpResponseEntity.error("aqiLevel must be positive");
            queryWrapper.ge("pm25", AQIUtil.AQILevel2value_pm25((Integer) map.get("pm25Level")));
        }
        if(map.containsKey("so2Level") && map.get("so2Level") != null && !map.get("so2Level").equals("")) {
            if((Integer) map.get("aqiLevel") < 1)
                return HttpResponseEntity.error("aqiLevel must be positive");
            queryWrapper.ge("so2", AQIUtil.AQILevel2value_so2((Integer) map.get("so2Level")));
        }
        if(map.containsKey("coLevel") && map.get("coLevel") != null && !map.get("coLevel").equals("")) {
            if((Integer) map.get("aqiLevel") < 1)
                return HttpResponseEntity.error("aqiLevel must be positive");
            queryWrapper.ge("co", AQIUtil.AQILevel2value_co((Integer) map.get("coLevel")));
        }
        Page<AirData> page = new Page<>((Integer) map.get("pageNum"), (Integer) map.get("pageSize"));
        Page<AirData> airDataPage = airDataService.page(page, queryWrapper);
        List<AirData> airDataList = airDataPage.getRecords();
        List<ResponseAirDataEntity> result = new ArrayList<ResponseAirDataEntity>();
        boolean success = !airDataList.isEmpty();
        if(success)
            for(AirData airData: airDataList) {
                City city = cityService.getCityById(airData.getCityId());
                result.add(new ResponseAirDataEntity(airData, city));
            }
        return HttpResponseEntity.response(!airDataList.isEmpty(), "query air data ", result);
    }
}
