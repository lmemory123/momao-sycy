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
import com.momao.wms.domain.bo.WmsStockBo;
import com.momao.wms.domain.vo.WmsStockVo;
import com.momao.wms.service.IWmsStockService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 库存信息
 *
 * @author Mo mao
 * @date 2025-04-11
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/wms/stock")
public class WmsStockController extends BaseController {

    private final IWmsStockService wmsStockService;

    /**
     * 查询库存信息列表
     */
    @SaCheckPermission("wms:stock:list")
    @GetMapping("/list")
    public TableDataInfo<WmsStockVo> list(WmsStockBo bo, PageQuery pageQuery) {
        return wmsStockService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出库存信息列表
     */
    @SaCheckPermission("wms:stock:export")
    @Log(title = "库存信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(WmsStockBo bo, HttpServletResponse response) {
        List<WmsStockVo> list = wmsStockService.queryList(bo);
        ExcelUtil.exportExcel(list, "库存信息", WmsStockVo.class, response);
    }

    /**
     * 获取库存信息详细信息
     *
     * @param stockId 主键
     */
    @SaCheckPermission("wms:stock:query")
    @GetMapping("/{stockId}")
    public R<WmsStockVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long stockId) {
        return R.ok(wmsStockService.queryById(stockId));
    }

    /**
     * 新增库存信息
     */
    @SaCheckPermission("wms:stock:add")
    @Log(title = "库存信息", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody WmsStockBo bo) {
        return toAjax(wmsStockService.insertByBo(bo));
    }

    /**
     * 修改库存信息
     */
    @SaCheckPermission("wms:stock:edit")
    @Log(title = "库存信息", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody WmsStockBo bo) {
        return toAjax(wmsStockService.updateByBo(bo));
    }

    /**
     * 删除库存信息
     *
     * @param stockIds 主键串
     */
    @SaCheckPermission("wms:stock:remove")
    @Log(title = "库存信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{stockIds}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] stockIds) {
        return toAjax(wmsStockService.deleteWithValidByIds(List.of(stockIds), true));
    }
}
