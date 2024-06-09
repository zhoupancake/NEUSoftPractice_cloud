package com.system.api;

import com.system.entity.data.Task;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface TaskAPI {
    @PostMapping("/api/getTaskById")
    public Task getTaskById(@RequestBody String id);
    @PostMapping("/api/updateTask")
    public boolean updateTaskById(@RequestBody Task task);
}
