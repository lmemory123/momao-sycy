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
import com.momao.wms.domain.bo.WmsWarehouseBo;
import com.momao.wms.domain.vo.WmsWarehouseVo;
import com.momao.wms.service.IWmsWarehouseService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 仓库信息
 *
 * @author Mo mao
 * @date 2025-04-11
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/wms/warehouse")
public class WmsWarehouseController extends BaseController {

    private final IWmsWarehouseService wmsWarehouseService;

    /**
     * 查询仓库信息列表
     */
    @SaCheckPermission("wms:warehouse:list")
    @GetMapping("/list")
    public TableDataInfo<WmsWarehouseVo> list(WmsWarehouseBo bo, PageQuery pageQuery) {
        return wmsWarehouseService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出仓库信息列表
     */
    @SaCheckPermission("wms:warehouse:export")
    @Log(title = "仓库信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(WmsWarehouseBo bo, HttpServletResponse response) {
        List<WmsWarehouseVo> list = wmsWarehouseService.queryList(bo);
        ExcelUtil.exportExcel(list, "仓库信息", WmsWarehouseVo.class, response);
    }

    /**
     * 获取仓库信息详细信息
     *
     * @param warehouseId 主键
     */
    @SaCheckPermission("wms:warehouse:query")
    @GetMapping("/{warehouseId}")
    public R<WmsWarehouseVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long warehouseId) {
        return R.ok(wmsWarehouseService.queryById(warehouseId));
    }

    /**
     * 新增仓库信息
     */
    @SaCheckPermission("wms:warehouse:add")
    @Log(title = "仓库信息", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody WmsWarehouseBo bo) {
        return toAjax(wmsWarehouseService.insertByBo(bo));
    }

    /**
     * 修改仓库信息
     */
    @SaCheckPermission("wms:warehouse:edit")
    @Log(title = "仓库信息", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody WmsWarehouseBo bo) {
        return toAjax(wmsWarehouseService.updateByBo(bo));
    }

    /**
     * 删除仓库信息
     *
     * @param warehouseIds 主键串
     */
    @SaCheckPermission("wms:warehouse:remove")
    @Log(title = "仓库信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{warehouseIds}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] warehouseIds) {
        return toAjax(wmsWarehouseService.deleteWithValidByIds(List.of(warehouseIds), true));
    }
}
