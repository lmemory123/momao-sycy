package com.momao.wms.domain.bo;

import com.momao.common.core.validate.AddGroup;
import com.momao.common.core.validate.EditGroup;
import com.momao.common.mybatis.core.domain.BaseEntity;
import com.momao.wms.domain.WmsStockLog;
import io.github.linpeilie.annotations.AutoMapper;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 库存操作日志业务对象 wms_stock_log
 *
 * @author Mo mao
 * @date 2025-04-11
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = WmsStockLog.class, reverseConvertGenerate = false)
public class WmsStockLogBo extends BaseEntity {

    /**
     * 日志ID
     */
    @NotNull(message = "日志ID不能为空", groups = { EditGroup.class })
    private Long logId;

    /**
     * 库存ID
     */
    @NotNull(message = "库存ID不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long stockId;

    /**
     * 仓库ID
     */
    @NotNull(message = "仓库ID不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long warehouseId;

    /**
     * 库位ID
     */
    @NotNull(message = "库位ID不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long locationId;

    /**
     * SKU ID
     */
    @NotNull(message = "SKU ID不能为空", groups = { AddGroup.class, EditGroup.class })
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
    @NotBlank(message = "操作类型（1入库 2出库 3盘点 4调拨）不能为空", groups = { AddGroup.class, EditGroup.class })
    private String operateType;

    /**
     * 操作单据ID
     */
    @NotNull(message = "操作单据ID不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long operateId;

    /**
     * 备注
     */
    private String remark;


}
