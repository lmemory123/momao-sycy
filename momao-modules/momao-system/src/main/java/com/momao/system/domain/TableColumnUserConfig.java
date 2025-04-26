package com.momao.system.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.io.Serializable;

/**
 * 用户格列属性配置对象 table_column_user_config
 *
 * @author mo mao
 * @date 2025-04-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("table_column_user_config")
public class TableColumnUserConfig implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 表名称
     */
    private String tableName;

    /**
     * 列字段名称
     */
    private String columnName;

    /**
     * 用户自定义列宽度
     */
    private Integer width;

    /**
     * 用户自定义是否显示(0-隐藏,1-显示)
     */
    private Long visible;

    /**
     * 用户自定义排序号
     */
    private Long sequence;


}
