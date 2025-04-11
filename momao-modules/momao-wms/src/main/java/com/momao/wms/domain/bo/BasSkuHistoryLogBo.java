package com.momao.wms.domain.bo;

import com.momao.common.core.validate.AddGroup;
import com.momao.common.core.validate.EditGroup;
import com.momao.common.mybatis.core.domain.BaseEntity;
import com.momao.wms.domain.BasSkuHistoryLog;
import io.github.linpeilie.annotations.AutoMapper;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * SKU信息历史变更业务对象 bas_sku_history_log
 *
 * @author Mo mao
 * @date 2025-04-11
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = BasSkuHistoryLog.class, reverseConvertGenerate = false)
public class BasSkuHistoryLogBo extends BaseEntity {

    /**
     * 日志ID
     */
    @NotNull(message = "日志ID不能为空", groups = { EditGroup.class })
    private Long logId;

    /**
     * SKU ID
     */
    @NotNull(message = "SKU ID不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long skuId;

    /**
     * SKU数据快照(JSON格式)
     */
    private String snapshot;

    /**
     * 操作类型（1新增 2修改 3删除）
     */
    @NotBlank(message = "操作类型（1新增 2修改 3删除）不能为空", groups = { AddGroup.class, EditGroup.class })
    private String operateType;

    /**
     * 操作时间
     */
    @NotNull(message = "操作时间不能为空", groups = { AddGroup.class, EditGroup.class })
    private Date operateTime;

    /**
     * 操作人ID
     */
    @NotNull(message = "操作人ID不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long operateBy;

    /**
     * 操作人姓名
     */
    private String operateName;

    /**
     * 操作部门
     */
    private Long operateDept;

    /**
     * 备注
     */
    private String remark;


}
