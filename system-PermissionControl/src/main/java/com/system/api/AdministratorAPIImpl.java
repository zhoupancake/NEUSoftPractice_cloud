package com.system.api;

import com.system.entity.character.Administrator;
import com.system.service.AdministratorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/administrator")
@Slf4j
@RequiredArgsConstructor
public class AdministratorAPIImpl implements AdministratorAPI{
    private final AdministratorService administratorService;
    @Override
    @PostMapping("/getAdministratorById")
    public Administrator getAdministratorById(@RequestBody String id) {
        return administratorService.getById(id);
    }
}
