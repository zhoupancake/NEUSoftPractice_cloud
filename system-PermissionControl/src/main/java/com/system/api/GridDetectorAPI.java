package com.system.api;

import com.system.entity.character.GridDetector;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

public interface GridDetectorAPI {
    @PostMapping("/getDetectorSameCity")
    List<GridDetector> getDetectorSameCity(Integer id, int pageNum, int pageSize);
    @PostMapping("/getDetectorSameProvince")
    List<GridDetector> getDetectorSameProvince(List<Integer> ids, int pageNum, int pageSize);

    @PostMapping("/getDetectorOtherProvince")
    List<GridDetector> getDetectorOtherProvince(List<Integer> ids, int pageNum, int pageSize);
}
