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
import com.momao.wms.domain.bo.BasUnitBo;
import com.momao.wms.domain.vo.BasUnitVo;
import com.momao.wms.service.IBasUnitService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 计量单位
 *
 * @author Mo mao
 * @date 2025-04-11
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/wms/unit")
public class BasUnitController extends BaseController {

    private final IBasUnitService basUnitService;

    /**
     * 查询计量单位列表
     */
    @SaCheckPermission("wms:unit:list")
    @GetMapping("/list")
    public TableDataInfo<BasUnitVo> list(BasUnitBo bo, PageQuery pageQuery) {
        return basUnitService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出计量单位列表
     */
    @SaCheckPermission("wms:unit:export")
    @Log(title = "计量单位", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(BasUnitBo bo, HttpServletResponse response) {
        List<BasUnitVo> list = basUnitService.queryList(bo);
        ExcelUtil.exportExcel(list, "计量单位", BasUnitVo.class, response);
    }

    /**
     * 获取计量单位详细信息
     *
     * @param unitId 主键
     */
    @SaCheckPermission("wms:unit:query")
    @GetMapping("/{unitId}")
    public R<BasUnitVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long unitId) {
        return R.ok(basUnitService.queryById(unitId));
    }

    /**
     * 新增计量单位
     */
    @SaCheckPermission("wms:unit:add")
    @Log(title = "计量单位", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody BasUnitBo bo) {
        return toAjax(basUnitService.insertByBo(bo));
    }

    /**
     * 修改计量单位
     */
    @SaCheckPermission("wms:unit:edit")
    @Log(title = "计量单位", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody BasUnitBo bo) {
        return toAjax(basUnitService.updateByBo(bo));
    }

    /**
     * 删除计量单位
     *
     * @param unitIds 主键串
     */
    @SaCheckPermission("wms:unit:remove")
    @Log(title = "计量单位", businessType = BusinessType.DELETE)
    @DeleteMapping("/{unitIds}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] unitIds) {
        return toAjax(basUnitService.deleteWithValidByIds(List.of(unitIds), true));
    }
}
