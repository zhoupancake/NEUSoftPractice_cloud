package com.system.entity.data;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("airData_info")
public class AirData implements Serializable {
    //identify attributes
    @TableId
    private String id;
    private String location;
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime date;

    //data attributes
    private Double pm25;
    private Double pm10;
    private Double so2;
    private Double no2;
    private Double co;
    private Double o3;
}
