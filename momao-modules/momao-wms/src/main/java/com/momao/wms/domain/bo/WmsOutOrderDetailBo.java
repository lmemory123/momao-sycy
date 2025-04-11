package com.momao.wms.domain.bo;

import com.momao.common.core.validate.AddGroup;
import com.momao.common.core.validate.EditGroup;
import com.momao.common.mybatis.core.domain.BaseEntity;
import com.momao.wms.domain.WmsOutOrderDetail;
import io.github.linpeilie.annotations.AutoMapper;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 出库单详情业务对象 wms_out_order_detail
 *
 * @author Mo mao
 * @date 2025-04-11
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = WmsOutOrderDetail.class, reverseConvertGenerate = false)
public class WmsOutOrderDetailBo extends BaseEntity {

    /**
     * 详情ID
     */
    @NotNull(message = "详情ID不能为空", groups = { EditGroup.class })
    private Long detailId;

    /**
     * 出库单ID
     */
    @NotNull(message = "出库单ID不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long orderId;

    /**
     * SKU ID
     */
    @NotNull(message = "SKU ID不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long skuId;

    /**
     * 计划数量
     */
    @NotNull(message = "计划数量不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long planQuantity;

    /**
     * 实际数量
     */
    private Long realQuantity;

    /**
     * 库位ID
     */
    private Long locationId;

    /**
     * 批次号
     */
    private String batchNo;

    /**
     * 生产日期
     */
    private Date produceDate;

    /**
     * 有效期
     */
    private Date expireDate;

    /**
     * 供应商ID
     */
    private Long supplierId;

    /**
     * 状态（0未出库 1已出库）
     */
    private String status;

    /**
     * 备注
     */
    private String remark;


}
