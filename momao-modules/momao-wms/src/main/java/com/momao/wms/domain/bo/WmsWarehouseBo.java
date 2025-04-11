package com.momao.wms.domain.bo;

import com.momao.common.core.validate.AddGroup;
import com.momao.common.core.validate.EditGroup;
import com.momao.common.mybatis.core.domain.BaseEntity;
import com.momao.wms.domain.WmsWarehouse;
import io.github.linpeilie.annotations.AutoMapper;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 仓库信息业务对象 wms_warehouse
 *
 * @author Mo mao
 * @date 2025-04-11
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = WmsWarehouse.class, reverseConvertGenerate = false)
public class WmsWarehouseBo extends BaseEntity {

    /**
     * 仓库ID
     */
    @NotNull(message = "仓库ID不能为空", groups = { EditGroup.class })
    private Long warehouseId;

    /**
     * 仓库名称
     */
    @NotBlank(message = "仓库名称不能为空", groups = { AddGroup.class, EditGroup.class })
    private String warehouseName;

    /**
     * 仓库编码
     */
    @NotBlank(message = "仓库编码不能为空", groups = { AddGroup.class, EditGroup.class })
    private String warehouseCode;

    /**
     * 面积(m²)
     */
    private Long area;

    /**
     * 地址
     */
    private String address;

    /**
     * 负责人
     */
    private Long chargePerson;

    /**
     * 状态（0正常 1停用）
     */
    private String status;

    /**
     * 备注
     */
    private String remark;


}
