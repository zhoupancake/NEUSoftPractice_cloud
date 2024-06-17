package com.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.system.common.HttpResponseEntity;
import com.system.dto.ResponseReportEntity;
import com.system.dto.ResponseTaskEntity;
import com.system.entity.character.GridDetector;
import com.system.entity.character.Supervisor;
import com.system.entity.data.City;
import com.system.entity.data.Report;
import com.system.entity.data.Task;
import com.system.service.*;
import com.system.util.SnowflakeUtil;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/task")
@Slf4j
@RequiredArgsConstructor
public class ActionController {
    private final TaskService taskService;
    private ReportServiceFeignClient reportService;
    private final CityServiceFeignClient cityService;
    private final CharacterServiceFeignClient characterService;

    @PostMapping("/getAppointee/local")
    public List<GridDetector> getAppointee_local(@RequestBody Map<String, Object> map) {
        Map<String, Integer> paraMap = Map.of("cityId", (Integer) map.get("cityId"),
                "pageNum", (Integer)map.get("pageNum"), "pageSize", (Integer)map.get("pageSize"));
        return characterService.getDetectorSameCity(paraMap);
    }

    @PostMapping("/getAppointee/province")
    public List<GridDetector> getAppointee_province(@RequestBody Map<String, Object> map) {
        List<Integer> cities = cityService.getCitiesSameProvince((Integer) map.get("cityId"));
        return characterService.getDetectorSameProvince(cities, (Integer)map.get("pageNum"), (Integer)map.get("pageSize"));
    }

    @PostMapping("/getAppoint/other")
    public List<GridDetector> getAppointee_other(@RequestBody Map<String, Object> map) {
        List<Integer> cities = cityService.getCitiesSameProvince((Integer) map.get("cityId"));
        return characterService.getDetectorOtherProvince(cities, (Integer)map.get("pageNum"), (Integer)map.get("pageSize"));
    }

    @PostMapping("/appoint")
    public HttpResponseEntity addTask(@RequestBody Task task) {
        task.setId(SnowflakeUtil.genId());
        task.setStatus(0);
        boolean success = taskService.save(task);
        return HttpResponseEntity.response(success, "create task", null);
    }

    @PostMapping("/queryTaskList/character")
    public HttpResponseEntity queryReportListBySubmitterId_character(@RequestBody Map<String, Object> map) throws ParseException {
        QueryWrapper<Task> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("appointee_id", map.get("appointeeId"));

        if (map.get("status") != null)
            queryWrapper.like("status", map.get("status"));
        if(map.get("startTime") != null) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yy-MM-dd");
            Date startDate = dateFormat.parse(String.valueOf(map.get("startTime")));
            long startMillis = startDate.getTime();
            queryWrapper.lambda().ge(Task::getCreatedTime,startMillis);
        }
        if(map.get("endTime") != null) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yy-MM-dd");
            Date startDate = dateFormat.parse(String.valueOf(map.get("endTime")));
            long startMillis = startDate.getTime();
            queryWrapper.lambda().le(Task::getCreatedTime,startMillis);
        }
        if (map.get("description") != null)
            queryWrapper.like("description", map.get("description"));
        if(map.get("location") != null)
            queryWrapper.like("location", map.get("location"));
        if(map.get("forecastApiLevel") != null)
            queryWrapper.eq("forecast_aqi_level", map.get("forecastApiLevel"));
        Page<Task> page = new Page<>((Integer)map.get("pageNum"), (Integer)map.get("pageSize"));
        Page<Task> taskPage = taskService.page(page, queryWrapper);

        List<Task> taskList = taskPage.getRecords();
        List<ResponseTaskEntity> result = new ArrayList<>();
        boolean success = !taskList.isEmpty();
        if(success)
            for(Task task: taskList) {
                Report report = reportService.getReportById(task.getRelativeReportId());
                City city = cityService.getCityById(report.getCityId());
                result.add(new ResponseTaskEntity(task,report, city));
            }
        return HttpResponseEntity.response(success, "query", result);
    }

    @PostMapping("/queryTaskList/administrator")
    public HttpResponseEntity queryReportListBySubmitterId_administrator(@RequestBody Map<String, Object> map) throws ParseException {
        QueryWrapper<Task> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("appointer_id", map.get("appointerId"));

        if (map.get("status") != null)
            queryWrapper.like("status", map.get("status"));
        if(map.get("startTime") != null) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yy-MM-dd");
            Date startDate = dateFormat.parse(String.valueOf(map.get("startTime")));
            long startMillis = startDate.getTime();
            queryWrapper.lambda().ge(Task::getCreatedTime,startMillis);
        }
        if(map.get("endTime") != null) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yy-MM-dd");
            Date startDate = dateFormat.parse(String.valueOf(map.get("endTime")));
            long startMillis = startDate.getTime();
            queryWrapper.lambda().le(Task::getCreatedTime,startMillis);
        }
        if (map.get("description") != null)
            queryWrapper.like("description", map.get("description"));
        if(map.get("location") != null)
            queryWrapper.like("location", map.get("location"));
        if(map.get("forecastApiLevel") != null)
            queryWrapper.eq("forecast_aqi_level", map.get("forecastApiLevel"));
        Page<Task> page = new Page<>((Integer)map.get("pageNum"), (Integer)map.get("pageSize"));
        Page<Task> taskPage = taskService.page(page, queryWrapper);

        List<Task> taskList = taskPage.getRecords();
        List<ResponseTaskEntity> result = new ArrayList<>();
        boolean success = !taskList.isEmpty();
        if(success)
            for(Task task: taskList) {
                Report report = reportService.getReportById(task.getRelativeReportId());
                City city = cityService.getCityById(report.getCityId());
                result.add(new ResponseTaskEntity(task, report, city));
            }
        return HttpResponseEntity.response(success, "query", result);
    }
}
