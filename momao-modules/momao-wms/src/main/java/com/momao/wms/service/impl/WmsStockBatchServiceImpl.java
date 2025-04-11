package com.momao.wms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.momao.common.core.utils.MapstructUtils;
import com.momao.common.core.utils.StringUtils;
import com.momao.common.mybatis.core.page.PageQuery;
import com.momao.common.mybatis.core.page.TableDataInfo;
import com.momao.wms.domain.WmsStockBatch;
import com.momao.wms.domain.bo.WmsStockBatchBo;
import com.momao.wms.domain.vo.WmsStockBatchVo;
import com.momao.wms.mapper.WmsStockBatchMapper;
import com.momao.wms.service.IWmsStockBatchService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 批次库存Service业务层处理
 *
 * @author Mo mao
 * @date 2025-04-11
 */
@RequiredArgsConstructor
@Service
public class WmsStockBatchServiceImpl implements IWmsStockBatchService {

    private final WmsStockBatchMapper baseMapper;

    /**
     * 查询批次库存
     *
     * @param batchId 主键
     * @return 批次库存
     */
    @Override
    public WmsStockBatchVo queryById(Long batchId){
        return baseMapper.selectVoById(batchId);
    }

    /**
     * 分页查询批次库存列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 批次库存分页列表
     */
    @Override
    public TableDataInfo<WmsStockBatchVo> queryPageList(WmsStockBatchBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<WmsStockBatch> lqw = buildQueryWrapper(bo);
        Page<WmsStockBatchVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的批次库存列表
     *
     * @param bo 查询条件
     * @return 批次库存列表
     */
    @Override
    public List<WmsStockBatchVo> queryList(WmsStockBatchBo bo) {
        LambdaQueryWrapper<WmsStockBatch> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<WmsStockBatch> buildQueryWrapper(WmsStockBatchBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<WmsStockBatch> lqw = Wrappers.lambdaQuery();
        lqw.orderByAsc(WmsStockBatch::getBatchId);
        lqw.eq(bo.getStockId() != null, WmsStockBatch::getStockId, bo.getStockId());
        lqw.eq(bo.getWarehouseId() != null, WmsStockBatch::getWarehouseId, bo.getWarehouseId());
        lqw.eq(bo.getAreaId() != null, WmsStockBatch::getAreaId, bo.getAreaId());
        lqw.eq(bo.getLocationId() != null, WmsStockBatch::getLocationId, bo.getLocationId());
        lqw.eq(bo.getSkuId() != null, WmsStockBatch::getSkuId, bo.getSkuId());
        lqw.eq(StringUtils.isNotBlank(bo.getBatchNo()), WmsStockBatch::getBatchNo, bo.getBatchNo());
        lqw.eq(bo.getBatchQuantity() != null, WmsStockBatch::getBatchQuantity, bo.getBatchQuantity());
        lqw.eq(bo.getLockedQuantity() != null, WmsStockBatch::getLockedQuantity, bo.getLockedQuantity());
        lqw.eq(bo.getAvailableQuantity() != null, WmsStockBatch::getAvailableQuantity, bo.getAvailableQuantity());
        lqw.eq(bo.getSupplierId() != null, WmsStockBatch::getSupplierId, bo.getSupplierId());
        lqw.eq(bo.getProduceDate() != null, WmsStockBatch::getProduceDate, bo.getProduceDate());
        lqw.eq(bo.getExpireDate() != null, WmsStockBatch::getExpireDate, bo.getExpireDate());
        lqw.eq(StringUtils.isNotBlank(bo.getStatus()), WmsStockBatch::getStatus, bo.getStatus());
        return lqw;
    }

    /**
     * 新增批次库存
     *
     * @param bo 批次库存
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(WmsStockBatchBo bo) {
        WmsStockBatch add = MapstructUtils.convert(bo, WmsStockBatch.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setBatchId(add.getBatchId());
        }
        return flag;
    }

    /**
     * 修改批次库存
     *
     * @param bo 批次库存
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(WmsStockBatchBo bo) {
        WmsStockBatch update = MapstructUtils.convert(bo, WmsStockBatch.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(WmsStockBatch entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 校验并批量删除批次库存信息
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
