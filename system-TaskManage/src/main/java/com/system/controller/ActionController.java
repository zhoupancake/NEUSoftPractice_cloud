package com.system.controller;

import com.system.common.HttpResponseEntity;
import com.system.entity.data.Task;
import com.system.service.AirDataServiceFallback;
import com.system.service.ReportServiceFallback;
import com.system.service.TaskService;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/task")
@Slf4j
@RequiredArgsConstructor
public class ActionController {
    private final TaskService taskService;
    @Resource
    private ReportServiceFallback reportServiceFallback;
    @Resource
    private AirDataServiceFallback airDataServiceFallback;
    @PostMapping("/appoint")
    public HttpResponseEntity appoint(@RequestBody Task task) {
        if(null == reportServiceFallback.getReportById(task.getRelativeReportId()))
            return HttpResponseEntity.error("该报告不存在");
        if(null == airDataServiceFallback.getAirDataById(task.getRelativeAirDataId()))
            return HttpResponseEntity.error("该空气数据不存在");
        boolean success = taskService.save(task);
        return HttpResponseEntity.response(success, "修改", null);
    }
}
