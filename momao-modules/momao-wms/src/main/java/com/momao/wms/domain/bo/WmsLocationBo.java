package com.momao.wms.domain.bo;

import com.momao.common.core.validate.AddGroup;
import com.momao.common.core.validate.EditGroup;
import com.momao.common.mybatis.core.domain.BaseEntity;
import com.momao.wms.domain.WmsLocation;
import io.github.linpeilie.annotations.AutoMapper;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
/**
 * 库位信息业务对象 wms_location
 *
 * @author Mo mao
 * @date 2025-04-11
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = WmsLocation.class, reverseConvertGenerate = false)
public class WmsLocationBo extends BaseEntity {

    /**
     * 库位ID
     */
    @NotNull(message = "库位ID不能为空", groups = { EditGroup.class })
    private Long locationId;

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
     * 库位名称
     */
    @NotBlank(message = "库位名称不能为空", groups = { AddGroup.class, EditGroup.class })
    private String locationName;

    /**
     * 库位编码
     */
    @NotBlank(message = "库位编码不能为空", groups = { AddGroup.class, EditGroup.class })
    private String locationCode;

    /**
     * 容量
     */
    private Long capacity;

    /**
     * 状态（0正常 1停用 2占用）
     */
    private String status;

    /**
     * 备注
     */
    private String remark;


}
