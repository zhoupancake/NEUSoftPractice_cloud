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

/**
 * The type Air data inner micro-service api impl.
 */
@RestController
@RequestMapping("/api/airData")
@Slf4j
@RequiredArgsConstructor
public class AirDataAPIImpl  implements AirDataAPI{
    private final AirDataService airDataService;

    /**
     *
     * @param airData the air data needed to be added
     * @return boolean to indicate whether the operation is successful
     */
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
}
