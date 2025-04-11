package com.momao.wms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.momao.common.core.utils.MapstructUtils;
import com.momao.common.core.utils.StringUtils;
import com.momao.common.mybatis.core.page.PageQuery;
import com.momao.common.mybatis.core.page.TableDataInfo;
import com.momao.wms.domain.BasSupplier;
import com.momao.wms.domain.bo.BasSupplierBo;
import com.momao.wms.domain.vo.BasSupplierVo;
import com.momao.wms.mapper.BasSupplierMapper;
import com.momao.wms.service.IBasSupplierService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 供应商信息Service业务层处理
 *
 * @author Mo mao
 * @date 2025-04-11
 */
@RequiredArgsConstructor
@Service
public class BasSupplierServiceImpl implements IBasSupplierService {

    private final BasSupplierMapper baseMapper;

    /**
     * 查询供应商信息
     *
     * @param supplierId 主键
     * @return 供应商信息
     */
    @Override
    public BasSupplierVo queryById(Long supplierId){
        return baseMapper.selectVoById(supplierId);
    }

    /**
     * 分页查询供应商信息列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 供应商信息分页列表
     */
    @Override
    public TableDataInfo<BasSupplierVo> queryPageList(BasSupplierBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<BasSupplier> lqw = buildQueryWrapper(bo);
        Page<BasSupplierVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的供应商信息列表
     *
     * @param bo 查询条件
     * @return 供应商信息列表
     */
    @Override
    public List<BasSupplierVo> queryList(BasSupplierBo bo) {
        LambdaQueryWrapper<BasSupplier> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<BasSupplier> buildQueryWrapper(BasSupplierBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<BasSupplier> lqw = Wrappers.lambdaQuery();
        lqw.orderByAsc(BasSupplier::getSupplierId);
        lqw.like(StringUtils.isNotBlank(bo.getSupplierName()), BasSupplier::getSupplierName, bo.getSupplierName());
        lqw.eq(StringUtils.isNotBlank(bo.getSupplierCode()), BasSupplier::getSupplierCode, bo.getSupplierCode());
        lqw.eq(StringUtils.isNotBlank(bo.getContact()), BasSupplier::getContact, bo.getContact());
        lqw.eq(StringUtils.isNotBlank(bo.getPhone()), BasSupplier::getPhone, bo.getPhone());
        lqw.eq(StringUtils.isNotBlank(bo.getEmail()), BasSupplier::getEmail, bo.getEmail());
        lqw.eq(StringUtils.isNotBlank(bo.getAddress()), BasSupplier::getAddress, bo.getAddress());
        lqw.eq(StringUtils.isNotBlank(bo.getStatus()), BasSupplier::getStatus, bo.getStatus());
        return lqw;
    }

    /**
     * 新增供应商信息
     *
     * @param bo 供应商信息
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(BasSupplierBo bo) {
        BasSupplier add = MapstructUtils.convert(bo, BasSupplier.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setSupplierId(add.getSupplierId());
        }
        return flag;
    }

    /**
     * 修改供应商信息
     *
     * @param bo 供应商信息
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(BasSupplierBo bo) {
        BasSupplier update = MapstructUtils.convert(bo, BasSupplier.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(BasSupplier entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 校验并批量删除供应商信息信息
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
