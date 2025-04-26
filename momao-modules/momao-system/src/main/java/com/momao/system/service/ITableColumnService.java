package com.momao.system.service;

import com.momao.system.domain.vo.TableColumnVo;
import com.momao.system.domain.bo.TableColumnBo;
import com.momao.common.mybatis.core.page.TableDataInfo;
import com.momao.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 字段配置Service接口
 *
 * @author Mo mao
 * @date 2025-04-14
 */
public interface ITableColumnService {

    /**
     * 查询字段配置
     *
     * @param id 主键
     * @return 字段配置
     */
    TableColumnVo queryById(Long id);

    /**
     * 分页查询字段配置列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 字段配置分页列表
     */
    TableDataInfo<TableColumnVo> queryPageList(TableColumnBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的字段配置列表
     *
     * @param bo 查询条件
     * @return 字段配置列表
     */
    List<TableColumnVo> queryList(TableColumnBo bo);

    /**
     * 新增字段配置
     *
     * @param bo 字段配置
     * @return 是否新增成功
     */
    Boolean insertByBo(TableColumnBo bo);

    /**
     * 修改字段配置
     *
     * @param bo 字段配置
     * @return 是否修改成功
     */
    Boolean updateByBo(TableColumnBo bo);

    /**
     * 校验并批量删除字段配置信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);

    /**
     *  获取用户自定义表头
     * @param tableName 表名
     * @return - 用户自定义表头列表
     */
    List<TableColumnVo> getUserColumns(String tableName);



    /**
     * 更新用户自定义表头
     * @param columns
     * @return
     */
    int updateUserColumns(List<TableColumnVo> columns);
}
