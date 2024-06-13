package com.system.service;

import com.system.entity.data.AirData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name="system-AirDataManage", url="localhost:8085") //  远程服务的名称
public interface AirDataServiceFeignClient {
    @PostMapping("/getAirDataById")
    public AirData getAirDataById(@RequestBody String id);
}