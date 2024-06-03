package com.system.controller;

import com.system.common.HttpResponseEntity;
import com.system.dto.User;
import com.system.entity.character.Administrator;
import com.system.entity.character.GridDetector;
import com.system.entity.character.Supervisor;
import com.system.service.AdministratorService;
import com.system.service.GridDetectorService;
import com.system.service.SupervisorService;
import com.system.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/")
@Slf4j
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final AdministratorService administratorService;
    private final GridDetectorService gridManagerService;
    private final SupervisorService supervisorService;

    @PostMapping("/login")
    public HttpResponseEntity login(@RequestBody User user, HttpServletResponse response) {
        List<User> administratorList = userService.query()
                .eq("username", user.getUsername())
                .eq("password", user.getPassword())
                .eq("status", '1')
                .list();
        if(administratorList.isEmpty())
            return HttpResponseEntity.response(false, "用户名或密码错误", null);
        else{
            User loginUser = administratorList.get(0);
            switch (loginUser.getRole()) {
                case "Administrator" -> {
                    Administrator administrator = administratorService.getById(loginUser.getId());
                    Cookie cookie = new Cookie("administratorId", administrator.getId());
                    cookie.setSecure(true);
                    cookie.setHttpOnly(true);
                    response.addCookie(cookie);
                    return HttpResponseEntity.response(true, "登录", administrator);
                }
                case "GridManager" -> {
                    GridDetector gridDetector = gridManagerService.getById(loginUser.getId());
                    Cookie cookie = new Cookie("gridManagerId", gridDetector.getId());
                    cookie.setSecure(true);
                    cookie.setHttpOnly(true);
                    response.addCookie(cookie);
                    return HttpResponseEntity.response(true, "登录", gridDetector);
                }
                case "Supervisor" -> {
                    Supervisor supervisor = supervisorService.getById(loginUser.getId());
                    Cookie cookie = new Cookie("supervisorId", supervisor.getId());
                    cookie.setSecure(true);
                    cookie.setHttpOnly(true);
                    response.addCookie(cookie);
                    return HttpResponseEntity.response(true, "登录", supervisor);
                }
                default -> {
                    return HttpResponseEntity.response(false, "用户角色信息错误，请联系管理员", null);
                }
            }
        }
    }
}
