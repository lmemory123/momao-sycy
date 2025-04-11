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
import com.momao.wms.domain.bo.WmsOutOrderDetailBo;
import com.momao.wms.domain.vo.WmsOutOrderDetailVo;
import com.momao.wms.service.IWmsOutOrderDetailService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 出库单详情
 *
 * @author Mo mao
 * @date 2025-04-11
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/wms/outOrderDetail")
public class WmsOutOrderDetailController extends BaseController {

    private final IWmsOutOrderDetailService wmsOutOrderDetailService;

    /**
     * 查询出库单详情列表
     */
    @SaCheckPermission("wms:outOrderDetail:list")
    @GetMapping("/list")
    public TableDataInfo<WmsOutOrderDetailVo> list(WmsOutOrderDetailBo bo, PageQuery pageQuery) {
        return wmsOutOrderDetailService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出出库单详情列表
     */
    @SaCheckPermission("wms:outOrderDetail:export")
    @Log(title = "出库单详情", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(WmsOutOrderDetailBo bo, HttpServletResponse response) {
        List<WmsOutOrderDetailVo> list = wmsOutOrderDetailService.queryList(bo);
        ExcelUtil.exportExcel(list, "出库单详情", WmsOutOrderDetailVo.class, response);
    }

    /**
     * 获取出库单详情详细信息
     *
     * @param detailId 主键
     */
    @SaCheckPermission("wms:outOrderDetail:query")
    @GetMapping("/{detailId}")
    public R<WmsOutOrderDetailVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long detailId) {
        return R.ok(wmsOutOrderDetailService.queryById(detailId));
    }

    /**
     * 新增出库单详情
     */
    @SaCheckPermission("wms:outOrderDetail:add")
    @Log(title = "出库单详情", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody WmsOutOrderDetailBo bo) {
        return toAjax(wmsOutOrderDetailService.insertByBo(bo));
    }

    /**
     * 修改出库单详情
     */
    @SaCheckPermission("wms:outOrderDetail:edit")
    @Log(title = "出库单详情", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody WmsOutOrderDetailBo bo) {
        return toAjax(wmsOutOrderDetailService.updateByBo(bo));
    }

    /**
     * 删除出库单详情
     *
     * @param detailIds 主键串
     */
    @SaCheckPermission("wms:outOrderDetail:remove")
    @Log(title = "出库单详情", businessType = BusinessType.DELETE)
    @DeleteMapping("/{detailIds}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] detailIds) {
        return toAjax(wmsOutOrderDetailService.deleteWithValidByIds(List.of(detailIds), true));
    }
}
