package com.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.system.common.HttpResponseEntity;
import com.system.dto.ResponseTaskEntity;
import com.system.entity.character.Supervisor;
import com.system.entity.data.City;
import com.system.entity.data.Report;
import com.system.entity.data.Submission;
import com.system.entity.data.Task;
import com.system.service.CharacterServiceFeignClient;
import com.system.service.CityServiceFeignClient;
import com.system.service.ReportServiceFeignClient;
import com.system.service.TaskService;
import com.system.util.SnowflakeUtil;
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
 * task controller for testing the CRUD operations
 * can be called using super admin account to call
 */
@RestController
@RequestMapping("/hide/task")
@Slf4j
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;
    private final CityServiceFeignClient cityService;
    private final ReportServiceFeignClient reportService;
    private final CharacterServiceFeignClient characterService;

    /**
     * create a new task
     * @param task the task object need to be created
     * @return the response entity to indicate the result
     */
    @PostMapping("/addTask")
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
        return HttpResponseEntity.response(success, "create task", null);
    }

    /**
     * modify a task
     * @param task the task object need to be modified
     * @return the response entity to indicate the result
     */
    @PostMapping("/modifyTask")
    public HttpResponseEntity modifyTask(@RequestBody Task task) {
        if(null == taskService.getById(task.getId()))
            return HttpResponseEntity.error("task not found");
        if(null == reportService.getReportById(task.getRelativeReportId()))
            return HttpResponseEntity.error("report not found");
        if(null == characterService.getAdministratorById(task.getAppointerId()))
            return HttpResponseEntity.error("character not found");
        if(null == characterService.getDetectorById(task.getAppointeeId()))
            return HttpResponseEntity.error("character not found");
        boolean success = taskService.updateById(task);
        return HttpResponseEntity.response(success, "modify ", null);
    }

    /**
     * delete a task
     * @param task the task object need to be deleted
     * @return the response entity to indicate the result
     */
    @PostMapping("/deleteTask")
    public HttpResponseEntity deleteTaskById(@RequestBody Task task) {
        if(null == taskService.getById(task.getId()))
            return HttpResponseEntity.error("task not found");
        boolean success = taskService.removeById(task);
        return HttpResponseEntity.response(success, "delete task record", null);
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
    @PostMapping("/queryTaskList")
    public HttpResponseEntity queryTaskList(@RequestBody Map<String, Object> map) throws ParseException {
        if((Integer)map.get("pageNum") < 1 || (Integer)map.get("pageSize") < 1)
            return HttpResponseEntity.error("pageNum and pageSize must be positive");
        QueryWrapper<Task> queryWrapper = new QueryWrapper<>();
        if(map.containsKey("appointerId") && map.get("appointerId") != null)
            queryWrapper.eq("appointer_id", map.get("appointerId"));
        else
            return HttpResponseEntity.error("appointerId is required");
        if(map.containsKey("id") && map.get("id") != null)
            queryWrapper.like("id", map.get("id"));
        if(map.containsKey("appointeeId") && map.get("appointeeId") != null)
            queryWrapper.like("appointee_id", map.get("appointeeId"));
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
        if (map.containsKey("status") && map.get("status") != null)
            queryWrapper.like("status", map.get("status"));
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
                result.add(new ResponseTaskEntity(task, report, city, supervisor));
            }
        return HttpResponseEntity.response(success, "query", result);
    }
}
