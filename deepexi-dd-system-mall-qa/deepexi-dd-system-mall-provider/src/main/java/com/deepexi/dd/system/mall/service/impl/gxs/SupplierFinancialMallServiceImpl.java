package com.deepexi.dd.system.mall.service.impl.gxs;

import com.deepexi.dd.domain.transaction.api.gxs.domain.CreatePayOrderItemRequestDTO;
import com.deepexi.dd.domain.transaction.api.gxs.domain.ListPayOrderRequestDTO;
import com.deepexi.dd.domain.transaction.api.gxs.domain.ListPayOrderResponseDTO;
import com.deepexi.dd.domain.transaction.api.gxs.domain.PayOrderDetailsResponseDTO;
import com.deepexi.dd.system.mall.remote.order.SupplierFinancialRemoteService;
import com.deepexi.dd.system.mall.service.gxs.SupplierFinancialMallService;
import com.deepexi.util.pageHelper.PageBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;

/**
 * @author huanghuai
 * @date 2020/10/13
 */
@Service
@Slf4j
public class SupplierFinancialMallServiceImpl implements SupplierFinancialMallService {

    @Autowired
    SupplierFinancialRemoteService supplierFinancialRemoteService;

    @Override
    public PageBean<ListPayOrderResponseDTO> listPayOrders(ListPayOrderRequestDTO vo) {
        return supplierFinancialRemoteService.listPayOrders(vo);
    }

    @Override
    public Boolean supplierCollection(@Valid CreatePayOrderItemRequestDTO vo) {
        return supplierFinancialRemoteService.supplierCollection(vo);
    }

    @Override
    public PayOrderDetailsResponseDTO payOrdersDetails(Long id) {
        return supplierFinancialRemoteService.payOrdersDetails(id);
    }
}
