package com.momao.wms.service;

import com.momao.common.mybatis.core.page.PageQuery;
import com.momao.common.mybatis.core.page.TableDataInfo;
import com.momao.wms.domain.bo.BasSkuHistoryLogBo;
import com.momao.wms.domain.vo.BasSkuHistoryLogVo;

import java.util.Collection;
import java.util.List;

/**
 * SKU信息历史变更Service接口
 *
 * @author Mo mao
 * @date 2025-04-11
 */
public interface IBasSkuHistoryLogService {

    /**
     * 查询SKU信息历史变更
     *
     * @param logId 主键
     * @return SKU信息历史变更
     */
    BasSkuHistoryLogVo queryById(Long logId);

    /**
     * 分页查询SKU信息历史变更列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return SKU信息历史变更分页列表
     */
    TableDataInfo<BasSkuHistoryLogVo> queryPageList(BasSkuHistoryLogBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的SKU信息历史变更列表
     *
     * @param bo 查询条件
     * @return SKU信息历史变更列表
     */
    List<BasSkuHistoryLogVo> queryList(BasSkuHistoryLogBo bo);

    /**
     * 新增SKU信息历史变更
     *
     * @param bo SKU信息历史变更
     * @return 是否新增成功
     */
    Boolean insertByBo(BasSkuHistoryLogBo bo);

    /**
     * 修改SKU信息历史变更
     *
     * @param bo SKU信息历史变更
     * @return 是否修改成功
     */
    Boolean updateByBo(BasSkuHistoryLogBo bo);

    /**
     * 校验并批量删除SKU信息历史变更信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
