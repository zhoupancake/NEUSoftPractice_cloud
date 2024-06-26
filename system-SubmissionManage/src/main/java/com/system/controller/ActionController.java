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

import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.sql.Wrapper;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
/**
 * the external interface for submission-concerning operation
 * this controller is exposed to the outside request
 */
@RestController
@RequestMapping("/submission")
@Slf4j
@RequiredArgsConstructor
public class ActionController {
    private final SubmissionService submissionService;
    private final ReportServiceFeignClient reportService;
    private final TaskServiceFeignClient taskService;
    private final AirDataServiceFeignClient airDataService;
    private final CityServiceFeignClient cityService;
    private final CharacterServiceFeignClient characterService;

    /**
     * submit a submission when confirming the report based on task
     * @Request_character grid detector
     * @param requestSubmissionEntity contains the necessary information of the submission
     * @return http response entity to indicate whether the submission is successful
     */
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

    /**
     * get submission by id
     * @Request_character administrator
     * @param map contains the id of the submission
     * @return the submission object
     */
    @PostMapping("/administrator/getSubmissionById")
    public HttpResponseEntity getSubmissionById(@RequestBody Map<String, Object> map) {
        String id = (String) map.get("id");
        Submission submission = submissionService.getById(id);
        if(null == submission)
            return HttpResponseEntity.error("submission not exist");
        return HttpResponseEntity.success("get submission", submission);
    }
    /**
     * query submission list
     * @Request_character grid detector
     * @param map contains the necessary information of the query
     * @key_in_map pageNum page number
     * @key_in_map pageSize page size
     * @key_in_map submitterId the id of the grid detector who submits the submission
     * @key_in_map city the city of the submission
     * @key_in_map province the province of the submission
     * @key_in_map startTime the start time of the submission query range
     * @key_in_map endTime the end time of the submission query range
     * @key_in_map taskId the id of the task the submission belongs to
     * @key_in_map description the description of the submission
     * @return http response entity to indicate whether the query is successful
     * @throws ParseException if the start time or end time is not valid
     */
    @PostMapping("/gridDetector/querySubmissionList")
    public HttpResponseEntity querySubmissionList_gridDetector(@RequestBody Map<String, Object> map) throws ParseException {
        if((Integer)map.get("pageNum") < 1 || (Integer)map.get("pageSize") < 1)
            return HttpResponseEntity.error("pageNum or pageSize is invalid");
        QueryWrapper<Submission> queryWrapper = new QueryWrapper<>();
        if (map.containsKey("submitterId") && map.get("submitterId") != null)
            queryWrapper.eq("submitter_id", map.get("submitterId"));
        else
            return HttpResponseEntity.error("submitter id is required");
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
        Map<String, Object> result = Map.of("count", submissionService.count(queryWrapper), "result", submissionList);
        return HttpResponseEntity.response(!submissionList.isEmpty(), "query submission ", result);
    }

    /**
     * query submission list
     * @Request_character administrator
     * @param map contains the necessary information of the query
     * @key_in_map pageNum page number
     * @key_in_map pageSize page size
     * @key_in_map submitterId the id of the grid detector who submits the submission
     * @key_in_map city the city of the submission
     * @key_in_map province the province of the submission
     * @key_in_map startTime the start time of the submission query range
     * @key_in_map endTime the end time of the submission query range
     * @key_in_map taskId the id of the task the submission belongs to
     * @key_in_map description the description of the submission
     * @return http response entity to indicate whether the query is successful
     * @throws ParseException if the start time or end time is not valid
     */
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
        Map<String, Object> result = Map.of("count", submissionService.count(queryWrapper), "result", submissionList);
        return HttpResponseEntity.response(!submissionList.isEmpty(), "query submission ", result);
    }

    /**
     * get the number of the submission, task, and report in the database
     * @return http response entity contains the number of the submission, task, and report in the database
     */
    @GetMapping("/digitalScreen/getProcession")
    public HttpResponseEntity getProcession() {
        Map<String,Integer> result = Map.of("report", reportService.getReportCount(),
                                            "task", taskService.getTaskCount(),
                                            "submission", submissionService.query().list().size());
        return HttpResponseEntity.response(true, "get procession", result);
    }
}
