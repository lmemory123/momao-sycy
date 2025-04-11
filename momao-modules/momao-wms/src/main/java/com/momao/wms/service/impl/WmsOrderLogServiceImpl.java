package com.momao.wms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.momao.common.core.utils.MapstructUtils;
import com.momao.common.core.utils.StringUtils;
import com.momao.common.mybatis.core.page.PageQuery;
import com.momao.common.mybatis.core.page.TableDataInfo;
import com.momao.wms.domain.WmsOrderLog;
import com.momao.wms.domain.bo.WmsOrderLogBo;
import com.momao.wms.domain.vo.WmsOrderLogVo;
import com.momao.wms.mapper.WmsOrderLogMapper;
import com.momao.wms.service.IWmsOrderLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 单据操作日志Service业务层处理
 *
 * @author Mo mao
 * @date 2025-04-11
 */
@RequiredArgsConstructor
@Service
public class WmsOrderLogServiceImpl implements IWmsOrderLogService {

    private final WmsOrderLogMapper baseMapper;

    /**
     * 查询单据操作日志
     *
     * @param logId 主键
     * @return 单据操作日志
     */
    @Override
    public WmsOrderLogVo queryById(Long logId){
        return baseMapper.selectVoById(logId);
    }

    /**
     * 分页查询单据操作日志列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 单据操作日志分页列表
     */
    @Override
    public TableDataInfo<WmsOrderLogVo> queryPageList(WmsOrderLogBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<WmsOrderLog> lqw = buildQueryWrapper(bo);
        Page<WmsOrderLogVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的单据操作日志列表
     *
     * @param bo 查询条件
     * @return 单据操作日志列表
     */
    @Override
    public List<WmsOrderLogVo> queryList(WmsOrderLogBo bo) {
        LambdaQueryWrapper<WmsOrderLog> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<WmsOrderLog> buildQueryWrapper(WmsOrderLogBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<WmsOrderLog> lqw = Wrappers.lambdaQuery();
        lqw.orderByAsc(WmsOrderLog::getLogId);
        lqw.eq(bo.getOrderId() != null, WmsOrderLog::getOrderId, bo.getOrderId());
        lqw.eq(StringUtils.isNotBlank(bo.getOrderType()), WmsOrderLog::getOrderType, bo.getOrderType());
        lqw.eq(StringUtils.isNotBlank(bo.getOrderNo()), WmsOrderLog::getOrderNo, bo.getOrderNo());
        lqw.eq(StringUtils.isNotBlank(bo.getOperateType()), WmsOrderLog::getOperateType, bo.getOperateType());
        lqw.eq(StringUtils.isNotBlank(bo.getStatusBefore()), WmsOrderLog::getStatusBefore, bo.getStatusBefore());
        lqw.eq(StringUtils.isNotBlank(bo.getStatusAfter()), WmsOrderLog::getStatusAfter, bo.getStatusAfter());
        lqw.eq(StringUtils.isNotBlank(bo.getOperateContent()), WmsOrderLog::getOperateContent, bo.getOperateContent());
        lqw.like(StringUtils.isNotBlank(bo.getCreateName()), WmsOrderLog::getCreateName, bo.getCreateName());
        return lqw;
    }

    /**
     * 新增单据操作日志
     *
     * @param bo 单据操作日志
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(WmsOrderLogBo bo) {
        WmsOrderLog add = MapstructUtils.convert(bo, WmsOrderLog.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setLogId(add.getLogId());
        }
        return flag;
    }

    /**
     * 修改单据操作日志
     *
     * @param bo 单据操作日志
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(WmsOrderLogBo bo) {
        WmsOrderLog update = MapstructUtils.convert(bo, WmsOrderLog.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(WmsOrderLog entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 校验并批量删除单据操作日志信息
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
