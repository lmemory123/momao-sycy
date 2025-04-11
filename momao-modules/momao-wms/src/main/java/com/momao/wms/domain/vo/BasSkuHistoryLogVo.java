package com.momao.wms.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.momao.common.excel.annotation.ExcelDictFormat;
import com.momao.common.excel.convert.ExcelDictConvert;
import com.momao.wms.domain.BasSkuHistoryLog;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;



/**
 * SKU信息历史变更视图对象 bas_sku_history_log
 *
 * @author Mo mao
 * @date 2025-04-11
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = BasSkuHistoryLog.class)
public class BasSkuHistoryLogVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 日志ID
     */
    @ExcelProperty(value = "日志ID")
    private Long logId;

    /**
     * SKU ID
     */
    @ExcelProperty(value = "SKU ID")
    private Long skuId;

    /**
     * SKU数据快照(JSON格式)
     */
    @ExcelProperty(value = "SKU数据快照(JSON格式)")
    private String snapshot;

    /**
     * 操作类型（1新增 2修改 3删除）
     */
    @ExcelProperty(value = "操作类型", converter = ExcelDictConvert.class)
    @ExcelDictFormat(readConverterExp = "1=新增,2=修改,3=删除")
    private String operateType;

    /**
     * 操作时间
     */
    @ExcelProperty(value = "操作时间")
    private Date operateTime;

    /**
     * 操作人ID
     */
    @ExcelProperty(value = "操作人ID")
    private Long operateBy;

    /**
     * 操作人姓名
     */
    @ExcelProperty(value = "操作人姓名")
    private String operateName;

    /**
     * 操作部门
     */
    @ExcelProperty(value = "操作部门")
    private Long operateDept;

    /**
     * 备注
     */
    @ExcelProperty(value = "备注")
    private String remark;


}
