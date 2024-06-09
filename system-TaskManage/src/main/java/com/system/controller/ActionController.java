package com.system.controller;

import com.system.common.HttpResponseEntity;
import com.system.entity.data.Report;
import com.system.entity.data.Task;
import com.system.service.AirDataServiceFallback;
import com.system.service.ReportServiceFallback;
import com.system.service.TaskService;
import com.system.util.SnowflakeUtil;
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
    public HttpResponseEntity addTask(@RequestBody Task task) {
        task.setId(SnowflakeUtil.genId());
        task.setStatus(0);
        boolean success = taskService.save(task);
        return HttpResponseEntity.response(success, "create task", null);
    }
}
