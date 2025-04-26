package com.momao.system.domain.bo;

import com.momao.common.core.validate.AddGroup;
import com.momao.common.core.validate.EditGroup;
import com.momao.system.domain.TableColumn;
import com.momao.common.mybatis.core.domain.BaseEntity;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.validation.constraints.*;

/**
 * 字段配置业务对象 table_column
 *
 * @author Mo mao
 * @date 2025-04-14
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = TableColumn.class, reverseConvertGenerate = false)
public class TableColumnBo extends BaseEntity {

    /**
     * 主键
     */
    @NotNull(message = "主键不能为空", groups = { EditGroup.class })
    private Long id;

    /**
     * 表名称
     */
    @NotBlank(message = "表名称不能为空", groups = { AddGroup.class, EditGroup.class })
    private String tableName;

    /**
     * 列字段名称
     */
    @NotBlank(message = "列字段名称不能为空", groups = { AddGroup.class, EditGroup.class })
    private String columnName;

    /**
     * 列数据类型
     */
    @NotBlank(message = "列数据类型不能为空", groups = { AddGroup.class, EditGroup.class })
    private String columnType;

    /**
     * 显示的表头名称
     */
    @NotBlank(message = "显示的表头名称不能为空", groups = { AddGroup.class, EditGroup.class })
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
