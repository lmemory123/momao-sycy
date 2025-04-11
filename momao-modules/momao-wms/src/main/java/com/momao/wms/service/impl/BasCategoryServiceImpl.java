package com.momao.wms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.momao.common.core.utils.MapstructUtils;
import com.momao.common.core.utils.StringUtils;
import com.momao.common.mybatis.core.page.PageQuery;
import com.momao.common.mybatis.core.page.TableDataInfo;
import com.momao.wms.domain.BasCategory;
import com.momao.wms.domain.bo.BasCategoryBo;
import com.momao.wms.domain.vo.BasCategoryVo;
import com.momao.wms.mapper.BasCategoryMapper;
import com.momao.wms.service.IBasCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * SKU分类Service业务层处理
 *
 * @author Mo mao
 * @date 2025-04-11
 */
@RequiredArgsConstructor
@Service
public class BasCategoryServiceImpl implements IBasCategoryService {

    private final BasCategoryMapper baseMapper;

    /**
     * 查询SKU分类
     *
     * @param categoryId 主键
     * @return SKU分类
     */
    @Override
    public BasCategoryVo queryById(Long categoryId){
        return baseMapper.selectVoById(categoryId);
    }

    /**
     * 分页查询SKU分类列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return SKU分类分页列表
     */
    @Override
    public TableDataInfo<BasCategoryVo> queryPageList(BasCategoryBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<BasCategory> lqw = buildQueryWrapper(bo);
        Page<BasCategoryVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的SKU分类列表
     *
     * @param bo 查询条件
     * @return SKU分类列表
     */
    @Override
    public List<BasCategoryVo> queryList(BasCategoryBo bo) {
        LambdaQueryWrapper<BasCategory> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<BasCategory> buildQueryWrapper(BasCategoryBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<BasCategory> lqw = Wrappers.lambdaQuery();
        lqw.orderByAsc(BasCategory::getCategoryId);
        lqw.eq(bo.getParentId() != null, BasCategory::getParentId, bo.getParentId());
        lqw.eq(StringUtils.isNotBlank(bo.getAncestors()), BasCategory::getAncestors, bo.getAncestors());
        lqw.like(StringUtils.isNotBlank(bo.getCategoryName()), BasCategory::getCategoryName, bo.getCategoryName());
        lqw.eq(StringUtils.isNotBlank(bo.getCategoryCode()), BasCategory::getCategoryCode, bo.getCategoryCode());
        lqw.eq(bo.getOrderNum() != null, BasCategory::getOrderNum, bo.getOrderNum());
        lqw.eq(bo.getLevel() != null, BasCategory::getLevel, bo.getLevel());
        lqw.eq(StringUtils.isNotBlank(bo.getStatus()), BasCategory::getStatus, bo.getStatus());
        return lqw;
    }

    /**
     * 新增SKU分类
     *
     * @param bo SKU分类
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(BasCategoryBo bo) {
        BasCategory add = MapstructUtils.convert(bo, BasCategory.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setCategoryId(add.getCategoryId());
        }
        return flag;
    }

    /**
     * 修改SKU分类
     *
     * @param bo SKU分类
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(BasCategoryBo bo) {
        BasCategory update = MapstructUtils.convert(bo, BasCategory.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(BasCategory entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 校验并批量删除SKU分类信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteByIds(ids) > 0;
    }
}
