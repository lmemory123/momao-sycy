package com.momao.wms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.momao.common.core.utils.MapstructUtils;
import com.momao.common.core.utils.StringUtils;
import com.momao.common.mybatis.core.page.PageQuery;
import com.momao.common.mybatis.core.page.TableDataInfo;
import com.momao.wms.domain.BasSku;
import com.momao.wms.domain.bo.BasSkuBo;
import com.momao.wms.domain.vo.BasSkuVo;
import com.momao.wms.mapper.BasSkuMapper;
import com.momao.wms.service.IBasSkuService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * SKU信息Service业务层处理
 *
 * @author Mo mao
 * @date 2025-04-11
 */
@RequiredArgsConstructor
@Service
public class BasSkuServiceImpl implements IBasSkuService {

    private final BasSkuMapper baseMapper;

    /**
     * 查询SKU信息
     *
     * @param skuId 主键
     * @return SKU信息
     */
    @Override
    public BasSkuVo queryById(Long skuId){
        return baseMapper.selectVoById(skuId);
    }

    /**
     * 分页查询SKU信息列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return SKU信息分页列表
     */
    @Override
    public TableDataInfo<BasSkuVo> queryPageList(BasSkuBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<BasSku> lqw = buildQueryWrapper(bo);
        Page<BasSkuVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的SKU信息列表
     *
     * @param bo 查询条件
     * @return SKU信息列表
     */
    @Override
    public List<BasSkuVo> queryList(BasSkuBo bo) {
        LambdaQueryWrapper<BasSku> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<BasSku> buildQueryWrapper(BasSkuBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<BasSku> lqw = Wrappers.lambdaQuery();
        lqw.orderByAsc(BasSku::getSkuId);
        lqw.eq(bo.getCategoryId() != null, BasSku::getCategoryId, bo.getCategoryId());
        lqw.like(StringUtils.isNotBlank(bo.getSkuName()), BasSku::getSkuName, bo.getSkuName());
        lqw.eq(StringUtils.isNotBlank(bo.getSkuCode()), BasSku::getSkuCode, bo.getSkuCode());
        lqw.eq(StringUtils.isNotBlank(bo.getBarCode()), BasSku::getBarCode, bo.getBarCode());
        lqw.eq(bo.getUnitId() != null, BasSku::getUnitId, bo.getUnitId());
        lqw.eq(bo.getWeight() != null, BasSku::getWeight, bo.getWeight());
        lqw.eq(bo.getVolume() != null, BasSku::getVolume, bo.getVolume());
        lqw.eq(bo.getShelfLife() != null, BasSku::getShelfLife, bo.getShelfLife());
        lqw.eq(bo.getMinStock() != null, BasSku::getMinStock, bo.getMinStock());
        lqw.eq(bo.getMaxStock() != null, BasSku::getMaxStock, bo.getMaxStock());
        lqw.eq(StringUtils.isNotBlank(bo.getSpecs()), BasSku::getSpecs, bo.getSpecs());
        lqw.eq(StringUtils.isNotBlank(bo.getBatchControl()), BasSku::getBatchControl, bo.getBatchControl());
        lqw.eq(StringUtils.isNotBlank(bo.getStatus()), BasSku::getStatus, bo.getStatus());
        lqw.eq(bo.getOutStrategyDays() != null, BasSku::getOutStrategyDays, bo.getOutStrategyDays());
        lqw.eq(bo.getInStrategyDays() != null, BasSku::getInStrategyDays, bo.getInStrategyDays());
        return lqw;
    }

    /**
     * 新增SKU信息
     *
     * @param bo SKU信息
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(BasSkuBo bo) {
        BasSku add = MapstructUtils.convert(bo, BasSku.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setSkuId(add.getSkuId());
        }
        return flag;
    }

    /**
     * 修改SKU信息
     *
     * @param bo SKU信息
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(BasSkuBo bo) {
        BasSku update = MapstructUtils.convert(bo, BasSku.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(BasSku entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 校验并批量删除SKU信息信息
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
