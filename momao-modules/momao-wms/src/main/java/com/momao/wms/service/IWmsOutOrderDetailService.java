package com.momao.wms.service;

import com.momao.common.mybatis.core.page.PageQuery;
import com.momao.common.mybatis.core.page.TableDataInfo;
import com.momao.wms.domain.bo.WmsOutOrderDetailBo;
import com.momao.wms.domain.vo.WmsOutOrderDetailVo;

import java.util.Collection;
import java.util.List;

/**
 * 出库单详情Service接口
 *
 * @author Mo mao
 * @date 2025-04-11
 */
public interface IWmsOutOrderDetailService {

    /**
     * 查询出库单详情
     *
     * @param detailId 主键
     * @return 出库单详情
     */
    WmsOutOrderDetailVo queryById(Long detailId);

    /**
     * 分页查询出库单详情列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 出库单详情分页列表
     */
    TableDataInfo<WmsOutOrderDetailVo> queryPageList(WmsOutOrderDetailBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的出库单详情列表
     *
     * @param bo 查询条件
     * @return 出库单详情列表
     */
    List<WmsOutOrderDetailVo> queryList(WmsOutOrderDetailBo bo);

    /**
     * 新增出库单详情
     *
     * @param bo 出库单详情
     * @return 是否新增成功
     */
    Boolean insertByBo(WmsOutOrderDetailBo bo);

    /**
     * 修改出库单详情
     *
     * @param bo 出库单详情
     * @return 是否修改成功
     */
    Boolean updateByBo(WmsOutOrderDetailBo bo);

    /**
     * 校验并批量删除出库单详情信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
