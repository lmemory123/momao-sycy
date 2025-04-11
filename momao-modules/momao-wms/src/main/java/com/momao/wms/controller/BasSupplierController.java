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
import com.momao.wms.domain.bo.BasSupplierBo;
import com.momao.wms.domain.vo.BasSupplierVo;
import com.momao.wms.service.IBasSupplierService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 供应商信息
 *
 * @author Mo mao
 * @date 2025-04-11
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/wms/supplier")
public class BasSupplierController extends BaseController {

    private final IBasSupplierService basSupplierService;

    /**
     * 查询供应商信息列表
     */
    @SaCheckPermission("wms:supplier:list")
    @GetMapping("/list")
    public TableDataInfo<BasSupplierVo> list(BasSupplierBo bo, PageQuery pageQuery) {
        return basSupplierService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出供应商信息列表
     */
    @SaCheckPermission("wms:supplier:export")
    @Log(title = "供应商信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(BasSupplierBo bo, HttpServletResponse response) {
        List<BasSupplierVo> list = basSupplierService.queryList(bo);
        ExcelUtil.exportExcel(list, "供应商信息", BasSupplierVo.class, response);
    }

    /**
     * 获取供应商信息详细信息
     *
     * @param supplierId 主键
     */
    @SaCheckPermission("wms:supplier:query")
    @GetMapping("/{supplierId}")
    public R<BasSupplierVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long supplierId) {
        return R.ok(basSupplierService.queryById(supplierId));
    }

    /**
     * 新增供应商信息
     */
    @SaCheckPermission("wms:supplier:add")
    @Log(title = "供应商信息", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody BasSupplierBo bo) {
        return toAjax(basSupplierService.insertByBo(bo));
    }

    /**
     * 修改供应商信息
     */
    @SaCheckPermission("wms:supplier:edit")
    @Log(title = "供应商信息", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody BasSupplierBo bo) {
        return toAjax(basSupplierService.updateByBo(bo));
    }

    /**
     * 删除供应商信息
     *
     * @param supplierIds 主键串
     */
    @SaCheckPermission("wms:supplier:remove")
    @Log(title = "供应商信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{supplierIds}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] supplierIds) {
        return toAjax(basSupplierService.deleteWithValidByIds(List.of(supplierIds), true));
    }
}
