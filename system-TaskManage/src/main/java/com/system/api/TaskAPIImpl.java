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

/**
 * The task inner micro-service api impl
 */
@RestController
@RequestMapping("/api/task")
@Slf4j
@RequiredArgsConstructor
public class TaskAPIImpl  implements TaskAPI{
    private final TaskService taskService;
    private final ReportServiceFeignClient reportService;
    private final CharacterServiceFeignClient characterService;

    /**
     * get the task by id
     * @param id the id of the task to be get
     * @return the task object
     */
    @Override
    @PostMapping("/getTaskById")
    public Task getTaskById(@RequestBody String id) {
        return taskService.getById(id);
    }

    /**
     * update the task by id
     * @param task the updated task object to replace the old one
     * @return boolean value to indicate the result
     */
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

    /**
     * get the task id by appointer id
     * @param appointerId the administrator id to query the task he or she created
     * @return the task id array that the administrator created
     */
    @Override
    @PostMapping("/getTaskIdByAppointerId")
    public String[] getTaskIdByAppointerId(@RequestBody String appointerId) {
        if(null == characterService.getAdministratorById(appointerId))
            return null;
        List<Task> list = taskService.query().eq("appointer_id", appointerId).list();
        return list.stream().map(Task::getId).toArray(String[]::new);
    }

    /**
     * get the task count
     * @return the number of the task in database
     */
    @Override
    @GetMapping("/getTaskCount")
    public int getTaskCount(){
        return taskService.query().list().size();
    }
}
