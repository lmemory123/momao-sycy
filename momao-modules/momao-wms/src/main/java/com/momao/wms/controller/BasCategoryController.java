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
import com.momao.wms.domain.bo.BasCategoryBo;
import com.momao.wms.domain.vo.BasCategoryVo;
import com.momao.wms.service.IBasCategoryService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * SKU分类
 *
 * @author Mo mao
 * @date 2025-04-11
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/wms/category")
public class BasCategoryController extends BaseController {

    private final IBasCategoryService basCategoryService;

    /**
     * 查询SKU分类列表
     */
    @SaCheckPermission("wms:category:list")
    @GetMapping("/list")
    public TableDataInfo<BasCategoryVo> list(BasCategoryBo bo, PageQuery pageQuery) {
        return basCategoryService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出SKU分类列表
     */
    @SaCheckPermission("wms:category:export")
    @Log(title = "SKU分类", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(BasCategoryBo bo, HttpServletResponse response) {
        List<BasCategoryVo> list = basCategoryService.queryList(bo);
        ExcelUtil.exportExcel(list, "SKU分类", BasCategoryVo.class, response);
    }

    /**
     * 获取SKU分类详细信息
     *
     * @param categoryId 主键
     */
    @SaCheckPermission("wms:category:query")
    @GetMapping("/{categoryId}")
    public R<BasCategoryVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long categoryId) {
        return R.ok(basCategoryService.queryById(categoryId));
    }

    /**
     * 新增SKU分类
     */
    @SaCheckPermission("wms:category:add")
    @Log(title = "SKU分类", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody BasCategoryBo bo) {
        return toAjax(basCategoryService.insertByBo(bo));
    }

    /**
     * 修改SKU分类
     */
    @SaCheckPermission("wms:category:edit")
    @Log(title = "SKU分类", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody BasCategoryBo bo) {
        return toAjax(basCategoryService.updateByBo(bo));
    }

    /**
     * 删除SKU分类
     *
     * @param categoryIds 主键串
     */
    @SaCheckPermission("wms:category:remove")
    @Log(title = "SKU分类", businessType = BusinessType.DELETE)
    @DeleteMapping("/{categoryIds}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] categoryIds) {
        return toAjax(basCategoryService.deleteWithValidByIds(List.of(categoryIds), true));
    }
}
