package com.momao.wms.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.momao.common.excel.annotation.ExcelDictFormat;
import com.momao.common.excel.convert.ExcelDictConvert;
import com.momao.wms.domain.BasCategory;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;



/**
 * SKU分类视图对象 bas_category
 *
 * @author Mo mao
 * @date 2025-04-11
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = BasCategory.class)
public class BasCategoryVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 分类ID
     */
    @ExcelProperty(value = "分类ID")
    private Long categoryId;

    /**
     * 父分类ID
     */
    @ExcelProperty(value = "父分类ID")
    private Long parentId;

    /**
     * 祖级列表
     */
    @ExcelProperty(value = "祖级列表")
    private String ancestors;

    /**
     * 分类名称
     */
    @ExcelProperty(value = "分类名称")
    private String categoryName;

    /**
     * 分类编码
     */
    @ExcelProperty(value = "分类编码")
    private String categoryCode;

    /**
     * 显示顺序
     */
    @ExcelProperty(value = "显示顺序")
    private Long orderNum;

    /**
     * 层级
     */
    @ExcelProperty(value = "层级")
    private Long level;

    /**
     * 状态（0正常 1停用）
     */
    @ExcelProperty(value = "状态", converter = ExcelDictConvert.class)
    @ExcelDictFormat(readConverterExp = "0=正常,1=停用")
    private String status;

    /**
     * 备注
     */
    @ExcelProperty(value = "备注")
    private String remark;


}
