package com.deepexi.dd.system.mall.service.app;

import com.deepexi.dd.middle.tool.domain.dto.ToolBilltypeResponseDTO;
import com.deepexi.dd.middle.tool.domain.dto.ToolBilltypeTabResponseDTO;
import com.deepexi.dd.middle.tool.domain.query.ToolBilltypeRequestQuery;

import java.util.List;

/**
 * @author yy
 * @version 1.0
 * @date 2020-09-01 23:39
 */
public interface ToolBilltypeService {

    /**
     * 获取单据类型列表
     * @param query
     * @return
     * @throws Exception
     */
    List<ToolBilltypeResponseDTO> listToolBilltype(ToolBilltypeRequestQuery query) throws Exception;

    /**
     * 单据类型id获取页签列表
     * @param id
     * @return
     * @throws Exception
     */
    List<ToolBilltypeTabResponseDTO> getTabList(Long id) throws Exception;
}
