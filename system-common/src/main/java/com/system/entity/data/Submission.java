package com.system.entity.data;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
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
    private String taskId;
    private String submitterId;
    private String description;
    private String relatedAirDataId;
    private String imageUrl;
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime submittedTime;
}
