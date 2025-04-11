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
import com.momao.wms.domain.bo.WmsStockBatchBo;
import com.momao.wms.domain.vo.WmsStockBatchVo;
import com.momao.wms.service.IWmsStockBatchService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 批次库存
 *
 * @author Mo mao
 * @date 2025-04-11
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/wms/stockBatch")
public class WmsStockBatchController extends BaseController {

    private final IWmsStockBatchService wmsStockBatchService;

    /**
     * 查询批次库存列表
     */
    @SaCheckPermission("wms:stockBatch:list")
    @GetMapping("/list")
    public TableDataInfo<WmsStockBatchVo> list(WmsStockBatchBo bo, PageQuery pageQuery) {
        return wmsStockBatchService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出批次库存列表
     */
    @SaCheckPermission("wms:stockBatch:export")
    @Log(title = "批次库存", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(WmsStockBatchBo bo, HttpServletResponse response) {
        List<WmsStockBatchVo> list = wmsStockBatchService.queryList(bo);
        ExcelUtil.exportExcel(list, "批次库存", WmsStockBatchVo.class, response);
    }

    /**
     * 获取批次库存详细信息
     *
     * @param batchId 主键
     */
    @SaCheckPermission("wms:stockBatch:query")
    @GetMapping("/{batchId}")
    public R<WmsStockBatchVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long batchId) {
        return R.ok(wmsStockBatchService.queryById(batchId));
    }

    /**
     * 新增批次库存
     */
    @SaCheckPermission("wms:stockBatch:add")
    @Log(title = "批次库存", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody WmsStockBatchBo bo) {
        return toAjax(wmsStockBatchService.insertByBo(bo));
    }

    /**
     * 修改批次库存
     */
    @SaCheckPermission("wms:stockBatch:edit")
    @Log(title = "批次库存", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody WmsStockBatchBo bo) {
        return toAjax(wmsStockBatchService.updateByBo(bo));
    }

    /**
     * 删除批次库存
     *
     * @param batchIds 主键串
     */
    @SaCheckPermission("wms:stockBatch:remove")
    @Log(title = "批次库存", businessType = BusinessType.DELETE)
    @DeleteMapping("/{batchIds}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] batchIds) {
        return toAjax(wmsStockBatchService.deleteWithValidByIds(List.of(batchIds), true));
    }
}
