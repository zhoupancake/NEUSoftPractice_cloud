package com.system.api;

import com.system.entity.character.Administrator;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 *  The interface administrator inner micro-service api.
 */
public interface AdministratorAPI {
    @PostMapping("/api/administrator/getAdministratorById")
    public Administrator getAdministratorById(@RequestBody String id);
}
