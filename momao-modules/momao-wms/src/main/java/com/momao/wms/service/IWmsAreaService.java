package com.momao.wms.service;

import com.momao.common.mybatis.core.page.PageQuery;
import com.momao.common.mybatis.core.page.TableDataInfo;
import com.momao.wms.domain.bo.WmsAreaBo;
import com.momao.wms.domain.vo.WmsAreaVo;

import java.util.Collection;
import java.util.List;

/**
 * 库区信息Service接口
 *
 * @author Mo mao
 * @date 2025-04-11
 */
public interface IWmsAreaService {

    /**
     * 查询库区信息
     *
     * @param areaId 主键
     * @return 库区信息
     */
    WmsAreaVo queryById(Long areaId);

    /**
     * 分页查询库区信息列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 库区信息分页列表
     */
    TableDataInfo<WmsAreaVo> queryPageList(WmsAreaBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的库区信息列表
     *
     * @param bo 查询条件
     * @return 库区信息列表
     */
    List<WmsAreaVo> queryList(WmsAreaBo bo);

    /**
     * 新增库区信息
     *
     * @param bo 库区信息
     * @return 是否新增成功
     */
    Boolean insertByBo(WmsAreaBo bo);

    /**
     * 修改库区信息
     *
     * @param bo 库区信息
     * @return 是否修改成功
     */
    Boolean updateByBo(WmsAreaBo bo);

    /**
     * 校验并批量删除库区信息信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
