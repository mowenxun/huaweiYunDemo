package com.deepexi.dd.domain.transaction.service.impl;

import com.deepexi.dd.domain.common.util.GeneralConvertUtils;
import com.deepexi.dd.domain.transaction.domain.dto.payOrder.PayOrderInfoDTO;
import com.deepexi.dd.domain.transaction.domain.dto.payOrder.PayOrderInfoResponseDTO;
import com.deepexi.dd.domain.transaction.domain.query.PayOrderRequestQuery;
import com.deepexi.dd.domain.transaction.remote.gxs.PayOrderPlatformRemoteService;
import com.deepexi.dd.domain.transaction.remote.gxs.PayOrderRemoteService;
import com.deepexi.dd.domain.transaction.service.PayOrderPlatformService;
import com.deepexi.dd.middle.order.domain.dto.PayOrderPlatformRequestDTO;
import com.deepexi.dd.middle.order.domain.dto.PayOrderPlatformResponseDTO;
import com.deepexi.dd.middle.order.domain.dto.PayOrderRequestDTO;
import com.deepexi.dd.middle.order.domain.dto.PayOrderResponseDTO;
import com.deepexi.dd.middle.order.domain.query.PayOrderPlatformRequestPageQuery;
import com.deepexi.dd.middle.order.domain.query.PayOrderRequestPageQuery;
import com.deepexi.util.pageHelper.PageBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author JeremyLian
 * @date 2020/10/22 10:27
 */
@Service
@Slf4j
public class PayOrderPlatformServiceImpl implements PayOrderPlatformService {


    @Autowired
    PayOrderPlatformRemoteService payOrderPlatformClient;


    @Override
    public List<PayOrderPlatformResponseDTO> list(PayOrderPlatformRequestDTO query) {
        return payOrderPlatformClient.list(query);
    }

    @Override
    public PageBean<PayOrderPlatformResponseDTO> listPage(PayOrderPlatformRequestPageQuery query) {
        return payOrderPlatformClient.listPage(query);
    }

    @Override
    public boolean deleteByIds(List<Long> ids) {
        return payOrderPlatformClient.deleteByIds(ids);
    }

    @Override
    public PayOrderPlatformResponseDTO insert(PayOrderPlatformRequestDTO record) {
        return payOrderPlatformClient.insert(record);
    }

    @Override
    public PayOrderPlatformResponseDTO selectById(Long id) {
        return payOrderPlatformClient.selectById(id);
    }

    @Override
    public boolean updateById(PayOrderPlatformRequestDTO record) {
        return payOrderPlatformClient.updateById(record);
    }
//    /**
//     * 分页查询付款单信息
//     * @param query
//     * @return
//     * @throws Exception
//     */
//    @Override
//    public PageBean<PayOrderInfoResponseDTO> listPage(PayOrderRequestQuery query) throws Exception{
//        PayOrderRequestPageQuery payOrderRequestPageQuery = query.clone(PayOrderRequestPageQuery.class);
//        PageBean<PayOrderResponseDTO> payOrderResponseDTO = payOrderClient.listPage(payOrderRequestPageQuery);
//        PageBean<PayOrderInfoResponseDTO> result = GeneralConvertUtils.convert2PageBean(payOrderResponseDTO, PayOrderInfoResponseDTO.class);
//        return result;
//    }
//
//    /**
//     * 插入一条付款单
//     * @param payOrderInfoDTO
//     * @return
//     * @throws Exception
//     */
//    @Override
//    public Boolean addPayOrder(PayOrderInfoDTO payOrderInfoDTO) throws Exception {
//        PayOrderRequestDTO payOrderRequestDTO = payOrderInfoDTO.clone(PayOrderRequestDTO.class);
//        PayOrderResponseDTO payOrderResponseDTO = payOrderClient.insert(payOrderRequestDTO);
//        if (payOrderResponseDTO != null) {
//            return true;
//        }else{
//            return false;
//        }
//    }
//
//
//
//    /**
//     * 更新付款单付款状态
//     * @param record
//     * @return
//     * @throws Exception
//     */
//    @Override
//    public boolean updateById(PayOrderRequestDTO record) throws  Exception{
//        return payOrderClient.updateById(record);
//    }
}
