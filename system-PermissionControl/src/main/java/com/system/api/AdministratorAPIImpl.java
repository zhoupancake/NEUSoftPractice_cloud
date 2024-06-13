package com.system.api;

import com.system.entity.character.Administrator;
import com.system.service.AdministratorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@Slf4j
@RequiredArgsConstructor
public class AdministratorAPIImpl implements AdministratorAPI{
    private final AdministratorService administratorService;
    @PostMapping("/getAdministratorById")
    public String getAdministratorById(String id) {
        return administratorService.getById(id).getName();
    }

    @PostMapping("/getAdministratorIdByName")
    public String[] getAdministratorIdByName(String name){
        List<Administrator> list = administratorService.query().eq("name", name).list();
        return list.stream().map(Administrator::getId).toArray(String[]::new);
    }
}
