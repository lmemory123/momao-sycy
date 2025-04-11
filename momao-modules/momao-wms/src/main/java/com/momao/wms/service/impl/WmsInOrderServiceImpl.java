package com.momao.wms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.momao.common.core.utils.MapstructUtils;
import com.momao.common.core.utils.StringUtils;
import com.momao.common.mybatis.core.page.PageQuery;
import com.momao.common.mybatis.core.page.TableDataInfo;
import com.momao.wms.domain.WmsInOrder;
import com.momao.wms.domain.bo.WmsInOrderBo;
import com.momao.wms.domain.vo.WmsInOrderVo;
import com.momao.wms.mapper.WmsInOrderMapper;
import com.momao.wms.service.IWmsInOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 入库单Service业务层处理
 *
 * @author Mo mao
 * @date 2025-04-11
 */
@RequiredArgsConstructor
@Service
public class WmsInOrderServiceImpl implements IWmsInOrderService {

    private final WmsInOrderMapper baseMapper;

    /**
     * 查询入库单
     *
     * @param orderId 主键
     * @return 入库单
     */
    @Override
    public WmsInOrderVo queryById(Long orderId){
        return baseMapper.selectVoById(orderId);
    }

    /**
     * 分页查询入库单列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 入库单分页列表
     */
    @Override
    public TableDataInfo<WmsInOrderVo> queryPageList(WmsInOrderBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<WmsInOrder> lqw = buildQueryWrapper(bo);
        Page<WmsInOrderVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的入库单列表
     *
     * @param bo 查询条件
     * @return 入库单列表
     */
    @Override
    public List<WmsInOrderVo> queryList(WmsInOrderBo bo) {
        LambdaQueryWrapper<WmsInOrder> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<WmsInOrder> buildQueryWrapper(WmsInOrderBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<WmsInOrder> lqw = Wrappers.lambdaQuery();
        lqw.orderByAsc(WmsInOrder::getOrderId);
        lqw.eq(StringUtils.isNotBlank(bo.getOrderNo()), WmsInOrder::getOrderNo, bo.getOrderNo());
        lqw.eq(StringUtils.isNotBlank(bo.getOrderType()), WmsInOrder::getOrderType, bo.getOrderType());
        lqw.eq(bo.getWarehouseId() != null, WmsInOrder::getWarehouseId, bo.getWarehouseId());
        lqw.eq(bo.getSupplierId() != null, WmsInOrder::getSupplierId, bo.getSupplierId());
        lqw.eq(bo.getOrderDate() != null, WmsInOrder::getOrderDate, bo.getOrderDate());
        lqw.eq(StringUtils.isNotBlank(bo.getStatus()), WmsInOrder::getStatus, bo.getStatus());
        return lqw;
    }

    /**
     * 新增入库单
     *
     * @param bo 入库单
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(WmsInOrderBo bo) {
        WmsInOrder add = MapstructUtils.convert(bo, WmsInOrder.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setOrderId(add.getOrderId());
        }
        return flag;
    }

    /**
     * 修改入库单
     *
     * @param bo 入库单
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(WmsInOrderBo bo) {
        WmsInOrder update = MapstructUtils.convert(bo, WmsInOrder.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(WmsInOrder entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 校验并批量删除入库单信息
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
