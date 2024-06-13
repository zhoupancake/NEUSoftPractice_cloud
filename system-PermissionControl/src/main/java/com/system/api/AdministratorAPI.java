package com.system.api;

import com.system.entity.character.Administrator;
import com.system.entity.data.Report;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface AdministratorAPI {
    @PostMapping("/getAdministratorById")
    public String getAdministratorById(@RequestBody String id);

    @PostMapping("/getAdministratorIdByName")
    public String[] getAdministratorIdByName(String name);
}
