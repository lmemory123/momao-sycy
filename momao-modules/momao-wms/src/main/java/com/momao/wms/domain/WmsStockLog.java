package com.momao.wms.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.momao.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * 库存操作日志对象 wms_stock_log
 *
 * @author Mo mao
 * @date 2025-04-11
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("wms_stock_log")
public class WmsStockLog extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 日志ID
     */
    @TableId(value = "log_id")
    private Long logId;

    /**
     * 库存ID
     */
    private Long stockId;

    /**
     * 仓库ID
     */
    private Long warehouseId;

    /**
     * 库位ID
     */
    private Long locationId;

    /**
     * SKU ID
     */
    private Long skuId;

    /**
     * 操作前数量
     */
    private Long quantityBefore;

    /**
     * 操作后数量
     */
    private Long quantityAfter;

    /**
     * 操作数量
     */
    private Long quantityChange;

    /**
     * 操作类型（1入库 2出库 3盘点 4调拨）
     */
    private String operateType;

    /**
     * 操作单据ID
     */
    private Long operateId;

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
