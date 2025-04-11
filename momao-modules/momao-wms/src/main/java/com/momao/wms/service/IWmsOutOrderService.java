package com.momao.wms.service;

import com.momao.common.mybatis.core.page.PageQuery;
import com.momao.common.mybatis.core.page.TableDataInfo;
import com.momao.wms.domain.bo.WmsOutOrderBo;
import com.momao.wms.domain.vo.WmsOutOrderVo;

import java.util.Collection;
import java.util.List;

/**
 * 出库单Service接口
 *
 * @author Mo mao
 * @date 2025-04-11
 */
public interface IWmsOutOrderService {

    /**
     * 查询出库单
     *
     * @param orderId 主键
     * @return 出库单
     */
    WmsOutOrderVo queryById(Long orderId);

    /**
     * 分页查询出库单列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 出库单分页列表
     */
    TableDataInfo<WmsOutOrderVo> queryPageList(WmsOutOrderBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的出库单列表
     *
     * @param bo 查询条件
     * @return 出库单列表
     */
    List<WmsOutOrderVo> queryList(WmsOutOrderBo bo);

    /**
     * 新增出库单
     *
     * @param bo 出库单
     * @return 是否新增成功
     */
    Boolean insertByBo(WmsOutOrderBo bo);

    /**
     * 修改出库单
     *
     * @param bo 出库单
     * @return 是否修改成功
     */
    Boolean updateByBo(WmsOutOrderBo bo);

    /**
     * 校验并批量删除出库单信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
