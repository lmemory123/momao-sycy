package com.momao.wms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.momao.common.core.utils.MapstructUtils;
import com.momao.common.core.utils.StringUtils;
import com.momao.common.mybatis.core.page.PageQuery;
import com.momao.common.mybatis.core.page.TableDataInfo;
import com.momao.wms.domain.WmsCheckDetail;
import com.momao.wms.domain.bo.WmsCheckDetailBo;
import com.momao.wms.domain.vo.WmsCheckDetailVo;
import com.momao.wms.mapper.WmsCheckDetailMapper;
import com.momao.wms.service.IWmsCheckDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 盘点单详情Service业务层处理
 *
 * @author Mo mao
 * @date 2025-04-11
 */
@RequiredArgsConstructor
@Service
public class WmsCheckDetailServiceImpl implements IWmsCheckDetailService {

    private final WmsCheckDetailMapper baseMapper;

    /**
     * 查询盘点单详情
     *
     * @param detailId 主键
     * @return 盘点单详情
     */
    @Override
    public WmsCheckDetailVo queryById(Long detailId){
        return baseMapper.selectVoById(detailId);
    }

    /**
     * 分页查询盘点单详情列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 盘点单详情分页列表
     */
    @Override
    public TableDataInfo<WmsCheckDetailVo> queryPageList(WmsCheckDetailBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<WmsCheckDetail> lqw = buildQueryWrapper(bo);
        Page<WmsCheckDetailVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的盘点单详情列表
     *
     * @param bo 查询条件
     * @return 盘点单详情列表
     */
    @Override
    public List<WmsCheckDetailVo> queryList(WmsCheckDetailBo bo) {
        LambdaQueryWrapper<WmsCheckDetail> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<WmsCheckDetail> buildQueryWrapper(WmsCheckDetailBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<WmsCheckDetail> lqw = Wrappers.lambdaQuery();
        lqw.orderByAsc(WmsCheckDetail::getDetailId);
        lqw.eq(bo.getOrderId() != null, WmsCheckDetail::getOrderId, bo.getOrderId());
        lqw.eq(bo.getSkuId() != null, WmsCheckDetail::getSkuId, bo.getSkuId());
        lqw.eq(bo.getLocationId() != null, WmsCheckDetail::getLocationId, bo.getLocationId());
        lqw.eq(bo.getStockQuantity() != null, WmsCheckDetail::getStockQuantity, bo.getStockQuantity());
        lqw.eq(bo.getCheckQuantity() != null, WmsCheckDetail::getCheckQuantity, bo.getCheckQuantity());
        lqw.eq(bo.getDiffQuantity() != null, WmsCheckDetail::getDiffQuantity, bo.getDiffQuantity());
        lqw.eq(StringUtils.isNotBlank(bo.getBatchNo()), WmsCheckDetail::getBatchNo, bo.getBatchNo());
        lqw.eq(bo.getProduceDate() != null, WmsCheckDetail::getProduceDate, bo.getProduceDate());
        lqw.eq(bo.getExpireDate() != null, WmsCheckDetail::getExpireDate, bo.getExpireDate());
        lqw.eq(bo.getSupplierId() != null, WmsCheckDetail::getSupplierId, bo.getSupplierId());
        lqw.eq(StringUtils.isNotBlank(bo.getStatus()), WmsCheckDetail::getStatus, bo.getStatus());
        return lqw;
    }

    /**
     * 新增盘点单详情
     *
     * @param bo 盘点单详情
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(WmsCheckDetailBo bo) {
        WmsCheckDetail add = MapstructUtils.convert(bo, WmsCheckDetail.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setDetailId(add.getDetailId());
        }
        return flag;
    }

    /**
     * 修改盘点单详情
     *
     * @param bo 盘点单详情
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(WmsCheckDetailBo bo) {
        WmsCheckDetail update = MapstructUtils.convert(bo, WmsCheckDetail.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(WmsCheckDetail entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 校验并批量删除盘点单详情信息
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
