package com.system.controller;

import com.system.common.HttpResponseEntity;
import com.system.dto.RequestReportEntity;
import com.system.entity.data.AirData;
import com.system.entity.data.Report;
import com.system.service.AirDataServiceFallback;
import com.system.service.ReportService;
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
    private AirDataServiceFallback airDataServiceFallback;
    @PostMapping("/report")
    public HttpResponseEntity report(@RequestBody RequestReportEntity requestReportEntity) {
        Report report = requestReportEntity.getReport_create();
        AirData airData = requestReportEntity.getAirData_create();

        boolean airDataSuccess = airDataServiceFallback.addAirData(airData);
        boolean reportSuccess =reportService.save(report);

        return HttpResponseEntity.response(airDataSuccess&&reportSuccess, "修改", null);
    }
}
