package com.momao.system.domain;

import com.baomidou.mybatisplus.annotation.*;
import com.momao.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.io.Serializable;

/**
 * 字段配置对象 table_column
 *
 * @author Mo mao
 * @date 2025-04-14
 */
@Data
@TableName("table_column")
public class TableColumn implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    /**
     * 表名称
     */
    private String tableName;

    /**
     * 列字段名称
     */
    private String columnName;

    /**
     * 列数据类型
     */
    private String columnType;

    /**
     * 显示的表头名称
     */
    private String label;

    /**
     * 列宽度
     */
    private Integer width;

    /**
     * 最小列宽度
     */
    private Integer minWidth;

    /**
     * 列是否固定(left/right)
     */
    private String fixed;

    /**
     * 是否可排序(0-不可排序,1-可排序)
     */
    private Long sortable;

    /**
     * 当内容过长被隐藏时显示tooltip(0-不显示,1-显示)
     */
    private Long showOverflow;

    /**
     * 对齐方式(left/center/right)
     */
    private String align;

    /**
     * 是否显示(0-隐藏,1-显示)
     */
    private Long visible;

    /**
     * 渲染类型(tag/image/link等)
     */
    private String renderType;

    /**
     * 排序号
     */
    private Long sequence;

    /**
     * 备注
     */
    private String remark;


}
