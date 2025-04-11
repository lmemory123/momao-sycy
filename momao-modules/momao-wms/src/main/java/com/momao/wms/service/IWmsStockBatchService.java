package com.momao.wms.service;

import com.momao.common.mybatis.core.page.PageQuery;
import com.momao.common.mybatis.core.page.TableDataInfo;
import com.momao.wms.domain.bo.WmsStockBatchBo;
import com.momao.wms.domain.vo.WmsStockBatchVo;

import java.util.Collection;
import java.util.List;

/**
 * 批次库存Service接口
 *
 * @author Mo mao
 * @date 2025-04-11
 */
public interface IWmsStockBatchService {

    /**
     * 查询批次库存
     *
     * @param batchId 主键
     * @return 批次库存
     */
    WmsStockBatchVo queryById(Long batchId);

    /**
     * 分页查询批次库存列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 批次库存分页列表
     */
    TableDataInfo<WmsStockBatchVo> queryPageList(WmsStockBatchBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的批次库存列表
     *
     * @param bo 查询条件
     * @return 批次库存列表
     */
    List<WmsStockBatchVo> queryList(WmsStockBatchBo bo);

    /**
     * 新增批次库存
     *
     * @param bo 批次库存
     * @return 是否新增成功
     */
    Boolean insertByBo(WmsStockBatchBo bo);

    /**
     * 修改批次库存
     *
     * @param bo 批次库存
     * @return 是否修改成功
     */
    Boolean updateByBo(WmsStockBatchBo bo);

    /**
     * 校验并批量删除批次库存信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
