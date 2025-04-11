package com.momao.wms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.momao.common.core.utils.MapstructUtils;
import com.momao.common.core.utils.StringUtils;
import com.momao.common.mybatis.core.page.PageQuery;
import com.momao.common.mybatis.core.page.TableDataInfo;
import com.momao.wms.domain.WmsOutOrder;
import com.momao.wms.domain.bo.WmsOutOrderBo;
import com.momao.wms.domain.vo.WmsOutOrderVo;
import com.momao.wms.mapper.WmsOutOrderMapper;
import com.momao.wms.service.IWmsOutOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 出库单Service业务层处理
 *
 * @author Mo mao
 * @date 2025-04-11
 */
@RequiredArgsConstructor
@Service
public class WmsOutOrderServiceImpl implements IWmsOutOrderService {

    private final WmsOutOrderMapper baseMapper;

    /**
     * 查询出库单
     *
     * @param orderId 主键
     * @return 出库单
     */
    @Override
    public WmsOutOrderVo queryById(Long orderId){
        return baseMapper.selectVoById(orderId);
    }

    /**
     * 分页查询出库单列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 出库单分页列表
     */
    @Override
    public TableDataInfo<WmsOutOrderVo> queryPageList(WmsOutOrderBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<WmsOutOrder> lqw = buildQueryWrapper(bo);
        Page<WmsOutOrderVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的出库单列表
     *
     * @param bo 查询条件
     * @return 出库单列表
     */
    @Override
    public List<WmsOutOrderVo> queryList(WmsOutOrderBo bo) {
        LambdaQueryWrapper<WmsOutOrder> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<WmsOutOrder> buildQueryWrapper(WmsOutOrderBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<WmsOutOrder> lqw = Wrappers.lambdaQuery();
        lqw.orderByAsc(WmsOutOrder::getOrderId);
        lqw.eq(StringUtils.isNotBlank(bo.getOrderNo()), WmsOutOrder::getOrderNo, bo.getOrderNo());
        lqw.eq(StringUtils.isNotBlank(bo.getOrderType()), WmsOutOrder::getOrderType, bo.getOrderType());
        lqw.eq(bo.getWarehouseId() != null, WmsOutOrder::getWarehouseId, bo.getWarehouseId());
        lqw.eq(bo.getCustomerId() != null, WmsOutOrder::getCustomerId, bo.getCustomerId());
        lqw.eq(bo.getOrderDate() != null, WmsOutOrder::getOrderDate, bo.getOrderDate());
        lqw.eq(StringUtils.isNotBlank(bo.getStatus()), WmsOutOrder::getStatus, bo.getStatus());
        return lqw;
    }

    /**
     * 新增出库单
     *
     * @param bo 出库单
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(WmsOutOrderBo bo) {
        WmsOutOrder add = MapstructUtils.convert(bo, WmsOutOrder.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setOrderId(add.getOrderId());
        }
        return flag;
    }

    /**
     * 修改出库单
     *
     * @param bo 出库单
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(WmsOutOrderBo bo) {
        WmsOutOrder update = MapstructUtils.convert(bo, WmsOutOrder.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(WmsOutOrder entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 校验并批量删除出库单信息
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
