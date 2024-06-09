package com.system.dto;

import com.system.entity.data.AirData;
import com.system.entity.data.Report;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestReportEntity {
    private String reportId;
    private String submitterId;
    private Integer status;
    private LocalDateTime createdTime;
    private String description;
    private String imageUrl;
    private String airDataId;
    private String province;
    private String city;
    private String location;
    private LocalDateTime date;
    private Double pm25;
    private Double pm10;
    private Double so2;
    private Double no2;
    private Double co;
    private Double o3;

    public Map<String,String> getLocation(){
        return Map.of("province",province,"city",city);
    }

    public Report getReport_create() {
        return Report.builder()
                .submitterId(submitterId)
                .description(description)
                .imageUrl(imageUrl)
                .build();
    }

    public Report getReport_modify() {
        return Report.builder()
                .id(reportId)
                .submitterId(submitterId)
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
