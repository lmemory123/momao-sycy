package com.momao.wms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.momao.common.core.utils.MapstructUtils;
import com.momao.common.core.utils.StringUtils;
import com.momao.common.mybatis.core.page.PageQuery;
import com.momao.common.mybatis.core.page.TableDataInfo;
import com.momao.wms.domain.WmsStockBatchLog;
import com.momao.wms.domain.bo.WmsStockBatchLogBo;
import com.momao.wms.domain.vo.WmsStockBatchLogVo;
import com.momao.wms.mapper.WmsStockBatchLogMapper;
import com.momao.wms.service.IWmsStockBatchLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 批次库存操作日志Service业务层处理
 *
 * @author Mo mao
 * @date 2025-04-11
 */
@RequiredArgsConstructor
@Service
public class WmsStockBatchLogServiceImpl implements IWmsStockBatchLogService {

    private final WmsStockBatchLogMapper baseMapper;

    /**
     * 查询批次库存操作日志
     *
     * @param logId 主键
     * @return 批次库存操作日志
     */
    @Override
    public WmsStockBatchLogVo queryById(Long logId){
        return baseMapper.selectVoById(logId);
    }

    /**
     * 分页查询批次库存操作日志列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 批次库存操作日志分页列表
     */
    @Override
    public TableDataInfo<WmsStockBatchLogVo> queryPageList(WmsStockBatchLogBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<WmsStockBatchLog> lqw = buildQueryWrapper(bo);
        Page<WmsStockBatchLogVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的批次库存操作日志列表
     *
     * @param bo 查询条件
     * @return 批次库存操作日志列表
     */
    @Override
    public List<WmsStockBatchLogVo> queryList(WmsStockBatchLogBo bo) {
        LambdaQueryWrapper<WmsStockBatchLog> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<WmsStockBatchLog> buildQueryWrapper(WmsStockBatchLogBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<WmsStockBatchLog> lqw = Wrappers.lambdaQuery();
        lqw.orderByAsc(WmsStockBatchLog::getLogId);
        lqw.eq(bo.getBatchId() != null, WmsStockBatchLog::getBatchId, bo.getBatchId());
        lqw.eq(bo.getStockId() != null, WmsStockBatchLog::getStockId, bo.getStockId());
        lqw.eq(bo.getWarehouseId() != null, WmsStockBatchLog::getWarehouseId, bo.getWarehouseId());
        lqw.eq(bo.getLocationId() != null, WmsStockBatchLog::getLocationId, bo.getLocationId());
        lqw.eq(bo.getSkuId() != null, WmsStockBatchLog::getSkuId, bo.getSkuId());
        lqw.eq(StringUtils.isNotBlank(bo.getBatchNo()), WmsStockBatchLog::getBatchNo, bo.getBatchNo());
        lqw.eq(bo.getQuantityBefore() != null, WmsStockBatchLog::getQuantityBefore, bo.getQuantityBefore());
        lqw.eq(bo.getQuantityAfter() != null, WmsStockBatchLog::getQuantityAfter, bo.getQuantityAfter());
        lqw.eq(bo.getQuantityChange() != null, WmsStockBatchLog::getQuantityChange, bo.getQuantityChange());
        lqw.eq(StringUtils.isNotBlank(bo.getOperateType()), WmsStockBatchLog::getOperateType, bo.getOperateType());
        lqw.eq(bo.getOperateId() != null, WmsStockBatchLog::getOperateId, bo.getOperateId());
        return lqw;
    }

    /**
     * 新增批次库存操作日志
     *
     * @param bo 批次库存操作日志
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(WmsStockBatchLogBo bo) {
        WmsStockBatchLog add = MapstructUtils.convert(bo, WmsStockBatchLog.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setLogId(add.getLogId());
        }
        return flag;
    }

    /**
     * 修改批次库存操作日志
     *
     * @param bo 批次库存操作日志
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(WmsStockBatchLogBo bo) {
        WmsStockBatchLog update = MapstructUtils.convert(bo, WmsStockBatchLog.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(WmsStockBatchLog entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 校验并批量删除批次库存操作日志信息
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
