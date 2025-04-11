package com.momao.wms.domain.bo;

import com.momao.common.core.validate.AddGroup;
import com.momao.common.core.validate.EditGroup;
import com.momao.common.mybatis.core.domain.BaseEntity;
import com.momao.wms.domain.WmsCheckDetail;
import io.github.linpeilie.annotations.AutoMapper;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 盘点单详情业务对象 wms_check_detail
 *
 * @author Mo mao
 * @date 2025-04-11
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = WmsCheckDetail.class, reverseConvertGenerate = false)
public class WmsCheckDetailBo extends BaseEntity {

    /**
     * 详情ID
     */
    @NotNull(message = "详情ID不能为空", groups = { EditGroup.class })
    private Long detailId;

    /**
     * 盘点单ID
     */
    @NotNull(message = "盘点单ID不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long orderId;

    /**
     * SKU ID
     */
    @NotNull(message = "SKU ID不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long skuId;

    /**
     * 库位ID
     */
    @NotNull(message = "库位ID不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long locationId;

    /**
     * 账面数量
     */
    @NotNull(message = "账面数量不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long stockQuantity;

    /**
     * 盘点数量
     */
    private Long checkQuantity;

    /**
     * 差异数量
     */
    private Long diffQuantity;

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
     * 状态（0未盘点 1已盘点）
     */
    private String status;

    /**
     * 备注
     */
    private String remark;


}
