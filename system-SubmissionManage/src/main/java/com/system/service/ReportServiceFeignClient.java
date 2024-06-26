package com.system.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name="system-ReportManage", url="localhost:8082") //  远程服务的名称
public interface ReportServiceFeignClient {
    @GetMapping("/api/report/getReportCount")
    public int getReportCount();
}