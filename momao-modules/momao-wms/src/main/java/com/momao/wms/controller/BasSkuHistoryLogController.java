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
import com.momao.wms.domain.bo.BasSkuHistoryLogBo;
import com.momao.wms.domain.vo.BasSkuHistoryLogVo;
import com.momao.wms.service.IBasSkuHistoryLogService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * SKU信息历史变更
 *
 * @author Mo mao
 * @date 2025-04-11
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/wms/skuHistoryLog")
public class BasSkuHistoryLogController extends BaseController {

    private final IBasSkuHistoryLogService basSkuHistoryLogService;

    /**
     * 查询SKU信息历史变更列表
     */
    @SaCheckPermission("wms:skuHistoryLog:list")
    @GetMapping("/list")
    public TableDataInfo<BasSkuHistoryLogVo> list(BasSkuHistoryLogBo bo, PageQuery pageQuery) {
        return basSkuHistoryLogService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出SKU信息历史变更列表
     */
    @SaCheckPermission("wms:skuHistoryLog:export")
    @Log(title = "SKU信息历史变更", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(BasSkuHistoryLogBo bo, HttpServletResponse response) {
        List<BasSkuHistoryLogVo> list = basSkuHistoryLogService.queryList(bo);
        ExcelUtil.exportExcel(list, "SKU信息历史变更", BasSkuHistoryLogVo.class, response);
    }

    /**
     * 获取SKU信息历史变更详细信息
     *
     * @param logId 主键
     */
    @SaCheckPermission("wms:skuHistoryLog:query")
    @GetMapping("/{logId}")
    public R<BasSkuHistoryLogVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long logId) {
        return R.ok(basSkuHistoryLogService.queryById(logId));
    }

    /**
     * 新增SKU信息历史变更
     */
    @SaCheckPermission("wms:skuHistoryLog:add")
    @Log(title = "SKU信息历史变更", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody BasSkuHistoryLogBo bo) {
        return toAjax(basSkuHistoryLogService.insertByBo(bo));
    }

    /**
     * 修改SKU信息历史变更
     */
    @SaCheckPermission("wms:skuHistoryLog:edit")
    @Log(title = "SKU信息历史变更", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody BasSkuHistoryLogBo bo) {
        return toAjax(basSkuHistoryLogService.updateByBo(bo));
    }

    /**
     * 删除SKU信息历史变更
     *
     * @param logIds 主键串
     */
    @SaCheckPermission("wms:skuHistoryLog:remove")
    @Log(title = "SKU信息历史变更", businessType = BusinessType.DELETE)
    @DeleteMapping("/{logIds}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] logIds) {
        return toAjax(basSkuHistoryLogService.deleteWithValidByIds(List.of(logIds), true));
    }
}
