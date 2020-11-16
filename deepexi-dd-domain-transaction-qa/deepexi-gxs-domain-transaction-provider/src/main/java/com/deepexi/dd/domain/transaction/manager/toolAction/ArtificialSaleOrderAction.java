package com.deepexi.dd.domain.transaction.manager.toolAction;

import com.deepexi.dd.domain.tool.domain.dto.ToolLinkNextBusinessResponseDTO;
import com.deepexi.dd.domain.tool.enums.LinkBusinessCodeEnum;
import com.deepexi.dd.domain.tool.enums.ListTypeEnum;
import com.deepexi.dd.domain.tool.enums.StatusCodeEnum;
import com.deepexi.dd.domain.transaction.annotation.ActionCodeAnnotation;
import com.deepexi.dd.domain.transaction.manager.SaleOrderToolAction;
import com.deepexi.dd.middle.order.domain.dto.SaleOrderResponseDTO;
import com.deepexi.util.extension.ApplicationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @ClassName : AccpetOrderAction
 * @Description : 接单操作
 * @Author : yuanzaishun
 * @Date: 2020-08-01 15:28
 */
@Component
@ActionCodeAnnotation(listType =ListTypeEnum.SalesOrder,code = "ArtificialOrder")
public class ArtificialSaleOrderAction extends SaleOrderToolAction {
    protected Logger logger= LoggerFactory.getLogger(ArtificialSaleOrderAction.class);

    @Override
    public boolean validationOrder(SaleOrderResponseDTO saleOrderResponseDTO) throws ApplicationException{
                if(saleOrderResponseDTO==null)
                {
                    return false;
                }
               return StatusCodeEnum.TakedOrder.getCode().equals(saleOrderResponseDTO.getAcceptStatus());
    }

    @Override
    public String getRuleData(SaleOrderResponseDTO saleOrderResponseDTO) throws ApplicationException{
        return null;
    }

    @Override
    public void callback(ToolLinkNextBusinessResponseDTO toolLinkNextBusinessResponseDTO, SaleOrderResponseDTO saleOrderResponseDTO) throws ApplicationException {
        if(!LinkBusinessCodeEnum.TakeOrderApproval.name().equals(toolLinkNextBusinessResponseDTO.getBusinessCode()) ) {
            super.callback(toolLinkNextBusinessResponseDTO, saleOrderResponseDTO);
        }
    }
}
