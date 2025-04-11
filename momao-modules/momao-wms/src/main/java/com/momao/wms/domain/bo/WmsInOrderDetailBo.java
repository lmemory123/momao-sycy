package com.momao.wms.domain.bo;

import com.momao.common.core.validate.AddGroup;
import com.momao.common.core.validate.EditGroup;
import com.momao.common.mybatis.core.domain.BaseEntity;
import com.momao.wms.domain.WmsInOrderDetail;
import io.github.linpeilie.annotations.AutoMapper;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
/**
 * 入库单详情业务对象 wms_in_order_detail
 *
 * @author Mo mao
 * @date 2025-04-11
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = WmsInOrderDetail.class, reverseConvertGenerate = false)
public class WmsInOrderDetailBo extends BaseEntity {

    /**
     * 详情ID
     */
    @NotNull(message = "详情ID不能为空", groups = { EditGroup.class })
    private Long detailId;

    /**
     * 入库单ID
     */
    @NotNull(message = "入库单ID不能为空", groups = { AddGroup.class, EditGroup.class })
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
     * 状态（0未入库 1已入库）
     */
    private String status;

    /**
     * 备注
     */
    private String remark;


}
