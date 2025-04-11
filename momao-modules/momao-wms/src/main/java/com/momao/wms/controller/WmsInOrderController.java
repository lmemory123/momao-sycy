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
import com.momao.wms.domain.bo.WmsInOrderBo;
import com.momao.wms.domain.vo.WmsInOrderVo;
import com.momao.wms.service.IWmsInOrderService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 入库单
 *
 * @author Mo mao
 * @date 2025-04-11
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/wms/inOrder")
public class WmsInOrderController extends BaseController {

    private final IWmsInOrderService wmsInOrderService;

    /**
     * 查询入库单列表
     */
    @SaCheckPermission("wms:inOrder:list")
    @GetMapping("/list")
    public TableDataInfo<WmsInOrderVo> list(WmsInOrderBo bo, PageQuery pageQuery) {
        return wmsInOrderService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出入库单列表
     */
    @SaCheckPermission("wms:inOrder:export")
    @Log(title = "入库单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(WmsInOrderBo bo, HttpServletResponse response) {
        List<WmsInOrderVo> list = wmsInOrderService.queryList(bo);
        ExcelUtil.exportExcel(list, "入库单", WmsInOrderVo.class, response);
    }

    /**
     * 获取入库单详细信息
     *
     * @param orderId 主键
     */
    @SaCheckPermission("wms:inOrder:query")
    @GetMapping("/{orderId}")
    public R<WmsInOrderVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long orderId) {
        return R.ok(wmsInOrderService.queryById(orderId));
    }

    /**
     * 新增入库单
     */
    @SaCheckPermission("wms:inOrder:add")
    @Log(title = "入库单", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody WmsInOrderBo bo) {
        return toAjax(wmsInOrderService.insertByBo(bo));
    }

    /**
     * 修改入库单
     */
    @SaCheckPermission("wms:inOrder:edit")
    @Log(title = "入库单", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody WmsInOrderBo bo) {
        return toAjax(wmsInOrderService.updateByBo(bo));
    }

    /**
     * 删除入库单
     *
     * @param orderIds 主键串
     */
    @SaCheckPermission("wms:inOrder:remove")
    @Log(title = "入库单", businessType = BusinessType.DELETE)
    @DeleteMapping("/{orderIds}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] orderIds) {
        return toAjax(wmsInOrderService.deleteWithValidByIds(List.of(orderIds), true));
    }
}
