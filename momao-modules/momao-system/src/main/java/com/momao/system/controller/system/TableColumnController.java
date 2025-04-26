package com.momao.system.controller.system;

import java.util.List;

import lombok.RequiredArgsConstructor;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.*;
import cn.dev33.satoken.annotation.SaCheckPermission;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.annotation.Validated;
import com.momao.common.idempotent.annotation.RepeatSubmit;
import com.momao.common.log.annotation.Log;
import com.momao.common.web.core.BaseController;
import com.momao.common.mybatis.core.page.PageQuery;
import com.momao.common.core.domain.R;
import com.momao.common.core.validate.AddGroup;
import com.momao.common.core.validate.EditGroup;
import com.momao.common.log.enums.BusinessType;
import com.momao.common.excel.utils.ExcelUtil;
import com.momao.system.domain.vo.TableColumnVo;
import com.momao.system.domain.bo.TableColumnBo;
import com.momao.system.service.ITableColumnService;
import com.momao.common.mybatis.core.page.TableDataInfo;

/**
 * 表头配置
 *
 * @author Mo mao
 * @date 2025-04-14
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/system/column")
public class TableColumnController extends BaseController {

    private final ITableColumnService tableColumnService;

    /**
     * 查询表头配置列表
     */
    @SaCheckPermission("system:column:list")
    @GetMapping("/list")
    public TableDataInfo<TableColumnVo> list(TableColumnBo bo, PageQuery pageQuery) {
        return tableColumnService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出表头配置列表
     */
    @SaCheckPermission("system:column:export")
    @Log(title = "表头配置", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(TableColumnBo bo, HttpServletResponse response) {
        List<TableColumnVo> list = tableColumnService.queryList(bo);
        ExcelUtil.exportExcel(list, "表头配置", TableColumnVo.class, response);
    }

    /**
     * 获取表头配置详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("system:column:query")
    @GetMapping("/{id}")
    public R<TableColumnVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(tableColumnService.queryById(id));
    }

    /**
     * 新增字段配置
     */
    @SaCheckPermission("system:column:add")
    @Log(title = "表头配置", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody TableColumnBo bo) {
        return toAjax(tableColumnService.insertByBo(bo));
    }

    /**
     * 修改字段配置
     */
    @SaCheckPermission("system:column:edit")
    @Log(title = "表头配置", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody TableColumnBo bo) {
        return toAjax(tableColumnService.updateByBo(bo));
    }

    /**
     * 删除表头配置
     *
     * @param ids 主键串
     */
    @SaCheckPermission("system:column:remove")
    @Log(title = "表头配置", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(tableColumnService.deleteWithValidByIds(List.of(ids), true));
    }

    /**
     * 获取用户级别表头配置
     */
    @GetMapping("/user")
    public R<List<TableColumnVo>> getUserColumns(@RequestParam String tableName) {
        return R.ok(tableColumnService.getUserColumns(tableName));
    }

    /**
     * 更新用户级别表头配置
     */
    @Log(title = "用户表头配置", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping("/user")
    public R<Void> updateUserColumns(@RequestBody List<TableColumnVo> columns) {
        if (columns.isEmpty()){
            return R.fail("表头配置不能为空");
        }
        return toAjax(tableColumnService.updateUserColumns(columns));
    }
}
