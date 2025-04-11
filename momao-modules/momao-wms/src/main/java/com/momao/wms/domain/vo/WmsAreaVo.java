package com.momao.wms.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.momao.common.excel.annotation.ExcelDictFormat;
import com.momao.common.excel.convert.ExcelDictConvert;
import com.momao.wms.domain.WmsArea;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;



/**
 * 库区信息视图对象 wms_area
 *
 * @author Mo mao
 * @date 2025-04-11
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = WmsArea.class)
public class WmsAreaVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 库区ID
     */
    @ExcelProperty(value = "库区ID")
    private Long areaId;

    /**
     * 仓库ID
     */
    @ExcelProperty(value = "仓库ID")
    private Long warehouseId;

    /**
     * 库区名称
     */
    @ExcelProperty(value = "库区名称")
    private String areaName;

    /**
     * 库区编码
     */
    @ExcelProperty(value = "库区编码")
    private String areaCode;

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
