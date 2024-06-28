package com.system.api;

import com.system.entity.character.Supervisor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * The interface supervisor inner micro-service api.
 */
public interface SupervisorAPI {
    @PostMapping("/api/supervisor/getSupervisorById")
    public Supervisor getSupervisorById(@RequestBody String id);
}
