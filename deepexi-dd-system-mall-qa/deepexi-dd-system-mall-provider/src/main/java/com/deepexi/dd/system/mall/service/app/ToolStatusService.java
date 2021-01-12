package com.deepexi.dd.system.mall.service.app;

import com.deepexi.dd.domain.tool.domain.dto.ToolStatusAllResponseDTO;
import com.deepexi.dd.domain.tool.domain.query.ToolStatusAllRequestQuery;

import java.util.List;

/**
 * @Author: mumu
 * @Datetime: 2020/8/29   17:11
 * @version: v1.0
 */
public interface ToolStatusService {

    List<ToolStatusAllResponseDTO> listAll(ToolStatusAllRequestQuery query) throws Exception;

}
