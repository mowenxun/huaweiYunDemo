package com.deepexi.dd.system.mall.service.impl;

import com.deepexi.dd.middle.order.domain.dto.PayOrderPlatformResponseDTO;
import com.deepexi.dd.middle.order.domain.dto.PayOrderRequestDTO;
import com.deepexi.dd.system.mall.domain.dto.PayOrderInfoDTO;
import com.deepexi.dd.system.mall.domain.dto.PayOrderInfoResponseDTO;
import com.deepexi.dd.system.mall.domain.query.payOrder.PayOrderRequestQuery;
import com.deepexi.dd.system.mall.remote.order.PayOrderPlatformRemote;
import com.deepexi.dd.system.mall.service.PayOrderPlatformService;
import com.deepexi.dd.system.mall.util.GeneralConvertUtils;
import com.deepexi.util.pageHelper.PageBean;
import com.google.inject.internal.cglib.core.$Signature;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author JeremyLian
 * @date 2020/10/24 22:19
 */
@Service
@Slf4j
public class PayOrderPlatformServiceImpl implements PayOrderPlatformService {

    @Autowired
    PayOrderPlatformRemote payOrderPlatformRemote;

    /**
     * 查询付款单
     * @param query
     * @return
     * @throws Exception
     */
    @Override
    public PageBean<PayOrderInfoResponseDTO> listPage(PayOrderRequestQuery query) throws Exception {
        PageBean<com.deepexi.dd.middle.order.domain.dto.PayOrderPlatformResponseDTO> PayOrderInfoResponseDTO =
        payOrderPlatformRemote.listPage(query.clone(com.deepexi.dd.middle.order.domain.query.PayOrderPlatformRequestPageQuery.class));
        return GeneralConvertUtils.convert2PageBean(PayOrderInfoResponseDTO,PayOrderInfoResponseDTO.class);
    }

    /**
     * 新增付款单
     * @param payOrderInfoDTO
     * @return
     * @throws Exception
     */
    @Override
    public Boolean addPayOrder(PayOrderInfoDTO payOrderInfoDTO) throws Exception {
        PayOrderPlatformResponseDTO result = payOrderPlatformRemote.insert(payOrderInfoDTO.clone(com.deepexi.dd.middle.order.domain.dto.PayOrderPlatformRequestDTO.class));
        if(result!=null) {
            return true;
        } else{
            return false;
        }
    }

    /**
     * 更新付款单
     * @param record
     * @return
     * @throws Exception
     */
    @Override
    public boolean updateById(PayOrderRequestDTO record) throws Exception {
        return payOrderPlatformRemote.updateById(record.clone(com.deepexi.dd.middle.order.domain.dto.PayOrderPlatformRequestDTO.class));
    }
}
