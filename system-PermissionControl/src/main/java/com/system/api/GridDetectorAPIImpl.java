package com.system.api;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.system.entity.character.GridDetector;
import com.system.service.GridDetectorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@Slf4j
@RequiredArgsConstructor
public class GridDetectorAPIImpl implements GridDetectorAPI{
    private final GridDetectorService gridDetectorService;
    @Override
    @PostMapping("/getDetectorSameCity")
    public List<GridDetector> getDetectorSameCity(Integer id, int pageNum, int pageSize) {
        Page<GridDetector> page = new Page<>(pageNum,pageSize);
        QueryWrapper<GridDetector> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("city_id", id);
        queryWrapper.eq("status", 1);
        return gridDetectorService.page(page,queryWrapper).getRecords();
    }

    @Override
    @PostMapping("/getDetectorSameProvince")
    public List<GridDetector> getDetectorSameProvince(List<Integer> ids, int pageNum, int pageSize) {
        Page<GridDetector> page = new Page<>(pageNum,pageSize);
        QueryWrapper<GridDetector> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("city_id", ids);
        queryWrapper.eq("status", 1);
        return gridDetectorService.page(page,queryWrapper).getRecords();
    }

    @Override
    @PostMapping("/getDetectorOtherProvince")
    public List<GridDetector> getDetectorOtherProvince(List<Integer> ids, int pageNum, int pageSize) {
        Page<GridDetector> page = new Page<>(pageNum,pageSize);
        QueryWrapper<GridDetector> queryWrapper = new QueryWrapper<>();
        queryWrapper.notIn("city_id", ids);
        queryWrapper.eq("status", 1);
        return gridDetectorService.page(page,queryWrapper).getRecords();
    }
}
