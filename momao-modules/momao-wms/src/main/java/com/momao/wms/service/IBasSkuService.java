package com.momao.wms.service;

import com.momao.common.mybatis.core.page.PageQuery;
import com.momao.common.mybatis.core.page.TableDataInfo;
import com.momao.wms.domain.bo.BasSkuBo;
import com.momao.wms.domain.vo.BasSkuVo;

import java.util.Collection;
import java.util.List;

/**
 * SKU信息Service接口
 *
 * @author Mo mao
 * @date 2025-04-11
 */
public interface IBasSkuService {

    /**
     * 查询SKU信息
     *
     * @param skuId 主键
     * @return SKU信息
     */
    BasSkuVo queryById(Long skuId);

    /**
     * 分页查询SKU信息列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return SKU信息分页列表
     */
    TableDataInfo<BasSkuVo> queryPageList(BasSkuBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的SKU信息列表
     *
     * @param bo 查询条件
     * @return SKU信息列表
     */
    List<BasSkuVo> queryList(BasSkuBo bo);

    /**
     * 新增SKU信息
     *
     * @param bo SKU信息
     * @return 是否新增成功
     */
    Boolean insertByBo(BasSkuBo bo);

    /**
     * 修改SKU信息
     *
     * @param bo SKU信息
     * @return 是否修改成功
     */
    Boolean updateByBo(BasSkuBo bo);

    /**
     * 校验并批量删除SKU信息信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
