package com.momao.wms.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.momao.common.excel.annotation.ExcelDictFormat;
import com.momao.common.excel.convert.ExcelDictConvert;
import com.momao.wms.domain.WmsStock;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;



/**
 * 库存信息视图对象 wms_stock
 *
 * @author Mo mao
 * @date 2025-04-11
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = WmsStock.class)
public class WmsStockVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

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
     * 库区ID
     */
    @ExcelProperty(value = "库区ID")
    private Long areaId;

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
     * 库存数量
     */
    @ExcelProperty(value = "库存数量")
    private Long stockQuantity;

    /**
     * 锁定数量
     */
    @ExcelProperty(value = "锁定数量")
    private Long lockedQuantity;

    /**
     * 可用数量
     */
    @ExcelProperty(value = "可用数量")
    private Long availableQuantity;

    /**
     * 状态（0正常 1警告 2异常）
     */
    @ExcelProperty(value = "状态", converter = ExcelDictConvert.class)
    @ExcelDictFormat(readConverterExp = "0=正常,1=警告,2=异常")
    private String status;

    /**
     * 备注
     */
    @ExcelProperty(value = "备注")
    private String remark;


}
