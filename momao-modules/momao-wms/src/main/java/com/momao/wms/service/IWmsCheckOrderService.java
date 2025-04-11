package com.momao.wms.service;

import com.momao.common.mybatis.core.page.PageQuery;
import com.momao.common.mybatis.core.page.TableDataInfo;
import com.momao.wms.domain.bo.WmsCheckOrderBo;
import com.momao.wms.domain.vo.WmsCheckOrderVo;

import java.util.Collection;
import java.util.List;

/**
 * 盘点单Service接口
 *
 * @author Mo mao
 * @date 2025-04-11
 */
public interface IWmsCheckOrderService {

    /**
     * 查询盘点单
     *
     * @param orderId 主键
     * @return 盘点单
     */
    WmsCheckOrderVo queryById(Long orderId);

    /**
     * 分页查询盘点单列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 盘点单分页列表
     */
    TableDataInfo<WmsCheckOrderVo> queryPageList(WmsCheckOrderBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的盘点单列表
     *
     * @param bo 查询条件
     * @return 盘点单列表
     */
    List<WmsCheckOrderVo> queryList(WmsCheckOrderBo bo);

    /**
     * 新增盘点单
     *
     * @param bo 盘点单
     * @return 是否新增成功
     */
    Boolean insertByBo(WmsCheckOrderBo bo);

    /**
     * 修改盘点单
     *
     * @param bo 盘点单
     * @return 是否修改成功
     */
    Boolean updateByBo(WmsCheckOrderBo bo);

    /**
     * 校验并批量删除盘点单信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
