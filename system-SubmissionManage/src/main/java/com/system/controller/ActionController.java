package com.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.system.common.HttpResponseEntity;
import com.system.dto.RequestSubmissionEntity;
import com.system.dto.ResponseReportEntity;
import com.system.entity.data.*;
import com.system.service.AirDataServiceFeignClient;
import com.system.service.CityServiceFeignClient;
import com.system.service.SubmissionService;
import com.system.service.TaskServiceFeignClient;
import com.system.util.SnowflakeUtil;

import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Wrapper;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/submission")
@Slf4j
@RequiredArgsConstructor
public class ActionController {
    private final SubmissionService submissionService;
    @Resource
    private final TaskServiceFeignClient taskService;
    @Resource
    private final AirDataServiceFeignClient airDataService;
    @Resource
    private final CityServiceFeignClient cityService;
    @PostMapping("/submit")
    public HttpResponseEntity submit(@RequestBody RequestSubmissionEntity requestSubmissionEntity) {
        Submission submission = requestSubmissionEntity.getSubmission_create();
        AirData airData = requestSubmissionEntity.getAirData_create();
        Task task = taskService.getTaskById(requestSubmissionEntity.getTaskId());
        String airDataId = SnowflakeUtil.genId();

        airData.setId(airDataId);
        airData.setCityId(cityService.getCityByLocation(requestSubmissionEntity.getLocation()).getId());
        task.setStatus(1);
        submission.setRelatedAirDataId(airDataId);

        boolean airDataSuccess = airDataService.addAirData(airData);
        boolean taskSuccess = taskService.updateTaskById(task);
        boolean submissionSuccess = submissionService.save(submission);

        return HttpResponseEntity.response(airDataSuccess&&taskSuccess&&submissionSuccess, "create submission", null);
    }

    @PostMapping("/selectAll/gridDetector")
    public HttpResponseEntity selectAll_gridDetector(@RequestBody Map<String, Object> map) {
        QueryWrapper<Submission> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("submitter_id", map.get("submitterId"));
        Page<Submission> page = new Page<>((Integer)map.get("pageNum"), (Integer)map.get("pageSize"));
        Page<Submission> submissionPage = submissionService.page(page, queryWrapper);

        List<Submission> submissionList = submissionPage.getRecords();
        return HttpResponseEntity.success("select all submission", submissionList);
    }

    @PostMapping("/querySubmissionList/gridDetector")
    public HttpResponseEntity querySubmissionList_gridDetector(@RequestBody Map<String, Object> map) throws ParseException {
        QueryWrapper<Submission> queryWrapper = new QueryWrapper<>();
        if (map.get("gridDetectorId") == null)
            queryWrapper.eq("submitter_id", map.get("submitterId"));
        if (map.get("description") != null)
            queryWrapper.like("description", map.get("description"));
        if(map.get("startTime") != null) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yy-MM-dd");
            Date startDate = dateFormat.parse(String.valueOf(map.get("startTime")));
            long startMillis = startDate.getTime();
            queryWrapper.lambda().ge(Submission::getSubmittedTime,startMillis);
        }
        if(map.get("endTime") != null) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yy-MM-dd");
            Date startDate = dateFormat.parse(String.valueOf(map.get("endTime")));
            long startMillis = startDate.getTime();
            queryWrapper.lambda().le(Submission::getSubmittedTime,startMillis);
        }

        Page<Submission> page = new Page<>((Integer)map.get("pageNum"), (Integer)map.get("pageSize"));
        Page<Submission> submissionPage = submissionService.page(page, queryWrapper);

        List<Submission> submissionList = submissionPage.getRecords();
        return HttpResponseEntity.success("select all submission", submissionList);
    }

    @PostMapping("/querySubmissionList/administrator")
    public HttpResponseEntity querySubmissionList_administrator(@RequestBody Map<String, Object> map) throws ParseException {
        QueryWrapper<Submission> queryWrapper = new QueryWrapper<>();
        String[] taskIds = taskService.getTaskIdByAppointerId(String.valueOf(map.get("administratorId")));
        for(String str: taskIds)
            queryWrapper.or().eq("task_id", str);
        if (map.get("description") != null)
            queryWrapper.like("description", map.get("description"));
        if(map.get("startTime") != null) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yy-MM-dd");
            Date startDate = dateFormat.parse(String.valueOf(map.get("startTime")));
            long startMillis = startDate.getTime();
            queryWrapper.lambda().ge(Submission::getSubmittedTime,startMillis);
        }
        if(map.get("endTime") != null) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yy-MM-dd");
            Date startDate = dateFormat.parse(String.valueOf(map.get("endTime")));
            long startMillis = startDate.getTime();
            queryWrapper.lambda().le(Submission::getSubmittedTime,startMillis);
        }

        Page<Submission> page = new Page<>((Integer)map.get("pageNum"), (Integer)map.get("pageSize"));
        Page<Submission> submissionPage = submissionService.page(page, queryWrapper);

        List<Submission> submissionList = submissionPage.getRecords();
        return HttpResponseEntity.success("select all submission", submissionList);
    }
}
