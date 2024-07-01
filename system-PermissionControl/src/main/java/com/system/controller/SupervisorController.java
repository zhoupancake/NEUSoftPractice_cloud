package com.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.system.common.HttpResponseEntity;
import com.system.dto.RequestCharacterEntity;
import com.system.dto.User;
import com.system.entity.character.Supervisor;
import com.system.entity.data.City;
import com.system.service.CityServiceFeignClient;
import com.system.service.SupervisorService;
import com.system.service.UserService;
import com.system.util.SHA256Util;
import com.system.util.SnowflakeUtil;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * supervisor controller for testing the CRUD operations
 * can be called using super admin account to call
 */
@RestController
@RequestMapping("/hide/supervisor")
@Slf4j
@RequiredArgsConstructor
public class SupervisorController {
    private final UserService userService;
    private final SupervisorService supervisorService;
    private final CityServiceFeignClient cityService;

    /**
     * add supervisor
     * @param requestCharacterEntity requestCharacterEntity contains the supervisor and user needed to create
     * @return http response entity to indicate whether the operation is successful
     */
    @PostMapping("/addSupervisor")
    public HttpResponseEntity addSupervisor(@RequestBody RequestCharacterEntity requestCharacterEntity) {
        Supervisor supervisor = requestCharacterEntity.getSupervisor_create();
        User user = requestCharacterEntity.getUser_create();

        String telRegex = "^(?:(?:(?:\\+|00)86)?\\s*)?1(?:(?:3[\\d])|(?:4[5-79])|(?:5[0-35-9])|(?:6[5-7])|(?:7[0-8])|(?:8[\\d])|(?:9[189]))\\d{8}$";
        Pattern telPattern = Pattern.compile(telRegex);
        Matcher telMatcher = telPattern.matcher(supervisor.getTel());
        if(!telMatcher.matches())
            return HttpResponseEntity.response(false, "create supervisor because the tel is not valid", null);
        if(Calendar.getInstance().get(Calendar.YEAR) - supervisor.getBirthYear() > 150 ||
                Calendar.getInstance().get(Calendar.YEAR) - supervisor.getBirthYear() < 14)
            return HttpResponseEntity.response(false, "create supervisor because the age is not valid", null);
        City city = cityService.getCityByLocation(requestCharacterEntity.getLocation());
        if(city == null)
            return HttpResponseEntity.error("the selected city is not exist");

        if(!userService.query().eq("username", supervisor.getTel()).list().isEmpty())
            return HttpResponseEntity.response(false, "create supervisor because the username is exist", null);
        supervisor.setId(SnowflakeUtil.genId());
        supervisor.setCityId(city.getId());
        user.setId(SnowflakeUtil.genId());
        user.setUsername(supervisor.getTel());
        user.setStatus(1);
        user.setRole("Supervisor");

        boolean supervisorSuccess = supervisorService.save(supervisor);
        boolean userSuccess = userService.save(user);

        return HttpResponseEntity.response(supervisorSuccess&&userSuccess, "create supervisor", supervisor.getId());
    }

    /**
     * modify supervisor
     * @param requestCharacterEntity requestCharacterEntity contains the supervisor and user needed to modify
     * @return http response entity to indicate whether the operation is successful
     */
    @PostMapping("/modifySupervisor")
    public HttpResponseEntity modifySupervisor(@RequestBody RequestCharacterEntity requestCharacterEntity) {
        Supervisor supervisor = requestCharacterEntity.getSupervisor_modify();
        User user = requestCharacterEntity.getUser_modify();
        Supervisor originalSupervisor = supervisorService.getById(supervisor.getId());
        User originalUser = userService.getById(user.getId());
        if(originalSupervisor == null || originalUser == null)
            return HttpResponseEntity.error("the supervisor is not exist");

        String telRegex = "^(?:(?:(?:\\+|00)86)?\\s*)?1(?:(?:3[\\d])|(?:4[5-79])|(?:5[0-35-9])|(?:6[5-7])|(?:7[0-8])|(?:8[\\d])|(?:9[189]))\\d{8}$";
        Pattern telPattern = Pattern.compile(telRegex);
        Matcher telMatcher = telPattern.matcher(supervisor.getTel());
        if(!telMatcher.matches())
            return HttpResponseEntity.response(false, "modify supervisor because the tel is not valid", null);
        if(Calendar.getInstance().get(Calendar.YEAR) - supervisor.getBirthYear() > 150 ||
                Calendar.getInstance().get(Calendar.YEAR) - supervisor.getBirthYear() < 14)
            return HttpResponseEntity.response(false, "modify supervisor because the age is not valid", null);
        City city = cityService.getCityByLocation(requestCharacterEntity.getLocation());
        if(city == null)
            return HttpResponseEntity.error("the selected city is not exist");

        user.setUsername(supervisor.getTel());
        supervisor.setCityId(city.getId());

        boolean supervisorSuccess = supervisorService.updateById(supervisor);
        boolean userSuccess = userService.updateById(user);

        return HttpResponseEntity.response(supervisorSuccess&&userSuccess, "modify supervisor account", null);
    }

    /**
     * delete supervisor
     * @param requestCharacterEntity requestCharacterEntity contains the user needed to delete
     * @return http response entity to indicate whether the operation is successful
     */
    @PostMapping("/deleteSupervisor")
    public HttpResponseEntity deleteSupervisorById(@RequestBody RequestCharacterEntity requestCharacterEntity) {
        User user = requestCharacterEntity.getUser_modify();
        User dbUser = userService.getById(user.getId());
        Supervisor supervisor = supervisorService.getById(user.getId());
        if(null == dbUser)
            return HttpResponseEntity.response(false, "delete supervisor for the deleted supervisor is not exist", null);
        if(!dbUser.getPassword().equals(SHA256Util.encrypt(user.getPassword())))
            return HttpResponseEntity.response(false, "delete supervisor for the password is wrong", null);

        boolean supervisorSuccess = supervisorService.removeById(supervisor);
        boolean userSuccess = userService.removeById(user);

        return HttpResponseEntity.response(supervisorSuccess&&userSuccess, "delete supervisor account ", null);
    }

    /**
     * query supervisor list
     * @param map contains the query using information
     * @key_in_map pageNum page number
     * @key_in_map pageSize page size
     * @key_in_map id supervisor id
     * @key_in_map tel supervisor tel
     * @key_in_map name supervisor name
     * @key_in_map sex supervisor sex
     * @key_in_map cityId the id of the city supervisor belongs to
     * @key_in_map birthYear supervisor birth year
     * @key_in_map status supervisor status whether the supervisor is active or not
     * @return http response entity to indicate whether the operation is successful
     */
    @PostMapping("/querySupervisorList")
    public HttpResponseEntity querySupervisorList(@RequestBody Map<String, Object> map) {
        if((Integer)map.get("pageNum") < 1 || (Integer)map.get("pageSize") < 1)
            return HttpResponseEntity.error("pageNum and pageSize must be greater than 0");
        QueryWrapper<Supervisor> queryWrapper = new QueryWrapper<>();
        if(map.containsKey("id") && map.get("id") != null)
            queryWrapper.like("id", map.get("id"));
        if (map.containsKey("tel") && map.get("tel") != null)
            queryWrapper.like("tel", map.get("tel"));
        if (map.containsKey("name") && map.get("name") != null)
            queryWrapper.like("name", map.get("name"));
        if(map.containsKey("sex") && map.get("sex") != null)
            if(map.get("sex").equals("male"))
                queryWrapper.eq("sex", 0);
            else if (map.get("sex").equals("female"))
                queryWrapper.eq("sex", 1);
        if(map.containsKey("province") && map.get("province") != null) {
            if(map.containsKey("city") && map.get("city") != null){
                City city = cityService.getCityByLocation(Map.of("province", (String)map.get("province"),
                        "city", (String)map.get("city")));
                if(city == null)
                    return HttpResponseEntity.error("the selected city is not exist");
                queryWrapper.eq("city_id", city.getId());
            }
            else {
                List<Integer> citiesList = cityService.getCitiesIdByProvince((String) map.get("province"));
                for(Integer cityId : citiesList)
                    queryWrapper.or().eq("city_id", cityId);
            }
        }

        Page<Supervisor> page = new Page<>((Integer)map.get("pageNum"), (Integer)map.get("pageSize"));
        Page<Supervisor> supervisorPage = supervisorService.page(page, queryWrapper);

        List<Supervisor> supervisorList = supervisorPage.getRecords();
        boolean success = !supervisorList.isEmpty();
        return HttpResponseEntity.response(success, "query", supervisorList);
    }
}
