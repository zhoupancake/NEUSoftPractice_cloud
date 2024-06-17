package com.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.system.common.HttpResponseEntity;
import com.system.dto.RequestSubmissionEntity;
import com.system.dto.ResponseReportEntity;
import com.system.entity.data.*;
import com.system.service.*;
import com.system.util.SnowflakeUtil;

import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
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
    private final TaskServiceFeignClient taskService;
    private final AirDataServiceFeignClient airDataService;
    private final CityServiceFeignClient cityService;
    private final CharacterServiceFeignClient characterService;
    @PostMapping("/gridDetector/submit")
    public HttpResponseEntity submit(@RequestBody RequestSubmissionEntity requestSubmissionEntity) {
        Submission submission = requestSubmissionEntity.getSubmission_create();
        AirData airData = requestSubmissionEntity.getAirData_create();
        Task task = taskService.getTaskById(requestSubmissionEntity.getTaskId());
        if(null == task)
            return HttpResponseEntity.error("task not exist");
        if(task.getStatus() == 1)
            return HttpResponseEntity.error("task is finished");
        if(null == characterService.getGridDetectorById(submission.getSubmitterId()))
            return HttpResponseEntity.error("submitter is not exist");
        String airDataId = SnowflakeUtil.genId();

        airData.setId(airDataId);
        City city = cityService.getCityByLocation(requestSubmissionEntity.getLocation());
        if(null == city)
            return HttpResponseEntity.error("city not exist");
        airData.setCityId(city.getId());
        task.setStatus(1);
        submission.setRelatedAirDataId(airDataId);

        boolean airDataSuccess = airDataService.addAirData(airData);
        boolean taskSuccess = taskService.updateTaskById(task);
        boolean submissionSuccess = submissionService.save(submission);

        return HttpResponseEntity.response(airDataSuccess&&taskSuccess&&submissionSuccess, "create submission ", null);
    }

//    @PostMapping("/selectAll/gridDetector")
//    public HttpResponseEntity selectAll_gridDetector(@RequestBody Map<String, Object> map) {
//        QueryWrapper<Submission> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("submitter_id", map.get("submitterId"));
//        Page<Submission> page = new Page<>((Integer)map.get("pageNum"), (Integer)map.get("pageSize"));
//        Page<Submission> submissionPage = submissionService.page(page, queryWrapper);
//
//        List<Submission> submissionList = submissionPage.getRecords();
//        return HttpResponseEntity.success("select all submission", submissionList);
//    }

    @PostMapping("/gridDetector/querySubmissionList")
    public HttpResponseEntity querySubmissionList_gridDetector(@RequestBody Map<String, Object> map) throws ParseException {
        if((Integer)map.get("pageNum") < 1 || (Integer)map.get("pageSize") < 1)
            return HttpResponseEntity.error("pageNum or pageSize is invalid");
        QueryWrapper<Submission> queryWrapper = new QueryWrapper<>();
        if (map.containsKey("submitterId") && map.get("submitterId") != null)
            queryWrapper.eq("submitter_id", map.get("submitterId"));
        else
            return HttpResponseEntity.error("submitter id is required");
        if(map.containsKey("id") && map.get("id") != null)
            queryWrapper.like("id", map.get("id"));
        if(map.containsKey("taskId") && map.get("taskId") != null)
            queryWrapper.like("task_id", map.get("taskId"));
        if (map.containsKey("description") && map.get("description") != null)
            queryWrapper.like("description", map.get("description"));
        if(map.containsKey("startTime") && map.get("startTime") != null && !map.get("startTime").equals("")) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date startDate = dateFormat.parse(String.valueOf(map.get("startTime")));
            Timestamp startTime = new Timestamp(startDate.getTime());
            queryWrapper.lambda().ge(Submission::getSubmittedTime,startTime);
        }
        if(map.containsKey("endTime") && map.get("endTime") != null && !map.get("endTime").equals("")) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date startDate = dateFormat.parse(String.valueOf(map.get("endTime")));
            Timestamp endTime = new Timestamp(startDate.getTime());
            queryWrapper.lambda().le(Submission::getSubmittedTime,endTime);
        }

        Page<Submission> page = new Page<>((Integer)map.get("pageNum"), (Integer)map.get("pageSize"));
        Page<Submission> submissionPage = submissionService.page(page, queryWrapper);

        List<Submission> submissionList = submissionPage.getRecords();
        return HttpResponseEntity.response(!submissionList.isEmpty(), "query submission ", submissionList);
    }

    @PostMapping("/administrator/querySubmissionList")
    public HttpResponseEntity querySubmissionList_administrator(@RequestBody Map<String, Object> map) throws ParseException {
        if((Integer)map.get("pageNum") < 1 || (Integer)map.get("pageSize") < 1)
            return HttpResponseEntity.error("pageNum or pageSize is invalid");
        QueryWrapper<Submission> queryWrapper = new QueryWrapper<>();
        if (map.containsKey("submitterId") && map.get("submitterId") != null)
            queryWrapper.eq("submitter_id", map.get("submitterId"));
        if(map.containsKey("id") && map.get("id") != null)
            queryWrapper.like("id", map.get("id"));
        if(map.containsKey("taskId") && map.get("taskId") != null)
            queryWrapper.like("task_id", map.get("taskId"));
        if (map.containsKey("description") && map.get("description") != null)
            queryWrapper.like("description", map.get("description"));
        if(map.containsKey("startTime") && map.get("startTime") != null && !map.get("startTime").equals("")) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date startDate = dateFormat.parse(String.valueOf(map.get("startTime")));
            Timestamp startTime = new Timestamp(startDate.getTime());
            queryWrapper.lambda().ge(Submission::getSubmittedTime,startTime);
        }
        if(map.containsKey("endTime") && map.get("endTime") != null && !map.get("endTime").equals("")) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date startDate = dateFormat.parse(String.valueOf(map.get("endTime")));
            Timestamp endTime = new Timestamp(startDate.getTime());
            queryWrapper.lambda().le(Submission::getSubmittedTime,endTime);
        }

        Page<Submission> page = new Page<>((Integer)map.get("pageNum"), (Integer)map.get("pageSize"));
        Page<Submission> submissionPage = submissionService.page(page, queryWrapper);

        List<Submission> submissionList = submissionPage.getRecords();
        if(map.containsKey("administratorId") && map.get("administratorId") != null)
            submissionList.removeIf(submission -> !taskService.getTaskById(submission.getTaskId()).getAppointerId().equals(map.get("administratorId")));
        return HttpResponseEntity.response(!submissionList.isEmpty(), "query submission ", submissionList);
    }
}
