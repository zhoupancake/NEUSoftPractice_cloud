package com.system.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.system.common.HttpResponseEntity;
import com.system.dto.RequestCharacterEntity;
import com.system.dto.User;
import com.system.entity.character.Administrator;
import com.system.service.AdministratorService;
import com.system.service.UserService;
import com.system.util.SnowflakeUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/administrator")
@Slf4j
@RequiredArgsConstructor
public class AdministratorController {
    private final AdministratorService administratorService;
    private final UserService userService;

    @PostMapping("/addAdministrator")
    public HttpResponseEntity addAdministrator(@RequestBody RequestCharacterEntity requestCharacterEntity) {
        Administrator administrator = requestCharacterEntity.getAdministrator_create();
        User user = requestCharacterEntity.getUser_create();

        administrator.setId(SnowflakeUtil.genId());
        user.setId(administrator.getId());
        user.setUsername(administrator.getIdCard());
        user.setStatus(1);
        user.setRole("Administrator");

        System.out.println(administrator);
        System.out.println(user);

        boolean administratorSuccess = administratorService.save(administrator);
        boolean userSuccess = userService.save(user);

        return HttpResponseEntity.response(administratorSuccess&&userSuccess, "add", administrator.getId());
    }


    @PostMapping("/modifyAdministrator")
    public HttpResponseEntity modifyAdministrator(@RequestBody RequestCharacterEntity requestCharacterEntity) {
        Administrator administrator = requestCharacterEntity.getAdministrator_modify();
        User user = requestCharacterEntity.getUser_modify();

        Administrator orginalAdministrator = administratorService.getById(administrator.getId());
        User orginalUser = userService.getById(user.getId());

        if(null == orginalAdministrator || null == orginalUser)
            return HttpResponseEntity.error("The modified administrator is not exist");

        user.setUsername(administrator.getIdCard());

        boolean administratorSuccess = administratorService.updateById(administrator);
        boolean userSuccess = userService.updateById(user);
        return HttpResponseEntity.response(administratorSuccess&&userSuccess, "modify", null);
    }

    @PostMapping("/deleteAdministrator")
    public HttpResponseEntity deleteAdministratorById(@RequestBody RequestCharacterEntity requestCharacterEntity) {
        Administrator administrator = requestCharacterEntity.getAdministrator_modify();
        User user = requestCharacterEntity.getUser_modify();
        User dbUser = userService.getById(user.getId());
        if(null == dbUser)
            return HttpResponseEntity.response(false, "The deleted administrator is not exist", null);
        if(!dbUser.getPassword().equals(user.getPassword()))
            return HttpResponseEntity.response(false, "The password is wrong", null);

        boolean administratorSuccess = administratorService.removeById(administrator);
        boolean userSuccess = userService.removeById(user);

        return HttpResponseEntity.response(administratorSuccess&&userSuccess, "delete administrator account", null);
    }

    @PostMapping("/queryAdministratorList")
    public HttpResponseEntity queryAdministratorList(@RequestBody Map<String, Object> map) {
        Integer pageNum = (Integer) map.get("pageNum");
        Integer pageSize = (Integer) map.get("pageSize");
        Page<Administrator> page = new Page<>(pageNum, pageSize);
        administratorService.query()
                .like("username", map.get("username")).page(page);
        List<Administrator> administratorList = page.getRecords();
        boolean success = !administratorList.isEmpty();
        return HttpResponseEntity.response(success, "query", administratorList);
    }
}
