package com.momao.wms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.momao.common.core.utils.MapstructUtils;
import com.momao.common.core.utils.StringUtils;
import com.momao.common.mybatis.core.page.PageQuery;
import com.momao.common.mybatis.core.page.TableDataInfo;
import com.momao.wms.domain.WmsLocation;
import com.momao.wms.domain.bo.WmsLocationBo;
import com.momao.wms.domain.vo.WmsLocationVo;
import com.momao.wms.mapper.WmsLocationMapper;
import com.momao.wms.service.IWmsLocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 库位信息Service业务层处理
 *
 * @author Mo mao
 * @date 2025-04-11
 */
@RequiredArgsConstructor
@Service
public class WmsLocationServiceImpl implements IWmsLocationService {

    private final WmsLocationMapper baseMapper;

    /**
     * 查询库位信息
     *
     * @param locationId 主键
     * @return 库位信息
     */
    @Override
    public WmsLocationVo queryById(Long locationId){
        return baseMapper.selectVoById(locationId);
    }

    /**
     * 分页查询库位信息列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 库位信息分页列表
     */
    @Override
    public TableDataInfo<WmsLocationVo> queryPageList(WmsLocationBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<WmsLocation> lqw = buildQueryWrapper(bo);
        Page<WmsLocationVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的库位信息列表
     *
     * @param bo 查询条件
     * @return 库位信息列表
     */
    @Override
    public List<WmsLocationVo> queryList(WmsLocationBo bo) {
        LambdaQueryWrapper<WmsLocation> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<WmsLocation> buildQueryWrapper(WmsLocationBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<WmsLocation> lqw = Wrappers.lambdaQuery();
        lqw.orderByAsc(WmsLocation::getLocationId);
        lqw.eq(bo.getWarehouseId() != null, WmsLocation::getWarehouseId, bo.getWarehouseId());
        lqw.eq(bo.getAreaId() != null, WmsLocation::getAreaId, bo.getAreaId());
        lqw.like(StringUtils.isNotBlank(bo.getLocationName()), WmsLocation::getLocationName, bo.getLocationName());
        lqw.eq(StringUtils.isNotBlank(bo.getLocationCode()), WmsLocation::getLocationCode, bo.getLocationCode());
        lqw.eq(bo.getCapacity() != null, WmsLocation::getCapacity, bo.getCapacity());
        lqw.eq(StringUtils.isNotBlank(bo.getStatus()), WmsLocation::getStatus, bo.getStatus());
        return lqw;
    }

    /**
     * 新增库位信息
     *
     * @param bo 库位信息
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(WmsLocationBo bo) {
        WmsLocation add = MapstructUtils.convert(bo, WmsLocation.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setLocationId(add.getLocationId());
        }
        return flag;
    }

    /**
     * 修改库位信息
     *
     * @param bo 库位信息
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(WmsLocationBo bo) {
        WmsLocation update = MapstructUtils.convert(bo, WmsLocation.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(WmsLocation entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 校验并批量删除库位信息信息
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
