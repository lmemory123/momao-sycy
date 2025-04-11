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
import com.momao.wms.domain.bo.WmsOrderLogBo;
import com.momao.wms.domain.vo.WmsOrderLogVo;
import com.momao.wms.service.IWmsOrderLogService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 单据操作日志
 *
 * @author Mo mao
 * @date 2025-04-11
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/wms/orderLog")
public class WmsOrderLogController extends BaseController {

    private final IWmsOrderLogService wmsOrderLogService;

    /**
     * 查询单据操作日志列表
     */
    @SaCheckPermission("wms:orderLog:list")
    @GetMapping("/list")
    public TableDataInfo<WmsOrderLogVo> list(WmsOrderLogBo bo, PageQuery pageQuery) {
        return wmsOrderLogService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出单据操作日志列表
     */
    @SaCheckPermission("wms:orderLog:export")
    @Log(title = "单据操作日志", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(WmsOrderLogBo bo, HttpServletResponse response) {
        List<WmsOrderLogVo> list = wmsOrderLogService.queryList(bo);
        ExcelUtil.exportExcel(list, "单据操作日志", WmsOrderLogVo.class, response);
    }

    /**
     * 获取单据操作日志详细信息
     *
     * @param logId 主键
     */
    @SaCheckPermission("wms:orderLog:query")
    @GetMapping("/{logId}")
    public R<WmsOrderLogVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long logId) {
        return R.ok(wmsOrderLogService.queryById(logId));
    }

    /**
     * 新增单据操作日志
     */
    @SaCheckPermission("wms:orderLog:add")
    @Log(title = "单据操作日志", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody WmsOrderLogBo bo) {
        return toAjax(wmsOrderLogService.insertByBo(bo));
    }

    /**
     * 修改单据操作日志
     */
    @SaCheckPermission("wms:orderLog:edit")
    @Log(title = "单据操作日志", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody WmsOrderLogBo bo) {
        return toAjax(wmsOrderLogService.updateByBo(bo));
    }

    /**
     * 删除单据操作日志
     *
     * @param logIds 主键串
     */
    @SaCheckPermission("wms:orderLog:remove")
    @Log(title = "单据操作日志", businessType = BusinessType.DELETE)
    @DeleteMapping("/{logIds}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] logIds) {
        return toAjax(wmsOrderLogService.deleteWithValidByIds(List.of(logIds), true));
    }
}
