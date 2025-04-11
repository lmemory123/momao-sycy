package com.momao.wms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.momao.common.core.utils.MapstructUtils;
import com.momao.common.core.utils.StringUtils;
import com.momao.common.mybatis.core.page.PageQuery;
import com.momao.common.mybatis.core.page.TableDataInfo;
import com.momao.wms.domain.WmsStock;
import com.momao.wms.domain.bo.WmsStockBo;
import com.momao.wms.domain.vo.WmsStockVo;
import com.momao.wms.mapper.WmsStockMapper;
import com.momao.wms.service.IWmsStockService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 库存信息Service业务层处理
 *
 * @author Mo mao
 * @date 2025-04-11
 */
@RequiredArgsConstructor
@Service
public class WmsStockServiceImpl implements IWmsStockService {

    private final WmsStockMapper baseMapper;

    /**
     * 查询库存信息
     *
     * @param stockId 主键
     * @return 库存信息
     */
    @Override
    public WmsStockVo queryById(Long stockId){
        return baseMapper.selectVoById(stockId);
    }

    /**
     * 分页查询库存信息列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 库存信息分页列表
     */
    @Override
    public TableDataInfo<WmsStockVo> queryPageList(WmsStockBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<WmsStock> lqw = buildQueryWrapper(bo);
        Page<WmsStockVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的库存信息列表
     *
     * @param bo 查询条件
     * @return 库存信息列表
     */
    @Override
    public List<WmsStockVo> queryList(WmsStockBo bo) {
        LambdaQueryWrapper<WmsStock> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<WmsStock> buildQueryWrapper(WmsStockBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<WmsStock> lqw = Wrappers.lambdaQuery();
        lqw.orderByAsc(WmsStock::getStockId);
        lqw.eq(bo.getWarehouseId() != null, WmsStock::getWarehouseId, bo.getWarehouseId());
        lqw.eq(bo.getAreaId() != null, WmsStock::getAreaId, bo.getAreaId());
        lqw.eq(bo.getLocationId() != null, WmsStock::getLocationId, bo.getLocationId());
        lqw.eq(bo.getSkuId() != null, WmsStock::getSkuId, bo.getSkuId());
        lqw.eq(bo.getStockQuantity() != null, WmsStock::getStockQuantity, bo.getStockQuantity());
        lqw.eq(bo.getLockedQuantity() != null, WmsStock::getLockedQuantity, bo.getLockedQuantity());
        lqw.eq(bo.getAvailableQuantity() != null, WmsStock::getAvailableQuantity, bo.getAvailableQuantity());
        lqw.eq(StringUtils.isNotBlank(bo.getStatus()), WmsStock::getStatus, bo.getStatus());
        return lqw;
    }

    /**
     * 新增库存信息
     *
     * @param bo 库存信息
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(WmsStockBo bo) {
        WmsStock add = MapstructUtils.convert(bo, WmsStock.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setStockId(add.getStockId());
        }
        return flag;
    }

    /**
     * 修改库存信息
     *
     * @param bo 库存信息
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(WmsStockBo bo) {
        WmsStock update = MapstructUtils.convert(bo, WmsStock.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(WmsStock entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 校验并批量删除库存信息信息
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
