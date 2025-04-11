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
import com.momao.wms.domain.bo.WmsCheckOrderBo;
import com.momao.wms.domain.vo.WmsCheckOrderVo;
import com.momao.wms.service.IWmsCheckOrderService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 盘点单
 *
 * @author Mo mao
 * @date 2025-04-11
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/wms/checkOrder")
public class WmsCheckOrderController extends BaseController {

    private final IWmsCheckOrderService wmsCheckOrderService;

    /**
     * 查询盘点单列表
     */
    @SaCheckPermission("wms:checkOrder:list")
    @GetMapping("/list")
    public TableDataInfo<WmsCheckOrderVo> list(WmsCheckOrderBo bo, PageQuery pageQuery) {
        return wmsCheckOrderService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出盘点单列表
     */
    @SaCheckPermission("wms:checkOrder:export")
    @Log(title = "盘点单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(WmsCheckOrderBo bo, HttpServletResponse response) {
        List<WmsCheckOrderVo> list = wmsCheckOrderService.queryList(bo);
        ExcelUtil.exportExcel(list, "盘点单", WmsCheckOrderVo.class, response);
    }

    /**
     * 获取盘点单详细信息
     *
     * @param orderId 主键
     */
    @SaCheckPermission("wms:checkOrder:query")
    @GetMapping("/{orderId}")
    public R<WmsCheckOrderVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long orderId) {
        return R.ok(wmsCheckOrderService.queryById(orderId));
    }

    /**
     * 新增盘点单
     */
    @SaCheckPermission("wms:checkOrder:add")
    @Log(title = "盘点单", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody WmsCheckOrderBo bo) {
        return toAjax(wmsCheckOrderService.insertByBo(bo));
    }

    /**
     * 修改盘点单
     */
    @SaCheckPermission("wms:checkOrder:edit")
    @Log(title = "盘点单", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody WmsCheckOrderBo bo) {
        return toAjax(wmsCheckOrderService.updateByBo(bo));
    }

    /**
     * 删除盘点单
     *
     * @param orderIds 主键串
     */
    @SaCheckPermission("wms:checkOrder:remove")
    @Log(title = "盘点单", businessType = BusinessType.DELETE)
    @DeleteMapping("/{orderIds}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] orderIds) {
        return toAjax(wmsCheckOrderService.deleteWithValidByIds(List.of(orderIds), true));
    }
}
