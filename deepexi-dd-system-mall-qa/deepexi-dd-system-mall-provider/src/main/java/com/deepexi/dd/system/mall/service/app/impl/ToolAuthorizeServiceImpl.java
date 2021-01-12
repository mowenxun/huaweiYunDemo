package com.deepexi.dd.system.mall.service.app.impl;

import com.deepexi.dd.domain.tool.domain.query.ToolUserRequestQuery;
import com.deepexi.dd.middle.tool.domain.dto.ToolAuthorizeCommodityResponseDTO;
import com.deepexi.dd.middle.tool.domain.query.ToolAuthorizeCommodityInfoQuery;
import com.deepexi.dd.system.mall.remote.tool.ToolAuthorizeClient;
import com.deepexi.dd.system.mall.service.app.ToolAuthorizeService;
import com.deepexi.util.config.Payload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: zhaochongsen
 * @Datetime: 2020/8/29   17:11
 * @version: v1.0
 */
@Service
public class ToolAuthorizeServiceImpl implements ToolAuthorizeService {

    @Autowired
    private ToolAuthorizeClient toolAuthorizeClient;

    @Override
    public List<ToolAuthorizeCommodityResponseDTO> listAuthorizeCommodityInfo(ToolAuthorizeCommodityInfoQuery query) throws Exception {
        Payload<List<ToolAuthorizeCommodityResponseDTO>> listPayload = toolAuthorizeClient.listAuthorizeCommodityInfo(query);
        return listPayload.getPayload();
    }

    @Override
    public Boolean getUserPriceAuthorize(ToolUserRequestQuery query) throws Exception {
        Payload<Boolean> listPayload = toolAuthorizeClient.getUserPriceAuthorize(query);
        return listPayload.getPayload();
    }
}
