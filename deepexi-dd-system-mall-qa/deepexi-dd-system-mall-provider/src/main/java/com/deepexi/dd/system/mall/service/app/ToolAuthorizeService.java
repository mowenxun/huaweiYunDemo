package com.deepexi.dd.system.mall.service.app;

import com.deepexi.dd.domain.tool.domain.dto.ToolStatusAllResponseDTO;
import com.deepexi.dd.domain.tool.domain.query.ToolStatusAllRequestQuery;
import com.deepexi.dd.domain.tool.domain.query.ToolUserRequestQuery;
import com.deepexi.dd.middle.tool.domain.dto.ToolAuthorizeCommodityResponseDTO;
import com.deepexi.dd.middle.tool.domain.query.ToolAuthorizeCommodityInfoQuery;
import com.deepexi.util.config.Payload;

import java.util.List;

/**
 * @Author: zhaochongsen
 * @Datetime: 2020/8/29   17:11
 * @version: v1.0
 */
public interface ToolAuthorizeService {

    List<ToolAuthorizeCommodityResponseDTO> listAuthorizeCommodityInfo(ToolAuthorizeCommodityInfoQuery query) throws Exception;

    Boolean getUserPriceAuthorize(ToolUserRequestQuery query) throws Exception;

}
