package com.momao.wms.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.momao.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * 库位信息对象 wms_location
 *
 * @author Mo mao
 * @date 2025-04-11
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("wms_location")
public class WmsLocation extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 库位ID
     */
    @TableId(value = "location_id")
    private Long locationId;

    /**
     * 仓库ID
     */
    private Long warehouseId;

    /**
     * 库区ID
     */
    private Long areaId;

    /**
     * 库位名称
     */
    private String locationName;

    /**
     * 库位编码
     */
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
