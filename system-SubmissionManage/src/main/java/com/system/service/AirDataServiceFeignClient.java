package com.system.service;

import com.system.entity.data.AirData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

@FeignClient(name="system-AirDataManage", url="localhost:8085") //  远程服务的名称
public interface AirDataServiceFeignClient {
    @PostMapping("/api/airData/addAirData")
    public boolean addAirData(@RequestBody AirData airData);

    @PostMapping("/api/airData/modifyAirData")
    public boolean modifyAirData(@RequestBody AirData airData);

    @PostMapping("/api/airData/deleteAirData")
    public boolean deleteAirDataById(@RequestBody AirData airData);

    @PostMapping("/api/airData/getAirDataById")
    public AirData getAirDataById(@RequestBody String id);

    @PostMapping("/api/airData/queryAirDataList")
    public List<AirData> queryAirDataList(@RequestBody Map<String, Object> map);
}