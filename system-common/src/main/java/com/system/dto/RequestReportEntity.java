package com.system.dto;

import com.system.entity.data.AirData;
import com.system.entity.data.Report;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestReportEntity implements Serializable {
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
    private Integer forecastAQILevel;

    public Map<String,String> getLocation(){
        return Map.of("province",province,"city",city);
    }

    public Report getReport_create() {
        return Report.builder()
                .location(location)
                .submitterId(submitterId)
                .description(description)
                .imageUrl(imageUrl)
                .forecastAqiLevel(forecastAQILevel)
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
                .forecastAqiLevel(forecastAQILevel)
                .build();
    }
}
