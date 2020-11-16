package com.deepexi.dd.domain.transaction.service.impl;

import com.deepexi.dd.domain.common.util.GeneralConvertUtils;
import com.deepexi.dd.domain.transaction.domain.dto.saleOrderItem.SaleOrderItemResponseDTO;
import com.deepexi.dd.domain.transaction.domain.query.SaleOrderItemRequestQuery;
import com.deepexi.dd.domain.transaction.remote.order.SaleOrderItemClient;
import com.deepexi.dd.domain.transaction.service.SaleOrderItemService;
import com.deepexi.dd.middle.order.domain.dto.SaleOrderItemMiddleResponseDTO;
import com.deepexi.dd.middle.order.domain.query.SaleOrderItemMiddleRequestQuery;
import com.deepexi.util.pageHelper.PageBean;
import com.github.pagehelper.page.PageMethod;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName SaleOrderItemServiceImpl
 * @Description TODO
 * @Author SongTao
 * @Date 2020-07-10
 * @Version 1.0
 **/
@Service
@Slf4j
public class SaleOrderItemServiceImpl  implements SaleOrderItemService {
    @Autowired
    private SaleOrderItemClient saleOrderItemClient;

    /**
     * @param query
     * @Description: 查询分页订单明细信息.
     * @Param: [query]
     * @return: com.deepexi.util.pageHelper.PageBean<com.deepexi.dd.domain.transaction.domain.dto.SaleOrderItem.SaleOrderItemResponseDTO>
     * @Author: SongTao
     * @Date: 2020/7/10
     */
    @Override
    public PageBean<SaleOrderItemResponseDTO> listSaleOrderItemsPage(SaleOrderItemRequestQuery query) throws Exception {
        PageMethod.startPage(query.getPage(), query.getSize());
        SaleOrderItemMiddleRequestQuery saleOrderItemMiddleRequestQuery = query.clone(SaleOrderItemMiddleRequestQuery.class);
        PageBean<SaleOrderItemMiddleResponseDTO> pageBean = saleOrderItemClient.listSaleOrderItemsPage(saleOrderItemMiddleRequestQuery);
        PageBean<SaleOrderItemResponseDTO> result = GeneralConvertUtils.convert2PageBean(pageBean, SaleOrderItemResponseDTO.class);
        return result;
    }
}
