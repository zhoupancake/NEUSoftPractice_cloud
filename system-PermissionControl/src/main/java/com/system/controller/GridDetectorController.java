package com.system.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.system.common.HttpResponseEntity;
import com.system.dto.RequestCharacterEntity;
import com.system.dto.User;
import com.system.entity.character.GridDetector;
import com.system.service.GridDetectorService;
import com.system.service.UserService;
import com.system.util.SnowflakeUtil;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/gridDetector")
@Slf4j
@RequiredArgsConstructor
public class GridDetectorController {
    private final GridDetectorService gridDetectorService;
    private final UserService userService;

    @PostMapping("/addGridDetector")
    public HttpResponseEntity addGridDetector(@RequestBody RequestCharacterEntity requestCharacterEntity) {
        GridDetector gridDetector = requestCharacterEntity.getGridDetector_create();
        User user = requestCharacterEntity.getUser_create();

        gridDetector.setId(SnowflakeUtil.genId());
        user.setId(gridDetector.getId());
        user.setUsername(gridDetector.getIdCard());
        user.setStatus(1);
        user.setRole("GridDetector");

        boolean gridDetectorSuccess = gridDetectorService.save(gridDetector);
        boolean userSuccess = userService.save(user);

        return HttpResponseEntity.response(gridDetectorSuccess&&userSuccess, "创建", null);
    }

    @PostMapping("/modifyGridDetector")
    public HttpResponseEntity modifyGridDetector(@RequestBody RequestCharacterEntity requestCharacterEntity) {
        GridDetector gridDetector = requestCharacterEntity.getGridDetector_modify();
        User user = requestCharacterEntity.getUser_modify();

        user.setUsername(gridDetector.getIdCard());

        boolean gridDetectorSuccess = gridDetectorService.updateById(gridDetector);
        boolean userSuccess = userService.updateById(user);

        return HttpResponseEntity.response(gridDetectorSuccess&&userSuccess, "修改", null);
    }

    @PostMapping("/deleteGridDetector")
    public HttpResponseEntity deleteGridDetectorById(@RequestBody RequestCharacterEntity requestCharacterEntity) {
        GridDetector gridDetector = requestCharacterEntity.getGridDetector_modify();
        User user = requestCharacterEntity.getUser_modify();

        boolean gridDetectorSuccess = gridDetectorService.removeById(gridDetector);
        boolean userSuccess = userService.removeById(user);

        return HttpResponseEntity.response(gridDetectorSuccess&&userSuccess, "删除", null);
    }

    @PostMapping("/queryGridDetectorList")
    public HttpResponseEntity queryGridDetectorList(@RequestBody Map<String, Object> map) {
        Integer pageNum = (Integer) map.get("pageNum");
        Integer pageSize = (Integer) map.get("pageSize");
        Page<GridDetector> page = new Page<>(pageNum, pageSize);
        gridDetectorService.query().eq("status", "1")
                .like("username", map.get("username")).page(page);
        List<GridDetector> gridDetectorList = page.getRecords();
        boolean success = !gridDetectorList.isEmpty();
        return HttpResponseEntity.response(success, "查询", gridDetectorList);
    }

    @PostMapping("/logout")
    public HttpResponseEntity logout(HttpServletResponse response) {
        return HttpResponseEntity.response(true, "登出", null);
    }
}
