package com.system.api;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
@RequestMapping("/api/report")
@Slf4j
@RequiredArgsConstructor
public class ReportAPIImpl  implements ReportAPI{
    private final ReportService reportService;
//
//    @Override
//    @PostMapping("/addReport")
//    public boolean addReport(@RequestBody Report report) {
//        return reportService.save(report);
//    }
//
//    @Override
//    @PostMapping("/modifyReport")
//    public boolean modifyReport(@RequestBody Report report) {
//        return reportService.updateById(report);
//    }
//
//    @Override
//    @PostMapping("/deleteReport")
//    public boolean deleteReportById(@RequestBody Report report) {
//        return reportService.removeById(report);
//    }

    @Override
    @PostMapping("/getReportById")
    public Report getReportById(@RequestBody String id) {
        return reportService.getById(id);
    }

    @Override
    @PostMapping("/updateReportById")
    public boolean updateReportById(@RequestBody Report report) {
        return reportService.updateById(report);
    }
//    @Override
//    @PostMapping("/queryReportList")
//    public List<Report> queryReportList(@RequestBody Map<String, Object> map) {
//        Integer pageNum = (Integer) map.get("pageNum");
//        Integer pageSize = (Integer) map.get("pageSize");
//        Page<Report> page = new Page<>(pageNum, pageSize);
//        reportService.query().eq("status", "1")
//                .like("username", map.get("username")).page(page);
//        return page.getRecords();
//    }
}
