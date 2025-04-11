package com.momao.wms.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.momao.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * 库存信息对象 wms_stock
 *
 * @author Mo mao
 * @date 2025-04-11
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("wms_stock")
public class WmsStock extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 库存ID
     */
    @TableId(value = "stock_id")
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
     * 库存数量
     */
    private Long stockQuantity;

    /**
     * 锁定数量
     */
    private Long lockedQuantity;

    /**
     * 可用数量
     */
    private Long availableQuantity;

    /**
     * 状态（0正常 1警告 2异常）
     */
    private String status;

    /**
     * 备注
     */
    private String remark;


}
