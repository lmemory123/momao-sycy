package com.momao.wms.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.momao.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * 单据操作日志对象 wms_order_log
 *
 * @author Mo mao
 * @date 2025-04-11
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("wms_order_log")
public class WmsOrderLog extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 日志ID
     */
    @TableId(value = "log_id")
    private Long logId;

    /**
     * 单据ID
     */
    private Long orderId;

    /**
     * 单据类型（1入库单 2出库单 3盘点单 4调拨单）
     */
    private String orderType;

    /**
     * 单据编号
     */
    private String orderNo;

    /**
     * 操作类型（1创建 2修改 3审核 4撤销 5删除 6确认）
     */
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

    /**
     * 删除标志（0代表存在 1代表删除）
     */
    @TableLogic
    private String delFlag;


}
