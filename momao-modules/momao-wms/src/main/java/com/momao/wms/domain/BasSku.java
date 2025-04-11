package com.momao.wms.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.momao.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * SKU信息对象 bas_sku
 *
 * @author Mo mao
 * @date 2025-04-11
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("bas_sku")
public class BasSku extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * SKU ID
     */
    @TableId(value = "sku_id")
    private Long skuId;

    /**
     * 分类ID
     */
    private Long categoryId;

    /**
     * SKU名称
     */
    private String skuName;

    /**
     * SKU编码
     */
    private String skuCode;

    /**
     * 条形码
     */
    private String barCode;

    /**
     * 基本单位
     */
    private Long unitId;

    /**
     * 重量(KG)
     */
    private Long weight;

    /**
     * 体积(m³)
     */
    private Long volume;

    /**
     * 保质期(天)
     */
    private Long shelfLife;

    /**
     * 最小库存
     */
    private Long minStock;

    /**
     * 最大库存
     */
    private Long maxStock;

    /**
     * 规格
     */
    private String specs;

    /**
     * 批次管理（0否 1是）
     */
    private String batchControl;

    /**
     * 状态（0正常 1停用）
     */
    private String status;

    /**
     * 备注
     */
    private String remark;

    /**
     * 出库策略：过期后容忍出库的天数
     */
    private Long outStrategyDays;

    /**
     * 入库策略：生产日期超过多少天不收
     */
    private Long inStrategyDays;


}
