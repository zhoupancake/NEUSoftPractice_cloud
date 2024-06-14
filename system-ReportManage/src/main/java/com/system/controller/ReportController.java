package com.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.system.common.HttpResponseEntity;
import com.system.dto.RequestReportEntity;
import com.system.dto.ResponseReportEntity;
import com.system.entity.data.City;
import com.system.entity.data.Report;
import com.system.service.CityServiceFeignClient;
import com.system.service.ReportService;
import com.system.util.SnowflakeUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/hide/report")
@Slf4j
@RequiredArgsConstructor
public class ReportController {
    private final ReportService reportService;
    private final CityServiceFeignClient cityService;
    @PostMapping("/addReport")
    public HttpResponseEntity addReport(@RequestBody RequestReportEntity requestReportEntity) {
        Report report = requestReportEntity.getReport_create();
        City city = cityService.getCityByLocation(requestReportEntity.getLocation());
        report.setCityId(city.getId());
        report.setId(SnowflakeUtil.genId());
        report.setStatus(0);

        boolean reportSuccess = reportService.save(report);
        return HttpResponseEntity.response(reportSuccess, "create report ", report);
    }

    @PostMapping("/modifyReport")
    public HttpResponseEntity modifyReport(@RequestBody RequestReportEntity requestReportEntity) {
        Report report = requestReportEntity.getReport_modify();
        boolean success = reportService.updateById(report);
        return HttpResponseEntity.response(success, "modify report ", report);
    }

    @PostMapping("/deleteReport")
    public HttpResponseEntity deleteReportById(@RequestBody Report report) {
        boolean success = reportService.removeById(report);
        return HttpResponseEntity.response(success, "delete report ", report);
    }
    
    @PostMapping("/queryReportList")
    public HttpResponseEntity queryReportListBySubmitterId(@RequestBody Map<String, Object> map) {
        List<Integer> cites = cityService.getCitiesIdByProvince(String.valueOf(map.get("province")));

        QueryWrapper<Report> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("submitter_id", map.get("submitterId"));

        for (Integer cityId : cites)
            queryWrapper.or().like("city_id", cityId);
        if (map.get("status") != null)
            queryWrapper.like("status", map.get("status"));
        if (map.get("description") != null)
            queryWrapper.like("description", map.get("description"));
        if(map.get("location") != null)
            queryWrapper.like("location", map.get("location"));
        if(map.get("forecastApiLevel") != null)
            queryWrapper.eq("forecast_aqi_level", map.get("forecastApiLevel"));

        Page<Report> page = new Page<>((Integer)map.get("pageNum"), (Integer)map.get("pageSize"));
        Page<Report> reportPage = reportService.page(page, queryWrapper);

        List<Report> reportList = reportPage.getRecords();
        List<ResponseReportEntity> result = new ArrayList<>();
        boolean success = !reportList.isEmpty();
        if(success)
            for(Report report: reportList) {
                City city = cityService.getCityById(report.getCityId());
                result.add(new ResponseReportEntity(report, city));
            }
        return HttpResponseEntity.response(success, "query", result);
    }
}
