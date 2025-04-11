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
 * 盘点单对象 wms_check_order
 *
 * @author Mo mao
 * @date 2025-04-11
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("wms_check_order")
public class WmsCheckOrder extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 盘点单ID
     */
    @TableId(value = "order_id")
    private Long orderId;

    /**
     * 盘点单号
     */
    private String orderNo;

    /**
     * 仓库ID
     */
    private Long warehouseId;

    /**
     * 库区ID
     */
    private Long areaId;

    /**
     * 库位ID
     */
    private Long locationId;

    /**
     * 盘点日期
     */
    private Date checkDate;

    /**
     * 单据状态（0草稿 1待审核 2已审核 3完成）
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
