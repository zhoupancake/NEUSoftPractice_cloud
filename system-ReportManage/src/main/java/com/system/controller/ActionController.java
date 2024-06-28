package com.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.system.common.HttpResponseEntity;
import com.system.dto.RequestReportEntity;
import com.system.dto.ResponseReportEntity;
import com.system.entity.data.AirData;
import com.system.entity.data.City;
import com.system.entity.data.Report;
import com.system.entity.data.Submission;
import com.system.service.CityServiceFeignClient;
import com.system.service.ReportService;
import com.system.service.SupervisorServiceFeignClient;
import com.system.util.SnowflakeUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
/**
 * the external interface for report-concerning operation
 * this controller is exposed to the outside request
 */
@RestController
@RequestMapping("/report")
@Slf4j
@RequiredArgsConstructor
public class ActionController {
    private final ReportService reportService;
    private final CityServiceFeignClient cityService;
    private final SupervisorServiceFeignClient supervisorService;

    /**
     * create a report
     * @Request_character supervisor
     * @param requestReportEntity the request report entity contains the report and the location
     * @return http response entity to indicate the creating result
     */
    @PostMapping("/supervisor/report")
    public HttpResponseEntity report(@RequestBody RequestReportEntity requestReportEntity) {
        Report report = requestReportEntity.getReport_create();
        if (null == supervisorService.getSupervisorById(report.getSubmitterId()))
            return HttpResponseEntity.error("reported submitter is not exist");
        City city = cityService.getCityByLocation(requestReportEntity.getLocation());
        if (null == city)
            return HttpResponseEntity.error("reported location is not exist");
        if (report.getForecastAqiLevel() < 1 || report.getForecastAqiLevel() > 8)
            return HttpResponseEntity.error("reported forecast AQI level is not exist");
        report.setCityId(city.getId());
        report.setId(SnowflakeUtil.genId());
        report.setStatus(0);

        boolean reportSuccess = reportService.save(report);
        return HttpResponseEntity.response(reportSuccess, "create report ", null);
    }

    /**
     * get report by id
     * @Request_character grid detector
     * @param map the map contains the id of the needing report
     * @return the corresponding report object
     */
    @PostMapping("/gridDetector/getReportById")
    public HttpResponseEntity getReportById(@RequestBody Map<String,String> map) {
        Report report = reportService.getById(String.valueOf(map.get("id")));
        if (report == null)
            return HttpResponseEntity.error("report is not exist");
        return HttpResponseEntity.response(true, "get report", report);
    }

    /**
     * get report by id
     * @Request_character administrator
     * @param map the map contains the id of the needing report
     * @return the corresponding report object
     */
    @PostMapping("/administrator/getReportById")
    public HttpResponseEntity getReportById_Administrator(@RequestBody Map<String,String> map) {
        Report report = reportService.getById(String.valueOf(map.get("id")));
        if (report == null)
            return HttpResponseEntity.error("report is not exist");
        ResponseReportEntity responseReport = new ResponseReportEntity(report, cityService.getCityById(report.getCityId()));
        return HttpResponseEntity.response(true, "get report", responseReport);
    }

    /**
     * query report list
     * @Request_character supervisor
     * @param map the map contains the information of the page
     * @key_in_map pageNum the page number
     * @key_in_map pageSize the page size
     * @key_in_map submitterId the submitter id of the report(supervisor id)
     * @key_in_map province the province of the report
     * @key_in_map city the city of the report
     * @key_in_map startTime the start time of the report
     * @key_in_map endTime the end time of the report
     * @key_in_map status the status of the report(finish appointment or not)
     * @key_in_map description the description of the report
     * @key_in_map forecastAqiLevel the forecast AQI level of the report
     * @return the http response entity contains the report list as required
     * @throws ParseException if the start time or end time is not valid
     */
    @PostMapping("/supervisor/queryReportList")
    public HttpResponseEntity queryReportListBySubmitterId(@RequestBody Map<String, Object> map) throws ParseException {
        if((Integer) map.get("pageNum") < 1 || (Integer) map.get("pageSize") < 1)
            return HttpResponseEntity.error("pageNum or pageSize is not valid");
        QueryWrapper<Report> queryWrapper = new QueryWrapper<>();
        if(map.containsKey("city") && map.get("city") != null && !map.get("city").equals("")){
            if(map.containsKey("province") && map.get("province") != null && !map.get("province").equals("")){
                City city = cityService.getCityByLocation(Map.of("province", (String) map.get("province"),
                        "city", (String) map.get("city")));
                if(city == null)
                    return HttpResponseEntity.error("the selected city is not exist");
                queryWrapper.eq("city_id", city.getId());
            }
            else{
                List<Integer> citiesList = cityService.getCitiesByLikeName((String) map.get("city"));
                if(citiesList == null || citiesList.isEmpty())
                    return HttpResponseEntity.error("the selected city is not exist in the list");
                for (Integer cityId : citiesList)
                    queryWrapper.or().eq("city_id", cityId);
            }
        }
        else{
            if(map.containsKey("province") && map.get("province") != null && !map.get("province").equals("")){
                List<Integer> citiesList = cityService.getCitiesIdByProvince((String) map.get("province"));
                for (Integer cityId : citiesList)
                    queryWrapper.or().eq("city_id", cityId);
            }
        }
        if(map.containsKey("submitterId") && map.get("submitterId") != null && !map.get("submitterId").equals("")) {
            if(null != supervisorService.getSupervisorById((String) map.get("submitterId")))
                queryWrapper.eq("submitter_id", map.get("submitterId"));
            else
                return HttpResponseEntity.error("submitter id is not exist");
        }
        else
            return HttpResponseEntity.error("submitter id is required");
        if (map.containsKey("id") && map.get("id") != null && !map.get("id").equals(""))
            queryWrapper.like("id", map.get("id"));
        if (map.containsKey("status") && map.get("status") != null && !map.get("status").equals(""))
            queryWrapper.like("status", map.get("status"));
        if (map.containsKey("description") && map.get("description") != null && !map.get("description").equals(""))
            queryWrapper.like("description", map.get("description"));
        if (map.containsKey("location") && map.get("location") != null && !map.get("location").equals(""))
            queryWrapper.like("location", map.get("location"));
        if (map.containsKey("forecastApiLevel") && map.get("forecastApiLevel") != null && !map.get("forecastApiLevel").equals(""))
            queryWrapper.eq("forecast_aqi_level", map.get("forecastApiLevel"));
        if(map.containsKey("startTime") && map.get("startTime") != null && !map.get("startTime").equals("")) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date startDate = dateFormat.parse(String.valueOf(map.get("startTime")));
            Timestamp startTime = new Timestamp(startDate.getTime());
            queryWrapper.lambda().ge(Report::getCreatedTime,startTime);
        }
        if(map.containsKey("endTime") && map.get("endTime") != null && !map.get("endTime").equals("")) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date startDate = dateFormat.parse(String.valueOf(map.get("endTime")));
            Timestamp endTime = new Timestamp(startDate.getTime());
            queryWrapper.lambda().le(Report::getCreatedTime,endTime);
        }

        Page<Report> page = new Page<>((Integer) map.get("pageNum"), (Integer) map.get("pageSize"));
        Page<Report> reportPage = reportService.page(page, queryWrapper);
        List<Report> reportList = reportPage.getRecords();
        List<ResponseReportEntity> result = new ArrayList<>();
        boolean success = !reportList.isEmpty();
        if (success)
            for (Report report : reportList) {
                City city = cityService.getCityById(report.getCityId());
                result.add(new ResponseReportEntity(report, city));
            }
        Map<String, Object> resultMap = Map.of("count", reportService.count(queryWrapper),"result", result);
        return HttpResponseEntity.response(success, "query ", result);
    }

    /**
     * query report list
     * @Request_character supervisor
     * @param map the map contains the information of the page
     * @key_in_map pageNum the page number
     * @key_in_map pageSize the page size
     * @key_in_map submitterId the submitter id of the report(supervisor id)
     * @key_in_map province the province of the report
     * @key_in_map city the city of the report
     * @key_in_map startTime the start time of the report
     * @key_in_map endTime the end time of the report
     * @key_in_map status the status of the report(finish appointment or not)
     * @key_in_map description the description of the report
     * @key_in_map forecastAqiLevel the forecast AQI level of the report
     * @return the http response entity contains the report list as required
     * @throws ParseException if the start time or end time is not valid
     */
    @PostMapping("/administrator/queryReportList")
    public HttpResponseEntity queryReportList(@RequestBody Map<String, Object> map) throws ParseException {
        if((Integer) map.get("pageNum") < 1 || (Integer) map.get("pageSize") < 1)
            return HttpResponseEntity.error("pageNum or pageSize is not valid");
        QueryWrapper<Report> queryWrapper = new QueryWrapper<>();
        if(map.containsKey("city") && map.get("city") != null && !map.get("city").equals("")){
            if(map.containsKey("province") && map.get("province") != null && !map.get("province").equals("")){
                City city = cityService.getCityByLocation(Map.of("province", (String) map.get("province"),
                        "city", (String) map.get("city")));
                if(city == null)
                    return HttpResponseEntity.error("the selected city is not exist");
                queryWrapper.eq("city_id", city.getId());
            }
            else{
                List<Integer> citiesList = cityService.getCitiesByLikeName((String) map.get("city"));
                if(citiesList == null || citiesList.isEmpty())
                    return HttpResponseEntity.error("the selected city is not exist in the list");
                for (Integer cityId : citiesList)
                    queryWrapper.or().eq("city_id", cityId);
            }
        }
        else{
            if(map.containsKey("province") && map.get("province") != null && !map.get("province").equals("")){
                List<Integer> citiesList = cityService.getCitiesIdByProvince((String) map.get("province"));
                for (Integer cityId : citiesList)
                    queryWrapper.or().eq("city_id", cityId);
            }
        }
        if (map.containsKey("id") && map.get("id") != null && !map.get("id").equals(""))
            queryWrapper.like("id", map.get("id"));
        if (map.containsKey("status") && map.get("status") != null && !map.get("status").equals(""))
            queryWrapper.like("status", map.get("status"));
        if (map.containsKey("description") && map.get("description") != null && !map.get("description").equals(""))
            queryWrapper.like("description", map.get("description"));
        if (map.containsKey("location") && map.get("location") != null && !map.get("location").equals(""))
            queryWrapper.like("location", map.get("location"));
        if (map.containsKey("forecastApiLevel") && map.get("forecastApiLevel") != null && !map.get("forecastApiLevel").equals(""))
            queryWrapper.eq("forecast_aqi_level", map.get("forecastApiLevel"));
        if(map.containsKey("submitterId") && map.get("submitterId") != null && !map.get("submitterId").equals(""))
            queryWrapper.like("submitter_id", map.get("submitterId"));
        if(map.containsKey("startTime") && map.get("startTime") != null && !map.get("startTime").equals("")) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date startDate = dateFormat.parse(String.valueOf(map.get("startTime")));
            Timestamp startTime = new Timestamp(startDate.getTime());
            queryWrapper.lambda().ge(Report::getCreatedTime,startTime);
        }
        if(map.containsKey("endTime") && map.get("endTime") != null && !map.get("endTime").equals("")) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date startDate = dateFormat.parse(String.valueOf(map.get("endTime")));
            Timestamp endTime = new Timestamp(startDate.getTime());
            queryWrapper.lambda().le(Report::getCreatedTime,endTime);
        }

        Page<Report> page = new Page<>((Integer) map.get("pageNum"), (Integer) map.get("pageSize"));
        Page<Report> reportPage = reportService.page(page, queryWrapper);
        List<Report> reportList = reportPage.getRecords();
        List<ResponseReportEntity> result = new ArrayList<>();
        boolean success = !reportList.isEmpty();
        if (success)
            for (Report report : reportList) {
                City city = cityService.getCityById(report.getCityId());
                result.add(new ResponseReportEntity(report, city));
            }
        Map<String, Object> resultList = Map.of("total", reportService.list().size(), "list", result);
        return HttpResponseEntity.response(success, "query", resultList);
    }

    /**
     * get the top several reports in the report list sorted by time in descending order
     * @Request_character digital screen
     * @param limitNum the number of reports to be returned
     * @return http response entity contains the top several reports as required
     */
    @GetMapping("/digitalScreen/selectOrderList")
    public HttpResponseEntity selectOrderList_digitalScreen(@RequestParam("limitNum") Integer limitNum) {
        QueryWrapper<Report> queryWrapper = new QueryWrapper<>();
        List<Report> reportList = reportService.list(queryWrapper.orderByDesc("created_time"));
        List<ResponseReportEntity> result = new ArrayList<>();
        for(int i = 0; i < limitNum && i < reportList.size(); i++){
            City city = cityService.getCityById(reportList.get(i).getCityId());
            result.add(new ResponseReportEntity(reportList.get(i), city));
        }
        return HttpResponseEntity.success("query ", result);
    }
}