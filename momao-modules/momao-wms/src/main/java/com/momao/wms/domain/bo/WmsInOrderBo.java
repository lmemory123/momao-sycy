package com.momao.wms.domain.bo;

import com.momao.common.core.validate.AddGroup;
import com.momao.common.core.validate.EditGroup;
import com.momao.common.mybatis.core.domain.BaseEntity;
import com.momao.wms.domain.WmsInOrder;
import io.github.linpeilie.annotations.AutoMapper;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 入库单业务对象 wms_in_order
 *
 * @author Mo mao
 * @date 2025-04-11
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = WmsInOrder.class, reverseConvertGenerate = false)
public class WmsInOrderBo extends BaseEntity {

    /**
     * 入库单ID
     */
    @NotNull(message = "入库单ID不能为空", groups = { EditGroup.class })
    private Long orderId;

    /**
     * 入库单号
     */
    @NotBlank(message = "入库单号不能为空", groups = { AddGroup.class, EditGroup.class })
    private String orderNo;

    /**
     * 入库类型（1采购入库 2退货入库 3调拨入库）
     */
    @NotBlank(message = "入库类型（1采购入库 2退货入库 3调拨入库）不能为空", groups = { AddGroup.class, EditGroup.class })
    private String orderType;

    /**
     * 仓库ID
     */
    @NotNull(message = "仓库ID不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long warehouseId;

    /**
     * 供应商ID
     */
    private Long supplierId;

    /**
     * 单据日期
     */
    @NotNull(message = "单据日期不能为空", groups = { AddGroup.class, EditGroup.class })
    private Date orderDate;

    /**
     * 单据状态（0草稿 1待审核 2已审核 3已入库）
     */
    private String status;

    /**
     * 备注
     */
    private String remark;


}
