package com.momao.wms.domain.bo;

import com.momao.common.core.validate.AddGroup;
import com.momao.common.core.validate.EditGroup;
import com.momao.common.mybatis.core.domain.BaseEntity;
import com.momao.wms.domain.WmsStockBatch;
import io.github.linpeilie.annotations.AutoMapper;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 批次库存业务对象 wms_stock_batch
 *
 * @author Mo mao
 * @date 2025-04-11
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = WmsStockBatch.class, reverseConvertGenerate = false)
public class WmsStockBatchBo extends BaseEntity {

    /**
     * 批次库存ID
     */
    @NotNull(message = "批次库存ID不能为空", groups = { EditGroup.class })
    private Long batchId;

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
     * 批次号
     */
    @NotBlank(message = "批次号不能为空", groups = { AddGroup.class, EditGroup.class })
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
