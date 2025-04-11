package com.momao.wms.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.momao.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.util.Date;

/**
 * 盘点单详情对象 wms_check_detail
 *
 * @author Mo mao
 * @date 2025-04-11
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("wms_check_detail")
public class WmsCheckDetail extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 详情ID
     */
    @TableId(value = "detail_id")
    private Long detailId;

    /**
     * 盘点单ID
     */
    private Long orderId;

    /**
     * SKU ID
     */
    private Long skuId;

    /**
     * 库位ID
     */
    private Long locationId;

    /**
     * 账面数量
     */
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

    /**
     * 删除标志（0代表存在 1代表删除）
     */
    @TableLogic
    private String delFlag;


}
