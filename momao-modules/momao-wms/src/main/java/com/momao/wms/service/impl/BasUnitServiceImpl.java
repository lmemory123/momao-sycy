package com.momao.wms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.momao.common.core.utils.MapstructUtils;
import com.momao.common.core.utils.StringUtils;
import com.momao.common.mybatis.core.page.PageQuery;
import com.momao.common.mybatis.core.page.TableDataInfo;
import com.momao.wms.domain.BasUnit;
import com.momao.wms.domain.bo.BasUnitBo;
import com.momao.wms.domain.vo.BasUnitVo;
import com.momao.wms.mapper.BasUnitMapper;
import com.momao.wms.service.IBasUnitService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 计量单位Service业务层处理
 *
 * @author Mo mao
 * @date 2025-04-11
 */
@RequiredArgsConstructor
@Service
public class BasUnitServiceImpl implements IBasUnitService {

    private final BasUnitMapper baseMapper;

    /**
     * 查询计量单位
     *
     * @param unitId 主键
     * @return 计量单位
     */
    @Override
    public BasUnitVo queryById(Long unitId){
        return baseMapper.selectVoById(unitId);
    }

    /**
     * 分页查询计量单位列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 计量单位分页列表
     */
    @Override
    public TableDataInfo<BasUnitVo> queryPageList(BasUnitBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<BasUnit> lqw = buildQueryWrapper(bo);
        Page<BasUnitVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的计量单位列表
     *
     * @param bo 查询条件
     * @return 计量单位列表
     */
    @Override
    public List<BasUnitVo> queryList(BasUnitBo bo) {
        LambdaQueryWrapper<BasUnit> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<BasUnit> buildQueryWrapper(BasUnitBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<BasUnit> lqw = Wrappers.lambdaQuery();
        lqw.orderByAsc(BasUnit::getUnitId);
        lqw.like(StringUtils.isNotBlank(bo.getUnitName()), BasUnit::getUnitName, bo.getUnitName());
        lqw.eq(StringUtils.isNotBlank(bo.getUnitCode()), BasUnit::getUnitCode, bo.getUnitCode());
        lqw.eq(bo.getOrderNum() != null, BasUnit::getOrderNum, bo.getOrderNum());
        lqw.eq(StringUtils.isNotBlank(bo.getStatus()), BasUnit::getStatus, bo.getStatus());
        return lqw;
    }

    /**
     * 新增计量单位
     *
     * @param bo 计量单位
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(BasUnitBo bo) {
        BasUnit add = MapstructUtils.convert(bo, BasUnit.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setUnitId(add.getUnitId());
        }
        return flag;
    }

    /**
     * 修改计量单位
     *
     * @param bo 计量单位
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(BasUnitBo bo) {
        BasUnit update = MapstructUtils.convert(bo, BasUnit.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(BasUnit entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 校验并批量删除计量单位信息
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
