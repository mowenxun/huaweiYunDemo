package com.deepexi.dd.system.mall.service.app.impl;

import com.deepexi.dd.domain.tool.domain.dto.ToolStatusAllResponseDTO;
import com.deepexi.dd.domain.tool.domain.query.ToolStatusAllRequestQuery;
import com.deepexi.dd.system.mall.remote.tool.ToolStatusClient;
import com.deepexi.dd.system.mall.service.app.ToolStatusService;
import com.deepexi.dd.system.mall.util.GeneralConvertUtils;
import com.deepexi.util.config.Payload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: mumu
 * @Datetime: 2020/8/29   17:11
 * @version: v1.0
 */
@Service
public class ToolStatusServiceImpl implements ToolStatusService {

    private static final String SUCCESS = "0";

    @Autowired
    private ToolStatusClient toolStatusClient;

    @Override
    public List<ToolStatusAllResponseDTO> listAll(ToolStatusAllRequestQuery query) throws Exception {
//        try {
        Payload<List<ToolStatusAllResponseDTO>> listPayload = toolStatusClient.listAll(query);
        return GeneralConvertUtils.convert2List(listPayload.getPayload(), ToolStatusAllResponseDTO.class);
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw new ApplicationException("获取工具域查询状态列表失败");
//        }
    }
}
