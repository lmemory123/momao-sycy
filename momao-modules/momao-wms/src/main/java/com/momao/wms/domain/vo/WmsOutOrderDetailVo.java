package com.momao.wms.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.momao.common.excel.annotation.ExcelDictFormat;
import com.momao.common.excel.convert.ExcelDictConvert;
import com.momao.wms.domain.WmsOutOrderDetail;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;


/**
 * 出库单详情视图对象 wms_out_order_detail
 *
 * @author Mo mao
 * @date 2025-04-11
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = WmsOutOrderDetail.class)
public class WmsOutOrderDetailVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 详情ID
     */
    @ExcelProperty(value = "详情ID")
    private Long detailId;

    /**
     * 出库单ID
     */
    @ExcelProperty(value = "出库单ID")
    private Long orderId;

    /**
     * SKU ID
     */
    @ExcelProperty(value = "SKU ID")
    private Long skuId;

    /**
     * 计划数量
     */
    @ExcelProperty(value = "计划数量")
    private Long planQuantity;

    /**
     * 实际数量
     */
    @ExcelProperty(value = "实际数量")
    private Long realQuantity;

    /**
     * 库位ID
     */
    @ExcelProperty(value = "库位ID")
    private Long locationId;

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
     * 状态（0未出库 1已出库）
     */
    @ExcelProperty(value = "状态", converter = ExcelDictConvert.class)
    @ExcelDictFormat(readConverterExp = "0=未出库,1=已出库")
    private String status;

    /**
     * 备注
     */
    @ExcelProperty(value = "备注")
    private String remark;


}
