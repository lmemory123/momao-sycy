package com.momao.wms.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.momao.common.excel.annotation.ExcelDictFormat;
import com.momao.common.excel.convert.ExcelDictConvert;
import com.momao.wms.domain.WmsCheckDetail;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;



/**
 * 盘点单详情视图对象 wms_check_detail
 *
 * @author Mo mao
 * @date 2025-04-11
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = WmsCheckDetail.class)
public class WmsCheckDetailVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 详情ID
     */
    @ExcelProperty(value = "详情ID")
    private Long detailId;

    /**
     * 盘点单ID
     */
    @ExcelProperty(value = "盘点单ID")
    private Long orderId;

    /**
     * SKU ID
     */
    @ExcelProperty(value = "SKU ID")
    private Long skuId;

    /**
     * 库位ID
     */
    @ExcelProperty(value = "库位ID")
    private Long locationId;

    /**
     * 账面数量
     */
    @ExcelProperty(value = "账面数量")
    private Long stockQuantity;

    /**
     * 盘点数量
     */
    @ExcelProperty(value = "盘点数量")
    private Long checkQuantity;

    /**
     * 差异数量
     */
    @ExcelProperty(value = "差异数量")
    private Long diffQuantity;

    /**
     * 批次号
     */
    @ExcelProperty(value = "批次号")
    private String batchNo;

    /**
     * 生产日期
     */
    @ExcelProperty(value = "生产日期")
    private Date produceDate;

    /**
     * 有效期
     */
    @ExcelProperty(value = "有效期")
    private Date expireDate;

    /**
     * 供应商ID
     */
    @ExcelProperty(value = "供应商ID")
    private Long supplierId;

    /**
     * 状态（0未盘点 1已盘点）
     */
    @ExcelProperty(value = "状态", converter = ExcelDictConvert.class)
    @ExcelDictFormat(readConverterExp = "0=未盘点,1=已盘点")
    private String status;

    /**
     * 备注
     */
    @ExcelProperty(value = "备注")
    private String remark;


}
