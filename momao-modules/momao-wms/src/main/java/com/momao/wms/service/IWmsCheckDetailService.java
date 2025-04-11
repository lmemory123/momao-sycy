package com.momao.wms.service;

import com.momao.common.mybatis.core.page.PageQuery;
import com.momao.common.mybatis.core.page.TableDataInfo;
import com.momao.wms.domain.bo.WmsCheckDetailBo;
import com.momao.wms.domain.vo.WmsCheckDetailVo;

import java.util.Collection;
import java.util.List;

/**
 * 盘点单详情Service接口
 *
 * @author Mo mao
 * @date 2025-04-11
 */
public interface IWmsCheckDetailService {

    /**
     * 查询盘点单详情
     *
     * @param detailId 主键
     * @return 盘点单详情
     */
    WmsCheckDetailVo queryById(Long detailId);

    /**
     * 分页查询盘点单详情列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 盘点单详情分页列表
     */
    TableDataInfo<WmsCheckDetailVo> queryPageList(WmsCheckDetailBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的盘点单详情列表
     *
     * @param bo 查询条件
     * @return 盘点单详情列表
     */
    List<WmsCheckDetailVo> queryList(WmsCheckDetailBo bo);

    /**
     * 新增盘点单详情
     *
     * @param bo 盘点单详情
     * @return 是否新增成功
     */
    Boolean insertByBo(WmsCheckDetailBo bo);

    /**
     * 修改盘点单详情
     *
     * @param bo 盘点单详情
     * @return 是否修改成功
     */
    Boolean updateByBo(WmsCheckDetailBo bo);

    /**
     * 校验并批量删除盘点单详情信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
