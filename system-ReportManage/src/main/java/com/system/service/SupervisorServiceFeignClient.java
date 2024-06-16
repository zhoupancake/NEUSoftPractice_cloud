package com.system.service;

import com.system.entity.character.Supervisor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name="system-PermissionControl", url="localhost:8081") //  远程服务的名称
public interface SupervisorServiceFeignClient {
    @PostMapping("/api/getSupervisorById")
    public Supervisor getSupervisorById(@RequestBody String id);
}
