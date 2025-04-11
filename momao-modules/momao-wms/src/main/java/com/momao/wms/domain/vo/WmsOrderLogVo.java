package com.momao.wms.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.momao.common.excel.annotation.ExcelDictFormat;
import com.momao.common.excel.convert.ExcelDictConvert;
import com.momao.wms.domain.WmsOrderLog;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;



/**
 * 单据操作日志视图对象 wms_order_log
 *
 * @author Mo mao
 * @date 2025-04-11
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = WmsOrderLog.class)
public class WmsOrderLogVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 日志ID
     */
    @ExcelProperty(value = "日志ID")
    private Long logId;

    /**
     * 单据ID
     */
    @ExcelProperty(value = "单据ID")
    private Long orderId;

    /**
     * 单据类型（1入库单 2出库单 3盘点单 4调拨单）
     */
    @ExcelProperty(value = "单据类型", converter = ExcelDictConvert.class)
    @ExcelDictFormat(readConverterExp = "1=入库单,2=出库单,3=盘点单,4=调拨单")
    private String orderType;

    /**
     * 单据编号
     */
    @ExcelProperty(value = "单据编号")
    private String orderNo;

    /**
     * 操作类型（1创建 2修改 3审核 4撤销 5删除 6确认）
     */
    @ExcelProperty(value = "操作类型", converter = ExcelDictConvert.class)
    @ExcelDictFormat(readConverterExp = "1=创建,2=修改,3=审核,4=撤销,5=删除,6=确认")
    private String operateType;

    /**
     * 操作前状态
     */
    @ExcelProperty(value = "操作前状态")
    private String statusBefore;

    /**
     * 操作后状态
     */
    @ExcelProperty(value = "操作后状态")
    private String statusAfter;

    /**
     * 操作内容
     */
    @ExcelProperty(value = "操作内容")
    private String operateContent;

    /**
     * 操作人名称
     */
    @ExcelProperty(value = "操作人名称")
    private String createName;

    /**
     * 备注
     */
    @ExcelProperty(value = "备注")
    private String remark;


}
