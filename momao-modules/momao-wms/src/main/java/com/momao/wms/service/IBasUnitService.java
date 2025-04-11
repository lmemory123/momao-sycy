package com.momao.wms.service;

import com.momao.common.mybatis.core.page.PageQuery;
import com.momao.common.mybatis.core.page.TableDataInfo;
import com.momao.wms.domain.bo.BasUnitBo;
import com.momao.wms.domain.vo.BasUnitVo;

import java.util.Collection;
import java.util.List;

/**
 * 计量单位Service接口
 *
 * @author Mo mao
 * @date 2025-04-11
 */
public interface IBasUnitService {

    /**
     * 查询计量单位
     *
     * @param unitId 主键
     * @return 计量单位
     */
    BasUnitVo queryById(Long unitId);

    /**
     * 分页查询计量单位列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 计量单位分页列表
     */
    TableDataInfo<BasUnitVo> queryPageList(BasUnitBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的计量单位列表
     *
     * @param bo 查询条件
     * @return 计量单位列表
     */
    List<BasUnitVo> queryList(BasUnitBo bo);

    /**
     * 新增计量单位
     *
     * @param bo 计量单位
     * @return 是否新增成功
     */
    Boolean insertByBo(BasUnitBo bo);

    /**
     * 修改计量单位
     *
     * @param bo 计量单位
     * @return 是否修改成功
     */
    Boolean updateByBo(BasUnitBo bo);

    /**
     * 校验并批量删除计量单位信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
