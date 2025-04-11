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
import com.momao.wms.domain.bo.WmsLocationBo;
import com.momao.wms.domain.vo.WmsLocationVo;
import com.momao.wms.service.IWmsLocationService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 库位信息
 *
 * @author Mo mao
 * @date 2025-04-11
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/wms/location")
public class WmsLocationController extends BaseController {

    private final IWmsLocationService wmsLocationService;

    /**
     * 查询库位信息列表
     */
    @SaCheckPermission("wms:location:list")
    @GetMapping("/list")
    public TableDataInfo<WmsLocationVo> list(WmsLocationBo bo, PageQuery pageQuery) {
        return wmsLocationService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出库位信息列表
     */
    @SaCheckPermission("wms:location:export")
    @Log(title = "库位信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(WmsLocationBo bo, HttpServletResponse response) {
        List<WmsLocationVo> list = wmsLocationService.queryList(bo);
        ExcelUtil.exportExcel(list, "库位信息", WmsLocationVo.class, response);
    }

    /**
     * 获取库位信息详细信息
     *
     * @param locationId 主键
     */
    @SaCheckPermission("wms:location:query")
    @GetMapping("/{locationId}")
    public R<WmsLocationVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long locationId) {
        return R.ok(wmsLocationService.queryById(locationId));
    }

    /**
     * 新增库位信息
     */
    @SaCheckPermission("wms:location:add")
    @Log(title = "库位信息", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody WmsLocationBo bo) {
        return toAjax(wmsLocationService.insertByBo(bo));
    }

    /**
     * 修改库位信息
     */
    @SaCheckPermission("wms:location:edit")
    @Log(title = "库位信息", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody WmsLocationBo bo) {
        return toAjax(wmsLocationService.updateByBo(bo));
    }

    /**
     * 删除库位信息
     *
     * @param locationIds 主键串
     */
    @SaCheckPermission("wms:location:remove")
    @Log(title = "库位信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{locationIds}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] locationIds) {
        return toAjax(wmsLocationService.deleteWithValidByIds(List.of(locationIds), true));
    }
}
