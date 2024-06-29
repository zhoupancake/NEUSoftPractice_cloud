package com.system.api;

import com.system.entity.character.GridDetector;
import com.system.service.GridDetectorService;
import com.system.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * The type grid detector inner micro-service api impl.
 */
@RestController
@RequestMapping("/api/gridDetector")
@Slf4j
@RequiredArgsConstructor
public class GridDetectorAPIImpl implements GridDetectorAPI{
    private final GridDetectorService gridDetectorService;
    private final UserService userService;

    /**
     * use the city id to get the grid detector list
     * @param map from the front end
     * @key_in_map cityId the city needed to be queried
     * @key_in_map pageNum
     * @key_in_map pageSize
     * @return the map contains the of the grid detector in the corresponding city
     */
    @Override
    @PostMapping("/getDetectorSameCity")
    public Map<String, Object> getDetectorSameCity(@RequestBody Map<String, Integer> map) {
        List<GridDetector> gridDetectorList = gridDetectorService.query().eq("city_id", map.get("cityId")).list();
        List<GridDetector> result = new ArrayList<GridDetector>();
        gridDetectorList.removeIf(gridDetector -> userService.getById(gridDetector.getId()).getStatus() == 0);
        int count = (Integer) map.get("pageNum") * (Integer) map.get("pageSize");
        if(count > gridDetectorList.size() + (Integer) map.get("pageSize"))
            return Map.of("count", gridDetectorList.size(),"result", result);
        else
            for(int i = count - (Integer) map.get("pageSize");i < Math.min(count, gridDetectorList.size());i++)
                result.add(gridDetectorList.get(i));
        return Map.of("count", gridDetectorList.size(),"result", result);
    }

    /**
     * get the grid detector list in the province that the city in
     * @param ids the list of the city id
     * @param pageNum page number of paging query
     * @param pageSize page size of paging query
     * @return the map contains the of the grid detector in the corresponding province
     */
    @Override
    @PostMapping("/getDetectorSameProvince")
    public Map<String, Object> getDetectorSameProvince(@RequestBody List<Integer> ids,
                                                      @RequestParam("pageNum") int pageNum,
                                                      @RequestParam("pageSize") int pageSize) {
        List<GridDetector> gridDetectorList = gridDetectorService.query().in("city_id", ids).list();
        List<GridDetector> result = new ArrayList<GridDetector>();
        gridDetectorList.removeIf(gridDetector -> userService.getById(gridDetector.getId()).getStatus() == 0);
        int count = pageNum * pageSize;
        if(count > gridDetectorList.size() + pageSize)
            return Map.of("count", gridDetectorList.size(),"result", result);
        else
            for(int i = count - pageSize;i < Math.min(count, gridDetectorList.size());i++)
                result.add(gridDetectorList.get(i));
        return Map.of("count", gridDetectorList.size(),"result", result);
    }

    /**
     * get the grid detector list in other province
     * @param ids the list of the city id
     * @param pageNum page number of paging query
     * @param pageSize page size of paging query
     * @return the map contains the list of the grid detector in the corresponding province
     */
    @Override
    @PostMapping("/getDetectorOtherProvince")
    public Map<String, Object> getDetectorOtherProvince(@RequestBody List<Integer> ids,
                                                       @RequestParam("pageNum") int pageNum,
                                                       @RequestParam("pageSize") int pageSize) {
        List<GridDetector> gridDetectorList = gridDetectorService.query().notIn("city_id", ids).list();
        List<GridDetector> result = new ArrayList<GridDetector>();
        gridDetectorList.removeIf(gridDetector -> userService.getById(gridDetector.getId()).getStatus() == 0);
        int count = pageNum * pageSize;
        if(count > gridDetectorList.size() + pageSize)
            return Map.of("count", gridDetectorList.size(),"result", result);
        else
            for(int i = count - pageSize;i < Math.min(count, gridDetectorList.size());i++)
                result.add(gridDetectorList.get(i));
        return Map.of("count", gridDetectorList.size(),"result", result);
    }

    /**
     * get the grid detector by id
     * @param id the id of the grid detector
     * @return the grid detector with the corresponding id
     */
    @Override
    @PostMapping("/getGridDetectorById")
    public GridDetector getDetectorById(@RequestBody String id) {
        return gridDetectorService.getById(id);
    }

    /**
     * get the list of the cities that have grid detector
     * @return the list of the cities that have grid detector
     */
    @Override
    @GetMapping("/getDetectorCities")
    public List<Integer> getDetectorCities(){
        List<Integer> cities = gridDetectorService.query().list().stream().map(GridDetector::getCityId).toList();
        return cities.stream().distinct().toList();
    }
}
