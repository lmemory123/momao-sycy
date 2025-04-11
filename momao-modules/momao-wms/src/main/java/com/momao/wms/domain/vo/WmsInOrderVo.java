package com.momao.wms.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.momao.common.excel.annotation.ExcelDictFormat;
import com.momao.common.excel.convert.ExcelDictConvert;
import com.momao.wms.domain.WmsInOrder;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;



/**
 * 入库单视图对象 wms_in_order
 *
 * @author Mo mao
 * @date 2025-04-11
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = WmsInOrder.class)
public class WmsInOrderVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 入库单ID
     */
    @ExcelProperty(value = "入库单ID")
    private Long orderId;

    /**
     * 入库单号
     */
    @ExcelProperty(value = "入库单号")
    private String orderNo;

    /**
     * 入库类型（1采购入库 2退货入库 3调拨入库）
     */
    @ExcelProperty(value = "入库类型", converter = ExcelDictConvert.class)
    @ExcelDictFormat(readConverterExp = "1=采购入库,2=退货入库,3=调拨入库")
    private String orderType;

    /**
     * 仓库ID
     */
    @ExcelProperty(value = "仓库ID")
    private Long warehouseId;

    /**
     * 供应商ID
     */
    @ExcelProperty(value = "供应商ID")
    private Long supplierId;

    /**
     * 单据日期
     */
    @ExcelProperty(value = "单据日期")
    private Date orderDate;

    /**
     * 单据状态（0草稿 1待审核 2已审核 3已入库）
     */
    @ExcelProperty(value = "单据状态", converter = ExcelDictConvert.class)
    @ExcelDictFormat(readConverterExp = "0=草稿,1=待审核,2=已审核,3=已入库")
    private String status;

    /**
     * 备注
     */
    @ExcelProperty(value = "备注")
    private String remark;


}
