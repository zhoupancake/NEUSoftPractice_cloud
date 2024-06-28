package com.system.api;

import com.system.entity.character.Administrator;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * The interface grid detector inner micro-service api.
 */
public interface IPAPI {
    @PostMapping("/api/IPExist")
    public boolean IPExist(@RequestBody String ip);
}
