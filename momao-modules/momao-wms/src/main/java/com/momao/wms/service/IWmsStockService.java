package com.momao.wms.service;

import com.momao.common.mybatis.core.page.PageQuery;
import com.momao.common.mybatis.core.page.TableDataInfo;
import com.momao.wms.domain.bo.WmsStockBo;
import com.momao.wms.domain.vo.WmsStockVo;

import java.util.Collection;
import java.util.List;

/**
 * 库存信息Service接口
 *
 * @author Mo mao
 * @date 2025-04-11
 */
public interface IWmsStockService {

    /**
     * 查询库存信息
     *
     * @param stockId 主键
     * @return 库存信息
     */
    WmsStockVo queryById(Long stockId);

    /**
     * 分页查询库存信息列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 库存信息分页列表
     */
    TableDataInfo<WmsStockVo> queryPageList(WmsStockBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的库存信息列表
     *
     * @param bo 查询条件
     * @return 库存信息列表
     */
    List<WmsStockVo> queryList(WmsStockBo bo);

    /**
     * 新增库存信息
     *
     * @param bo 库存信息
     * @return 是否新增成功
     */
    Boolean insertByBo(WmsStockBo bo);

    /**
     * 修改库存信息
     *
     * @param bo 库存信息
     * @return 是否修改成功
     */
    Boolean updateByBo(WmsStockBo bo);

    /**
     * 校验并批量删除库存信息信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
