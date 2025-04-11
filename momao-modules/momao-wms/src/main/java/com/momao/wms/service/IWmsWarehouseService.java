package com.momao.wms.service;

import com.momao.common.mybatis.core.page.PageQuery;
import com.momao.common.mybatis.core.page.TableDataInfo;
import com.momao.wms.domain.bo.WmsWarehouseBo;
import com.momao.wms.domain.vo.WmsWarehouseVo;

import java.util.Collection;
import java.util.List;

/**
 * 仓库信息Service接口
 *
 * @author Mo mao
 * @date 2025-04-11
 */
public interface IWmsWarehouseService {

    /**
     * 查询仓库信息
     *
     * @param warehouseId 主键
     * @return 仓库信息
     */
    WmsWarehouseVo queryById(Long warehouseId);

    /**
     * 分页查询仓库信息列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 仓库信息分页列表
     */
    TableDataInfo<WmsWarehouseVo> queryPageList(WmsWarehouseBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的仓库信息列表
     *
     * @param bo 查询条件
     * @return 仓库信息列表
     */
    List<WmsWarehouseVo> queryList(WmsWarehouseBo bo);

    /**
     * 新增仓库信息
     *
     * @param bo 仓库信息
     * @return 是否新增成功
     */
    Boolean insertByBo(WmsWarehouseBo bo);

    /**
     * 修改仓库信息
     *
     * @param bo 仓库信息
     * @return 是否修改成功
     */
    Boolean updateByBo(WmsWarehouseBo bo);

    /**
     * 校验并批量删除仓库信息信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
