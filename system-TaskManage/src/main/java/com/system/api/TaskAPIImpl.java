package com.system.api;

import com.system.common.HttpResponseEntity;
import com.system.entity.character.Administrator;
import com.system.entity.data.Task;
import com.system.service.CharacterServiceFeignClient;
import com.system.service.ReportServiceFeignClient;
import com.system.service.TaskService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/task")
@Slf4j
@RequiredArgsConstructor
public class TaskAPIImpl  implements TaskAPI{
    private final TaskService taskService;
    private final ReportServiceFeignClient reportService;
    private final CharacterServiceFeignClient characterService;
    @Override
    @PostMapping("/getTaskById")
    public Task getTaskById(@RequestBody String id) {
        return taskService.getById(id);
    }

    @Override
    @PostMapping("/updateTaskById")
    public boolean updateTaskById(@RequestBody Task task){
        if(null == taskService.getById(task.getId()))
            return false;
        if(null == reportService.getReportById(task.getRelativeReportId()))
            return false;
        if(null == characterService.getAdministratorById(task.getAppointerId()))
            return false;
        if(null == characterService.getDetectorById(task.getAppointeeId()))
            return false;
        return taskService.updateById(task);
    }

    @Override
    @PostMapping("/getTaskIdByAppointerId")
    public String[] getTaskIdByAppointerId(@RequestBody String appointerId) {
        if(null == characterService.getAdministratorById(appointerId))
            return null;
        List<Task> list = taskService.query().eq("appointer_id", appointerId).list();
        return list.stream().map(Task::getId).toArray(String[]::new);
    }

    @Override
    @GetMapping("/getTaskCount")
    public int getTaskCount(){
        return taskService.query().list().size();
    }
}
