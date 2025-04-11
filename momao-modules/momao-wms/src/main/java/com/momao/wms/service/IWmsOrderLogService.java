package com.momao.wms.service;

import com.momao.common.mybatis.core.page.PageQuery;
import com.momao.common.mybatis.core.page.TableDataInfo;
import com.momao.wms.domain.bo.WmsOrderLogBo;
import com.momao.wms.domain.vo.WmsOrderLogVo;

import java.util.Collection;
import java.util.List;

/**
 * 单据操作日志Service接口
 *
 * @author Mo mao
 * @date 2025-04-11
 */
public interface IWmsOrderLogService {

    /**
     * 查询单据操作日志
     *
     * @param logId 主键
     * @return 单据操作日志
     */
    WmsOrderLogVo queryById(Long logId);

    /**
     * 分页查询单据操作日志列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 单据操作日志分页列表
     */
    TableDataInfo<WmsOrderLogVo> queryPageList(WmsOrderLogBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的单据操作日志列表
     *
     * @param bo 查询条件
     * @return 单据操作日志列表
     */
    List<WmsOrderLogVo> queryList(WmsOrderLogBo bo);

    /**
     * 新增单据操作日志
     *
     * @param bo 单据操作日志
     * @return 是否新增成功
     */
    Boolean insertByBo(WmsOrderLogBo bo);

    /**
     * 修改单据操作日志
     *
     * @param bo 单据操作日志
     * @return 是否修改成功
     */
    Boolean updateByBo(WmsOrderLogBo bo);

    /**
     * 校验并批量删除单据操作日志信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
