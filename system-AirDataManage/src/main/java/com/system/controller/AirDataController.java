package com.system.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.system.common.HttpResponseEntity;
import com.system.entity.data.AirData;
import com.system.service.AirDataService;
import com.system.util.SnowflakeUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

import static com.system.util.AQIUtil.getAQI;
import static com.system.util.AQIUtil.getAQILevel;

@RestController
@RequestMapping("/airData")
@Slf4j
@RequiredArgsConstructor
public class AirDataController {
    private final AirDataService airDataService;
    @PostMapping("/addAirData")
    public HttpResponseEntity addAirData(@RequestBody AirData airData) {
        airData.setId(SnowflakeUtil.genId());
        airData.setAqiLevel(getAQILevel(airData.getPm25(), airData.getSo2(), airData.getCo()));
        airData.setAqi(getAQI(airData.getPm25(), airData.getSo2(), airData.getCo()));

        boolean success = airDataService.save(airData);
        return HttpResponseEntity.response(success, "create airData ", null);
    }

    @PostMapping("/modifyAirData")
    public HttpResponseEntity modifyAirData(@RequestBody AirData airData) {
        airData.setCityId(airData.getCityId());
        boolean success = airDataService.updateById(airData);
        return HttpResponseEntity.response(success, "modify airData", null);
    }

    @PostMapping("/deleteAirData")
    public HttpResponseEntity deleteAirDataById(@RequestBody AirData airData) {
        boolean success = airDataService.removeById(airData);
        return HttpResponseEntity.response(success, "delete airData ", null);
    }

    @PostMapping("/queryAirDataList")
    public HttpResponseEntity queryAirDataList(@RequestBody Map<String, Object> map) {
        Integer pageNum = (Integer) map.get("pageNum");
        Integer pageSize = (Integer) map.get("pageSize");
        Page<AirData> page = new Page<>(pageNum, pageSize);
        airDataService.query().eq("status", "1")
                .like("username", map.get("username")).page(page);
        List<AirData> airDataList = page.getRecords();
        boolean success = !airDataList.isEmpty();
        return HttpResponseEntity.response(success, "查询", airDataList);
    }
}
