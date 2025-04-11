package com.momao.wms.service;

import com.momao.common.mybatis.core.page.PageQuery;
import com.momao.common.mybatis.core.page.TableDataInfo;
import com.momao.wms.domain.bo.WmsLocationBo;
import com.momao.wms.domain.vo.WmsLocationVo;

import java.util.Collection;
import java.util.List;

/**
 * 库位信息Service接口
 *
 * @author Mo mao
 * @date 2025-04-11
 */
public interface IWmsLocationService {

    /**
     * 查询库位信息
     *
     * @param locationId 主键
     * @return 库位信息
     */
    WmsLocationVo queryById(Long locationId);

    /**
     * 分页查询库位信息列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 库位信息分页列表
     */
    TableDataInfo<WmsLocationVo> queryPageList(WmsLocationBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的库位信息列表
     *
     * @param bo 查询条件
     * @return 库位信息列表
     */
    List<WmsLocationVo> queryList(WmsLocationBo bo);

    /**
     * 新增库位信息
     *
     * @param bo 库位信息
     * @return 是否新增成功
     */
    Boolean insertByBo(WmsLocationBo bo);

    /**
     * 修改库位信息
     *
     * @param bo 库位信息
     * @return 是否修改成功
     */
    Boolean updateByBo(WmsLocationBo bo);

    /**
     * 校验并批量删除库位信息信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
