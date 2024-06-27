package com.system.entity.data;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

/**
 * Intermediary class for city
 * Call entity: grid detector, supervisor, air data
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("city_info")
public class City {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name;
    private String province;
    private String level;
}
