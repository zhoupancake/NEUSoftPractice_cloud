package com.system.service;

import com.system.entity.data.AirData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

@FeignClient(name="system-AirDataManage", url="localhost:8085") //  远程服务的名称
public interface AirDataServiceFallback {
    @PostMapping("/addAirData")
    public boolean addAirData(@RequestBody AirData airData);

    @PostMapping("/modifyAirData")
    public boolean modifyAirData(@RequestBody AirData airData);

    @PostMapping("/deleteAirData")
    public boolean deleteAirDataById(@RequestBody AirData airData);

    @PostMapping("/getAirDataById")
    public AirData getAirDataById(@RequestBody String id);

    @PostMapping("/queryAirDataList")
    public List<AirData> queryAirDataList(@RequestBody Map<String, Object> map);
}