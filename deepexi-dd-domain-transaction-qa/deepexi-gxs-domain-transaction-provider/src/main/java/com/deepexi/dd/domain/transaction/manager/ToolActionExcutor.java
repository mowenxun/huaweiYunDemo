package com.deepexi.dd.domain.transaction.manager;

import com.deepexi.dd.domain.common.util.GeneralConvertUtils;
import com.deepexi.dd.domain.tool.domain.dto.ToolLinkNextBusinessRequestDTO;
import com.deepexi.dd.domain.tool.domain.dto.ToolLinkNextBusinessResponseDTO;
import com.deepexi.dd.domain.transaction.domain.TenantDTO;
import com.deepexi.dd.domain.transaction.extension.ToolActionFactory;
import com.deepexi.dd.domain.transaction.remote.order.ToolLinkClient;
import com.deepexi.dd.domain.transaction.service.common.ToolLinkService;
import com.deepexi.iam.security.autoconfiguration.spring.SpringContextUtils;
import com.deepexi.util.JsonUtil;
import com.deepexi.util.config.Payload;
import com.deepexi.util.pojo.AbstractObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.tools.Tool;
import java.util.Map;

/**
 * @ClassName : ToolActionExcutor
 * @Description : 业务链路扭转器
 * @Author : yuanzaishun
 * @Date: 2020-08-05 11:29
 */
@Component
public class ToolActionExcutor<T> {
    Logger logger= LoggerFactory.getLogger(ToolActionExcutor.class);
    @Autowired
    private ToolActionFactory<T> toolActionFactory;
    @Autowired
    private ToolLinkClient toolLinkClient;
    /**
     * 执行扭转
     * @param actionCode 环节编码
     * @param id 需要扭转的单据id
     */
    public void excute(String actionCode,String listType,Long id,String tenanId,Long appId) throws Exception {
        logger.info("执行订单:{}业务链路:{},环节:{}",id,listType,actionCode);
        AbstractToolAction<T> toolAction = toolActionFactory.getToolAction(actionCode, listType);
        AbstractToolAction<T> action= toolAction;
        if(action==null){
            logger.error("环节获取为空,不执行..");
            return;
        }
        logger.info("获取业务链路环节:{}",action);
        T t=  action.getOrderObj(id);
        logger.info("获取单据信息:{}", t);
        boolean validStatu= action.validationOrder(t);
        logger.info("条件验证是否通过:{}",validStatu);
        //当当前步骤为可执行状态时，则执行
        if(validStatu) {
            ToolLinkNextBusinessRequestDTO toolLink = new ToolLinkNextBusinessRequestDTO();
            //订单ID
            toolLink.setListId(id);
            //类型 例如发货：SalesOrder
            toolLink.setListType(action.getListType());
            //版本号
            logger.info("获取业务链路版本号");
            toolLink.setVersion(action.getVersion(id));
            toolLink.setBusinessCode(actionCode);
            toolLink.setTenantId(tenanId);
            toolLink.setAppId(appId);
            logger.info("执行业务链路扭转..");
            //调用业务链路下一步接口获取状态 更新订单主状态
            Payload<ToolLinkNextBusinessResponseDTO> toolLinkNextBusinessResponseDTOPayload = toolLinkClient.nextLinkBusiness(toolLink);
            ToolLinkNextBusinessResponseDTO toolLinkNextBusinessResponseDTO = GeneralConvertUtils.conv(toolLinkNextBusinessResponseDTOPayload.getPayload(),
                    ToolLinkNextBusinessResponseDTO.class);
            logger.info("业务链路执行结果:{}",JsonUtil.bean2JsonString(toolLinkNextBusinessResponseDTO));
            logger.info("执行业务链路回调...");
            //回调回调执行结果
            action.callback(toolLinkNextBusinessResponseDTO,t);
            //如果返回的CODE不等于当前CODE,继续执行下一步操作
            if(!actionCode.equals(toolLinkNextBusinessResponseDTO.getBusinessCode())) {
                logger.info("继续执行下一步操作:{}",toolLinkNextBusinessResponseDTO.getBusinessCode());
                excute(toolLinkNextBusinessResponseDTO.getBusinessCode(), listType, id,tenanId,appId);
            }
        }else{//否则不执行
            return ;
        }
    }



}
