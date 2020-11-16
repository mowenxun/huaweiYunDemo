package com.deepexi.dd.domain.transaction.manager.toolAction;

import com.deepexi.dd.domain.tool.domain.dto.ToolLinkNextBusinessResponseDTO;
import com.deepexi.dd.domain.tool.enums.LinkBusinessCodeEnum;
import com.deepexi.dd.domain.tool.enums.ListTypeEnum;
import com.deepexi.dd.domain.transaction.annotation.ActionCodeAnnotation;
import com.deepexi.dd.domain.transaction.manager.SaleOrderInfoManager;
import com.deepexi.dd.domain.transaction.manager.SaleOrderToolAction;
import com.deepexi.dd.middle.order.domain.dto.SaleOrderResponseDTO;
import com.deepexi.util.JsonUtil;
import com.deepexi.util.extension.ApplicationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @ClassName : SaleOrderPaySureAction
 * @Description : 付款确认环节
 * @Author : yuanzaishun
 * @Date: 2020-08-15 10:46
 */
@Component
@ActionCodeAnnotation(listType = ListTypeEnum.SalesOrder,code = "ReceiptConfirm")
public class SaleOrderPaySureAction extends SaleOrderToolAction {
    Logger logger= LoggerFactory.getLogger(SaleOrderPayAction.class);
    @Autowired
    SaleOrderInfoManager saleOrderInfoManager;
    @Override
    public boolean validationOrder(SaleOrderResponseDTO saleOrderResponseDTO) throws ApplicationException {
        //如果已支付金额大于等于应付金额并且已确认金额大于等于应收，已确认数量等于总数，则进行扭转
        try {
            if (saleOrderInfoManager.validPayRecordSure(saleOrderResponseDTO.getId()) && saleOrderResponseDTO.getPayAmount().compareTo(saleOrderResponseDTO.getAccrueAmount()) > -1) {
                return true;
            }
        }catch(Exception e){
            logger.error("验证订单支付信息异常:{}", JsonUtil.bean2JsonString(saleOrderResponseDTO));
            throw new ApplicationException("验证订单支付信息异常:",e);
        }
        return false;
    }

    @Override
    public String getRuleData(SaleOrderResponseDTO saleOrderResponseDTO) throws ApplicationException {
        return null;
    }

    @Override
    public void callback(ToolLinkNextBusinessResponseDTO toolLinkNextBusinessResponseDTO, SaleOrderResponseDTO saleOrderResponseDTO) throws ApplicationException {
        super.callback(toolLinkNextBusinessResponseDTO, saleOrderResponseDTO);
    }
}
