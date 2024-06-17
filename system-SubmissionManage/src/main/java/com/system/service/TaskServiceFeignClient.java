package com.system.service;

import com.system.entity.data.Task;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name="system-TaskManage", url="localhost:8084") //  远程服务的名称
public interface TaskServiceFeignClient {
    @PostMapping("/api/task/getTaskById")
    public Task getTaskById(@RequestBody String id);

    @PostMapping("/api/task/updateTaskById")
    public boolean updateTaskById(@RequestBody Task task);

    @PostMapping("/api/task/getTaskIdByAppointerId")
    public String[] getTaskIdByAppointerId(@RequestBody String appointerId);

}
