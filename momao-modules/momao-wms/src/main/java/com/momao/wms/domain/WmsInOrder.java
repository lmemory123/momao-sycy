package com.momao.wms.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.momao.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.util.Date;

/**
 * 入库单对象 wms_in_order
 *
 * @author Mo mao
 * @date 2025-04-11
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("wms_in_order")
public class WmsInOrder extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 入库单ID
     */
    @TableId(value = "order_id")
    private Long orderId;

    /**
     * 入库单号
     */
    private String orderNo;

    /**
     * 入库类型（1采购入库 2退货入库 3调拨入库）
     */
    private String orderType;

    /**
     * 仓库ID
     */
    private Long warehouseId;

    /**
     * 供应商ID
     */
    private Long supplierId;

    /**
     * 单据日期
     */
    private Date orderDate;

    /**
     * 单据状态（0草稿 1待审核 2已审核 3已入库）
     */
    private String status;

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
