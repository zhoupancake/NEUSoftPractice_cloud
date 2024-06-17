package com.system.service;

import com.system.entity.character.GridDetector;
import com.system.entity.character.Supervisor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@FeignClient(name="system-PermissionControl", url="localhost:8081") //  远程服务的名称
public interface CharacterServiceFeignClient {
    @PostMapping("/api/gridDetector/getDetectorSameCity")
    public List<GridDetector> getDetectorSameCity(Map<String, Integer> map);

    @PostMapping("/api/gridDetector/getDetectorSameProvince")
    public List<GridDetector> getDetectorSameProvince(@RequestBody List<Integer> ids,
                                                      @RequestParam("pageNum") int pageNum,
                                                      @RequestParam("pageSize") int pageSize);

    @PostMapping("/api/gridDetector/getDetectorOtherProvince")
    public List<GridDetector> getDetectorOtherProvince(@RequestBody List<Integer> ids,
                                                       @RequestParam("pageNum") int pageNum,
                                                       @RequestParam("pageSize") int pageSize);

    @PostMapping("/api/supervisor/getSupervisorById")
    public Supervisor getSupervisorById(@RequestBody String id);

    @PostMapping("/api/gridDetector/getGridDetectorById")
    public GridDetector getDetectorById(@RequestBody String id);

    @PostMapping("/api/administrator/getAdministratorById")
    public Supervisor getAdministratorById(@RequestBody String id);
}
