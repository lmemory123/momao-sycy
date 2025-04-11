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
import com.momao.wms.domain.bo.WmsCheckDetailBo;
import com.momao.wms.domain.vo.WmsCheckDetailVo;
import com.momao.wms.service.IWmsCheckDetailService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 盘点单详情
 *
 * @author Mo mao
 * @date 2025-04-11
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/wms/checkDetail")
public class WmsCheckDetailController extends BaseController {

    private final IWmsCheckDetailService wmsCheckDetailService;

    /**
     * 查询盘点单详情列表
     */
    @SaCheckPermission("wms:checkDetail:list")
    @GetMapping("/list")
    public TableDataInfo<WmsCheckDetailVo> list(WmsCheckDetailBo bo, PageQuery pageQuery) {
        return wmsCheckDetailService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出盘点单详情列表
     */
    @SaCheckPermission("wms:checkDetail:export")
    @Log(title = "盘点单详情", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(WmsCheckDetailBo bo, HttpServletResponse response) {
        List<WmsCheckDetailVo> list = wmsCheckDetailService.queryList(bo);
        ExcelUtil.exportExcel(list, "盘点单详情", WmsCheckDetailVo.class, response);
    }

    /**
     * 获取盘点单详情详细信息
     *
     * @param detailId 主键
     */
    @SaCheckPermission("wms:checkDetail:query")
    @GetMapping("/{detailId}")
    public R<WmsCheckDetailVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long detailId) {
        return R.ok(wmsCheckDetailService.queryById(detailId));
    }

    /**
     * 新增盘点单详情
     */
    @SaCheckPermission("wms:checkDetail:add")
    @Log(title = "盘点单详情", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody WmsCheckDetailBo bo) {
        return toAjax(wmsCheckDetailService.insertByBo(bo));
    }

    /**
     * 修改盘点单详情
     */
    @SaCheckPermission("wms:checkDetail:edit")
    @Log(title = "盘点单详情", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody WmsCheckDetailBo bo) {
        return toAjax(wmsCheckDetailService.updateByBo(bo));
    }

    /**
     * 删除盘点单详情
     *
     * @param detailIds 主键串
     */
    @SaCheckPermission("wms:checkDetail:remove")
    @Log(title = "盘点单详情", businessType = BusinessType.DELETE)
    @DeleteMapping("/{detailIds}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] detailIds) {
        return toAjax(wmsCheckDetailService.deleteWithValidByIds(List.of(detailIds), true));
    }
}
