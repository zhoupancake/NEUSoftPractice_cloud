package com.system.api;

import com.system.entity.character.Supervisor;
import org.springframework.web.bind.annotation.PostMapping;

public interface SupervisorAPI {
    @PostMapping("/getSupervisorById")
    public Supervisor getSupervisorById(String id);
}
