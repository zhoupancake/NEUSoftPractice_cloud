package com.system.controller;

import com.system.common.HttpResponseEntity;
import com.system.dto.RequestCharacterEntity;
import com.system.dto.User;
import com.system.entity.character.Supervisor;
import com.system.entity.data.City;
import com.system.service.CityServiceFeignClient;
import com.system.service.SupervisorService;
import com.system.service.UserService;
import com.system.util.SnowflakeUtil;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/supervisor")
@Slf4j
@RequiredArgsConstructor
public class SupervisorController {
    private final UserService userService;
    private final SupervisorService supervisorService;
    @Resource
    private final CityServiceFeignClient cityService;

    @PostMapping("/addSupervisor")
    public HttpResponseEntity addSupervisor(@RequestBody RequestCharacterEntity requestCharacterEntity) {
        Supervisor supervisor = requestCharacterEntity.getSupervisor_create();
        User user = requestCharacterEntity.getUser_create();
        City city = requestCharacterEntity.getCity();
        Map<String, String> location = new HashMap<String, String>();
        location.put("province", city.getProvince());
        location.put("city", city.getName());
        String cityId = cityService.getCityByLocation(location).getId();

        supervisor.setId(SnowflakeUtil.genId());
        supervisor.setCityId(cityId);
        user.setId(SnowflakeUtil.genId());
        user.setUsername(supervisor.getTel());
        user.setStatus(1);
        user.setRole("Supervisor");

        boolean supervisorSuccess = supervisorService.save(supervisor);
        boolean userSuccess = userService.save(user);

        return HttpResponseEntity.response(supervisorSuccess&&userSuccess, "创建", null);
    }

    @PostMapping("/modifySupervisor")
    public HttpResponseEntity modifySupervisor(@RequestBody RequestCharacterEntity requestCharacterEntity) {
        Supervisor supervisor = requestCharacterEntity.getSupervisor_modify();
        User user = requestCharacterEntity.getUser_modify();
        City city = requestCharacterEntity.getCity();
        Map<String, String> location = new HashMap<String, String>();
        location.put("province", city.getProvince());
        location.put("city", city.getName());
        String cityId = cityService.getCityByLocation(location).getId();

        user.setUsername(supervisor.getTel());
        supervisor.setCityId(cityId);

        boolean supervisorSuccess = supervisorService.updateById(supervisor);
        boolean userSuccess = userService.updateById(user);

        return HttpResponseEntity.response(supervisorSuccess&&userSuccess, "修改", null);
    }

    @PostMapping("/deleteSupervisor")
    public HttpResponseEntity deleteSupervisorById(@RequestBody RequestCharacterEntity requestCharacterEntity) {
        Supervisor supervisor = requestCharacterEntity.getSupervisor_modify();
        User user = requestCharacterEntity.getUser_modify();

        user.setUsername(supervisor.getTel());

        boolean supervisorSuccess = supervisorService.removeById(supervisor);
        boolean userSuccess = userService.removeById(user);

        return HttpResponseEntity.response(supervisorSuccess&&userSuccess, "删除", null);
    }

    @PostMapping("/querySupervisorList")
    public HttpResponseEntity querySupervisorList(@RequestBody Map<String, Object> map) {
        Integer pageNum = (Integer) map.get("pageNum");
        Integer pageSize = (Integer) map.get("pageSize");
        Page<Supervisor> page = new Page<>(pageNum, pageSize);
        supervisorService.query().eq("status", "1")
                .like("username", map.get("username")).page(page);
        List<Supervisor> supervisorList = page.getRecords();
        boolean success = !supervisorList.isEmpty();
        return HttpResponseEntity.response(success, "查询", supervisorList);
    }

    @PostMapping("/logout")
    public HttpResponseEntity logout(HttpServletResponse response) {
        return HttpResponseEntity.response(true, "登出", null);
    }
}
