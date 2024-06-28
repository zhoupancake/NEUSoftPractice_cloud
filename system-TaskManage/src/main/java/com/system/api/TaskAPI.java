package com.system.api;

import com.system.entity.data.Task;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
/**
 *  The interface report inner micro-service api.
 */
public interface TaskAPI {
    @PostMapping("/api/getTaskById")
    public Task getTaskById(@RequestBody String id);
    @PostMapping("/api/updateTask")
    public boolean updateTaskById(@RequestBody Task task);

    @PostMapping("/api/getTaskIdByAppointerId")
    public String[] getTaskIdByAppointerId(@RequestBody String appointerId);

    @GetMapping("/api/task/getTaskCount")
    public int getTaskCount();
}
