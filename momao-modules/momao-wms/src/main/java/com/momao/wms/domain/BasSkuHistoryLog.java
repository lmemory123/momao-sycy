package com.momao.wms.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.momao.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.util.Date;

/**
 * SKU信息历史变更对象 bas_sku_history_log
 *
 * @author Mo mao
 * @date 2025-04-11
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("bas_sku_history_log")
public class BasSkuHistoryLog extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 日志ID
     */
    @TableId(value = "log_id")
    private Long logId;

    /**
     * SKU ID
     */
    private Long skuId;

    /**
     * SKU数据快照(JSON格式)
     */
    private String snapshot;

    /**
     * 操作类型（1新增 2修改 3删除）
     */
    private String operateType;

    /**
     * 操作时间
     */
    private Date operateTime;

    /**
     * 操作人ID
     */
    private Long operateBy;

    /**
     * 操作人姓名
     */
    private String operateName;

    /**
     * 操作部门
     */
    private Long operateDept;

    /**
     * 备注
     */
    private String remark;


}
