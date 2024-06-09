package com.system.controller;

import com.system.common.HttpResponseEntity;
import com.system.dto.RequestReportEntity;
import com.system.entity.data.AirData;
import com.system.entity.data.City;
import com.system.entity.data.Report;
import com.system.service.AirDataServiceFeignClient;
import com.system.service.CityServiceFeignClient;
import com.system.service.ReportService;
import com.system.util.SnowflakeUtil;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/report")
@Slf4j
@RequiredArgsConstructor
public class ActionController {
    private final ReportService reportService;
    @Resource
    private final AirDataServiceFeignClient airDataService;
    @Resource
    private final CityServiceFeignClient cityService;

    @PostMapping("/report")
    public HttpResponseEntity addReport(@RequestBody RequestReportEntity requestReportEntity) {
        Report report = requestReportEntity.getReport_create();
        AirData airData = requestReportEntity.getAirData_create();


        String airDataId = SnowflakeUtil.genId();
        airData.setId(airDataId);
        City city = cityService.getCityByLocation(requestReportEntity.getLocation());
        System.out.println(requestReportEntity.getLocation());
        airData.setCityId(city.getId());
        report.setId(SnowflakeUtil.genId());
        report.setRelativeAirDataId(airDataId);
        report.setStatus(0);

        boolean reportSuccess = reportService.save(report);
        boolean airDataSuccess = airDataService.addAirData(airData);
        return HttpResponseEntity.response(reportSuccess&&airDataSuccess, "create report ", null);
    }
}
