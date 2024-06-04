package com.system.api;

import com.system.entity.data.Report;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

public interface ReportAPI {
//    @PostMapping("/addReport")
//    public boolean addReport(@RequestBody Report report);
//
//    @PostMapping("/modifyReport")
//    public boolean modifyReport(@RequestBody Report report);
//
//    @PostMapping("/deleteReport")
//    public boolean deleteReportById(@RequestBody Report report);

    @PostMapping("/getReportById")
    public Report getReportById(@RequestBody String id);

//    @PostMapping("/queryReportList")
//    public List<Report> queryReportList(@RequestBody Map<String, Object> map);
}
