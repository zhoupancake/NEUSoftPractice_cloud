package com.system.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.system.common.HttpResponseEntity;
import com.system.entity.data.Report;
import com.system.service.ReportService;
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
    @PostMapping("/addReport")
    public HttpResponseEntity addReport(@RequestBody Report report) {
        boolean success = reportService.save(report);
        return HttpResponseEntity.response(success, "创建", null);
    }

    @PostMapping("/modifyReport")
    public HttpResponseEntity modifyReport(@RequestBody Report report) {
        boolean success = reportService.updateById(report);
        return HttpResponseEntity.response(success, "修改", null);
    }

    @PostMapping("/deleteReport")
    public HttpResponseEntity deleteReportById(@RequestBody Report report) {
        boolean success = reportService.removeById(report);
        return HttpResponseEntity.response(success, "删除", null);
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
