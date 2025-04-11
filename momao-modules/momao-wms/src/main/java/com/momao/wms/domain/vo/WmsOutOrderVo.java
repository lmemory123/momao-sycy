package com.momao.wms.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.momao.common.excel.annotation.ExcelDictFormat;
import com.momao.common.excel.convert.ExcelDictConvert;
import com.momao.wms.domain.WmsOutOrder;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;



/**
 * 出库单视图对象 wms_out_order
 *
 * @author Mo mao
 * @date 2025-04-11
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = WmsOutOrder.class)
public class WmsOutOrderVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 出库单ID
     */
    @ExcelProperty(value = "出库单ID")
    private Long orderId;

    /**
     * 出库单号
     */
    @ExcelProperty(value = "出库单号")
    private String orderNo;

    /**
     * 出库类型（1销售出库 2退货出库 3调拨出库）
     */
    @ExcelProperty(value = "出库类型", converter = ExcelDictConvert.class)
    @ExcelDictFormat(readConverterExp = "1=销售出库,2=退货出库,3=调拨出库")
    private String orderType;

    /**
     * 仓库ID
     */
    @ExcelProperty(value = "仓库ID")
    private Long warehouseId;

    /**
     * 客户ID
     */
    @ExcelProperty(value = "客户ID")
    private Long customerId;

    /**
     * 单据日期
     */
    @ExcelProperty(value = "单据日期")
    private Date orderDate;

    /**
     * 单据状态（0草稿 1待审核 2已审核 3已出库）
     */
    @ExcelProperty(value = "单据状态", converter = ExcelDictConvert.class)
    @ExcelDictFormat(readConverterExp = "0=草稿,1=待审核,2=已审核,3=已出库")
    private String status;

    /**
     * 备注
     */
    @ExcelProperty(value = "备注")
    private String remark;


}
