package com.system.api;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.system.common.HttpResponseEntity;
import com.system.entity.data.AirData;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

/**
 * The interface AirData inner micro-service api.
 */
public interface AirDataAPI {
    /**
     * Add air data boolean.
     *
     * @param airData the air data
     * @return the boolean to indicate whether the operation is successful
     */
    @PostMapping("/api/airData/addAirData")
    public boolean addAirData(@RequestBody AirData airData);
}
