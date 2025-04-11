package com.momao.wms.service;

import com.momao.common.mybatis.core.page.PageQuery;
import com.momao.common.mybatis.core.page.TableDataInfo;
import com.momao.wms.domain.bo.WmsStockLogBo;
import com.momao.wms.domain.vo.WmsStockLogVo;

import java.util.Collection;
import java.util.List;

/**
 * 库存操作日志Service接口
 *
 * @author Mo mao
 * @date 2025-04-11
 */
public interface IWmsStockLogService {

    /**
     * 查询库存操作日志
     *
     * @param logId 主键
     * @return 库存操作日志
     */
    WmsStockLogVo queryById(Long logId);

    /**
     * 分页查询库存操作日志列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 库存操作日志分页列表
     */
    TableDataInfo<WmsStockLogVo> queryPageList(WmsStockLogBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的库存操作日志列表
     *
     * @param bo 查询条件
     * @return 库存操作日志列表
     */
    List<WmsStockLogVo> queryList(WmsStockLogBo bo);

    /**
     * 新增库存操作日志
     *
     * @param bo 库存操作日志
     * @return 是否新增成功
     */
    Boolean insertByBo(WmsStockLogBo bo);

    /**
     * 修改库存操作日志
     *
     * @param bo 库存操作日志
     * @return 是否修改成功
     */
    Boolean updateByBo(WmsStockLogBo bo);

    /**
     * 校验并批量删除库存操作日志信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
