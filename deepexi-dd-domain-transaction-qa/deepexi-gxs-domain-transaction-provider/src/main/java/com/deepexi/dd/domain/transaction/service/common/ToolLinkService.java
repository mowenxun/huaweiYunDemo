package com.deepexi.dd.domain.transaction.service.common;

import com.alibaba.fastjson.JSON;
import com.deepexi.dd.domain.common.util.GeneralConvertUtils;
import com.deepexi.dd.domain.tool.domain.dto.ToolLinkListResponseDTO;
import com.deepexi.dd.domain.tool.domain.dto.ToolLinkNextBusinessRequestDTO;
import com.deepexi.dd.domain.tool.domain.dto.ToolLinkNextBusinessResponseDTO;
import com.deepexi.dd.domain.tool.domain.query.ToolLinkListRequestQuery;
import com.deepexi.dd.domain.tool.domain.query.ToolStatusRequestQuery;
import com.deepexi.dd.domain.transaction.domain.dto.LinkBusinessRequestDTO;
import com.deepexi.dd.domain.transaction.enums.ResultEnum;
import com.deepexi.dd.domain.transaction.remote.order.SaleOrderInfoClient;
import com.deepexi.dd.domain.transaction.remote.order.ToolLinkClient;
import com.deepexi.dd.domain.transaction.remote.order.ToolStatusClient;
import com.deepexi.dd.middle.order.domain.dto.SaleOrderInfoResponseDTO;
import com.deepexi.dd.middle.order.domain.dto.SaleOrderInfoStatusEditRequestDTO;
import com.deepexi.dd.middle.tool.domain.dto.ToolLinkResponseDTO;
import com.deepexi.util.JsonUtil;
import com.deepexi.util.config.Payload;
import com.deepexi.util.extension.ApplicationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

@Component
@Slf4j
public class ToolLinkService {

    @Resource
    private ToolStatusClient toolStatusClient;

    @Resource
    SaleOrderInfoClient saleOrderInfoClient;

    @Resource
    ToolLinkClient toolLinkClient;

    /**
     * @Description: 业务链路开始下一步.
     * @Param: [id]
     * @return: boolean
     * @Author: SongTao
     * @Date: 2020/7/16
     */
    public String nextLinkBusiness(LinkBusinessRequestDTO linkBusinessRequestDTO) throws Exception {
        log.info("业务链路状态流转入参: {}", JSON.toJSONString(linkBusinessRequestDTO));
        //开发环境 appId:720 tenantId:gree ruleData:需要穿json格式的字符串 例如："{"deliveryGoodsQuantity":200}" listType：对应业务操作
        if (Objects.isNull(linkBusinessRequestDTO)){
            return null;
        }
        Long id = linkBusinessRequestDTO.getId();
        Payload<SaleOrderInfoResponseDTO> payload = saleOrderInfoClient.selectById(id);
        SaleOrderInfoResponseDTO saleOrderInfoResponseDTO = GeneralConvertUtils.conv(payload.getPayload(), SaleOrderInfoResponseDTO.class);
        ToolLinkNextBusinessRequestDTO toolLink = saleOrderInfoResponseDTO.clone(ToolLinkNextBusinessRequestDTO.class);
        toolLink.setListId(id);//订单ID
        toolLink.setListType(linkBusinessRequestDTO.getListType());//类型 例如发货：SalesOrder
        toolLink.setVersion(getToolLinkVersion(id));//版本号
        toolLink.setBusinessCode(linkBusinessRequestDTO.getBusinessCode());
        toolLink.setActionCode(linkBusinessRequestDTO.getActionCode());
        //调用业务链路下一步接口获取状态 更新订单主状态
        Payload<ToolLinkNextBusinessResponseDTO> toolLinkNextBusinessResponseDTOPayload = toolLinkClient.nextLinkBusiness(toolLink);
        ToolLinkNextBusinessResponseDTO toolLinkNextBusinessResponseDTO = GeneralConvertUtils.conv(toolLinkNextBusinessResponseDTOPayload.getPayload(),
            ToolLinkNextBusinessResponseDTO.class);
        log.info("获取环节状态:{}", JsonUtil.bean2JsonString(toolLinkNextBusinessResponseDTOPayload));
         updateSaleOrderStatus(saleOrderInfoResponseDTO.getId(),toolLinkNextBusinessResponseDTO.getStatus());//更改订单状态
        return toolLinkNextBusinessResponseDTO.getBusinessCode();
    }

    /**
     * @Description: 获取业务链路当前状态.
     * @Param: [id]
     * @return: java.lang.Boolean
     * @Author: SongTao
     * @Date: 2020/7/16
     */
    public Integer getLinkBusinessStatus(LinkBusinessRequestDTO linkBusinessRequestDTO) throws Exception {
        log.info("获取业务链路当前状态入参: {}", JSON.toJSONString(linkBusinessRequestDTO));
        ToolStatusRequestQuery query = new ToolStatusRequestQuery();
        query.setActionCode(linkBusinessRequestDTO.getActionCode());
        query.setAppId(linkBusinessRequestDTO.getAppId());
        query.setTenantId(linkBusinessRequestDTO.getTenantId());
        query.setListType("SalesOrder");
        query.setRuleData(linkBusinessRequestDTO.getRuleData());
        Payload<Integer> codePayload = toolStatusClient.getStatusCode(query);
        log.info("获取链路状态:{}", JSON.toJSONString(codePayload.getPayload()));
        return codePayload.getPayload();
    }
    /**
     * @Description:  根据订单Id拿到业务链路对应的版本号.
     * @Param: [id]
     * @return: java.lang.Integer
     * @Author: SongTao
     * @Date: 2020/7/23
     */
    public Integer getToolLinkVersion(Long id) throws Exception {
        if (Objects.isNull(id)){//订单不存在
            throw new ApplicationException(ResultEnum.ORDER_NOT_FOUND);
        }
        ToolLinkListRequestQuery query = new ToolLinkListRequestQuery();
        query.setListId(id);
        Payload<List<ToolLinkListResponseDTO>> listPayload = toolLinkClient.listToolLinkLists(query);
        List<ToolLinkResponseDTO> toolLinkList = GeneralConvertUtils.convert2List(listPayload.getPayload(), ToolLinkResponseDTO.class);
        if (CollectionUtils.isEmpty(toolLinkList)){
            throw new ApplicationException(ResultEnum.ORDER_NOT_FOUND);
        }
        return toolLinkList.get(0).getVersion();
    }
    /**
     * @Description:  拿到业务链路的状态更改订单状态.
     * @Param: [id, status]
     * @return: java.lang.Boolean
     * @Author: SongTao
     * @Date: 2020/7/23
     */
    public Boolean updateSaleOrderStatus(Long id, Integer status) throws Exception {
        log.info("获取链路状态 id:{},status:{}", id,status);
        SaleOrderInfoStatusEditRequestDTO requestDTO = new SaleOrderInfoStatusEditRequestDTO();
        requestDTO.setId(id);
        requestDTO.setStatus(status);
        return saleOrderInfoClient.updateOrderStatus(requestDTO).getPayload();//更改订单状态
    }
}
