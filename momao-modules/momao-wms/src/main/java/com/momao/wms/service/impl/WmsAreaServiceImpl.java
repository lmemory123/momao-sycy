package com.momao.wms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.momao.common.core.utils.MapstructUtils;
import com.momao.common.core.utils.StringUtils;
import com.momao.common.mybatis.core.page.PageQuery;
import com.momao.common.mybatis.core.page.TableDataInfo;
import com.momao.wms.domain.WmsArea;
import com.momao.wms.domain.bo.WmsAreaBo;
import com.momao.wms.domain.vo.WmsAreaVo;
import com.momao.wms.mapper.WmsAreaMapper;
import com.momao.wms.service.IWmsAreaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 库区信息Service业务层处理
 *
 * @author Mo mao
 * @date 2025-04-11
 */
@RequiredArgsConstructor
@Service
public class WmsAreaServiceImpl implements IWmsAreaService {

    private final WmsAreaMapper baseMapper;

    /**
     * 查询库区信息
     *
     * @param areaId 主键
     * @return 库区信息
     */
    @Override
    public WmsAreaVo queryById(Long areaId){
        return baseMapper.selectVoById(areaId);
    }

    /**
     * 分页查询库区信息列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 库区信息分页列表
     */
    @Override
    public TableDataInfo<WmsAreaVo> queryPageList(WmsAreaBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<WmsArea> lqw = buildQueryWrapper(bo);
        Page<WmsAreaVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的库区信息列表
     *
     * @param bo 查询条件
     * @return 库区信息列表
     */
    @Override
    public List<WmsAreaVo> queryList(WmsAreaBo bo) {
        LambdaQueryWrapper<WmsArea> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<WmsArea> buildQueryWrapper(WmsAreaBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<WmsArea> lqw = Wrappers.lambdaQuery();
        lqw.orderByAsc(WmsArea::getAreaId);
        lqw.eq(bo.getWarehouseId() != null, WmsArea::getWarehouseId, bo.getWarehouseId());
        lqw.like(StringUtils.isNotBlank(bo.getAreaName()), WmsArea::getAreaName, bo.getAreaName());
        lqw.eq(StringUtils.isNotBlank(bo.getAreaCode()), WmsArea::getAreaCode, bo.getAreaCode());
        lqw.eq(StringUtils.isNotBlank(bo.getStatus()), WmsArea::getStatus, bo.getStatus());
        return lqw;
    }

    /**
     * 新增库区信息
     *
     * @param bo 库区信息
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(WmsAreaBo bo) {
        WmsArea add = MapstructUtils.convert(bo, WmsArea.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setAreaId(add.getAreaId());
        }
        return flag;
    }

    /**
     * 修改库区信息
     *
     * @param bo 库区信息
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(WmsAreaBo bo) {
        WmsArea update = MapstructUtils.convert(bo, WmsArea.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(WmsArea entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 校验并批量删除库区信息信息
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
