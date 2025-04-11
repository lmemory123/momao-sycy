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
import com.momao.wms.domain.bo.WmsStockBatchLogBo;
import com.momao.wms.domain.vo.WmsStockBatchLogVo;
import com.momao.wms.service.IWmsStockBatchLogService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 批次库存操作日志
 *
 * @author Mo mao
 * @date 2025-04-11
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/wms/stockBatchLog")
public class WmsStockBatchLogController extends BaseController {

    private final IWmsStockBatchLogService wmsStockBatchLogService;

    /**
     * 查询批次库存操作日志列表
     */
    @SaCheckPermission("wms:stockBatchLog:list")
    @GetMapping("/list")
    public TableDataInfo<WmsStockBatchLogVo> list(WmsStockBatchLogBo bo, PageQuery pageQuery) {
        return wmsStockBatchLogService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出批次库存操作日志列表
     */
    @SaCheckPermission("wms:stockBatchLog:export")
    @Log(title = "批次库存操作日志", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(WmsStockBatchLogBo bo, HttpServletResponse response) {
        List<WmsStockBatchLogVo> list = wmsStockBatchLogService.queryList(bo);
        ExcelUtil.exportExcel(list, "批次库存操作日志", WmsStockBatchLogVo.class, response);
    }

    /**
     * 获取批次库存操作日志详细信息
     *
     * @param logId 主键
     */
    @SaCheckPermission("wms:stockBatchLog:query")
    @GetMapping("/{logId}")
    public R<WmsStockBatchLogVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long logId) {
        return R.ok(wmsStockBatchLogService.queryById(logId));
    }

    /**
     * 新增批次库存操作日志
     */
    @SaCheckPermission("wms:stockBatchLog:add")
    @Log(title = "批次库存操作日志", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody WmsStockBatchLogBo bo) {
        return toAjax(wmsStockBatchLogService.insertByBo(bo));
    }

    /**
     * 修改批次库存操作日志
     */
    @SaCheckPermission("wms:stockBatchLog:edit")
    @Log(title = "批次库存操作日志", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody WmsStockBatchLogBo bo) {
        return toAjax(wmsStockBatchLogService.updateByBo(bo));
    }

    /**
     * 删除批次库存操作日志
     *
     * @param logIds 主键串
     */
    @SaCheckPermission("wms:stockBatchLog:remove")
    @Log(title = "批次库存操作日志", businessType = BusinessType.DELETE)
    @DeleteMapping("/{logIds}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] logIds) {
        return toAjax(wmsStockBatchLogService.deleteWithValidByIds(List.of(logIds), true));
    }
}
