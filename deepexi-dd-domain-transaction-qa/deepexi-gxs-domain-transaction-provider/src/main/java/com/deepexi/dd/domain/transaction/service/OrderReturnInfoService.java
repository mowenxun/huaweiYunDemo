package com.deepexi.dd.domain.transaction.service;

import com.deepexi.dd.domain.transaction.domain.dto.OrderReturnInfoDTO;
import com.deepexi.dd.domain.transaction.domain.dto.OrderReturnInfoRequestDTO;
import com.deepexi.dd.domain.transaction.domain.dto.OrderReturnInfoResponseDTO;
import com.deepexi.dd.domain.transaction.domain.query.OrderReturnInfoQuery;
import com.deepexi.dd.domain.transaction.domain.query.SaleOrderInfoQuery;
import com.deepexi.dd.middle.order.domain.dto.SaleOrderInfoResponseDTO;
import com.deepexi.util.pageHelper.PageBean;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public interface OrderReturnInfoService{

    /**
     * 新增退货订单表
     *
     * @param record
     * @return
     */
    OrderReturnInfoDTO insert(OrderReturnInfoRequestDTO record) throws Exception;

    /**
     * 分页查询退货单表列表
     *
     * @return
     */
    PageBean<OrderReturnInfoResponseDTO> listOrderReturnInfosPage(OrderReturnInfoQuery query) throws Exception;

    /**
     * 分页查询退货单表列表(api)
     *
     * @return
     */
    PageBean<OrderReturnInfoResponseDTO> listOrderReturnInfosPageForApi(OrderReturnInfoQuery query) throws Exception;

    /**
     * 查询销售退货表详情
     *
     * @param id
     * @return
     */
    OrderReturnInfoResponseDTO selectById(Long id) throws Exception;
}