package com.momao.wms.service;

import com.momao.common.mybatis.core.page.PageQuery;
import com.momao.common.mybatis.core.page.TableDataInfo;
import com.momao.wms.domain.bo.WmsInOrderBo;
import com.momao.wms.domain.vo.WmsInOrderVo;

import java.util.Collection;
import java.util.List;

/**
 * 入库单Service接口
 *
 * @author Mo mao
 * @date 2025-04-11
 */
public interface IWmsInOrderService {

    /**
     * 查询入库单
     *
     * @param orderId 主键
     * @return 入库单
     */
    WmsInOrderVo queryById(Long orderId);

    /**
     * 分页查询入库单列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 入库单分页列表
     */
    TableDataInfo<WmsInOrderVo> queryPageList(WmsInOrderBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的入库单列表
     *
     * @param bo 查询条件
     * @return 入库单列表
     */
    List<WmsInOrderVo> queryList(WmsInOrderBo bo);

    /**
     * 新增入库单
     *
     * @param bo 入库单
     * @return 是否新增成功
     */
    Boolean insertByBo(WmsInOrderBo bo);

    /**
     * 修改入库单
     *
     * @param bo 入库单
     * @return 是否修改成功
     */
    Boolean updateByBo(WmsInOrderBo bo);

    /**
     * 校验并批量删除入库单信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
