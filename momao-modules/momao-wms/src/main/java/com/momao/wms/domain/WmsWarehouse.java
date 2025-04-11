package com.momao.wms.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.momao.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * 仓库信息对象 wms_warehouse
 *
 * @author Mo mao
 * @date 2025-04-11
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("wms_warehouse")
public class WmsWarehouse extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 仓库ID
     */
    @TableId(value = "warehouse_id")
    private Long warehouseId;

    /**
     * 仓库名称
     */
    private String warehouseName;

    /**
     * 仓库编码
     */
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
