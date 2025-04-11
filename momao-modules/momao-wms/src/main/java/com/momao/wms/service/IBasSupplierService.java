package com.momao.wms.service;

import com.momao.common.mybatis.core.page.PageQuery;
import com.momao.common.mybatis.core.page.TableDataInfo;
import com.momao.wms.domain.bo.BasSupplierBo;
import com.momao.wms.domain.vo.BasSupplierVo;

import java.util.Collection;
import java.util.List;

/**
 * 供应商信息Service接口
 *
 * @author Mo mao
 * @date 2025-04-11
 */
public interface IBasSupplierService {

    /**
     * 查询供应商信息
     *
     * @param supplierId 主键
     * @return 供应商信息
     */
    BasSupplierVo queryById(Long supplierId);

    /**
     * 分页查询供应商信息列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 供应商信息分页列表
     */
    TableDataInfo<BasSupplierVo> queryPageList(BasSupplierBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的供应商信息列表
     *
     * @param bo 查询条件
     * @return 供应商信息列表
     */
    List<BasSupplierVo> queryList(BasSupplierBo bo);

    /**
     * 新增供应商信息
     *
     * @param bo 供应商信息
     * @return 是否新增成功
     */
    Boolean insertByBo(BasSupplierBo bo);

    /**
     * 修改供应商信息
     *
     * @param bo 供应商信息
     * @return 是否修改成功
     */
    Boolean updateByBo(BasSupplierBo bo);

    /**
     * 校验并批量删除供应商信息信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
