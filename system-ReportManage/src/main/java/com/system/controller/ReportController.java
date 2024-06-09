package com.system.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.system.common.HttpResponseEntity;
import com.system.dto.RequestCharacterEntity;
import com.system.dto.RequestReportEntity;
import com.system.entity.data.AirData;
import com.system.entity.data.Report;
import com.system.service.AirDataServiceFeignClient;
import com.system.service.ReportService;
import com.system.util.SnowflakeUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/report")
@Slf4j
@RequiredArgsConstructor
public class ReportController {
    private final ReportService reportService;
    private final AirDataServiceFeignClient airDataService;
    @PostMapping("/addReport")
    public HttpResponseEntity addReport(@RequestBody RequestReportEntity requestReportEntity) {
        Report report = requestReportEntity.getReport_create();
        AirData airData = requestReportEntity.getAirData_create();

        String airDataId = SnowflakeUtil.genId();
        airData.setId(airDataId);
        report.setId(SnowflakeUtil.genId());
        report.setRelativeAirDataId(airDataId);
        report.setStatus(0);

        boolean reportSuccess = reportService.save(report);
        boolean airDataSuccess = airDataService.addAirData(airData);
        return HttpResponseEntity.response(reportSuccess&&airDataSuccess, "create report ", null);
    }

    @PostMapping("/modifyReport")
    public HttpResponseEntity modifyReport(@RequestBody RequestReportEntity requestReportEntity) {
        Report report = requestReportEntity.getReport_modify();
        System.out.println(report);

        boolean success = reportService.updateById(report);
        return HttpResponseEntity.response(success, "modify report ", null);
    }

    @PostMapping("/deleteReport")
    public HttpResponseEntity deleteReportById(@RequestBody Report report) {
        boolean success = reportService.removeById(report);
        return HttpResponseEntity.response(success, "delete report ", null);
    }

    @PostMapping("/queryReportList")
    public HttpResponseEntity queryReportList(@RequestBody Map<String, Object> map) {
        Integer pageNum = (Integer) map.get("pageNum");
        Integer pageSize = (Integer) map.get("pageSize");
        Page<Report> page = new Page<>(pageNum, pageSize);
        reportService.query().eq("status", "1")
                .like("username", map.get("username")).page(page);
        List<Report> reportList = page.getRecords();
        boolean success = !reportList.isEmpty();
        return HttpResponseEntity.response(success, "查询", reportList);
    }
}
