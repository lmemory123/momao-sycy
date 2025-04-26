package com.momao.system.mapper;

import com.momao.system.domain.TableColumn;
import com.momao.system.domain.vo.TableColumnVo;
import com.momao.common.mybatis.core.mapper.BaseMapperPlus;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 字段配置Mapper接口
 *
 * @author Mo mao
 * @date 2025-04-14
 */
public interface TableColumnMapper extends BaseMapperPlus<TableColumn, TableColumnVo> {

    List<TableColumnVo> selectUserColumns(@Param("tableName") String tableName, @Param("userId") Long userId);
}
