package com.momao.wms.domain.bo;

import com.momao.common.core.validate.AddGroup;
import com.momao.common.core.validate.EditGroup;
import com.momao.common.mybatis.core.domain.BaseEntity;
import com.momao.wms.domain.BasUnit;
import io.github.linpeilie.annotations.AutoMapper;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 计量单位业务对象 bas_unit
 *
 * @author Mo mao
 * @date 2025-04-11
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = BasUnit.class, reverseConvertGenerate = false)
public class BasUnitBo extends BaseEntity {

    /**
     * 单位ID
     */
    @NotNull(message = "单位ID不能为空", groups = { EditGroup.class })
    private Long unitId;

    /**
     * 单位名称
     */
    @NotBlank(message = "单位名称不能为空", groups = { AddGroup.class, EditGroup.class })
    private String unitName;

    /**
     * 单位编码
     */
    private String unitCode;

    /**
     * 显示顺序
     */
    private Long orderNum;

    /**
     * 状态（0正常 1停用）
     */
    private String status;

    /**
     * 备注
     */
    private String remark;


}
