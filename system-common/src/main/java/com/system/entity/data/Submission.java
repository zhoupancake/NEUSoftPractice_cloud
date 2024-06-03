package com.system.entity.data;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("submission_info")
public class Submission {
    @TableId
    private String id;
    private String request;
    private String description;
    private String relatedAirData;
    private String imageUrl;
    private LocalDateTime submittedTime;
    private String location;
}
