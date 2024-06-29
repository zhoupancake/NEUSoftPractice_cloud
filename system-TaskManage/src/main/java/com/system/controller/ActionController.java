package com.system.controller;

import com.system.common.HttpResponseEntity;
import com.system.dto.ResponseTaskEntity;
import com.system.entity.character.GridDetector;
import com.system.entity.character.Supervisor;
import com.system.entity.data.City;
import com.system.entity.data.Report;
import com.system.entity.data.Task;
import com.system.service.*;
import com.system.util.SnowflakeUtil;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
/**
 * the external interface for task-concerning operation
 * this controller is exposed to the outside request
 */
@RestController
@RequestMapping("/task")
@Slf4j
@RequiredArgsConstructor
public class ActionController {
    private final TaskService taskService;
    private final ReportServiceFeignClient reportService;
    private final CityServiceFeignClient cityService;
    private final CharacterServiceFeignClient characterService;

    /**
     * get the appointee list of the grid detector(same city appointment)
     * @Request_character administrator
     * @param map the information of the request
     * @key_in_map "pageNum" the page number of the response list
     * @key_in_map "pageSize" the page size of the
     * @key_in_map "province" the province of the appointee city
     * @key_in_map "city" the city of the appointee city
     * @return the response entity contains the grid detector list can be appointed
     */
    @PostMapping("/administrator/getAppointee/local")
    public HttpResponseEntity getAppointee_local(@RequestBody Map<String, Object> map) {
        if((Integer) map.get("pageNum") < 1 || (Integer) map.get("pageSize") < 1)
            return HttpResponseEntity.error("The page size and the page number should be positive");
        Map<String, String> location = Map.of("province", String.valueOf(map.get("province")),
                "city", String.valueOf(map.get("city")));
        City city = cityService.getCityByLocation((location));
        if(city == null)
            return HttpResponseEntity.error("request city is not supported");
        Map<String, Integer> paraMap = Map.of("cityId", city.getId(),
                "pageNum", (Integer)map.get("pageNum"), "pageSize", (Integer)map.get("pageSize"));
        Map<String, Object> gridDetectors = characterService.getDetectorSameCity(paraMap);
        return HttpResponseEntity.response(!gridDetectors.isEmpty(), "get appointee", gridDetectors);
    }

    /**
     * get the appointee list of the grid detector(same province without the corresponding city appointment)
     * @Request_character administrator
     * @param map contains the query fields information
     * @key_in_map "pageNum" the page number of the response list
     * @key_in_map "pageSize" the page size of the response list
     * @key_in_map "province" the province of the appointee city
     * @key_in_map "city" the city of the appointee city
     * @return the response entity contains the grid detector list can be appointed
     */
    @PostMapping("/administrator/getAppointee/province")
    public HttpResponseEntity getAppointee_province(@RequestBody Map<String, Object> map) {
        if((Integer) map.get("pageNum") < 1 || (Integer) map.get("pageSize") < 1)
            return HttpResponseEntity.error("The page size and the page number should be positive");
        Map<String, String> location = Map.of("province", String.valueOf(map.get("province")),
                "city", String.valueOf(map.get("city")));
        City city = cityService.getCityByLocation((location));
        if(city == null)
            return HttpResponseEntity.error("request city is not supported");
        List<Integer> cities = cityService.getCitiesSameProvince((city.getId()));
        if(cities == null || cities.isEmpty())
            return HttpResponseEntity.error("request province has no other supported city");
        Map<String, Integer> paraMap = Map.of("cityId", city.getId(),
                "pageNum", (Integer)map.get("pageNum"), "pageSize", (Integer)map.get("pageSize"));
        Map<String, Object> gridDetectors = characterService.getDetectorSameCity(paraMap);
        return HttpResponseEntity.response(!gridDetectors.isEmpty(), "get appointee", gridDetectors);
    }

    /**
     * get the appointee list of the grid detector(other province without the corresponding province appointment)
     * @Request_character administrator
     * @param map contains the query fields information
     * @key_in_map "pageNum" the page number of the response list
     * @key_in_map "pageSize" the page size of the response list
     * @key_in_map "province" the province of the appointee city
     * @key_in_map "city" the city of the appointee city
     * @return the response entity contains the grid detector list can be appointed
     */
    @PostMapping("/administrator/getAppointee/other")
    public HttpResponseEntity getAppointee_other(@RequestBody Map<String, Object> map) {
        if((Integer) map.get("pageNum") < 1 || (Integer) map.get("pageSize") < 1)
            return HttpResponseEntity.error("The page size and the page number should be positive");
        Map<String, String> location = Map.of("province", String.valueOf(map.get("province")),
                "city", String.valueOf(map.get("city")));
        City city = cityService.getCityByLocation((location));
        if(city == null)
            return HttpResponseEntity.error("request city is not supported");
        List<Integer> cities = cityService.getCitiesSameProvince((city.getId()));
        cities.add(city.getId());
        Map<String, Integer> paraMap = Map.of("cityId", city.getId(),
                "pageNum", (Integer)map.get("pageNum"), "pageSize", (Integer)map.get("pageSize"));
        Map<String, Object> gridDetectors = characterService.getDetectorSameCity(paraMap);
        return HttpResponseEntity.response(!gridDetectors.isEmpty(), "get appointee", gridDetectors);
    }

    /** appoint a task based on a specific report
     * @Request_character administrator
     * @param task the task information contains the relative report id and the appointee id
     * @return the response entity indicating the result of the operation
     */
    @PostMapping("/administrator/appoint")
    public HttpResponseEntity addTask(@RequestBody Task task) {
        task.setId(SnowflakeUtil.genId());
        task.setStatus(0);

        if(null == reportService.getReportById(task.getRelativeReportId()))
            return HttpResponseEntity.error("report not found");
        if(reportService.getReportById(task.getRelativeReportId()).getStatus() == 1)
            return HttpResponseEntity.error("report has been appointed");
        if(null == characterService.getAdministratorById(task.getAppointerId()))
            return HttpResponseEntity.error("administrator not found");
        if(null == characterService.getDetectorById(task.getAppointeeId()))
            return HttpResponseEntity.error("grid detector not found");

        boolean success = taskService.save(task);
        return HttpResponseEntity.response(success, "create task ", null);
    }

    /**
     * query the task list with specific query fields
     * @Request_character grid detector
     * @param map contains the query fields information
     * @key_in_map "pageNum" the page number of the response list
     * @key_in_map "pageSize" the page size of the response list
     * @key_in_map "appointeeId" the id of the grid detector who is appointed in the task
     * @key_in_map "id" the id of the task
     * @key_in_map "appointerId" the id of the administrator who appointed in the task
     * @key_in_map "status" the status of the task(finished or not)
     * @key_in_map "startTime" the start time of the query time range
     * @key_in_map "endTime" the end time of the query time range
     * @return the response entity contains the task list
     * @throws ParseException if the start time or end time is not valid
     */
    @PostMapping("/gridDetector/queryTaskList")
    public HttpResponseEntity queryReportListBySubmitterId_character(@RequestBody Map<String, Object> map) throws ParseException {
        System.out.println(map);
        if((Integer)map.get("pageNum") < 1 || (Integer)map.get("pageSize") < 1)
            return HttpResponseEntity.error("The page size and the page number should be positive");
        QueryWrapper<Task> queryWrapper = new QueryWrapper<>();
        if(map.containsKey("appointeeId") && map.get("appointeeId") != null)
            queryWrapper.eq("appointee_id", map.get("appointeeId"));
        else
            return HttpResponseEntity.error("appointeeId is required");

        if(map.containsKey("id") && map.get("id") != null)
            queryWrapper.like("id", map.get("id"));
        if(map.containsKey("appointerId") && map.get("appointerId") != null)
            queryWrapper.like("appointer_id", map.get("appointerId"));
        if(map.containsKey("appointeeId") && map.get("appointeeId") != null)
            queryWrapper.like("appointee_id", map.get("appointeeId"));
        if (map.containsKey("status") && map.get("status") != null)
            queryWrapper.like("status", map.get("status"));
        if(map.containsKey("startTime") && map.get("startTime") != null && !map.get("startTime").equals("")) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date startDate = dateFormat.parse(String.valueOf(map.get("startTime")));
            Timestamp startTime = new Timestamp(startDate.getTime());
            queryWrapper.lambda().ge(Task::getCreatedTime,startTime);
        }
        if(map.containsKey("endTime") && map.get("endTime") != null && !map.get("endTime").equals("")) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date startDate = dateFormat.parse(String.valueOf(map.get("endTime")));
            Timestamp endTime = new Timestamp(startDate.getTime());
            queryWrapper.lambda().le(Task::getCreatedTime,endTime);
        }
        Page<Task> page = new Page<>((Integer)map.get("pageNum"), (Integer)map.get("pageSize"));
        Page<Task> taskPage = taskService.page(page, queryWrapper);

        List<Task> taskList = taskPage.getRecords();
        List<ResponseTaskEntity> result = new ArrayList<>();
        boolean success = !taskList.isEmpty();
        if(success)
            for(Task task: taskList) {
                Report report = reportService.getReportById(task.getRelativeReportId());
                Supervisor supervisor = characterService.getSupervisorById(report.getSubmitterId());
                City city = cityService.getCityById(report.getCityId());
                result.add(new ResponseTaskEntity(task,report, city, supervisor));
            }
        return HttpResponseEntity.response(success, "query", result);
    }

    /**
     * query the task list with specific query fields
     * @Request_character administrator
     * @param map contains the query fields information
     * @key_in_map "pageNum" the page number of the response list
     * @key_in_map "pageSize" the page size of the response list
     * @key_in_map "appointeeId" the id of the grid detector who is appointed in the task
     * @key_in_map "id" the id of the task
     * @key_in_map "appointerId" the id of the administrator who appointed in the task
     * @key_in_map "status" the status of the task(finished or not)
     * @key_in_map "startTime" the start time of the query time range
     * @key_in_map "endTime" the end time of the query time range
     * @return the response entity contains the task list
     * @throws ParseException if the start time or end time is not valid
     */
    @PostMapping("/administrator/queryTaskList")
    public HttpResponseEntity queryReportListBySubmitterId_administrator(@RequestBody Map<String, Object> map) throws ParseException {
        if((Integer)map.get("pageNum") < 1 || (Integer)map.get("pageSize") < 1)
            return HttpResponseEntity.error("The page size and the page number should be positive");
        QueryWrapper<Task> queryWrapper = new QueryWrapper<>();
        if(map.containsKey("appointerId") && map.get("appointerId") != null)
            queryWrapper.eq("appointer_id", map.get("appointerId"));
        else
            return HttpResponseEntity.error("appointerId is required");

        if(map.containsKey("id") && map.get("id") != null)
            queryWrapper.like("id", map.get("id"));
        if(map.containsKey("appointeeId") && map.get("appointeeId") != null)
            queryWrapper.like("appointee_id", map.get("appointeeId"));
        if(map.containsKey("appointeeId") && map.get("appointeeId") != null)
            queryWrapper.like("appointee_id", map.get("appointeeId"));
        if (map.containsKey("status") && map.get("status") != null)
            queryWrapper.like("status", map.get("status"));
        if(map.containsKey("startTime") && map.get("startTime") != null && !map.get("startTime").equals("")) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date startDate = dateFormat.parse(String.valueOf(map.get("startTime")));
            Timestamp startTime = new Timestamp(startDate.getTime());
            queryWrapper.lambda().ge(Task::getCreatedTime,startTime);
        }
        if(map.containsKey("endTime") && map.get("endTime") != null && !map.get("endTime").equals("")) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date startDate = dateFormat.parse(String.valueOf(map.get("endTime")));
            Timestamp endTime = new Timestamp(startDate.getTime());
            queryWrapper.lambda().le(Task::getCreatedTime,endTime);
        }
        Page<Task> page = new Page<>((Integer)map.get("pageNum"), (Integer)map.get("pageSize"));
        Page<Task> taskPage = taskService.page(page, queryWrapper);

        List<Task> taskList = taskPage.getRecords();
        List<ResponseTaskEntity> result = new ArrayList<>();
        boolean success = !taskList.isEmpty();
        if(success)
            for(Task task: taskList) {
                Report report = reportService.getReportById(task.getRelativeReportId());
                Supervisor supervisor = characterService.getSupervisorById(report.getSubmitterId());
                City city = cityService.getCityById(report.getCityId());
                result.add(new ResponseTaskEntity(task,report, city, supervisor));
            }
        return HttpResponseEntity.response(success, "query", result);
    }
}
