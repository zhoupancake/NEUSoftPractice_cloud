package com.system.controller;

import com.system.common.HttpResponseEntity;
import com.system.dto.RequestCharacterEntity;
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
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/")
@Slf4j
@RequiredArgsConstructor
public class UserController {
    private static final int MAX_LOGIN_ATTEMPTS = 5;
    private static final int LOCK_DURATION_MINUTES = 900;
    private final RedisTemplate<String, Integer> redisTemplate;
    private final UserService userService;
    private final AdministratorService administratorService;
    private final GridDetectorService gridManagerService;
    private final SupervisorService supervisorService;

    @PostMapping("/login")
    public HttpResponseEntity login(@RequestBody User user, HttpServletResponse response) {
        // check whether the account exist and in usable status
        User loginUser = userService.query().eq("username", user.getUsername()).list().get(0);
        if (loginUser == null)
            return HttpResponseEntity.response(false, "Error username or password", null);
        if (loginUser.getStatus() == 0)
            return HttpResponseEntity.response(false, "login, The account is banned", null);

        // check whether the account is locked because of too many login attempts
        boolean boos = redisTemplate.hasKey(loginUser.getId());
        if (boos) {
            Integer attempts = redisTemplate.opsForValue().get(loginUser.getId());
            if (attempts == null) {
                attempts = 0;
            }
            if (attempts >= MAX_LOGIN_ATTEMPTS) {
                redisTemplate.expire(user.getUsername(), LOCK_DURATION_MINUTES, TimeUnit.SECONDS);
                return HttpResponseEntity.response(false, "The account is locked with 15 minutes", null);
            }
        }
        else {
            redisTemplate.opsForValue().set(loginUser.getId(), 0);
        }
        if(user.getPassword().equals(loginUser.getPassword())) {
            redisTemplate.opsForValue().set(loginUser.getId(), 0);
            switch (loginUser.getRole()) {
                case "Administrator" -> {
                    Administrator administrator = administratorService.getById(loginUser.getId());
                    Cookie cookie = new Cookie("administratorId", administrator.getId());
                    cookie.setSecure(true);
                    cookie.setHttpOnly(true);
                    response.addCookie(cookie);
                    return HttpResponseEntity.response(true, "Login successfully", administrator);
                }
                case "GridManager" -> {
                    GridDetector gridDetector = gridManagerService.getById(loginUser.getId());
                    Cookie cookie = new Cookie("gridManagerId", gridDetector.getId());
                    cookie.setSecure(true);
                    cookie.setHttpOnly(true);
                    response.addCookie(cookie);
                    return HttpResponseEntity.response(true, "Login successfully", gridDetector);
                }
                case "Supervisor" -> {
                    Supervisor supervisor = supervisorService.getById(loginUser.getId());
                    Cookie cookie = new Cookie("supervisorId", supervisor.getId());
                    cookie.setSecure(true);
                    cookie.setHttpOnly(true);
                    response.addCookie(cookie);
                    return HttpResponseEntity.response(true, "Login successfully", supervisor);
                }
                default -> {
                    return HttpResponseEntity.response(false, "Access Deny", null);
                }
            }
        }
        else {
            redisTemplate.opsForValue().increment(loginUser.getId());
            return HttpResponseEntity.response(false, "login, reason Error username or password", null);
        }
    }

    @PostMapping("/changePassword")
    public HttpResponseEntity changePassword(@RequestBody RequestCharacterEntity requestCharacterEntity) {
        Map<String, String> map = requestCharacterEntity.getUser_modifyPassword();
        User requestUser = userService.getById(map.get("id"));
        if (requestUser == null)
            return HttpResponseEntity.response(false, "The modify user is not exist", null);
        if(Boolean.TRUE.equals(redisTemplate.hasKey(map.get("id"))))
            if((Integer)redisTemplate.opsForValue().get(map.get("id")) >= MAX_LOGIN_ATTEMPTS) {
                redisTemplate.expire(map.get("id"), LOCK_DURATION_MINUTES, TimeUnit.SECONDS);
                return HttpResponseEntity.response(false, "The account is locked with 15 minutes", null);
            }
        if (requestUser.getPassword().equals(map.get("password"))) {
            redisTemplate.opsForValue().set(map.get("id"), 0);
            requestUser.setPassword(map.get("newPassword"));
            userService.updateById(requestUser);
            System.out.println(userService.getById(requestUser.getId()));
            return HttpResponseEntity.response(true, "modify successfully", null);
        }
        else {
            redisTemplate.opsForValue().increment(map.get("id"));
            return HttpResponseEntity.response(false, "The original password is wrong", null);
        }
    }
}
