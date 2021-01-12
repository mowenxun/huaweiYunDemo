package com.deepexi.dd.system.mall.service.impl.gxs;

import com.deepexi.dd.domain.transaction.api.gxs.domain.*;
import com.deepexi.dd.system.mall.remote.order.ShopAfterSaleRemoteService;
import com.deepexi.dd.system.mall.service.gxs.ShopAfterSaleMallService;
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
public class ShopAfterSaleMallServiceImpl implements ShopAfterSaleMallService {

    @Autowired
    ShopAfterSaleRemoteService shopAfterSaleRemoteService;

    @Override
    public PageBean<ListAfterSaleOrderResponseDTO> listAfterSales(ListAfterSaleOrderRequestDTO vo) {
        return shopAfterSaleRemoteService.listAfterSales(vo);
    }

    @Override
    public PageBean<ListAfterSaleOrderResponseDTO> listSubordinateShopAfterSales(ListAfterSaleOrderRequestDTO vo) {
        return shopAfterSaleRemoteService.listSubordinateShopAfterSales(vo);
    }

    @Override
    public Boolean createOrUpdateAfterSale(@Valid CreateAfterSaleOrderVO vo) {
        return shopAfterSaleRemoteService.createOrUpdateAfterSale(vo);
    }

    @Override
    public AfterSaleOrderDetailsResponseDTO afterSaleDetails(Long id) {
        return shopAfterSaleRemoteService.afterSaleDetails(id);
    }

    @Override
    public Boolean updateStatus(Long id, String status) {
        return shopAfterSaleRemoteService.updateStatus(id,status);
    }

    /**
     * 查询售后单商品item(创建售后单)
     * 通过商品sku编码和商品名称模糊查询
     */
    public PageBean<ShopOrderItemDomainResponseDTO> queryFinishedShopItems(SupplerOrderItemDomainRequestQuery query) {
        return shopAfterSaleRemoteService.queryFinishedShopItems(query);
    }
}
