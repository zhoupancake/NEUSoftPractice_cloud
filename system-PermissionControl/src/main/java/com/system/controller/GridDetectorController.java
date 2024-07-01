package com.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.system.common.HttpResponseEntity;
import com.system.dto.RequestCharacterEntity;
import com.system.dto.User;
import com.system.entity.character.Administrator;
import com.system.entity.character.GridDetector;
import com.system.entity.data.City;
import com.system.service.CityServiceFeignClient;
import com.system.service.GridDetectorService;
import com.system.service.UserService;
import com.system.util.SHA256Util;
import com.system.util.SnowflakeUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * grid detector controller for testing the CRUD operations
 * can be called using super admin account to call
 */
@RestController
@RequestMapping("/hide/gridDetector")
@Slf4j
@RequiredArgsConstructor
public class GridDetectorController {
    private final GridDetectorService gridDetectorService;
    private final UserService userService;
    private final CityServiceFeignClient cityService;

    /**
     * add grid detector
     * @param requestCharacterEntity contains grid detector and user information needed to creat
     * @return http response entity to indicate whether the operation is successful
     */
    @PostMapping("/addGridDetector")
    public HttpResponseEntity addGridDetector(@RequestBody RequestCharacterEntity requestCharacterEntity) {
        GridDetector gridDetector = requestCharacterEntity.getGridDetector_create();
        User user = requestCharacterEntity.getUser_create();

        if(!gridDetectorService.query().eq("id_card", gridDetector.getIdCard()).list().isEmpty())
            return HttpResponseEntity.error("this person is already exist in the system");
        Pattern pattern = Pattern.compile("^(20[1-9][0-9])([0-9]{6})$");
        Matcher matcher = pattern.matcher(gridDetector.getIdCard());
        if(!matcher.matches())
            return HttpResponseEntity.error("idCard is not correct");

        gridDetector.setId(SnowflakeUtil.genId());
        City city = cityService.getCityByLocation(requestCharacterEntity.getLocation());
        if(city == null)
            return HttpResponseEntity.error("the selected city is not exist");
        gridDetector.setCityId(city.getId());
        user.setId(gridDetector.getId());
        user.setUsername(gridDetector.getIdCard());
        user.setStatus(1);
        user.setRole("GridDetector");

        boolean gridDetectorSuccess = gridDetectorService.save(gridDetector);
        boolean userSuccess = userService.save(user);

        return HttpResponseEntity.response(gridDetectorSuccess&&userSuccess, "create grid detector", gridDetector.getId());
    }

    /**
     * modify grid detector
     * @param requestCharacterEntity contains grid detector and user information needed to modify
     * @return http response entity to indicate whether the operation is successful
     */
    @PostMapping("/modifyGridDetector")
    public HttpResponseEntity modifyGridDetector(@RequestBody RequestCharacterEntity requestCharacterEntity) {
        GridDetector gridDetector = requestCharacterEntity.getGridDetector_modify();
        User user = requestCharacterEntity.getUser_modify();
        City city = cityService.getCityByLocation(requestCharacterEntity.getLocation());
        if(city == null)
            return HttpResponseEntity.error("the selected city is not exist");
        GridDetector orginalGridDetector = gridDetectorService.getById(gridDetector.getId());
        User orginalUser = userService.getById(user.getId());

        if(null == orginalGridDetector || null == orginalUser)
            return HttpResponseEntity.error("The modified gridDetector is not exist");
        if(!orginalUser.getPassword().equals(SHA256Util.encrypt(user.getPassword())))
            return HttpResponseEntity.error("The modification of password is forbidden");
        Pattern pattern = Pattern.compile("^(20[1-9][0-9])([0-9]{6})$");
        Matcher matcher = pattern.matcher(gridDetector.getIdCard());
        if(!matcher.matches())
            return HttpResponseEntity.error("idCard is not correct");

        user.setUsername(gridDetector.getIdCard());
        gridDetector.setCityId(city.getId());

        boolean gridDetectorSuccess = gridDetectorService.updateById(gridDetector);
        boolean userSuccess = userService.updateById(user);
        return HttpResponseEntity.response(gridDetectorSuccess&&userSuccess, "modify grid detector", null);
    }

    /**
     * delete grid detector
     * @param requestCharacterEntity contains grid detector and user information needed to delete
     * @return http response entity to indicate whether the operation is successful
     */
    @PostMapping("/deleteGridDetector")
    public HttpResponseEntity deleteGridDetectorById(@RequestBody RequestCharacterEntity requestCharacterEntity) {
        GridDetector gridDetector = requestCharacterEntity.getGridDetector_modify();
        User user = requestCharacterEntity.getUser_modify();
        User dbUser = userService.getById(user.getId());
        if(null == dbUser)
            return HttpResponseEntity.error("The deleted grid detector is not exist");
        if(!dbUser.getPassword().equals(SHA256Util.encrypt(user.getPassword())))
            return HttpResponseEntity.error("The password is wrong");

        boolean gridDetectorSuccess = gridDetectorService.removeById(gridDetector);
        boolean userSuccess = userService.removeById(user);

        return HttpResponseEntity.response(gridDetectorSuccess&&userSuccess, "delete grid detector account", null);
    }

    /**
     * query grid detector list
     * @param map contains query information
     * @key_in_map pageNum page number
     * @key_in_map pageSize page size
     * @key_in_map id grid detector id
     * @key_in_map idCard grid detector id card
     * @key_in_map name grid detector name
     * @return http response entity contains the grid detector list as the request
     */
    @PostMapping("/queryGridDetectorList")
    public HttpResponseEntity queryGridDetectorList(@RequestBody Map<String, Object> map) {
        if((Integer)map.get("pageNum") < 1 || (Integer)map.get("pageSize") < 1)
            return HttpResponseEntity.error("pageNum and pageSize must be greater than 0");
        QueryWrapper<GridDetector> queryWrapper = new QueryWrapper<>();
        if(map.containsKey("id") && map.get("id") != null)
            queryWrapper.like("id", map.get("id"));
        if (map.containsKey("idCard") && map.get("idCard") != null)
            queryWrapper.like("id_card", map.get("idCard"));
        if (map.containsKey("name") && map.get("name") != null)
            queryWrapper.like("name", map.get("name"));

        Page<GridDetector> page = new Page<>((Integer)map.get("pageNum"), (Integer)map.get("pageSize"));
        Page<GridDetector> gridDetectorPage = gridDetectorService.page(page, queryWrapper);

        List<GridDetector> gridDetectorList = gridDetectorPage.getRecords();
        boolean success = !gridDetectorList.isEmpty();
        return HttpResponseEntity.response(success, "query", gridDetectorList);
    }
}
