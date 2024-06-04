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
@TableName("task_info")
public class Task {
    @TableId
    private String id;
    private String appointerId;
    private String appointeeId;
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createdTime;
    private Integer status;
    private String relativeAirDataId;
    private String relativeReportId;
}
