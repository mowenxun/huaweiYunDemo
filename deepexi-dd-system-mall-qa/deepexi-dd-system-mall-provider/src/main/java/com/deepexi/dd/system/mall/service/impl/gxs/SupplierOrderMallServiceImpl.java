package com.deepexi.dd.system.mall.service.impl.gxs;

import com.deepexi.dd.domain.transaction.api.gxs.domain.*;
import com.deepexi.dd.system.mall.remote.order.SupplierOrderRemoteService;
import com.deepexi.dd.system.mall.service.gxs.SupplierOrderMallService;
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
public class SupplierOrderMallServiceImpl implements SupplierOrderMallService {

    @Autowired
    SupplierOrderRemoteService supplierOrderRemoteService;

    @Override
    public PageBean<ListSupplierOrderResponseDTO> listSupplierOrdersPage(ListSupplierOrderRequestDTO vo) {
        return supplierOrderRemoteService.listSupplierOrdersPage(vo);
    }

    @Override
    public Boolean receiveOrder(Long id) {
        return supplierOrderRemoteService.receiveOrder(id);
    }

    @Override
    public Boolean rejectOrder(Long id, String reason) {
        return supplierOrderRemoteService.rejectOrder(id,reason);
    }

    @Override
    public SupplierOrderDetailResponseDTO orderDetailsById(Long id) {
        return supplierOrderRemoteService.orderDetailsById(id);
    }

    @Override
    public Boolean deliverGoods(@Valid SupplierDeliverGoodsRequestDTO vo) {
        return supplierOrderRemoteService.deliverGoods(vo);
    }

    @Override
    public QueryPayVoucherResponseDTO queryPayVoucher(Long id) {
        return supplierOrderRemoteService.queryPayVoucher(id);
    }

    @Override
    public SupplierOrderDetailResponseDTO orderDetailsByCode(String code) {
        return supplierOrderRemoteService.orderDetailsByCode(code);
    }
}
