package com.momao.wms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.momao.common.core.utils.MapstructUtils;
import com.momao.common.core.utils.StringUtils;
import com.momao.common.mybatis.core.page.PageQuery;
import com.momao.common.mybatis.core.page.TableDataInfo;
import com.momao.wms.domain.WmsStockLog;
import com.momao.wms.domain.bo.WmsStockLogBo;
import com.momao.wms.domain.vo.WmsStockLogVo;
import com.momao.wms.mapper.WmsStockLogMapper;
import com.momao.wms.service.IWmsStockLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 库存操作日志Service业务层处理
 *
 * @author Mo mao
 * @date 2025-04-11
 */
@RequiredArgsConstructor
@Service
public class WmsStockLogServiceImpl implements IWmsStockLogService {

    private final WmsStockLogMapper baseMapper;

    /**
     * 查询库存操作日志
     *
     * @param logId 主键
     * @return 库存操作日志
     */
    @Override
    public WmsStockLogVo queryById(Long logId){
        return baseMapper.selectVoById(logId);
    }

    /**
     * 分页查询库存操作日志列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 库存操作日志分页列表
     */
    @Override
    public TableDataInfo<WmsStockLogVo> queryPageList(WmsStockLogBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<WmsStockLog> lqw = buildQueryWrapper(bo);
        Page<WmsStockLogVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的库存操作日志列表
     *
     * @param bo 查询条件
     * @return 库存操作日志列表
     */
    @Override
    public List<WmsStockLogVo> queryList(WmsStockLogBo bo) {
        LambdaQueryWrapper<WmsStockLog> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<WmsStockLog> buildQueryWrapper(WmsStockLogBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<WmsStockLog> lqw = Wrappers.lambdaQuery();
        lqw.orderByAsc(WmsStockLog::getLogId);
        lqw.eq(bo.getStockId() != null, WmsStockLog::getStockId, bo.getStockId());
        lqw.eq(bo.getWarehouseId() != null, WmsStockLog::getWarehouseId, bo.getWarehouseId());
        lqw.eq(bo.getLocationId() != null, WmsStockLog::getLocationId, bo.getLocationId());
        lqw.eq(bo.getSkuId() != null, WmsStockLog::getSkuId, bo.getSkuId());
        lqw.eq(bo.getQuantityBefore() != null, WmsStockLog::getQuantityBefore, bo.getQuantityBefore());
        lqw.eq(bo.getQuantityAfter() != null, WmsStockLog::getQuantityAfter, bo.getQuantityAfter());
        lqw.eq(bo.getQuantityChange() != null, WmsStockLog::getQuantityChange, bo.getQuantityChange());
        lqw.eq(StringUtils.isNotBlank(bo.getOperateType()), WmsStockLog::getOperateType, bo.getOperateType());
        lqw.eq(bo.getOperateId() != null, WmsStockLog::getOperateId, bo.getOperateId());
        return lqw;
    }

    /**
     * 新增库存操作日志
     *
     * @param bo 库存操作日志
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(WmsStockLogBo bo) {
        WmsStockLog add = MapstructUtils.convert(bo, WmsStockLog.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setLogId(add.getLogId());
        }
        return flag;
    }

    /**
     * 修改库存操作日志
     *
     * @param bo 库存操作日志
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(WmsStockLogBo bo) {
        WmsStockLog update = MapstructUtils.convert(bo, WmsStockLog.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(WmsStockLog entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 校验并批量删除库存操作日志信息
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
