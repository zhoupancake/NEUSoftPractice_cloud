package com.system.api;

import com.system.entity.character.Supervisor;
import com.system.service.SupervisorService;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/supervisor")
@Slf4j
@RequiredArgsConstructor
public class SupervisorAPIImpl implements SupervisorAPI{
    @Resource
    private final SupervisorService supervisorService;
    @Override
    @PostMapping("/getSupervisorById")
    public Supervisor getSupervisorById(@RequestBody String id){
        return supervisorService.getById(id);
    }
}
