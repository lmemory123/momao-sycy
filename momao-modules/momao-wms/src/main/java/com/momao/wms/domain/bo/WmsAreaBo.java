package com.momao.wms.domain.bo;

import com.momao.common.core.validate.AddGroup;
import com.momao.common.core.validate.EditGroup;
import com.momao.common.mybatis.core.domain.BaseEntity;
import com.momao.wms.domain.WmsArea;
import io.github.linpeilie.annotations.AutoMapper;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 库区信息业务对象 wms_area
 *
 * @author Mo mao
 * @date 2025-04-11
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = WmsArea.class, reverseConvertGenerate = false)
public class WmsAreaBo extends BaseEntity {

    /**
     * 库区ID
     */
    @NotNull(message = "库区ID不能为空", groups = { EditGroup.class })
    private Long areaId;

    /**
     * 仓库ID
     */
    @NotNull(message = "仓库ID不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long warehouseId;

    /**
     * 库区名称
     */
    @NotBlank(message = "库区名称不能为空", groups = { AddGroup.class, EditGroup.class })
    private String areaName;

    /**
     * 库区编码
     */
    @NotBlank(message = "库区编码不能为空", groups = { AddGroup.class, EditGroup.class })
    private String areaCode;

    /**
     * 状态（0正常 1停用）
     */
    private String status;

    /**
     * 备注
     */
    private String remark;


}
