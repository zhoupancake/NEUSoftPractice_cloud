package com.system.api;

import com.system.entity.data.Report;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

/**
 *  The interface report inner micro-service api.
 */
public interface ReportAPI {
    @PostMapping("/report/getReportById")
    public Report getReportById(@RequestBody String id);

    @PostMapping("/report/updateReportById")
    public boolean updateReportById(@RequestBody Report report);

    @GetMapping("/api/report/getReportCount")
    public int getReportCount();
}
