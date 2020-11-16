package com.deepexi.dd.domain.transaction.manager.toolAction;

import com.deepexi.dd.domain.tool.enums.ListTypeEnum;
import com.deepexi.dd.domain.transaction.annotation.ActionCodeAnnotation;
import com.deepexi.dd.domain.transaction.manager.SaleOrderInfoManager;
import com.deepexi.dd.domain.transaction.manager.SaleOrderToolAction;
import com.deepexi.dd.middle.order.domain.dto.SaleOrderResponseDTO;
import com.deepexi.util.JsonUtil;
import com.deepexi.util.extension.ApplicationException;
import org.apache.commons.lang.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @ClassName SaleOutTaskSignAction
 * @Description 出库单签收
 * @Author SongTao
 * @Date 2020-08-27
 * @Version 1.0
 **/
@Component
@ActionCodeAnnotation(listType = ListTypeEnum.SalesOrder,code = "BuyerReceive")
public class SaleOutTaskSignAction extends SaleOrderToolAction {
    Logger logger= LoggerFactory.getLogger(SaleOrderPayAction.class);

    @Autowired
    SaleOrderInfoManager saleOrderInfoManager;
    /**
     * 交给具体环节实现
     *
     * @param saleOrderResponseDTO
     * @return
     * @throws ApplicationException
     */
    @Override
    public boolean validationOrder(SaleOrderResponseDTO saleOrderResponseDTO) throws ApplicationException {
        try {
            //先拿到订单信息
            SaleOrderResponseDTO dto = saleOrderInfoManager.getSaleOrderById(saleOrderResponseDTO.getId());
            logger.info("订单信息: {}" , dto);
            Long quantity = Optional.ofNullable(dto.getQuantity()).orElse(0L);//总数量
            Long totalSignQuantity = Optional.ofNullable(dto.getTotalSignQuantity()).orElse(0L);//总签收数量
            Long unSignQuantity = quantity - totalSignQuantity;//未签收数量
            logger.info("签收--未签收数量: {}" , unSignQuantity.toString());
            //待签收数 =0 则通知业务链路下一步
            if (ObjectUtils.equals(unSignQuantity,0L)) {
                return true;
            }
        }catch(Exception e){
            logger.error("验证出库单签收信息异常:{}", JsonUtil.bean2JsonString(saleOrderResponseDTO));
            throw new ApplicationException("验证出库单签收异常:",e);
        }
        return false;
    }

    /**
     * 交给具体环节实现
     *
     * @param saleOrderResponseDTO
     * @return
     * @throws ApplicationException
     */
    @Override
    public String getRuleData(SaleOrderResponseDTO saleOrderResponseDTO) throws ApplicationException {
        return null;
    }
}
