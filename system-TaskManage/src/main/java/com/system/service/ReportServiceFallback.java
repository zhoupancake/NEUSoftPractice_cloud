package com.system.service;

import com.system.entity.data.Report;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

@FeignClient(name="system-ReportManage", url="localhost:8082") //  远程服务的名称
public interface ReportServiceFallback {
    @PostMapping("/getReportById")
    public Report getReportById(@RequestBody String id);
}
