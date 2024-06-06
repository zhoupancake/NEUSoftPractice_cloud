package com.system.dto;

import com.system.entity.data.AirData;
import com.system.entity.data.Submission;

import java.time.LocalDateTime;

public class RequestSubmissionEntity {
    private String id;
    private String taskId;
    private String description;
    private String relatedAirDataId;
    private String imageUrl;
    private LocalDateTime submittedTime;
    private String location;
    private String airDataId;
    private LocalDateTime date;
    private Double pm25;
    private Double pm10;
    private Double so2;
    private Double no2;
    private Double co;
    private Double o3;

    public Submission getSubmission_create() {
        return Submission.builder()
                .taskId(taskId)
                .description(description)
                .relatedAirDataId(relatedAirDataId)
                .imageUrl(imageUrl)
                .build();
    }

    public Submission getSubmission_modify() {
        return Submission.builder()
                .id(id)
                .taskId(taskId)
                .description(description)
                .relatedAirDataId(relatedAirDataId)
                .imageUrl(imageUrl)
                .submittedTime(submittedTime)
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
