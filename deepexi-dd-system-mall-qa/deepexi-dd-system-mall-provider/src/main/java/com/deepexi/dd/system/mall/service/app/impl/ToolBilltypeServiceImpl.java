package com.deepexi.dd.system.mall.service.app.impl;

import com.deepexi.dd.middle.tool.domain.dto.ToolBilltypeResponseDTO;
import com.deepexi.dd.middle.tool.domain.dto.ToolBilltypeTabResponseDTO;
import com.deepexi.dd.middle.tool.domain.query.ToolBilltypeRequestQuery;
import com.deepexi.dd.system.mall.remote.tool.ToolBilltypeRemote;
import com.deepexi.dd.system.mall.service.app.ToolBilltypeService;
import com.deepexi.dd.system.mall.util.GeneralConvertUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yy
 * @version 1.0
 * @date 2020-09-01 23:40
 */
@Service
@Slf4j
public class ToolBilltypeServiceImpl implements ToolBilltypeService {

    @Autowired
    private ToolBilltypeRemote toolBilltypeRemote;

    @Override
    public List<ToolBilltypeResponseDTO> listToolBilltype(ToolBilltypeRequestQuery query) throws Exception {
        ToolBilltypeRequestQuery toolBilltypeRequestQuery = query.clone(ToolBilltypeRequestQuery.class);
        //toolBilltypeRequestQuery.setIsolationId(this.getTopOrganizationId().toString());
        toolBilltypeRequestQuery.setIsEnabled(1);
        return GeneralConvertUtils.convert2List(toolBilltypeRemote.listToolBilltypes(toolBilltypeRequestQuery).getPayload(),ToolBilltypeResponseDTO.class);
    }

    @Override
    public List<ToolBilltypeTabResponseDTO> getTabList(Long id) throws Exception {
        ToolBilltypeResponseDTO toolBilltypeResponseDTO = GeneralConvertUtils.conv(toolBilltypeRemote.selectById(id).getPayload(),ToolBilltypeResponseDTO.class);
        if(toolBilltypeResponseDTO!=null){
            return toolBilltypeResponseDTO.getToolBilltypeTabResponseDTOList();
        }
        return new ArrayList<>();
    }

}
