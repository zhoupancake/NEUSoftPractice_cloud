package com.system.api;

import com.system.common.HttpResponseEntity;
import com.system.entity.data.Task;
import com.system.service.TaskService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
@Slf4j
@RequiredArgsConstructor
public class TaskAPIImpl  implements TaskAPI{
    private final TaskService taskService;
    @Override
    @PostMapping("/getReportById")
    public Task getTaskById(@RequestBody String id) {
        return taskService.getById(id);
    }

    @Override
    @PostMapping("/api/updateTask")
    public boolean updateTaskById(@RequestBody Task task){
        return taskService.updateById(task);
    }

}
