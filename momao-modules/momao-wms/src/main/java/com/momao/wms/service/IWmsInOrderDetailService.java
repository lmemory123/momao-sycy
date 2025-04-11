package com.momao.wms.service;

import com.momao.common.mybatis.core.page.PageQuery;
import com.momao.common.mybatis.core.page.TableDataInfo;
import com.momao.wms.domain.bo.WmsInOrderDetailBo;
import com.momao.wms.domain.vo.WmsInOrderDetailVo;

import java.util.Collection;
import java.util.List;

/**
 * 入库单详情Service接口
 *
 * @author Mo mao
 * @date 2025-04-11
 */
public interface IWmsInOrderDetailService {

    /**
     * 查询入库单详情
     *
     * @param detailId 主键
     * @return 入库单详情
     */
    WmsInOrderDetailVo queryById(Long detailId);

    /**
     * 分页查询入库单详情列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 入库单详情分页列表
     */
    TableDataInfo<WmsInOrderDetailVo> queryPageList(WmsInOrderDetailBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的入库单详情列表
     *
     * @param bo 查询条件
     * @return 入库单详情列表
     */
    List<WmsInOrderDetailVo> queryList(WmsInOrderDetailBo bo);

    /**
     * 新增入库单详情
     *
     * @param bo 入库单详情
     * @return 是否新增成功
     */
    Boolean insertByBo(WmsInOrderDetailBo bo);

    /**
     * 修改入库单详情
     *
     * @param bo 入库单详情
     * @return 是否修改成功
     */
    Boolean updateByBo(WmsInOrderDetailBo bo);

    /**
     * 校验并批量删除入库单详情信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
