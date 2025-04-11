package com.momao.wms.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.momao.common.excel.annotation.ExcelDictFormat;
import com.momao.common.excel.convert.ExcelDictConvert;
import com.momao.wms.domain.BasUnit;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;



/**
 * 计量单位视图对象 bas_unit
 *
 * @author Mo mao
 * @date 2025-04-11
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = BasUnit.class)
public class BasUnitVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 单位ID
     */
    @ExcelProperty(value = "单位ID")
    private Long unitId;

    /**
     * 单位名称
     */
    @ExcelProperty(value = "单位名称")
    private String unitName;

    /**
     * 单位编码
     */
    @ExcelProperty(value = "单位编码")
    private String unitCode;

    /**
     * 显示顺序
     */
    @ExcelProperty(value = "显示顺序")
    private Long orderNum;

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
