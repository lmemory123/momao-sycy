package com.momao.system.domain.vo;

import com.momao.system.domain.TableColumn;
import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.momao.common.excel.annotation.ExcelDictFormat;
import com.momao.common.excel.convert.ExcelDictConvert;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;



/**
 * 字段配置视图对象 table_column
 *
 * @author Mo mao
 * @date 2025-04-14
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = TableColumn.class)
public class TableColumnVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @ExcelProperty(value = "主键")
    private Long id;

    /**
     * 表名称
     */
    @ExcelProperty(value = "表名称")
    private String tableName;

    /**
     * 列字段名称
     */
    @ExcelProperty(value = "列字段名称")
    private String columnName;

    /**
     * 列数据类型
     */
    @ExcelProperty(value = "列数据类型")
    private String columnType;

    /**
     * 显示的表头名称
     */
    @ExcelProperty(value = "显示的表头名称")
    private String label;

    /**
     * 列宽度
     */
    @ExcelProperty(value = "列宽度")
    private Integer width;

    /**
     * 最小列宽度
     */
    @ExcelProperty(value = "最小列宽度")
    private Integer minWidth;

    /**
     * 列是否固定(left/right)
     */
    @ExcelProperty(value = "列是否固定(left/right)")
    private String fixed;

    /**
     * 是否可排序(0-不可排序,1-可排序)
     */
    @ExcelProperty(value = "是否可排序(0-不可排序,1-可排序)")
    private Long sortable;

    /**
     * 当内容过长被隐藏时显示tooltip(0-不显示,1-显示)
     */
    @ExcelProperty(value = "当内容过长被隐藏时显示tooltip(0-不显示,1-显示)")
    private Long showOverflow;

    /**
     * 对齐方式(left/center/right)
     */
    @ExcelProperty(value = "对齐方式(left/center/right)")
    private String align;

    /**
     * 是否显示(0-隐藏,1-显示)
     */
    @ExcelProperty(value = "是否显示(0-隐藏,1-显示)")
    private Long visible;

    /**
     * 渲染类型(tag/image/link等)
     */
    @ExcelProperty(value = "渲染类型(tag/image/link等)")
    private String renderType;

    /**
     * 排序号
     */
    @ExcelProperty(value = "排序号")
    private Long sequence;

    /**
     * 备注
     */
    @ExcelProperty(value = "备注")
    private String remark;


    // 用户id
    private Long UserId;


}
