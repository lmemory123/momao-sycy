package com.momao.wms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.momao.common.core.utils.MapstructUtils;
import com.momao.common.core.utils.StringUtils;
import com.momao.common.mybatis.core.page.PageQuery;
import com.momao.common.mybatis.core.page.TableDataInfo;
import com.momao.wms.domain.WmsCheckOrder;
import com.momao.wms.domain.bo.WmsCheckOrderBo;
import com.momao.wms.domain.vo.WmsCheckOrderVo;
import com.momao.wms.mapper.WmsCheckOrderMapper;
import com.momao.wms.service.IWmsCheckOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 盘点单Service业务层处理
 *
 * @author Mo mao
 * @date 2025-04-11
 */
@RequiredArgsConstructor
@Service
public class WmsCheckOrderServiceImpl implements IWmsCheckOrderService {

    private final WmsCheckOrderMapper baseMapper;

    /**
     * 查询盘点单
     *
     * @param orderId 主键
     * @return 盘点单
     */
    @Override
    public WmsCheckOrderVo queryById(Long orderId){
        return baseMapper.selectVoById(orderId);
    }

    /**
     * 分页查询盘点单列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 盘点单分页列表
     */
    @Override
    public TableDataInfo<WmsCheckOrderVo> queryPageList(WmsCheckOrderBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<WmsCheckOrder> lqw = buildQueryWrapper(bo);
        Page<WmsCheckOrderVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的盘点单列表
     *
     * @param bo 查询条件
     * @return 盘点单列表
     */
    @Override
    public List<WmsCheckOrderVo> queryList(WmsCheckOrderBo bo) {
        LambdaQueryWrapper<WmsCheckOrder> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<WmsCheckOrder> buildQueryWrapper(WmsCheckOrderBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<WmsCheckOrder> lqw = Wrappers.lambdaQuery();
        lqw.orderByAsc(WmsCheckOrder::getOrderId);
        lqw.eq(StringUtils.isNotBlank(bo.getOrderNo()), WmsCheckOrder::getOrderNo, bo.getOrderNo());
        lqw.eq(bo.getWarehouseId() != null, WmsCheckOrder::getWarehouseId, bo.getWarehouseId());
        lqw.eq(bo.getAreaId() != null, WmsCheckOrder::getAreaId, bo.getAreaId());
        lqw.eq(bo.getLocationId() != null, WmsCheckOrder::getLocationId, bo.getLocationId());
        lqw.eq(bo.getCheckDate() != null, WmsCheckOrder::getCheckDate, bo.getCheckDate());
        lqw.eq(StringUtils.isNotBlank(bo.getStatus()), WmsCheckOrder::getStatus, bo.getStatus());
        return lqw;
    }

    /**
     * 新增盘点单
     *
     * @param bo 盘点单
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(WmsCheckOrderBo bo) {
        WmsCheckOrder add = MapstructUtils.convert(bo, WmsCheckOrder.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setOrderId(add.getOrderId());
        }
        return flag;
    }

    /**
     * 修改盘点单
     *
     * @param bo 盘点单
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(WmsCheckOrderBo bo) {
        WmsCheckOrder update = MapstructUtils.convert(bo, WmsCheckOrder.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(WmsCheckOrder entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 校验并批量删除盘点单信息
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
