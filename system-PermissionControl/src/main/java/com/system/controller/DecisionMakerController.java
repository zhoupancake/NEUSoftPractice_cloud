package com.system.controller;

import com.system.common.HttpResponseEntity;
import com.system.dto.IP;
import com.system.dto.User;
import com.system.service.IPService;
import com.system.service.UserService;
import com.system.util.SnowflakeUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/decisionMaker")
@Slf4j
@RequiredArgsConstructor
public class DecisionMakerController {
    private final UserService userService;
    private final IPService ipService;
    @PostMapping("/addDecisionMaker")
    public HttpResponseEntity addDecisionMaker(@RequestBody Map<String, String> map) {
        String username = "";
        String password = "";
        if(map.containsKey("username") && map.get("username") != null && !map.get("username").isEmpty())
            username = map.get("username");
        else
            return HttpResponseEntity.error("the username is not null");
        if(userService.query().eq("username", username).list().isEmpty())
            return HttpResponseEntity.error("the username is already exist");
        if(map.containsKey("password") && map.get("password") != null && !map.get("password").isEmpty())
            password = map.get("password");
        else
            return HttpResponseEntity.error("the password is not null");
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setRole("decisionMaker");
        user.setStatus(1);
        user.setId(SnowflakeUtil.genId());
        return userService.save(user) ? HttpResponseEntity.success("add decisionMaker success") : HttpResponseEntity.error("add decisionMaker failed");
    }

    @PostMapping("/deleteDecisionMakerById")
    public HttpResponseEntity deleteDecisionMakerById(@RequestBody Map<String, String> map) {
        String id = "";
        if(map.containsKey("id") && map.get("id") != null && !map.get("id").isEmpty())
            id = map.get("id");
        else
            return HttpResponseEntity.error("the id is not null");
        return userService.removeById(id) ? HttpResponseEntity.success("delete decisionMaker success") : HttpResponseEntity.error("delete decisionMaker failed");
   }

    @PostMapping("/modifyDecisionMaker")
    public HttpResponseEntity modifyDecisionMaker(@RequestBody Map<String, String> map) {
        String id = "";
        if(map.containsKey("id") && map.get("id") != null && !map.get("id").isEmpty())
            id = map.get("id");
        else
            return HttpResponseEntity.error("the id is not null");
        int status = Integer.parseInt(map.get("status"));
        if(status != 0 && status != 1)
            return HttpResponseEntity.error("the status must be 0 or 1");
        boolean success = userService.updateById(new User(id, map.get("username"), map.get("password"), status, "DecisionMaker"));
        return HttpResponseEntity.response(success, "modify decisionMaker", null);
    }

    @PostMapping("/addIP")
    public HttpResponseEntity addIP(@RequestBody Map<String, String> map){
        String username = "";
        String password = "";
        String ipAddress = "";
        if(map.containsKey("username") && map.get("username") != null && !map.get("username").isEmpty())
            username = map.get("username");
        else
            return HttpResponseEntity.error("the username is not null");
        if(map.containsKey("password") && map.get("password") != null && !map.get("password").isEmpty())
            password = map.get("password");
        else
            return HttpResponseEntity.error("the password is not null");
        if(userService.query().eq("username", username).eq("password", password).list().isEmpty())
            return HttpResponseEntity.error("the username or password is wrong");
        if(map.containsKey("ipAddress") && map.get("ip") != null && !map.get("ip").isEmpty())
            ipAddress = map.get("ipAddress");
        Pattern pattern = Pattern.compile("/^((\\d|[1-9]\\d|1\\d\\d|2[0-4]\\d|25[0-5])\\.){3}(\\d|[1-9]\\d|1\\d\\d|2[0-4]\\d|25[0-5])(?::(?:[0-9]|[1-9][0-9]{1,3}|[1-5][0-9]{4}|6[0-4][0-9]{3}|65[0-4][0-9]{2}|655[0-2][0-9]|6553[0-5]))?$/");
        Matcher telMatcher = pattern.matcher(ipAddress);
        if(!telMatcher.matches())
            return HttpResponseEntity.error("the ipAddress is not correct");
        return ipService.save(new IP(ipAddress)) ? HttpResponseEntity.success("add ip success") : HttpResponseEntity.error("add ip failed");
    }

    @PostMapping("/deleteIP")
    public HttpResponseEntity deleteIP(@RequestBody Map<String, String> map){
        String username = "";
        String password = "";
        String ipAddress = "";
        if(map.containsKey("username") && map.get("username") != null && !map.get("username").isEmpty())
            username = map.get("username");
        else
            return HttpResponseEntity.error("the username is not null");
        if(map.containsKey("password") && map.get("password") != null && !map.get("password").isEmpty())
            password = map.get("password");
        else
            return HttpResponseEntity.error("the password is not null");
        if(userService.query().eq("username", username).eq("password", password).list().isEmpty())
            return HttpResponseEntity.error("the username or password is wrong");
        if(map.containsKey("ipAddress") && map.get("ip") != null && !map.get("ip").isEmpty())
            ipAddress = map.get("ipAddress");
        if(ipService.query().eq("ip_address", ipAddress).list().isEmpty())
            return HttpResponseEntity.error("the ipAddress is not correct");
        return ipService.removeById(ipAddress) ? HttpResponseEntity.success("delete ip success") : HttpResponseEntity.error("delete ip failed");
    }
}
