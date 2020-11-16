package com.deepexi.dd.domain.transaction.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.deepexi.dd.domain.common.enums.IdentifierTypeEnum;
import com.deepexi.dd.domain.common.service.IdentifierGenerator;
import com.deepexi.dd.domain.tool.enums.LinkBusinessCodeEnum;
import com.deepexi.dd.domain.transaction.domain.dto.LinkBusinessRequestDTO;
import com.deepexi.dd.domain.transaction.domain.dto.OrderItemUpdateRequestDTO;
import com.deepexi.dd.domain.transaction.domain.dto.SaleOmsLogisticsTrajectoryInfoRequestDTO;
import com.deepexi.dd.domain.transaction.remote.order.SaleOmsLogisticsTrajectoryClient;
import com.deepexi.dd.domain.transaction.remote.order.SaleOrderItemLogisticsClient;
import com.deepexi.dd.domain.transaction.service.SaleOrderInfoService;
import com.deepexi.dd.domain.transaction.service.SaleOrderItemLogisticsService;
import com.deepexi.dd.domain.transaction.service.common.ToolLinkService;
import com.deepexi.dd.middle.order.domain.dto.SaleOmsLogisticsTrajectoryRequestDTO;
import com.deepexi.dd.middle.order.domain.dto.SaleOrderItemLogisticsRequestDTO;
import com.deepexi.dd.middle.order.domain.dto.SaleOrderResponseDTO;
import com.deepexi.util.DateUtils;
import com.netflix.discovery.converters.Auto;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @ClassName SaleOrderItemLogisticsServiceImpl
 * @Description OMS物流信息
 * @Author SongTao
 * @Date 2020-08-25
 * @Version 1.0
 **/
@Service
@Slf4j
public class SaleOrderItemLogisticsServiceImpl implements SaleOrderItemLogisticsService {

    @Autowired
    SaleOrderItemLogisticsClient saleOrderItemLogisticsClient;

    @Autowired
    private SaleOmsLogisticsTrajectoryClient saleOmsLogisticsTrajectoryClient;

    @Autowired
    IdentifierGenerator identifierGenerator;

    @Autowired
    private SaleOrderInfoService saleOrderInfoService;

    @Autowired
    private ToolLinkService toolLinkService;
    /**
     * @param dto
     * @Description: OMS同步发货状态、物流基础信息至B端.
     * @return: java.lang.Boolean
     * @Author: SongTao
     * @Date: 2020/8/25
     */
    @Override
    public Boolean orderItemUpdate(OrderItemUpdateRequestDTO dto) throws Exception {
        String orderNo = identifierGenerator.getIdentifier(IdentifierTypeEnum.ORDER_OUT_CODE.getType(), IdentifierTypeEnum.ORDER_OUT_CODE.getPrefix()
                + DateUtils.format(new Date(), "yyyyMMdd"), IdentifierTypeEnum.ORDER_OUT_CODE.getLen());//出库单编号
        dto.setSaleOutTaskCode(orderNo);
        Long saleOrderId =  saleOrderItemLogisticsClient.insert(dto.clone(SaleOrderItemLogisticsRequestDTO.class));
        SaleOrderResponseDTO saleOrderResponseDTO = saleOrderInfoService.selectSaleOrder(saleOrderId);
        LinkBusinessRequestDTO linkBusinessRequestDTO = new LinkBusinessRequestDTO();
        log.info("发货--总数量数量: {}--已发货数量: {}", saleOrderResponseDTO.getQuantity().toString(), saleOrderResponseDTO.getTotalQuantity().toString());
        Long unTotalQuantity = saleOrderResponseDTO.getQuantity().longValue() - saleOrderResponseDTO.getTotalQuantity().longValue();//未发货数量
        linkBusinessRequestDTO.setId(saleOrderResponseDTO.getId());
        if (ObjectUtils.equals(unTotalQuantity, 0L)) {//未发货数量为0 则全部发完货
            linkBusinessRequestDTO.setListType("SalesOrder");
            linkBusinessRequestDTO.setBusinessCode(LinkBusinessCodeEnum.Delivery.name());
             toolLinkService.nextLinkBusiness(linkBusinessRequestDTO);//业务链路下一步
            return true;
        } else {//未发完货 则获取状态更新
            linkBusinessRequestDTO.setActionCode("Delivery");
            linkBusinessRequestDTO.setTenantId(saleOrderResponseDTO.getTenantId());
            linkBusinessRequestDTO.setAppId(saleOrderResponseDTO.getAppId());
            JSONObject json = new JSONObject();
            json.put("deliveryGoodsQuantity", unTotalQuantity);
            linkBusinessRequestDTO.setRuleData(json.toString());
            Integer status = toolLinkService.getLinkBusinessStatus(linkBusinessRequestDTO);
            return toolLinkService.updateSaleOrderStatus(saleOrderResponseDTO.getId(), status);
        }
    }
    /**
     * @param dto
     * @Description: 新加oms物流轨迹.
     * @Param: [record]
     * @return: java.lang.Boolean
     * @Author: SongTao
     * @Date: 2020/8/25
     */
    @Override
    public Boolean logisticsInfoUpdate(SaleOmsLogisticsTrajectoryInfoRequestDTO dto) throws Exception {
        SaleOmsLogisticsTrajectoryRequestDTO saleOmsLogisticsTrajectoryRequestDTO = dto.clone(SaleOmsLogisticsTrajectoryRequestDTO.class);
        return saleOmsLogisticsTrajectoryClient.insert(saleOmsLogisticsTrajectoryRequestDTO);
    }
}
