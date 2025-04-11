package com.momao.wms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.momao.common.core.utils.MapstructUtils;
import com.momao.common.core.utils.StringUtils;
import com.momao.common.mybatis.core.page.PageQuery;
import com.momao.common.mybatis.core.page.TableDataInfo;
import com.momao.wms.domain.WmsWarehouse;
import com.momao.wms.domain.bo.WmsWarehouseBo;
import com.momao.wms.domain.vo.WmsWarehouseVo;
import com.momao.wms.mapper.WmsWarehouseMapper;
import com.momao.wms.service.IWmsWarehouseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 仓库信息Service业务层处理
 *
 * @author Mo mao
 * @date 2025-04-11
 */
@RequiredArgsConstructor
@Service
public class WmsWarehouseServiceImpl implements IWmsWarehouseService {

    private final WmsWarehouseMapper baseMapper;

    /**
     * 查询仓库信息
     *
     * @param warehouseId 主键
     * @return 仓库信息
     */
    @Override
    public WmsWarehouseVo queryById(Long warehouseId){
        return baseMapper.selectVoById(warehouseId);
    }

    /**
     * 分页查询仓库信息列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 仓库信息分页列表
     */
    @Override
    public TableDataInfo<WmsWarehouseVo> queryPageList(WmsWarehouseBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<WmsWarehouse> lqw = buildQueryWrapper(bo);
        Page<WmsWarehouseVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的仓库信息列表
     *
     * @param bo 查询条件
     * @return 仓库信息列表
     */
    @Override
    public List<WmsWarehouseVo> queryList(WmsWarehouseBo bo) {
        LambdaQueryWrapper<WmsWarehouse> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<WmsWarehouse> buildQueryWrapper(WmsWarehouseBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<WmsWarehouse> lqw = Wrappers.lambdaQuery();
        lqw.orderByAsc(WmsWarehouse::getWarehouseId);
        lqw.like(StringUtils.isNotBlank(bo.getWarehouseName()), WmsWarehouse::getWarehouseName, bo.getWarehouseName());
        lqw.eq(StringUtils.isNotBlank(bo.getWarehouseCode()), WmsWarehouse::getWarehouseCode, bo.getWarehouseCode());
        lqw.eq(bo.getArea() != null, WmsWarehouse::getArea, bo.getArea());
        lqw.eq(StringUtils.isNotBlank(bo.getAddress()), WmsWarehouse::getAddress, bo.getAddress());
        lqw.eq(bo.getChargePerson() != null, WmsWarehouse::getChargePerson, bo.getChargePerson());
        lqw.eq(StringUtils.isNotBlank(bo.getStatus()), WmsWarehouse::getStatus, bo.getStatus());
        return lqw;
    }

    /**
     * 新增仓库信息
     *
     * @param bo 仓库信息
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(WmsWarehouseBo bo) {
        WmsWarehouse add = MapstructUtils.convert(bo, WmsWarehouse.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setWarehouseId(add.getWarehouseId());
        }
        return flag;
    }

    /**
     * 修改仓库信息
     *
     * @param bo 仓库信息
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(WmsWarehouseBo bo) {
        WmsWarehouse update = MapstructUtils.convert(bo, WmsWarehouse.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(WmsWarehouse entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 校验并批量删除仓库信息信息
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
