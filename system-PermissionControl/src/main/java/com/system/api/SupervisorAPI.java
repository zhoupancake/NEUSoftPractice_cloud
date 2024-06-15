package com.system.api;

import com.system.entity.character.Supervisor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface SupervisorAPI {
    @PostMapping("/getSupervisorById")
    public Supervisor getSupervisorById(@RequestBody String id);
}
