package com.momao.wms.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.momao.common.excel.annotation.ExcelDictFormat;
import com.momao.common.excel.convert.ExcelDictConvert;
import com.momao.wms.domain.BasSku;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;



/**
 * SKU信息视图对象 bas_sku
 *
 * @author Mo mao
 * @date 2025-04-11
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = BasSku.class)
public class BasSkuVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * SKU ID
     */
    @ExcelProperty(value = "SKU ID")
    private Long skuId;

    /**
     * 分类ID
     */
    @ExcelProperty(value = "分类ID")
    private Long categoryId;

    /**
     * SKU名称
     */
    @ExcelProperty(value = "SKU名称")
    private String skuName;

    /**
     * SKU编码
     */
    @ExcelProperty(value = "SKU编码")
    private String skuCode;

    /**
     * 条形码
     */
    @ExcelProperty(value = "条形码")
    private String barCode;

    /**
     * 基本单位
     */
    @ExcelProperty(value = "基本单位")
    private Long unitId;

    /**
     * 重量(KG)
     */
    @ExcelProperty(value = "重量(KG)")
    private Long weight;

    /**
     * 体积(m³)
     */
    @ExcelProperty(value = "体积(m³)")
    private Long volume;

    /**
     * 保质期(天)
     */
    @ExcelProperty(value = "保质期(天)")
    private Long shelfLife;

    /**
     * 最小库存
     */
    @ExcelProperty(value = "最小库存")
    private Long minStock;

    /**
     * 最大库存
     */
    @ExcelProperty(value = "最大库存")
    private Long maxStock;

    /**
     * 规格
     */
    @ExcelProperty(value = "规格")
    private String specs;

    /**
     * 批次管理（0否 1是）
     */
    @ExcelProperty(value = "批次管理", converter = ExcelDictConvert.class)
    @ExcelDictFormat(readConverterExp = "0=否,1=是")
    private String batchControl;

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

    /**
     * 出库策略：过期后容忍出库的天数
     */
    @ExcelProperty(value = "出库策略：过期后容忍出库的天数")
    private Long outStrategyDays;

    /**
     * 入库策略：生产日期超过多少天不收
     */
    @ExcelProperty(value = "入库策略：生产日期超过多少天不收")
    private Long inStrategyDays;


}
