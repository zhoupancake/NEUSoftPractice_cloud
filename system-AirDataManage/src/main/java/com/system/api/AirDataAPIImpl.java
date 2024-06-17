package com.system.api;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
@RequestMapping("/api/airData")
@Slf4j
@RequiredArgsConstructor
public class AirDataAPIImpl  implements AirDataAPI{
    private final AirDataService airDataService;

    @Override
    @PostMapping("/addAirData")
    public boolean addAirData(@RequestBody AirData airData) {
        if(airData.getPm25() < 0 || airData.getPm10() < 0 || airData.getSo2() < 0 || airData.getCo() < 0 || airData.getO3() < 0 || airData.getNo2() < 0)
            return false;
        if(airData.getPm25() > 500)
            airData.setPm25(500.0);
        if(airData.getSo2() > 2620)
            airData.setSo2(2620.0);
        if(airData.getCo() > 60)
            airData.setCo(60.0);
        airData.setId(SnowflakeUtil.genId());
        airData.setAqiLevel(getAQILevel(airData.getPm25(), airData.getSo2(), airData.getCo()));
        airData.setAqi(getAQI(airData.getPm25(), airData.getSo2(), airData.getCo()));

        return airDataService.save(airData);
    }

//    @Override
//    @PostMapping("/modifyAirData")
//    public boolean modifyAirData(@RequestBody AirData airData) {
//        return airDataService.updateById(airData);
//    }
//
//    @Override
//    @PostMapping("/deleteAirData")
//    public boolean deleteAirDataById(@RequestBody AirData airData) {
//        return airDataService.removeById(airData);
//    }
//
//    @Override
//    @PostMapping("/getAirDataById")
//    public AirData getAirDataById(String id) {
//        return airDataService.getById(id);
//    }
//
//    @Override
//    @PostMapping("/queryAirDataList")
//    public List<AirData> queryAirDataList(@RequestBody Map<String, Object> map) {
//        Integer pageNum = (Integer) map.get("pageNum");
//        Integer pageSize = (Integer) map.get("pageSize");
//        Page<AirData> page = new Page<>(pageNum, pageSize);
//        airDataService.query().eq("status", "1")
//                .like("username", map.get("username")).page(page);
//        return page.getRecords();
//    }
}
