package com.deepexi.dd.domain.transaction.manager.toolAction;

import com.deepexi.dd.domain.tool.domain.dto.ToolLinkNextBusinessResponseDTO;
import com.deepexi.dd.domain.tool.enums.ActionCodeEnum;
import com.deepexi.dd.domain.tool.enums.LinkBusinessCodeEnum;
import com.deepexi.dd.domain.tool.enums.ListTypeEnum;
import com.deepexi.dd.domain.transaction.annotation.ActionCodeAnnotation;
import com.deepexi.dd.domain.transaction.manager.SaleOrderToolAction;
import com.deepexi.dd.domain.transaction.remote.order.SaleOrderInfoClient;
import com.deepexi.dd.middle.order.domain.dto.SaleOrderInfoRequestDTO;
import com.deepexi.dd.middle.order.domain.dto.SaleOrderResponseDTO;
import com.deepexi.util.extension.ApplicationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 审核环节
 *
 * @author aofeng
 * @version 1.0
 * @date 2020-08-19 10:46
 */
//@Component
//@ActionCodeAnnotation(listType = ListTypeEnum.SalesOrder, code = "TakeOrderApproval")
public class OrderToExamine extends SaleOrderToolAction {

    @Autowired
    private SaleOrderInfoClient saleOrderInfoClient;

    @Override
    public boolean validationOrder(SaleOrderResponseDTO saleOrderResponseDTO) throws ApplicationException {
        return true;
    }

    @Override
    public String getRuleData(SaleOrderResponseDTO saleOrderResponseDTO) throws ApplicationException {
        return null;
    }

    @Override
    public void callback(ToolLinkNextBusinessResponseDTO toolLinkNextBusinessResponseDTO, SaleOrderResponseDTO saleOrderResponseDTO) throws ApplicationException {
        //更新订单状态
        super.callback(toolLinkNextBusinessResponseDTO, saleOrderResponseDTO);
    }
}
