package com.momao.wms.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.momao.common.excel.annotation.ExcelDictFormat;
import com.momao.common.excel.convert.ExcelDictConvert;
import com.momao.wms.domain.WmsStockBatchLog;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;



/**
 * 批次库存操作日志视图对象 wms_stock_batch_log
 *
 * @author Mo mao
 * @date 2025-04-11
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = WmsStockBatchLog.class)
public class WmsStockBatchLogVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 日志ID
     */
    @ExcelProperty(value = "日志ID")
    private Long logId;

    /**
     * 批次库存ID
     */
    @ExcelProperty(value = "批次库存ID")
    private Long batchId;

    /**
     * 库存ID
     */
    @ExcelProperty(value = "库存ID")
    private Long stockId;

    /**
     * 仓库ID
     */
    @ExcelProperty(value = "仓库ID")
    private Long warehouseId;

    /**
     * 库位ID
     */
    @ExcelProperty(value = "库位ID")
    private Long locationId;

    /**
     * SKU ID
     */
    @ExcelProperty(value = "SKU ID")
    private Long skuId;

    /**
     * 批次号
     */
    @ExcelProperty(value = "批次号")
    private String batchNo;

    /**
     * 操作前数量
     */
    @ExcelProperty(value = "操作前数量")
    private Long quantityBefore;

    /**
     * 操作后数量
     */
    @ExcelProperty(value = "操作后数量")
    private Long quantityAfter;

    /**
     * 操作数量
     */
    @ExcelProperty(value = "操作数量")
    private Long quantityChange;

    /**
     * 操作类型（1入库 2出库 3盘点 4调拨）
     */
    @ExcelProperty(value = "操作类型", converter = ExcelDictConvert.class)
    @ExcelDictFormat(readConverterExp = "1=入库,2=出库,3=盘点,4=调拨")
    private String operateType;

    /**
     * 操作单据ID
     */
    @ExcelProperty(value = "操作单据ID")
    private Long operateId;

    /**
     * 备注
     */
    @ExcelProperty(value = "备注")
    private String remark;


}
