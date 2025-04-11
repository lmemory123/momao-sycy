package com.momao.wms.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.momao.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.util.Date;

/**
 * 批次库存对象 wms_stock_batch
 *
 * @author Mo mao
 * @date 2025-04-11
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("wms_stock_batch")
public class WmsStockBatch extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 批次库存ID
     */
    @TableId(value = "batch_id")
    private Long batchId;

    /**
     * 库存ID
     */
    private Long stockId;

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
     * SKU ID
     */
    private Long skuId;

    /**
     * 批次号
     */
    private String batchNo;

    /**
     * 批次库存数量
     */
    private Long batchQuantity;

    /**
     * 锁定数量
     */
    private Long lockedQuantity;

    /**
     * 可用数量
     */
    private Long availableQuantity;

    /**
     * 供应商ID
     */
    private Long supplierId;

    /**
     * 生产日期
     */
    private Date produceDate;

    /**
     * 有效期
     */
    private Date expireDate;

    /**
     * 状态（0正常 1警告 2异常）
     */
    private String status;

    /**
     * 备注
     */
    private String remark;


}
