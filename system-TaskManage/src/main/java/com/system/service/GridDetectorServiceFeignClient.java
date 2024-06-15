package com.system.service;

import com.system.entity.character.GridDetector;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Map;

@FeignClient(name="system-PermissionControl", url="localhost:8081") //  远程服务的名称
public interface GridDetectorServiceFeignClient {
    @PostMapping("/api/getDetectorSameCity")
    public List<GridDetector> getDetectorSameCity(Map<String, Integer> map);

    @PostMapping("/api/getDetectorSameProvince")
    List<GridDetector> getDetectorSameProvince(List<Integer> ids, int pageNum, int pageSize);

    @PostMapping("/api/getDetectorOtherProvince")
    List<GridDetector> getDetectorOtherProvince(List<Integer> ids, int pageNum, int pageSize);
}
