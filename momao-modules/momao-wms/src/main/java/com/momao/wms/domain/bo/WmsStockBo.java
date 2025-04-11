package com.momao.wms.domain.bo;

import com.momao.common.core.validate.AddGroup;
import com.momao.common.core.validate.EditGroup;
import com.momao.common.mybatis.core.domain.BaseEntity;
import com.momao.wms.domain.WmsStock;
import io.github.linpeilie.annotations.AutoMapper;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
/**
 * 库存信息业务对象 wms_stock
 *
 * @author Mo mao
 * @date 2025-04-11
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = WmsStock.class, reverseConvertGenerate = false)
public class WmsStockBo extends BaseEntity {

    /**
     * 库存ID
     */
    @NotNull(message = "库存ID不能为空", groups = { EditGroup.class })
    private Long stockId;

    /**
     * 仓库ID
     */
    @NotNull(message = "仓库ID不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long warehouseId;

    /**
     * 库区ID
     */
    @NotNull(message = "库区ID不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long areaId;

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
