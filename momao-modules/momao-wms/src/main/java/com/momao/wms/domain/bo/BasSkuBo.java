package com.momao.wms.domain.bo;

import com.momao.common.core.validate.AddGroup;
import com.momao.common.core.validate.EditGroup;
import com.momao.common.mybatis.core.domain.BaseEntity;
import com.momao.wms.domain.BasSku;
import io.github.linpeilie.annotations.AutoMapper;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * SKU信息业务对象 bas_sku
 *
 * @author Mo mao
 * @date 2025-04-11
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = BasSku.class, reverseConvertGenerate = false)
public class BasSkuBo extends BaseEntity {

    /**
     * SKU ID
     */
    @NotNull(message = "SKU ID不能为空", groups = { EditGroup.class })
    private Long skuId;

    /**
     * 分类ID
     */
    @NotNull(message = "分类ID不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long categoryId;

    /**
     * SKU名称
     */
    @NotBlank(message = "SKU名称不能为空", groups = { AddGroup.class, EditGroup.class })
    private String skuName;

    /**
     * SKU编码
     */
    @NotBlank(message = "SKU编码不能为空", groups = { AddGroup.class, EditGroup.class })
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
