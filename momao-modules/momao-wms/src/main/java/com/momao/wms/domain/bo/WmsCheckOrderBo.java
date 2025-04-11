package com.momao.wms.domain.bo;

import com.momao.common.core.validate.AddGroup;
import com.momao.common.core.validate.EditGroup;
import com.momao.common.mybatis.core.domain.BaseEntity;
import com.momao.wms.domain.WmsCheckOrder;
import io.github.linpeilie.annotations.AutoMapper;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 盘点单业务对象 wms_check_order
 *
 * @author Mo mao
 * @date 2025-04-11
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = WmsCheckOrder.class, reverseConvertGenerate = false)
public class WmsCheckOrderBo extends BaseEntity {

    /**
     * 盘点单ID
     */
    @NotNull(message = "盘点单ID不能为空", groups = { EditGroup.class })
    private Long orderId;

    /**
     * 盘点单号
     */
    @NotBlank(message = "盘点单号不能为空", groups = { AddGroup.class, EditGroup.class })
    private String orderNo;

    /**
     * 仓库ID
     */
    @NotNull(message = "仓库ID不能为空", groups = { AddGroup.class, EditGroup.class })
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
     * 盘点日期
     */
    @NotNull(message = "盘点日期不能为空", groups = { AddGroup.class, EditGroup.class })
    private Date checkDate;

    /**
     * 单据状态（0草稿 1待审核 2已审核 3完成）
     */
    private String status;

    /**
     * 备注
     */
    private String remark;


}
