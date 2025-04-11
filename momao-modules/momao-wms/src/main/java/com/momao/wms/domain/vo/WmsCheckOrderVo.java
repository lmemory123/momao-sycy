package com.momao.wms.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.momao.common.excel.annotation.ExcelDictFormat;
import com.momao.common.excel.convert.ExcelDictConvert;
import com.momao.wms.domain.WmsCheckOrder;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;



/**
 * 盘点单视图对象 wms_check_order
 *
 * @author Mo mao
 * @date 2025-04-11
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = WmsCheckOrder.class)
public class WmsCheckOrderVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 盘点单ID
     */
    @ExcelProperty(value = "盘点单ID")
    private Long orderId;

    /**
     * 盘点单号
     */
    @ExcelProperty(value = "盘点单号")
    private String orderNo;

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
     * 盘点日期
     */
    @ExcelProperty(value = "盘点日期")
    private Date checkDate;

    /**
     * 单据状态（0草稿 1待审核 2已审核 3完成）
     */
    @ExcelProperty(value = "单据状态", converter = ExcelDictConvert.class)
    @ExcelDictFormat(readConverterExp = "0=草稿,1=待审核,2=已审核,3=完成")
    private String status;

    /**
     * 备注
     */
    @ExcelProperty(value = "备注")
    private String remark;


}
