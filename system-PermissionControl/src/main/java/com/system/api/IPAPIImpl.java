package com.system.api;

import com.system.service.IPService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RequiredArgsConstructor
public class IPAPIImpl implements IPAPI{
    private final IPService ipService;
    @PostMapping("/api/IPExist")
    public boolean IPExist(@RequestBody String ip){
        return ipService.getById(ip) != null;
    }
}
