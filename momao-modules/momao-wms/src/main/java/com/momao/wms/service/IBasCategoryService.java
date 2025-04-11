package com.momao.wms.service;

import com.momao.common.mybatis.core.page.PageQuery;
import com.momao.common.mybatis.core.page.TableDataInfo;
import com.momao.wms.domain.bo.BasCategoryBo;
import com.momao.wms.domain.vo.BasCategoryVo;

import java.util.Collection;
import java.util.List;

/**
 * SKU分类Service接口
 *
 * @author Mo mao
 * @date 2025-04-11
 */
public interface IBasCategoryService {

    /**
     * 查询SKU分类
     *
     * @param categoryId 主键
     * @return SKU分类
     */
    BasCategoryVo queryById(Long categoryId);

    /**
     * 分页查询SKU分类列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return SKU分类分页列表
     */
    TableDataInfo<BasCategoryVo> queryPageList(BasCategoryBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的SKU分类列表
     *
     * @param bo 查询条件
     * @return SKU分类列表
     */
    List<BasCategoryVo> queryList(BasCategoryBo bo);

    /**
     * 新增SKU分类
     *
     * @param bo SKU分类
     * @return 是否新增成功
     */
    Boolean insertByBo(BasCategoryBo bo);

    /**
     * 修改SKU分类
     *
     * @param bo SKU分类
     * @return 是否修改成功
     */
    Boolean updateByBo(BasCategoryBo bo);

    /**
     * 校验并批量删除SKU分类信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
