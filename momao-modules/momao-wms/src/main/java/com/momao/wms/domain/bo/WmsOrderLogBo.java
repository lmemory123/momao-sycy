package com.momao.wms.domain.bo;

import com.momao.common.core.validate.AddGroup;
import com.momao.common.core.validate.EditGroup;
import com.momao.common.mybatis.core.domain.BaseEntity;
import com.momao.wms.domain.WmsOrderLog;
import io.github.linpeilie.annotations.AutoMapper;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 单据操作日志业务对象 wms_order_log
 *
 * @author Mo mao
 * @date 2025-04-11
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = WmsOrderLog.class, reverseConvertGenerate = false)
public class WmsOrderLogBo extends BaseEntity {

    /**
     * 日志ID
     */
    @NotNull(message = "日志ID不能为空", groups = { EditGroup.class })
    private Long logId;

    /**
     * 单据ID
     */
    @NotNull(message = "单据ID不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long orderId;

    /**
     * 单据类型（1入库单 2出库单 3盘点单 4调拨单）
     */
    @NotBlank(message = "单据类型（1入库单 2出库单 3盘点单 4调拨单）不能为空", groups = { AddGroup.class, EditGroup.class })
    private String orderType;

    /**
     * 单据编号
     */
    @NotBlank(message = "单据编号不能为空", groups = { AddGroup.class, EditGroup.class })
    private String orderNo;

    /**
     * 操作类型（1创建 2修改 3审核 4撤销 5删除 6确认）
     */
    @NotBlank(message = "操作类型（1创建 2修改 3审核 4撤销 5删除 6确认）不能为空", groups = { AddGroup.class, EditGroup.class })
    private String operateType;

    /**
     * 操作前状态
     */
    private String statusBefore;

    /**
     * 操作后状态
     */
    private String statusAfter;

    /**
     * 操作内容
     */
    private String operateContent;

    /**
     * 操作人名称
     */
    private String createName;

    /**
     * 备注
     */
    private String remark;


}
