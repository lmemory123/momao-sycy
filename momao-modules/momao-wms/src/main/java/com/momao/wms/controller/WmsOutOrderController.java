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
import com.momao.wms.domain.bo.WmsOutOrderBo;
import com.momao.wms.domain.vo.WmsOutOrderVo;
import com.momao.wms.service.IWmsOutOrderService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 出库单
 *
 * @author Mo mao
 * @date 2025-04-11
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/wms/outOrder")
public class WmsOutOrderController extends BaseController {

    private final IWmsOutOrderService wmsOutOrderService;

    /**
     * 查询出库单列表
     */
    @SaCheckPermission("wms:outOrder:list")
    @GetMapping("/list")
    public TableDataInfo<WmsOutOrderVo> list(WmsOutOrderBo bo, PageQuery pageQuery) {
        return wmsOutOrderService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出出库单列表
     */
    @SaCheckPermission("wms:outOrder:export")
    @Log(title = "出库单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(WmsOutOrderBo bo, HttpServletResponse response) {
        List<WmsOutOrderVo> list = wmsOutOrderService.queryList(bo);
        ExcelUtil.exportExcel(list, "出库单", WmsOutOrderVo.class, response);
    }

    /**
     * 获取出库单详细信息
     *
     * @param orderId 主键
     */
    @SaCheckPermission("wms:outOrder:query")
    @GetMapping("/{orderId}")
    public R<WmsOutOrderVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long orderId) {
        return R.ok(wmsOutOrderService.queryById(orderId));
    }

    /**
     * 新增出库单
     */
    @SaCheckPermission("wms:outOrder:add")
    @Log(title = "出库单", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody WmsOutOrderBo bo) {
        return toAjax(wmsOutOrderService.insertByBo(bo));
    }

    /**
     * 修改出库单
     */
    @SaCheckPermission("wms:outOrder:edit")
    @Log(title = "出库单", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody WmsOutOrderBo bo) {
        return toAjax(wmsOutOrderService.updateByBo(bo));
    }

    /**
     * 删除出库单
     *
     * @param orderIds 主键串
     */
    @SaCheckPermission("wms:outOrder:remove")
    @Log(title = "出库单", businessType = BusinessType.DELETE)
    @DeleteMapping("/{orderIds}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] orderIds) {
        return toAjax(wmsOutOrderService.deleteWithValidByIds(List.of(orderIds), true));
    }
}
