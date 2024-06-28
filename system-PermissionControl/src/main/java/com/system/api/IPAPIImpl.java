package com.system.api;

import com.system.service.IPService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * the implementation of the IPAPI
 */
@RequiredArgsConstructor
public class IPAPIImpl implements IPAPI{
    private final IPService ipService;

    /**
     * check whether the ip exists in the allowing-visit list
     * @param ip the ip to be checked
     * @return boolean result to indicate the result
     */
    @PostMapping("/api/IPExist")
    public boolean IPExist(@RequestBody String ip){
        String ipv4Pattern = "^(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$";
        Pattern pattern = Pattern.compile(ipv4Pattern);
        Matcher matcher = pattern.matcher(ip);
        if(!matcher.matches())
            return false;
        return ipService.getById(ip) != null;
    }
}
