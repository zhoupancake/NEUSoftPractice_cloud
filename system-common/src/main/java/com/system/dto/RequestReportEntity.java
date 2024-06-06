package com.system.dto;

import com.system.entity.data.AirData;
import com.system.entity.data.Report;

import java.time.LocalDateTime;

public class RequestReportEntity {
    private String reportId;
    private Integer status;
    private LocalDateTime createdTime;
    private String description;
    private String imageUrl;
    private String airDataId;
    private String location;
    private LocalDateTime date;
    private Double pm25;
    private Double pm10;
    private Double so2;
    private Double no2;
    private Double co;
    private Double o3;

    public Report getReport_create() {
        return Report.builder()
                .description(description)
                .imageUrl(imageUrl)
                .build();
    }

    public Report getReport_modify() {
        return Report.builder()
                .id(reportId)
                .status(status)
                .createdTime(createdTime)
                .description(description)
                .imageUrl(imageUrl)
                .relativeAirDataId(airDataId)
                .build();
    }
    
    public AirData getAirData_create() {
        return AirData.builder()
                .location(location)
                .date(date)
                .pm25(pm25)
                .pm10(pm10)
                .so2(so2)
                .no2(no2)
                .co(co)
                .o3(o3)
                .build();
    }
    
    public AirData getAirData_modify() {
        return AirData.builder()
                .id(airDataId)
                .location(location)
                .date(date)
                .pm25(pm25)
                .pm10(pm10)
                .so2(so2)
                .no2(no2)
                .co(co)
                .o3(o3)
                .build();
    }
}
