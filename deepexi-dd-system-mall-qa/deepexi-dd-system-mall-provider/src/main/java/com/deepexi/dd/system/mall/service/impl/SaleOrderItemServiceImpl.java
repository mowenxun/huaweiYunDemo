package com.deepexi.dd.system.mall.service.impl;

import com.deepexi.dd.domain.transaction.domain.query.SaleOrderItemRequestQuery;
import com.deepexi.dd.system.mall.domain.dto.SaleOrderItemInfoResponseDTO;
import com.deepexi.dd.system.mall.remote.order.SaleOrderItemRemote;
import com.deepexi.dd.system.mall.service.SaleOrderItemService;
import com.deepexi.dd.system.mall.util.GeneralConvertUtils;
import com.deepexi.util.pageHelper.PageBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName SaleOrderItemServiceImpl
 * @Description TODO
 * @Author SongTao
 * @Date 2020-07-24
 * @Version 1.0
 **/
@Service
@Slf4j
public class SaleOrderItemServiceImpl implements SaleOrderItemService {

    @Autowired
    SaleOrderItemRemote saleOrderItemRemote;


    /**
     * @Description:   查询分页订单明细信息..
     * @Param: [query]
     * @return: com.deepexi.util.pageHelper.PageBean<com.deepexi.dd.system.mall.domain.dto.SaleOrderItemInfoResponseDTO>
     * @Author: SongTao
     * @Date: 2020/7/24
     */
    @Override
    public PageBean<SaleOrderItemInfoResponseDTO> listSaleOrderItemsPage(SaleOrderItemRequestQuery query) throws Exception {
        PageBean<SaleOrderItemInfoResponseDTO> saleOrderItemInfoResponseDTOPageBean = GeneralConvertUtils.convert2PageBean(saleOrderItemRemote.
                listSaleOrderItemsPage(query), SaleOrderItemInfoResponseDTO.class);
        return saleOrderItemInfoResponseDTOPageBean;
    }
}
