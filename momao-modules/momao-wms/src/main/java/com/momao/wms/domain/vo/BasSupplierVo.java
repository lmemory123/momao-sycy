package com.momao.wms.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.momao.common.excel.annotation.ExcelDictFormat;
import com.momao.common.excel.convert.ExcelDictConvert;
import com.momao.wms.domain.BasSupplier;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;



/**
 * 供应商信息视图对象 bas_supplier
 *
 * @author Mo mao
 * @date 2025-04-11
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = BasSupplier.class)
public class BasSupplierVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 供应商ID
     */
    @ExcelProperty(value = "供应商ID")
    private Long supplierId;

    /**
     * 供应商名称
     */
    @ExcelProperty(value = "供应商名称")
    private String supplierName;

    /**
     * 供应商编码
     */
    @ExcelProperty(value = "供应商编码")
    private String supplierCode;

    /**
     * 联系人
     */
    @ExcelProperty(value = "联系人")
    private String contact;

    /**
     * 联系电话
     */
    @ExcelProperty(value = "联系电话")
    private String phone;

    /**
     * 邮箱
     */
    @ExcelProperty(value = "邮箱")
    private String email;

    /**
     * 地址
     */
    @ExcelProperty(value = "地址")
    private String address;

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
