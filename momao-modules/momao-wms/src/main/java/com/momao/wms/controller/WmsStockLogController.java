package com.momao.wms.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.momao.common.core.domain.R;
import com.momao.common.core.validate.AddGroup;
import com.momao.common.core.validate.EditGroup;
import com.momao.common.excel.utils.ExcelUtil;
import com.momao.common.idempotent.annotation.RepeatSubmit;
import com.momao.common.log.annotation.Log;
import com.momao.common.log.enums.BusinessType;
import com.momao.common.mybatis.core.page.PageQuery;
import com.momao.common.mybatis.core.page.TableDataInfo;
import com.momao.common.web.core.BaseController;
import com.momao.wms.domain.bo.WmsStockLogBo;
import com.momao.wms.domain.vo.WmsStockLogVo;
import com.momao.wms.service.IWmsStockLogService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 库存操作日志
 *
 * @author Mo mao
 * @date 2025-04-11
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/wms/stockLog")
public class WmsStockLogController extends BaseController {

    private final IWmsStockLogService wmsStockLogService;

    /**
     * 查询库存操作日志列表
     */
    @SaCheckPermission("wms:stockLog:list")
    @GetMapping("/list")
    public TableDataInfo<WmsStockLogVo> list(WmsStockLogBo bo, PageQuery pageQuery) {
        return wmsStockLogService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出库存操作日志列表
     */
    @SaCheckPermission("wms:stockLog:export")
    @Log(title = "库存操作日志", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(WmsStockLogBo bo, HttpServletResponse response) {
        List<WmsStockLogVo> list = wmsStockLogService.queryList(bo);
        ExcelUtil.exportExcel(list, "库存操作日志", WmsStockLogVo.class, response);
    }

    /**
     * 获取库存操作日志详细信息
     *
     * @param logId 主键
     */
    @SaCheckPermission("wms:stockLog:query")
    @GetMapping("/{logId}")
    public R<WmsStockLogVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long logId) {
        return R.ok(wmsStockLogService.queryById(logId));
    }

    /**
     * 新增库存操作日志
     */
    @SaCheckPermission("wms:stockLog:add")
    @Log(title = "库存操作日志", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody WmsStockLogBo bo) {
        return toAjax(wmsStockLogService.insertByBo(bo));
    }

    /**
     * 修改库存操作日志
     */
    @SaCheckPermission("wms:stockLog:edit")
    @Log(title = "库存操作日志", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody WmsStockLogBo bo) {
        return toAjax(wmsStockLogService.updateByBo(bo));
    }

    /**
     * 删除库存操作日志
     *
     * @param logIds 主键串
     */
    @SaCheckPermission("wms:stockLog:remove")
    @Log(title = "库存操作日志", businessType = BusinessType.DELETE)
    @DeleteMapping("/{logIds}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] logIds) {
        return toAjax(wmsStockLogService.deleteWithValidByIds(List.of(logIds), true));
    }
}
