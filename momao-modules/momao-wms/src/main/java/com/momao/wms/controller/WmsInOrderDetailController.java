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
import com.momao.wms.domain.bo.WmsInOrderDetailBo;
import com.momao.wms.domain.vo.WmsInOrderDetailVo;
import com.momao.wms.service.IWmsInOrderDetailService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 入库单详情
 *
 * @author Mo mao
 * @date 2025-04-11
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/wms/inOrderDetail")
public class WmsInOrderDetailController extends BaseController {

    private final IWmsInOrderDetailService wmsInOrderDetailService;

    /**
     * 查询入库单详情列表
     */
    @SaCheckPermission("wms:inOrderDetail:list")
    @GetMapping("/list")
    public TableDataInfo<WmsInOrderDetailVo> list(WmsInOrderDetailBo bo, PageQuery pageQuery) {
        return wmsInOrderDetailService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出入库单详情列表
     */
    @SaCheckPermission("wms:inOrderDetail:export")
    @Log(title = "入库单详情", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(WmsInOrderDetailBo bo, HttpServletResponse response) {
        List<WmsInOrderDetailVo> list = wmsInOrderDetailService.queryList(bo);
        ExcelUtil.exportExcel(list, "入库单详情", WmsInOrderDetailVo.class, response);
    }

    /**
     * 获取入库单详情详细信息
     *
     * @param detailId 主键
     */
    @SaCheckPermission("wms:inOrderDetail:query")
    @GetMapping("/{detailId}")
    public R<WmsInOrderDetailVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long detailId) {
        return R.ok(wmsInOrderDetailService.queryById(detailId));
    }

    /**
     * 新增入库单详情
     */
    @SaCheckPermission("wms:inOrderDetail:add")
    @Log(title = "入库单详情", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody WmsInOrderDetailBo bo) {
        return toAjax(wmsInOrderDetailService.insertByBo(bo));
    }

    /**
     * 修改入库单详情
     */
    @SaCheckPermission("wms:inOrderDetail:edit")
    @Log(title = "入库单详情", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody WmsInOrderDetailBo bo) {
        return toAjax(wmsInOrderDetailService.updateByBo(bo));
    }

    /**
     * 删除入库单详情
     *
     * @param detailIds 主键串
     */
    @SaCheckPermission("wms:inOrderDetail:remove")
    @Log(title = "入库单详情", businessType = BusinessType.DELETE)
    @DeleteMapping("/{detailIds}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] detailIds) {
        return toAjax(wmsInOrderDetailService.deleteWithValidByIds(List.of(detailIds), true));
    }
}
