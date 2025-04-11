package com.momao.wms.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.momao.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * SKU分类对象 bas_category
 *
 * @author Mo mao
 * @date 2025-04-11
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("bas_category")
public class BasCategory extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 分类ID
     */
    @TableId(value = "category_id")
    private Long categoryId;

    /**
     * 父分类ID
     */
    private Long parentId;

    /**
     * 祖级列表
     */
    private String ancestors;

    /**
     * 分类名称
     */
    private String categoryName;

    /**
     * 分类编码
     */
    private String categoryCode;

    /**
     * 显示顺序
     */
    private Long orderNum;

    /**
     * 层级
     */
    private Long level;

    /**
     * 状态（0正常 1停用）
     */
    private String status;

    /**
     * 备注
     */
    private String remark;


}
