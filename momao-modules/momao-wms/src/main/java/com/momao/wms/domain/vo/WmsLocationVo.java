package com.momao.wms.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.momao.common.excel.annotation.ExcelDictFormat;
import com.momao.common.excel.convert.ExcelDictConvert;
import com.momao.wms.domain.WmsLocation;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;



/**
 * 库位信息视图对象 wms_location
 *
 * @author Mo mao
 * @date 2025-04-11
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = WmsLocation.class)
public class WmsLocationVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 库位ID
     */
    @ExcelProperty(value = "库位ID")
    private Long locationId;

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
     * 库位名称
     */
    @ExcelProperty(value = "库位名称")
    private String locationName;

    /**
     * 库位编码
     */
    @ExcelProperty(value = "库位编码")
    private String locationCode;

    /**
     * 容量
     */
    @ExcelProperty(value = "容量")
    private Long capacity;

    /**
     * 状态（0正常 1停用 2占用）
     */
    @ExcelProperty(value = "状态", converter = ExcelDictConvert.class)
    @ExcelDictFormat(readConverterExp = "0=正常,1=停用,2=占用")
    private String status;

    /**
     * 备注
     */
    @ExcelProperty(value = "备注")
    private String remark;


}
