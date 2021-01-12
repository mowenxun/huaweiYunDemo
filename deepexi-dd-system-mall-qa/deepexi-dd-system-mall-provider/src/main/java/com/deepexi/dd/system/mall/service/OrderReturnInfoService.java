package com.deepexi.dd.system.mall.service;

import com.deepexi.dd.domain.transaction.domain.dto.OrderReturnInfoResponseDTO;
import com.deepexi.dd.domain.transaction.domain.query.OrderReturnInfoRequestQuery;
import com.deepexi.dd.system.mall.domain.dto.OrderReturnInfoAppAddRequestDTO;
import com.deepexi.dd.system.mall.domain.dto.OrderReturnInfoAppAddResponseDTO;
import com.deepexi.util.config.Payload;
import com.deepexi.util.extension.ApplicationException;
import com.deepexi.util.pageHelper.PageBean;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

/**
 * @author chenqili
 * @version 1.0
 * @date 2020-07-08 19:46
 */
public interface OrderReturnInfoService {
    /**
     * 创建退货单
     *
     * @param orderReturn
     * @return
     */
    OrderReturnInfoAppAddResponseDTO createOrderReturn(OrderReturnInfoAppAddRequestDTO orderReturn) throws Exception;

    /**
     * 分页查询退货单列表
     *
     * @return
     */
    PageBean<OrderReturnInfoResponseDTO> listOrderReturnInfosPage(OrderReturnInfoRequestQuery query) throws Exception;

    OrderReturnInfoResponseDTO selectById(Long id) throws Exception;

}
