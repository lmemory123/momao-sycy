package com.momao.wms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.momao.common.core.utils.MapstructUtils;
import com.momao.common.core.utils.StringUtils;
import com.momao.common.mybatis.core.page.PageQuery;
import com.momao.common.mybatis.core.page.TableDataInfo;
import com.momao.wms.domain.WmsInOrderDetail;
import com.momao.wms.domain.bo.WmsInOrderDetailBo;
import com.momao.wms.domain.vo.WmsInOrderDetailVo;
import com.momao.wms.mapper.WmsInOrderDetailMapper;
import com.momao.wms.service.IWmsInOrderDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 入库单详情Service业务层处理
 *
 * @author Mo mao
 * @date 2025-04-11
 */
@RequiredArgsConstructor
@Service
public class WmsInOrderDetailServiceImpl implements IWmsInOrderDetailService {

    private final WmsInOrderDetailMapper baseMapper;

    /**
     * 查询入库单详情
     *
     * @param detailId 主键
     * @return 入库单详情
     */
    @Override
    public WmsInOrderDetailVo queryById(Long detailId){
        return baseMapper.selectVoById(detailId);
    }

    /**
     * 分页查询入库单详情列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 入库单详情分页列表
     */
    @Override
    public TableDataInfo<WmsInOrderDetailVo> queryPageList(WmsInOrderDetailBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<WmsInOrderDetail> lqw = buildQueryWrapper(bo);
        Page<WmsInOrderDetailVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的入库单详情列表
     *
     * @param bo 查询条件
     * @return 入库单详情列表
     */
    @Override
    public List<WmsInOrderDetailVo> queryList(WmsInOrderDetailBo bo) {
        LambdaQueryWrapper<WmsInOrderDetail> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<WmsInOrderDetail> buildQueryWrapper(WmsInOrderDetailBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<WmsInOrderDetail> lqw = Wrappers.lambdaQuery();
        lqw.orderByAsc(WmsInOrderDetail::getDetailId);
        lqw.eq(bo.getOrderId() != null, WmsInOrderDetail::getOrderId, bo.getOrderId());
        lqw.eq(bo.getSkuId() != null, WmsInOrderDetail::getSkuId, bo.getSkuId());
        lqw.eq(bo.getPlanQuantity() != null, WmsInOrderDetail::getPlanQuantity, bo.getPlanQuantity());
        lqw.eq(bo.getRealQuantity() != null, WmsInOrderDetail::getRealQuantity, bo.getRealQuantity());
        lqw.eq(bo.getLocationId() != null, WmsInOrderDetail::getLocationId, bo.getLocationId());
        lqw.eq(StringUtils.isNotBlank(bo.getBatchNo()), WmsInOrderDetail::getBatchNo, bo.getBatchNo());
        lqw.eq(bo.getProduceDate() != null, WmsInOrderDetail::getProduceDate, bo.getProduceDate());
        lqw.eq(bo.getExpireDate() != null, WmsInOrderDetail::getExpireDate, bo.getExpireDate());
        lqw.eq(bo.getSupplierId() != null, WmsInOrderDetail::getSupplierId, bo.getSupplierId());
        lqw.eq(StringUtils.isNotBlank(bo.getStatus()), WmsInOrderDetail::getStatus, bo.getStatus());
        return lqw;
    }

    /**
     * 新增入库单详情
     *
     * @param bo 入库单详情
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(WmsInOrderDetailBo bo) {
        WmsInOrderDetail add = MapstructUtils.convert(bo, WmsInOrderDetail.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setDetailId(add.getDetailId());
        }
        return flag;
    }

    /**
     * 修改入库单详情
     *
     * @param bo 入库单详情
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(WmsInOrderDetailBo bo) {
        WmsInOrderDetail update = MapstructUtils.convert(bo, WmsInOrderDetail.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(WmsInOrderDetail entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 校验并批量删除入库单详情信息
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
