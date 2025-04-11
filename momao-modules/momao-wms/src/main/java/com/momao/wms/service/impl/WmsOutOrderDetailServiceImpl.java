package com.momao.wms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.momao.common.core.utils.MapstructUtils;
import com.momao.common.core.utils.StringUtils;
import com.momao.common.mybatis.core.page.PageQuery;
import com.momao.common.mybatis.core.page.TableDataInfo;
import com.momao.wms.domain.WmsOutOrderDetail;
import com.momao.wms.domain.bo.WmsOutOrderDetailBo;
import com.momao.wms.domain.vo.WmsOutOrderDetailVo;
import com.momao.wms.mapper.WmsOutOrderDetailMapper;
import com.momao.wms.service.IWmsOutOrderDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 出库单详情Service业务层处理
 *
 * @author Mo mao
 * @date 2025-04-11
 */
@RequiredArgsConstructor
@Service
public class WmsOutOrderDetailServiceImpl implements IWmsOutOrderDetailService {

    private final WmsOutOrderDetailMapper baseMapper;

    /**
     * 查询出库单详情
     *
     * @param detailId 主键
     * @return 出库单详情
     */
    @Override
    public WmsOutOrderDetailVo queryById(Long detailId){
        return baseMapper.selectVoById(detailId);
    }

    /**
     * 分页查询出库单详情列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 出库单详情分页列表
     */
    @Override
    public TableDataInfo<WmsOutOrderDetailVo> queryPageList(WmsOutOrderDetailBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<WmsOutOrderDetail> lqw = buildQueryWrapper(bo);
        Page<WmsOutOrderDetailVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的出库单详情列表
     *
     * @param bo 查询条件
     * @return 出库单详情列表
     */
    @Override
    public List<WmsOutOrderDetailVo> queryList(WmsOutOrderDetailBo bo) {
        LambdaQueryWrapper<WmsOutOrderDetail> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<WmsOutOrderDetail> buildQueryWrapper(WmsOutOrderDetailBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<WmsOutOrderDetail> lqw = Wrappers.lambdaQuery();
        lqw.orderByAsc(WmsOutOrderDetail::getDetailId);
        lqw.eq(bo.getOrderId() != null, WmsOutOrderDetail::getOrderId, bo.getOrderId());
        lqw.eq(bo.getSkuId() != null, WmsOutOrderDetail::getSkuId, bo.getSkuId());
        lqw.eq(bo.getPlanQuantity() != null, WmsOutOrderDetail::getPlanQuantity, bo.getPlanQuantity());
        lqw.eq(bo.getRealQuantity() != null, WmsOutOrderDetail::getRealQuantity, bo.getRealQuantity());
        lqw.eq(bo.getLocationId() != null, WmsOutOrderDetail::getLocationId, bo.getLocationId());
        lqw.eq(StringUtils.isNotBlank(bo.getBatchNo()), WmsOutOrderDetail::getBatchNo, bo.getBatchNo());
        lqw.eq(bo.getProduceDate() != null, WmsOutOrderDetail::getProduceDate, bo.getProduceDate());
        lqw.eq(bo.getExpireDate() != null, WmsOutOrderDetail::getExpireDate, bo.getExpireDate());
        lqw.eq(bo.getSupplierId() != null, WmsOutOrderDetail::getSupplierId, bo.getSupplierId());
        lqw.eq(StringUtils.isNotBlank(bo.getStatus()), WmsOutOrderDetail::getStatus, bo.getStatus());
        return lqw;
    }

    /**
     * 新增出库单详情
     *
     * @param bo 出库单详情
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(WmsOutOrderDetailBo bo) {
        WmsOutOrderDetail add = MapstructUtils.convert(bo, WmsOutOrderDetail.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setDetailId(add.getDetailId());
        }
        return flag;
    }

    /**
     * 修改出库单详情
     *
     * @param bo 出库单详情
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(WmsOutOrderDetailBo bo) {
        WmsOutOrderDetail update = MapstructUtils.convert(bo, WmsOutOrderDetail.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(WmsOutOrderDetail entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 校验并批量删除出库单详情信息
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
