package com.system.api;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.system.entity.data.AirData;
import com.system.service.AirDataService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@Slf4j
@RequiredArgsConstructor
public class AirDataAPIImpl  implements AirDataAPI{
    private final AirDataService airDataService;

    @Override
    @PostMapping("/addAirData")
    public boolean addAirData(@RequestBody AirData airData) {
        return airDataService.save(airData);
    }

    @Override
    @PostMapping("/modifyAirData")
    public boolean modifyAirData(@RequestBody AirData airData) {
        return airDataService.updateById(airData);
    }

    @Override
    @PostMapping("/deleteAirData")
    public boolean deleteAirDataById(@RequestBody AirData airData) {
        return airDataService.removeById(airData);
    }

    @Override
    @PostMapping("/getAirDataById")
    public AirData getAirDataById(String id) {
        return airDataService.getById(id);
    }

    @Override
    @PostMapping("/queryAirDataList")
    public List<AirData> queryAirDataList(@RequestBody Map<String, Object> map) {
        Integer pageNum = (Integer) map.get("pageNum");
        Integer pageSize = (Integer) map.get("pageSize");
        Page<AirData> page = new Page<>(pageNum, pageSize);
        airDataService.query().eq("status", "1")
                .like("username", map.get("username")).page(page);
        return page.getRecords();
    }
}
