package com.deepexi.dd.domain.transaction.manager;

import com.deepexi.dd.domain.common.util.GeneralConvertUtils;
import com.deepexi.dd.domain.tool.domain.dto.ToolLinkNextBusinessResponseDTO;
import com.deepexi.dd.domain.tool.enums.ActionCodeEnum;
import com.deepexi.dd.domain.tool.enums.ListTypeEnum;
import com.deepexi.dd.domain.tool.enums.StatusCodeEnum;
import com.deepexi.dd.domain.transaction.enums.TicketTypeEnum;
import com.deepexi.dd.domain.transaction.remote.order.SaleOrderInfoClient;
import com.deepexi.dd.domain.transaction.service.common.ToolLinkService;
import com.deepexi.dd.domain.transaction.service.impl.send.SaleOrderSendServiceImpl;
import com.deepexi.dd.middle.order.domain.dto.SaleOrderResponseDTO;
import com.deepexi.util.config.Payload;
import com.deepexi.util.extension.ApplicationException;
import org.apache.poi.ss.formula.functions.T;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @ClassName : SaleOrderToolAction
 * @Description : 订单业务链路扭转抽象类
 * @Author : yuanzaishun
 * @Date: 2020-08-05 10:32
 */
public abstract class SaleOrderToolAction extends AbstractToolAction<SaleOrderResponseDTO> {
    Logger logger = LoggerFactory.getLogger(SaleOrderToolAction.class);
    @Autowired
    protected SaleOrderInfoClient saleOrderInfoClient;
    @Autowired
    ToolLinkService toolLinkService;

    @Autowired
    SaleOrderSendServiceImpl saleOrderSendService;

    @Override
    public SaleOrderResponseDTO getOrderObj(Long id) throws ApplicationException {
        Payload<SaleOrderResponseDTO> payload = saleOrderInfoClient.selectSaleOrder(id);
        if (SUCCESS.equals(payload.getCode())) {
            try {
                return GeneralConvertUtils.conv(payload.getPayload(), SaleOrderResponseDTO.class);
            } catch (Exception e) {
                logger.error("获取订单失败:", e);
                throw new ApplicationException("获取订单失败:{}", id);
            }
        }
        return null;
    }

    /**
     * 交给具体环节实现
     *
     * @param saleOrderResponseDTO
     * @return
     * @throws ApplicationException
     */
    @Override
    public abstract boolean validationOrder(SaleOrderResponseDTO saleOrderResponseDTO) throws ApplicationException;

    /**
     * 交给具体环节实现
     *
     * @param saleOrderResponseDTO
     * @return
     * @throws ApplicationException
     */
    @Override
    public abstract String getRuleData(SaleOrderResponseDTO saleOrderResponseDTO) throws ApplicationException;

    @Override
    public void callback(ToolLinkNextBusinessResponseDTO toolLinkNextBusinessResponseDTO,
                         SaleOrderResponseDTO saleOrderResponseDTO) throws ApplicationException {
        try {
            toolLinkService.updateSaleOrderStatus(saleOrderResponseDTO.getId(),
                    toolLinkNextBusinessResponseDTO.getStatus());
            logger.info("toolLinkNextBusinessResponseDTO=={}", toolLinkNextBusinessResponseDTO);
            if (StatusCodeEnum.ToDelivery.getCode().equals(toolLinkNextBusinessResponseDTO.getStatus()) && !TicketTypeEnum.PLAN.getValue().equals(saleOrderResponseDTO.getTicketType())) {
                //待发货状态下把网批订单同步到OMS
                saleOrderSendService.sendOrderToGl(saleOrderResponseDTO.getId());
            }
        } catch (Exception e) {
            logger.error("更新订单状态异常:", e);
            throw new ApplicationException("更新订单状态异常");
        }
    }


    @Override
    public String getListType() {
        return ListTypeEnum.SalesOrder.name();
    }
}
