package com.momao.wms.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.momao.common.excel.annotation.ExcelDictFormat;
import com.momao.common.excel.convert.ExcelDictConvert;
import com.momao.wms.domain.WmsWarehouse;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;



/**
 * 仓库信息视图对象 wms_warehouse
 *
 * @author Mo mao
 * @date 2025-04-11
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = WmsWarehouse.class)
public class WmsWarehouseVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 仓库ID
     */
    @ExcelProperty(value = "仓库ID")
    private Long warehouseId;

    /**
     * 仓库名称
     */
    @ExcelProperty(value = "仓库名称")
    private String warehouseName;

    /**
     * 仓库编码
     */
    @ExcelProperty(value = "仓库编码")
    private String warehouseCode;

    /**
     * 面积(m²)
     */
    @ExcelProperty(value = "面积(m²)")
    private Long area;

    /**
     * 地址
     */
    @ExcelProperty(value = "地址")
    private String address;

    /**
     * 负责人
     */
    @ExcelProperty(value = "负责人")
    private Long chargePerson;

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
