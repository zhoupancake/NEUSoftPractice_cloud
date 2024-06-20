package com.system.api;

import com.system.entity.character.GridDetector;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

public interface GridDetectorAPI {
    @PostMapping("/getDetectorSameCity")
    List<GridDetector> getDetectorSameCity(@RequestBody Map<String, Integer> map);
    @PostMapping("/getDetectorSameProvince")
    public List<GridDetector> getDetectorSameProvince(@RequestBody List<Integer> ids,
                                                      @RequestParam("pageNum") int pageNum,
                                                      @RequestParam("pageSize") int pageSize);

    @PostMapping("/getDetectorOtherProvince")
    public List<GridDetector> getDetectorOtherProvince(@RequestBody List<Integer> ids,
                                                       @RequestParam("pageNum") int pageNum,
                                                       @RequestParam("pageSize") int pageSize);

    @PostMapping("/getDetectorById")
    public GridDetector getDetectorById(@RequestBody String id);

    @PostMapping("/getDetectorCities")
    public List<Integer> getDetectorCities();
}
