package com.momao.wms.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.momao.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * 供应商信息对象 bas_supplier
 *
 * @author Mo mao
 * @date 2025-04-11
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("bas_supplier")
public class BasSupplier extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 供应商ID
     */
    @TableId(value = "supplier_id")
    private Long supplierId;

    /**
     * 供应商名称
     */
    private String supplierName;

    /**
     * 供应商编码
     */
    private String supplierCode;

    /**
     * 联系人
     */
    private String contact;

    /**
     * 联系电话
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 地址
     */
    private String address;

    /**
     * 状态（0正常 1停用）
     */
    private String status;

    /**
     * 备注
     */
    private String remark;


}
