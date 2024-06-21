package com.system.api;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.system.common.HttpResponseEntity;
import com.system.dto.User;
import com.system.entity.character.GridDetector;
import com.system.entity.data.City;
import com.system.service.GridDetectorService;
import com.system.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/gridDetector")
@Slf4j
@RequiredArgsConstructor
public class GridDetectorAPIImpl implements GridDetectorAPI{
    private final GridDetectorService gridDetectorService;
    private final UserService userService;
    @Override
    @PostMapping("/getDetectorSameCity")
    public List<GridDetector> getDetectorSameCity(@RequestBody Map<String, Integer> map) {
//        Page<GridDetector> page = new Page<>(map.get("pageNum"), map.get("pageSize"));
//        QueryWrapper<GridDetector> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("city_id", map.get("id"));
//        queryWrapper.eq("status", 1);
//        return gridDetectorService.page(page,queryWrapper).getRecords();
        List<GridDetector> gridDetectorList = gridDetectorService.query().eq("city_id", map.get("cityId")).list();
        List<GridDetector> result = new ArrayList<GridDetector>();
        gridDetectorList.removeIf(gridDetector -> userService.getById(gridDetector.getId()).getStatus() == 0);
        int count = (Integer) map.get("pageNum") * (Integer) map.get("pageSize");
        if(count > gridDetectorList.size() + (Integer) map.get("pageSize"))
            return result;
        else
            for(int i = count - (Integer) map.get("pageSize");i < Math.min(count, gridDetectorList.size());i++)
                result.add(gridDetectorList.get(i));
        return result;
    }

    @Override
    @PostMapping("/getDetectorSameProvince")
    public List<GridDetector> getDetectorSameProvince(@RequestBody List<Integer> ids,
                                                      @RequestParam("pageNum") int pageNum,
                                                      @RequestParam("pageSize") int pageSize) {
//        Page<GridDetector> page = new Page<>(pageNum,pageSize);
//        QueryWrapper<GridDetector> queryWrapper = new QueryWrapper<>();
//        queryWrapper.in("city_id", ids);
//        queryWrapper.eq("status", 1);
//        return gridDetectorService.page(page,queryWrapper).getRecords();
        List<GridDetector> gridDetectorList = gridDetectorService.query().in("city_id", ids).list();
        List<GridDetector> result = new ArrayList<GridDetector>();
        gridDetectorList.removeIf(gridDetector -> userService.getById(gridDetector.getId()).getStatus() == 0);
        int count = pageNum * pageSize;
        if(count > gridDetectorList.size() + pageSize)
            return result;
        else
            for(int i = count - pageSize;i < Math.min(count, gridDetectorList.size());i++)
                result.add(gridDetectorList.get(i));
        return result;
    }

    @Override
    @PostMapping("/getDetectorOtherProvince")
    public List<GridDetector> getDetectorOtherProvince(@RequestBody List<Integer> ids,
                                                       @RequestParam("pageNum") int pageNum,
                                                       @RequestParam("pageSize") int pageSize) {
        System.out.println(ids);
//        Page<GridDetector> page = new Page<>(pageNum,pageSize);
//        QueryWrapper<GridDetector> queryWrapper = new QueryWrapper<>();
//        queryWrapper.notIn("city_id", ids);
//        queryWrapper.eq("status", 1);
//        return gridDetectorService.page(page,queryWrapper).getRecords();
        List<GridDetector> gridDetectorList = gridDetectorService.query().notIn("city_id", ids).list();
        List<GridDetector> result = new ArrayList<GridDetector>();
        gridDetectorList.removeIf(gridDetector -> userService.getById(gridDetector.getId()).getStatus() == 0);
        int count = pageNum * pageSize;
        if(count > gridDetectorList.size() + pageSize)
            return result;
        else
            for(int i = count - pageSize;i < Math.min(count, gridDetectorList.size());i++)
                result.add(gridDetectorList.get(i));
        return result;
    }

    @Override
    @PostMapping("/getGridDetectorById")
    public GridDetector getDetectorById(@RequestBody String id) {
        return gridDetectorService.getById(id);
    }

    @Override
    @GetMapping("/getDetectorCities")
    public List<Integer> getDetectorCities(){
        List<Integer> cities = gridDetectorService.query().list().stream().map(GridDetector::getCityId).toList();
        return cities.stream().distinct().toList();
    }
}
