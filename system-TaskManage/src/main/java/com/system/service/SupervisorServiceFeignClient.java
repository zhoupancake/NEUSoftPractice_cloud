package com.system.service;

import com.system.entity.character.Supervisor;
import org.springframework.web.bind.annotation.PostMapping;

public interface SupervisorServiceFeignClient {
    @PostMapping("/api/getSupervisorById")
    public Supervisor getSupervisorById(String id);

}
