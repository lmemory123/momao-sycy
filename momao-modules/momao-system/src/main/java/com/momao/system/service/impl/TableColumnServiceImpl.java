package com.momao.system.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.momao.common.core.domain.model.LoginUser;
import com.momao.common.core.utils.MapstructUtils;
import com.momao.common.core.utils.StringUtils;
import com.momao.common.mybatis.core.page.TableDataInfo;
import com.momao.common.mybatis.core.page.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.momao.common.satoken.utils.LoginHelper;
import com.momao.system.domain.TableColumnUserConfig;
import com.momao.system.mapper.TableColumnUserConfigMapper;
import com.momao.system.service.ITableColumnUserConfigService;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import com.momao.system.domain.bo.TableColumnBo;
import com.momao.system.domain.vo.TableColumnVo;
import com.momao.system.domain.TableColumn;
import com.momao.system.mapper.TableColumnMapper;
import com.momao.system.service.ITableColumnService;

import java.util.List;
import java.util.Map;
import java.util.Collection;
import java.util.concurrent.CompletableFuture;

import org.springframework.transaction.annotation.Transactional;

/**
 * 字段配置Service业务层处理
 *
 * @author Mo mao
 * @date 2025-04-14
 */
@RequiredArgsConstructor
@Service
public class TableColumnServiceImpl implements ITableColumnService {

    private final TableColumnMapper baseMapper;
    private final TableColumnUserConfigMapper columnUserConfigMapper;

    /**
     * 查询字段配置
     *
     * @param id 主键
     * @return 字段配置
     */
    @Override
    public TableColumnVo queryById(Long id) {
        return baseMapper.selectVoById(id);
    }

    /**
     * 分页查询字段配置列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 字段配置分页列表
     */
    @Override
    public TableDataInfo<TableColumnVo> queryPageList(TableColumnBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<TableColumn> lqw = buildQueryWrapper(bo);
        Page<TableColumnVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的字段配置列表
     *
     * @param bo 查询条件
     * @return 字段配置列表
     */
    @Override
    public List<TableColumnVo> queryList(TableColumnBo bo) {
        LambdaQueryWrapper<TableColumn> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<TableColumn> buildQueryWrapper(TableColumnBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<TableColumn> lqw = Wrappers.lambdaQuery();
        lqw.orderByAsc(TableColumn::getId);
        lqw.eq(StringUtils.isNotBlank(bo.getTableName()), TableColumn::getTableName, bo.getTableName());
        lqw.eq(StringUtils.isNotBlank(bo.getColumnName()), TableColumn::getColumnName, bo.getColumnName());
        lqw.eq(bo.getVisible() != null, TableColumn::getVisible, bo.getVisible());
        return lqw;
    }

    /**
     * 新增字段配置
     *
     * @param bo 字段配置
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(TableColumnBo bo) {
        TableColumn add = MapstructUtils.convert(bo, TableColumn.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改字段配置
     *
     * @param bo 字段配置
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(TableColumnBo bo) {
        TableColumn update = MapstructUtils.convert(bo, TableColumn.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(TableColumn entity) {
        // TODO 做一些数据校验,如唯一约束
    }

    /**
     * 校验并批量删除字段配置信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if (isValid) {
            // TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteByIds(ids) > 0;
    }

    /**
     * 获取用户自定义表头
     *
     * @param tableName 表名
     * @return 用户自定义表头列表
     */
    @Override
    @Cacheable(value = "tableColumn", key = "#tableName+':'+T(com.momao.common.satoken.utils.LoginHelper).getUserId()")
    public List<TableColumnVo> getUserColumns(String tableName) {
        LoginUser user = LoginHelper.getLoginUser();
        return baseMapper.selectUserColumns(tableName, user.getUserId());

    }
    /**
     * 更新用户自定义表头
     * 没必要使用锁，失败了也没关系，
     *
     * @param columns 用户自定义表头列表
     * @return 影响行数
     */
    @Override
    @CacheEvict(value = "tableColumn", key = "#columns.get(0).tableName+':'+T(com.momao.common.satoken.utils.LoginHelper).getUserId()")
    public int updateUserColumns(List<TableColumnVo> columns) {
        if (columns == null || columns.isEmpty()) {
            return 0;
        }
        Long userId = LoginHelper.getUserId();
        /*
        要求前端把不对查询的结果重新封装，而是直接传过来 这样通过每行的userId来判断 是否新增还是修改
        拆分新增和修改 使用并发编程
        */
        CompletableFuture.allOf(
            CompletableFuture.runAsync(() -> {
                // 新增
                List<TableColumnUserConfig> insertList = columns.stream()
                    .filter(item -> item.getUserId() == null)
                    .map(item -> {
                        TableColumnUserConfig config = new TableColumnUserConfig();
                        BeanUtil.copyProperties(item, config);
                        config.setId(null);
                        config.setUserId(userId); // 设置用户ID
                        return config;
                    })
                    .toList();
                columnUserConfigMapper.insertBatch(insertList);
            }),
            CompletableFuture.runAsync(() -> {
                // 修改
                List<TableColumnUserConfig> updateList = columns.stream()
                    .filter(item -> item.getUserId() != null)
                    .map(item -> {
                        TableColumnUserConfig config = new TableColumnUserConfig();
                        BeanUtil.copyProperties(item, config);
                        return config;
                    })
                    .toList();
                columnUserConfigMapper.updateBatchById(updateList);
            })
        ).join();
        return 1;
    }
}
