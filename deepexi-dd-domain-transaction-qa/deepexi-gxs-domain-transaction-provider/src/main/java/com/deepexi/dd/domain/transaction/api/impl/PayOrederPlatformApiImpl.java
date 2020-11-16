package com.deepexi.dd.domain.transaction.api.impl;

import com.deepexi.dd.domain.transaction.api.PayOrderPlatformApi;
import com.deepexi.dd.domain.transaction.domain.dto.payOrder.PayOrderInfoDTO;
import com.deepexi.dd.domain.transaction.domain.dto.payOrder.PayOrderInfoResponseDTO;
import com.deepexi.dd.domain.transaction.domain.query.PayOrderRequestQuery;
import com.deepexi.dd.domain.transaction.service.PayOrderPlatformService;
import com.deepexi.dd.middle.order.domain.dto.PayOrderPlatformRequestDTO;
import com.deepexi.dd.middle.order.domain.dto.PayOrderPlatformResponseDTO;
import com.deepexi.dd.middle.order.domain.dto.PayOrderRequestDTO;
import com.deepexi.dd.middle.order.domain.query.PayOrderPlatformRequestPageQuery;
import com.deepexi.util.pageHelper.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author JeremyLian
 * @date 2020/10/24 18:37
 */
@RestController
public class PayOrederPlatformApiImpl implements PayOrderPlatformApi {

    @Autowired
    private PayOrderPlatformService payOrderPlatformService;

    /**
     * 查询付款单
     * @param query
     * @return
     * @throws Exception
     */
    @Override
    public List<PayOrderPlatformResponseDTO> list(PayOrderPlatformRequestDTO query) {
        return payOrderPlatformService.list(query);
    }

    /**
     * 分页查询付款单
     * @param query
     * @return
     * @throws Exception
     */
    @Override
    public PageBean<PayOrderPlatformResponseDTO> listPage(PayOrderPlatformRequestPageQuery query) {
        return payOrderPlatformService.listPage(query);
    }

    /**
     * 根据ID批量查询
     * @return
     * @throws Exception
     */
    @Override
    public boolean deleteByIds(List<Long> ids) {
        return payOrderPlatformService.deleteByIds(ids);
    }
    /**
     * 插入付款单
     * @return
     * @throws Exception
     */
    @Override
    public PayOrderPlatformResponseDTO insert(PayOrderPlatformRequestDTO record) {
        return payOrderPlatformService.insert(record);
    }
    /**
     * 根据ID查询
     * @return
     * @throws Exception
     */
    @Override
    public PayOrderPlatformResponseDTO selectById(Long id) {
        return payOrderPlatformService.selectById(id);
    }
    /**
     * 根据ID更新
     * @return
     * @throws Exception
     */
    @Override
    public boolean updateById(PayOrderPlatformRequestDTO record) {
        return payOrderPlatformService.updateById(record);
    }
}
