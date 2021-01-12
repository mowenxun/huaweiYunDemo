package com.deepexi.dd.system.mall.service;

import com.deepexi.dd.middle.order.domain.dto.PayOrderRequestDTO;
import com.deepexi.dd.system.mall.domain.dto.PayOrderInfoDTO;
import com.deepexi.dd.system.mall.domain.dto.PayOrderInfoResponseDTO;
import com.deepexi.dd.system.mall.domain.query.payOrder.PayOrderRequestQuery;
import com.deepexi.util.pageHelper.PageBean;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author JeremyLian
 * @date 2020/10/22 10:20
 */
public interface PayOrderPlatformService {

    /**
     * 分页查询付款单列表
     * @return
     */
    PageBean<PayOrderInfoResponseDTO> listPage(PayOrderRequestQuery query) throws Exception;

    /**
     * 新增
     * @param payOrderInfoDTO
     * @return
     * @throws Exception
     */
    Boolean addPayOrder(PayOrderInfoDTO payOrderInfoDTO) throws  Exception;

    /**
     * 更新
     * @param record
     * @return
     * @throws Exception
     */
    boolean updateById(@RequestBody PayOrderRequestDTO record) throws Exception;


}
