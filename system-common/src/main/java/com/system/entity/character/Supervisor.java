package com.system.entity.character;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * The supervisor of the system
 * Major function: report the forecasted air quality
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("supervisor_info")
public class Supervisor implements Serializable {
    //Account Attributes
    @TableId
    private String id;

    private String tel;
    private String name;
    private Integer birthYear;
    private Integer sex;
    private Integer cityId;
}
