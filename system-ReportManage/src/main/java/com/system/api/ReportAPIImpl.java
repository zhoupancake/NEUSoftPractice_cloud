package com.system.api;

import com.system.entity.data.Report;
import com.system.service.ReportService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * The report inner micro-service api impl
 */
@RestController
@RequestMapping("/api/report")
@Slf4j
@RequiredArgsConstructor
public class ReportAPIImpl  implements ReportAPI{
    private final ReportService reportService;

    /**
     * get the report by id
     * @param id the report id
     * @return the report object
     */
    @Override
    @PostMapping("/getReportById")
    public Report getReportById(@RequestBody String id) {
        return reportService.getById(id);
    }

    /**
     * update the report by id
     * @param report the updated report object
     * @return true if the report is updated successfully
     */
    @Override
    @PostMapping("/updateReportById")
    public boolean updateReportById(@RequestBody Report report) {
        return reportService.updateById(report);
    }

    /**
     * get the number of the reports in the database
     * @return integer number of the reports
     */
    @Override
    @GetMapping("/getReportCount")
    public int getReportCount(){
        System.out.println("getReportCount");
        return reportService.query().list().size();
    }
}
