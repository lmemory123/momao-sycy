package com.momao.test;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.momao.common.json.utils.JsonUtils;
import com.momao.system.domain.TableColumn;
import com.momao.system.domain.TableColumnUserConfig;
import com.momao.system.domain.vo.TableColumnVo;
import com.momao.system.mapper.TableColumnMapper;
import com.momao.system.mapper.TableColumnUserConfigMapper;
import com.momao.system.service.ITableColumnService;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Created with IntelliJ IDEA.
 * Description: tableColumn测试
 *
 * @Author: TokyoMomao
 * DateTime: 2025-04-18 13:38
 */

@SpringBootTest
public class TableColumnTest {


    @Resource
    ITableColumnService tableColumnService;

    @Resource
    TableColumnMapper tableColumnMapper;

    @Resource
    TableColumnUserConfigMapper columnUserConfigMapper;


    @Test
    void getColumns() {
        // 使用lambdaQueryWrapper查询
        LambdaQueryWrapper<TableColumn> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(TableColumn::getTableName, "wms_stock_batch");
        List<TableColumn> columns = tableColumnMapper.selectList(queryWrapper);
        System.out.println(JsonUtils.toJsonString(columns));



    }

    @Test
    void getUserColumns() {
        List<TableColumnVo> wmsStockBatch = tableColumnMapper.selectUserColumns("demo_table", 1L);
        System.out.println(JsonUtils.toJsonString(wmsStockBatch));

    }

    @Test
    void updateUserColumns() {


        List<TableColumnVo> columns = tableColumnMapper.selectUserColumns("wms_stock_batch", 1L);
        Long userId = 1L;

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


    }
}
