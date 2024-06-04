package com.system.service;

import com.system.entity.data.Task;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name="system-TaskManage", url="localhost:8084") //  远程服务的名称
public interface TaskServiceFallback {
    @PostMapping("/getTaskById")
    public Task getTaskById(@RequestBody String id);

}
