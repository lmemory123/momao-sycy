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
import com.momao.wms.domain.bo.WmsAreaBo;
import com.momao.wms.domain.vo.WmsAreaVo;
import com.momao.wms.service.IWmsAreaService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 库区信息
 *
 * @author Mo mao
 * @date 2025-04-11
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/wms/area")
public class WmsAreaController extends BaseController {

    private final IWmsAreaService wmsAreaService;

    /**
     * 查询库区信息列表
     */
    @SaCheckPermission("wms:area:list")
    @GetMapping("/list")
    public TableDataInfo<WmsAreaVo> list(WmsAreaBo bo, PageQuery pageQuery) {
        return wmsAreaService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出库区信息列表
     */
    @SaCheckPermission("wms:area:export")
    @Log(title = "库区信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(WmsAreaBo bo, HttpServletResponse response) {
        List<WmsAreaVo> list = wmsAreaService.queryList(bo);
        ExcelUtil.exportExcel(list, "库区信息", WmsAreaVo.class, response);
    }

    /**
     * 获取库区信息详细信息
     *
     * @param areaId 主键
     */
    @SaCheckPermission("wms:area:query")
    @GetMapping("/{areaId}")
    public R<WmsAreaVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long areaId) {
        return R.ok(wmsAreaService.queryById(areaId));
    }

    /**
     * 新增库区信息
     */
    @SaCheckPermission("wms:area:add")
    @Log(title = "库区信息", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody WmsAreaBo bo) {
        return toAjax(wmsAreaService.insertByBo(bo));
    }

    /**
     * 修改库区信息
     */
    @SaCheckPermission("wms:area:edit")
    @Log(title = "库区信息", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody WmsAreaBo bo) {
        return toAjax(wmsAreaService.updateByBo(bo));
    }

    /**
     * 删除库区信息
     *
     * @param areaIds 主键串
     */
    @SaCheckPermission("wms:area:remove")
    @Log(title = "库区信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{areaIds}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] areaIds) {
        return toAjax(wmsAreaService.deleteWithValidByIds(List.of(areaIds), true));
    }
}
