package com.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.system.common.HttpResponseEntity;
import com.system.dto.IP;
import com.system.dto.RequestCharacterEntity;
import com.system.dto.User;
import com.system.entity.character.Administrator;
import com.system.entity.character.GridDetector;
import com.system.entity.data.City;
import com.system.service.*;
import com.system.util.SnowflakeUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/super")
@Slf4j
@RequiredArgsConstructor
public class SuperController {
    private final UserService userService;
    private final CityServiceFeignClient cityService;
    private final AdministratorService administratorService;
    private final GridDetectorService gridDetectorService;
    private final IPService ipService;

    /**
     * add ip to the ip-allow list in database
     * @Request_character super administrator(human resource system)
     * @param map contains the username, password and ipAddress
     * @key_in_map username the username of the super account
     * @key_in_map password the password of the super account
     * @key_in_map ipAddress the ip address to be added
     * @return the result to indicate whether the operation is successful
     */
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
        if(map.containsKey("ipAddress") && map.get("ipAddress") != null && !map.get("ipAddress").isEmpty())
            ipAddress = map.get("ipAddress");
        Pattern pattern = Pattern.compile("^((2((5[0-5])|([0-4]\\d)))|([0-1]?\\d{1,2}))(\\.((2((5[0-5])|([0-4]\\d)))|([0-1]?\\d{1,2}))){3}$");
        Matcher matcher = pattern.matcher(ipAddress);
        if(!matcher.matches())
            return HttpResponseEntity.error("the ipAddress is not correct");
        return ipService.save(new IP(ipAddress)) ? HttpResponseEntity.success("add ip success") : HttpResponseEntity.error("add ip failed");
    }

    /**
     * add ip to the ip-allow list in database
     * @Request_character super administrator(human resource system)
     * @param map contains the username, password and ipAddress
     * @key_in_map username the username of the super account
     * @key_in_map password the password of the super account
     * @key_in_map ipAddress the ip address to be deleted
     * @return the result to indicate whether the operation is successful
     */
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
            return HttpResponseEntity.error("the ipAddress is not exist");
        return ipService.removeById(ipAddress) ? HttpResponseEntity.success("delete ip success") : HttpResponseEntity.error("delete ip failed");
    }

    /**
     * add administrator
     * @Request_character super administrator(human resource system)
     * @param requestCharacterEntity contains administrator and user information needed to create
     * @return http response entity to indicate whether the operation is successful
     */
    @PostMapping("/addAdministrator")
    public HttpResponseEntity addAdministrator(@RequestBody RequestCharacterEntity requestCharacterEntity) {
        Administrator administrator = requestCharacterEntity.getAdministrator_create();
        User user = requestCharacterEntity.getUser_create();

        if(!administratorService.query().eq("id_card", administrator.getIdCard()).list().isEmpty())
            return HttpResponseEntity.error("this person is already exist in the system");
        Pattern pattern = Pattern.compile("^(20[1-9][0-9])([0-9]{6})$");
        Matcher matcher = pattern.matcher(administrator.getIdCard());
        if(!matcher.matches())
            return HttpResponseEntity.error("idCard is not correct");
        administrator.setId(SnowflakeUtil.genId());
        user.setId(administrator.getId());
        user.setUsername(administrator.getIdCard());
        user.setStatus(1);
        user.setRole("Administrator");

        boolean administratorSuccess = administratorService.save(administrator);
        boolean userSuccess = userService.save(user);

        return HttpResponseEntity.response(administratorSuccess&&userSuccess, "add administrator ", administrator.getId());
    }

    /**
     * modify administrator
     * @Request_character super administrator(human resource system)
     * @param requestCharacterEntity contains administrator and user information needed to modify
     * @return the http response entity to indicate whether the operation is successful
     */
    @PostMapping("/modifyAdministrator")
    public HttpResponseEntity modifyAdministrator(@RequestBody RequestCharacterEntity requestCharacterEntity) {
        Administrator administrator = requestCharacterEntity.getAdministrator_modify();
        User user = requestCharacterEntity.getUser_modify();

        Administrator orginalAdministrator = administratorService.getById(administrator.getId());
        User orginalUser = userService.getById(user.getId());
        if(null == orginalAdministrator || null == orginalUser)
            return HttpResponseEntity.error("The modified administrator is not exist");
        if(!orginalUser.getPassword().equals(user.getPassword()))
            return HttpResponseEntity.error("The modification of password is forbidden");
        Pattern pattern = Pattern.compile("^(20[1-9][0-9])([0-9]{6})$");
        Matcher matcher = pattern.matcher(administrator.getIdCard());
        if(!matcher.matches())
            return HttpResponseEntity.error("idCard is not correct");
        user.setUsername(administrator.getIdCard());

        boolean administratorSuccess = administratorService.updateById(administrator);
        boolean userSuccess = userService.updateById(user);
        return HttpResponseEntity.response(administratorSuccess&&userSuccess, "modify administrator ", null);
    }

    /**
     * delete administrator
     * @Request_character super administrator(human resource system)
     * @param requestCharacterEntity contains administrator and user information needed to delete
     * @return the http response entity to indicate whether the operation is successful
     */
    @PostMapping("/deleteAdministrator")
    public HttpResponseEntity deleteAdministratorById(@RequestBody RequestCharacterEntity requestCharacterEntity) {
        Administrator administrator = requestCharacterEntity.getAdministrator_modify();
        User user = requestCharacterEntity.getUser_modify();
        User dbUser = userService.getById(user.getId());
        if(null == dbUser)
            return HttpResponseEntity.error("The deleted administrator is not exist");
        if(!dbUser.getPassword().equals(user.getPassword()))
            return HttpResponseEntity.error("The password is wrong");

        boolean administratorSuccess = administratorService.removeById(administrator);
        boolean userSuccess = userService.removeById(user);

        return HttpResponseEntity.response(administratorSuccess&&userSuccess, "delete administrator account", null);
    }

    /**
     * query administrator list
     * @Request_character super administrator(human resource system)
     * @param map contains query parameters
     * @key_in_map pageNum page number(required)
     * @key_in_map pageSize page size(required)
     * @key_in_map id id of administrator
     * @key_in_map idCard idCard of administrator
     * @key_in_map name name of administrator
     * @return the http response entity contains the required administrator list
     */
    @PostMapping("/queryAdministratorList")
    public HttpResponseEntity queryAdministratorList(@RequestBody Map<String, Object> map) {
        if((Integer)map.get("pageNum") < 1 || (Integer)map.get("pageSize") < 1)
            return HttpResponseEntity.error("pageNum and pageSize must be greater than 0");
        QueryWrapper<Administrator> queryWrapper = new QueryWrapper<>();
        if(map.containsKey("id") && map.get("id") != null)
            queryWrapper.like("id", map.get("id"));
        if (map.containsKey("idCard") && map.get("idCard") != null)
            queryWrapper.like("id_card", map.get("idCard"));
        if (map.containsKey("name") && map.get("name") != null)
            queryWrapper.like("name", map.get("name"));

        Page<Administrator> page = new Page<>((Integer)map.get("pageNum"), (Integer)map.get("pageSize"));
        Page<Administrator> administratorPage = administratorService.page(page, queryWrapper);

        List<Administrator> administratorList = administratorPage.getRecords();
        boolean success = !administratorList.isEmpty();
        return HttpResponseEntity.response(success, "query grid detector", administratorList);
    }

    /**
     * add grid detector
     * @Request_character super administrator(human resource system)
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
     * @Request_character super administrator(human resource system)
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
        if(!orginalUser.getPassword().equals(user.getPassword()))
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
     * @Request_character super administrator(human resource system)
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
        if(!dbUser.getPassword().equals(user.getPassword()))
            return HttpResponseEntity.error("The password is wrong");

        boolean gridDetectorSuccess = gridDetectorService.removeById(gridDetector);
        boolean userSuccess = userService.removeById(user);

        return HttpResponseEntity.response(gridDetectorSuccess&&userSuccess, "delete grid detector account", null);
    }

    /**
     * query grid detector list
     * @Request_character super administrator(human resource system)
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
