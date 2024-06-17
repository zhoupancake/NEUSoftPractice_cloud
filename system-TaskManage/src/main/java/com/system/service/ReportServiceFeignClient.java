package com.system.service;

import com.system.entity.data.Report;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name="system-ReportManage", url="localhost:8082") //  远程服务的名称
public interface ReportServiceFeignClient {
    @PostMapping("/api/report/getReportById")
    public Report getReportById(@RequestBody String id);
}
