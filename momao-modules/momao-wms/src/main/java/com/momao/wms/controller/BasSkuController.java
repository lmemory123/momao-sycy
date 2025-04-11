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
import com.momao.wms.domain.bo.BasSkuBo;
import com.momao.wms.domain.vo.BasSkuVo;
import com.momao.wms.service.IBasSkuService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * SKU信息
 *
 * @author Mo mao
 * @date 2025-04-11
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/wms/sku")
public class BasSkuController extends BaseController {

    private final IBasSkuService basSkuService;

    /**
     * 查询SKU信息列表
     */
    @SaCheckPermission("wms:sku:list")
    @GetMapping("/list")
    public TableDataInfo<BasSkuVo> list(BasSkuBo bo, PageQuery pageQuery) {
        return basSkuService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出SKU信息列表
     */
    @SaCheckPermission("wms:sku:export")
    @Log(title = "SKU信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(BasSkuBo bo, HttpServletResponse response) {
        List<BasSkuVo> list = basSkuService.queryList(bo);
        ExcelUtil.exportExcel(list, "SKU信息", BasSkuVo.class, response);
    }

    /**
     * 获取SKU信息详细信息
     *
     * @param skuId 主键
     */
    @SaCheckPermission("wms:sku:query")
    @GetMapping("/{skuId}")
    public R<BasSkuVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long skuId) {
        return R.ok(basSkuService.queryById(skuId));
    }

    /**
     * 新增SKU信息
     */
    @SaCheckPermission("wms:sku:add")
    @Log(title = "SKU信息", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody BasSkuBo bo) {
        return toAjax(basSkuService.insertByBo(bo));
    }

    /**
     * 修改SKU信息
     */
    @SaCheckPermission("wms:sku:edit")
    @Log(title = "SKU信息", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody BasSkuBo bo) {
        return toAjax(basSkuService.updateByBo(bo));
    }

    /**
     * 删除SKU信息
     *
     * @param skuIds 主键串
     */
    @SaCheckPermission("wms:sku:remove")
    @Log(title = "SKU信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{skuIds}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] skuIds) {
        return toAjax(basSkuService.deleteWithValidByIds(List.of(skuIds), true));
    }
}
