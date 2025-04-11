package com.momao.wms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.momao.common.core.utils.MapstructUtils;
import com.momao.common.core.utils.StringUtils;
import com.momao.common.mybatis.core.page.PageQuery;
import com.momao.common.mybatis.core.page.TableDataInfo;
import com.momao.wms.domain.BasSkuHistoryLog;
import com.momao.wms.domain.bo.BasSkuHistoryLogBo;
import com.momao.wms.domain.vo.BasSkuHistoryLogVo;
import com.momao.wms.mapper.BasSkuHistoryLogMapper;
import com.momao.wms.service.IBasSkuHistoryLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * SKU信息历史变更Service业务层处理
 *
 * @author Mo mao
 * @date 2025-04-11
 */
@RequiredArgsConstructor
@Service
public class BasSkuHistoryLogServiceImpl implements IBasSkuHistoryLogService {

    private final BasSkuHistoryLogMapper baseMapper;

    /**
     * 查询SKU信息历史变更
     *
     * @param logId 主键
     * @return SKU信息历史变更
     */
    @Override
    public BasSkuHistoryLogVo queryById(Long logId){
        return baseMapper.selectVoById(logId);
    }

    /**
     * 分页查询SKU信息历史变更列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return SKU信息历史变更分页列表
     */
    @Override
    public TableDataInfo<BasSkuHistoryLogVo> queryPageList(BasSkuHistoryLogBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<BasSkuHistoryLog> lqw = buildQueryWrapper(bo);
        Page<BasSkuHistoryLogVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的SKU信息历史变更列表
     *
     * @param bo 查询条件
     * @return SKU信息历史变更列表
     */
    @Override
    public List<BasSkuHistoryLogVo> queryList(BasSkuHistoryLogBo bo) {
        LambdaQueryWrapper<BasSkuHistoryLog> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<BasSkuHistoryLog> buildQueryWrapper(BasSkuHistoryLogBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<BasSkuHistoryLog> lqw = Wrappers.lambdaQuery();
        lqw.orderByAsc(BasSkuHistoryLog::getLogId);
        lqw.eq(bo.getSkuId() != null, BasSkuHistoryLog::getSkuId, bo.getSkuId());
        lqw.eq(StringUtils.isNotBlank(bo.getSnapshot()), BasSkuHistoryLog::getSnapshot, bo.getSnapshot());
        lqw.eq(StringUtils.isNotBlank(bo.getOperateType()), BasSkuHistoryLog::getOperateType, bo.getOperateType());
        lqw.eq(bo.getOperateTime() != null, BasSkuHistoryLog::getOperateTime, bo.getOperateTime());
        lqw.eq(bo.getOperateBy() != null, BasSkuHistoryLog::getOperateBy, bo.getOperateBy());
        lqw.like(StringUtils.isNotBlank(bo.getOperateName()), BasSkuHistoryLog::getOperateName, bo.getOperateName());
        lqw.eq(bo.getOperateDept() != null, BasSkuHistoryLog::getOperateDept, bo.getOperateDept());
        return lqw;
    }

    /**
     * 新增SKU信息历史变更
     *
     * @param bo SKU信息历史变更
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(BasSkuHistoryLogBo bo) {
        BasSkuHistoryLog add = MapstructUtils.convert(bo, BasSkuHistoryLog.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setLogId(add.getLogId());
        }
        return flag;
    }

    /**
     * 修改SKU信息历史变更
     *
     * @param bo SKU信息历史变更
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(BasSkuHistoryLogBo bo) {
        BasSkuHistoryLog update = MapstructUtils.convert(bo, BasSkuHistoryLog.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(BasSkuHistoryLog entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 校验并批量删除SKU信息历史变更信息
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
