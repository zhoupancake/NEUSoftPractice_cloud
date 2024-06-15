package com.system.api;

import com.system.entity.character.GridDetector;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

public interface GridDetectorAPI {
    @PostMapping("/getDetectorSameCity")
    List<GridDetector> getDetectorSameCity(@RequestBody Map<String, Integer> map);
    @PostMapping("/getDetectorSameProvince")
    List<GridDetector> getDetectorSameProvince(List<Integer> ids, int pageNum, int pageSize);

    @PostMapping("/getDetectorOtherProvince")
    List<GridDetector> getDetectorOtherProvince(List<Integer> ids, int pageNum, int pageSize);
}
