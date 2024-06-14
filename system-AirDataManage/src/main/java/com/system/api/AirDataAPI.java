package com.system.api;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.system.common.HttpResponseEntity;
import com.system.entity.data.AirData;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

public interface AirDataAPI {
    @PostMapping("/addAirData")
    public boolean addAirData(@RequestBody AirData airData);

//    @PostMapping("/modifyAirData")
//    public boolean modifyAirData(@RequestBody AirData airData);
//
//    @PostMapping("/deleteAirData")
//    public boolean deleteAirDataById(@RequestBody AirData airData);
//
//    @PostMapping("/getAirDataById")
//    public AirData getAirDataById(@RequestBody String id);
//
//    @PostMapping("/queryAirDataList")
//    public List<AirData> queryAirDataList(@RequestBody Map<String, Object> map);
}
