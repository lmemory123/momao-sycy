package com.momao.wms.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.momao.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * 库区信息对象 wms_area
 *
 * @author Mo mao
 * @date 2025-04-11
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("wms_area")
public class WmsArea extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 库区ID
     */
    @TableId(value = "area_id")
    private Long areaId;

    /**
     * 仓库ID
     */
    private Long warehouseId;

    /**
     * 库区名称
     */
    private String areaName;

    /**
     * 库区编码
     */
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
