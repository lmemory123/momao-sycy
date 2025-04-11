package com.momao.wms.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.momao.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;



/**
 * 计量单位对象 bas_unit
 *
 * @author Mo mao
 * @date 2025-04-11
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("bas_unit")
public class BasUnit extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 单位ID
     */
    @TableId(value = "unit_id")
    private Long unitId;

    /**
     * 单位名称
     */
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
