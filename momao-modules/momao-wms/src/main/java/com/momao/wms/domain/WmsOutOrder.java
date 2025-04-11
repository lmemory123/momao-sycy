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
 * 出库单对象 wms_out_order
 *
 * @author Mo mao
 * @date 2025-04-11
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("wms_out_order")
public class WmsOutOrder extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 出库单ID
     */
    @TableId(value = "order_id")
    private Long orderId;

    /**
     * 出库单号
     */
    private String orderNo;

    /**
     * 出库类型（1销售出库 2退货出库 3调拨出库）
     */
    private String orderType;

    /**
     * 仓库ID
     */
    private Long warehouseId;

    /**
     * 客户ID
     */
    private Long customerId;

    /**
     * 单据日期
     */
    private Date orderDate;

    /**
     * 单据状态（0草稿 1待审核 2已审核 3已出库）
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
