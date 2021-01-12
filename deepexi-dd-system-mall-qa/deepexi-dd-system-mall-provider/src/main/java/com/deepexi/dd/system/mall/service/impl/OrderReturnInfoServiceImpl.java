package com.deepexi.dd.system.mall.service.impl;

import com.deepexi.dd.domain.transaction.api.OrderReturnInfoApi;
import com.deepexi.dd.domain.transaction.domain.dto.OrderReturnInfoAddResponseDTO;
import com.deepexi.dd.domain.transaction.domain.dto.OrderReturnInfoRequestDTO;
import com.deepexi.dd.domain.transaction.domain.dto.OrderReturnInfoResponseDTO;
import com.deepexi.dd.domain.transaction.domain.query.OrderReturnInfoRequestQuery;
import com.deepexi.dd.system.mall.domain.dto.OrderReturnInfoAppAddRequestDTO;
import com.deepexi.dd.system.mall.domain.dto.OrderReturnInfoAppAddResponseDTO;
import com.deepexi.dd.system.mall.remote.order.OrderReturnInfoRemote;
import com.deepexi.dd.system.mall.service.OrderReturnInfoService;
import com.deepexi.dd.system.mall.util.GeneralConvertUtils;
import com.deepexi.util.config.Payload;
import com.deepexi.util.extension.ApplicationException;
import com.deepexi.util.pageHelper.PageBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

/**
 * @author chenqili
 * @version 1.0
 * @date 2020-07-08 19:57
 */
@Service
public class OrderReturnInfoServiceImpl implements OrderReturnInfoService {

    private static final  String SUCCESS="0";
    Logger logger= LoggerFactory.getLogger(OrderReturnInfoServiceImpl.class);

    @Autowired
    OrderReturnInfoRemote orderReturnInfoRemote;

    @Override
    public PageBean<OrderReturnInfoResponseDTO> listOrderReturnInfosPage(OrderReturnInfoRequestQuery query) throws Exception {

        OrderReturnInfoRequestQuery model = query.clone(OrderReturnInfoRequestQuery.class);

        Payload<PageBean<OrderReturnInfoResponseDTO>> result = orderReturnInfoRemote.listOrderReturnInfosPage(model);

        if(SUCCESS.equals(result.getCode())){
            try{
                return GeneralConvertUtils.convert2PageBean(result.getPayload(), OrderReturnInfoResponseDTO.class);
            }
            catch (Exception e){
                logger.error("获取退货分页结果失败",e);
                throw new ApplicationException("获取退货分页结果失败");
            }
        }
        else {
            throw new ApplicationException(result.getMsg());
        }

    }


    @Override
    public OrderReturnInfoAppAddResponseDTO createOrderReturn(OrderReturnInfoAppAddRequestDTO orderReturn) throws Exception {
        Payload<OrderReturnInfoAddResponseDTO> orderReturnInfoAddResponseDTOPayload = orderReturnInfoRemote.createOrderReturn(GeneralConvertUtils.conv(orderReturn,
                OrderReturnInfoRequestDTO.class));

        if(SUCCESS.equals(orderReturnInfoAddResponseDTOPayload.getCode())){
            try{
                OrderReturnInfoAppAddResponseDTO result = GeneralConvertUtils.conv(orderReturnInfoAddResponseDTOPayload.getPayload(), OrderReturnInfoAppAddResponseDTO.class);
                return result;
            }
            catch (Exception e){
                logger.error("获取创建退货结果失败",e);
                throw new ApplicationException("获取创建退货结果失败");
            }
        }
        else {
            throw new ApplicationException(orderReturnInfoAddResponseDTOPayload.getMsg());
        }

    }

    @Override
    public OrderReturnInfoResponseDTO selectById(Long id) throws Exception {
        Payload<OrderReturnInfoResponseDTO> payload = orderReturnInfoRemote.selectById(id);

        if(SUCCESS.equals(payload.getCode())){
            try{
                OrderReturnInfoResponseDTO result = GeneralConvertUtils.conv(payload.getPayload(),OrderReturnInfoResponseDTO.class);
                return result;
            }
            catch (Exception e){
                logger.error("获取退货详情失败",e);
                throw new ApplicationException("获取退货详情失败");
            }
        }
        else {
            throw new ApplicationException(payload.getMsg());
        }
    }
}
