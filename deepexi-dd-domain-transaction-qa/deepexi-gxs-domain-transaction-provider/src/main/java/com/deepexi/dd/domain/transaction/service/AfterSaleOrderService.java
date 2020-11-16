package com.deepexi.dd.domain.transaction.service;

import com.deepexi.dd.middle.order.domain.dto.AfterSaleOrdeRequestDTO;
import com.deepexi.dd.middle.order.domain.dto.AfterSaleOrdeResponseDTO;
import com.deepexi.dd.middle.order.domain.dto.PayOrderItemResponseDTO;
import com.deepexi.dd.middle.order.domain.query.AfterSaleOrdeRequestQuery;
import com.deepexi.util.pageHelper.PageBean;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @author huanghuai
 * @date 2020/10/13
 */
public interface AfterSaleOrderService {

    String ORDER_CODE_GENERATE_TYPE = "GxsAfterSaleOrderCode";

    /**
     * 查询售后申请单列表
     *
     * @return
     */
    List<PayOrderItemResponseDTO> listAfterSaleOrdes(AfterSaleOrdeRequestQuery query);

    /**
     * 分页查询售后申请单列表
     *
     * @return
     */
    PageBean<AfterSaleOrdeResponseDTO> listAfterSaleOrdesPage(AfterSaleOrdeRequestQuery query);


    /**
     * 根据ID删除售后申请单
     *
     * @param id
     * @return
     */
    Boolean deleteByIdIn(@RequestBody List<Long> id);

    /**
     * 新增售后申请单
     *
     * @param record
     * @return
     */
    AfterSaleOrdeResponseDTO insert(@RequestBody AfterSaleOrdeRequestDTO record);

    /**
     * 查询售后申请单详情
     *
     * @param id
     * @return
     */
    AfterSaleOrdeResponseDTO selectById(@PathVariable Long id);


    /**
     * 根据ID修改售后申请单
     *
     * @param id
     * @param record
     * @return
     */
    Boolean updateById(@PathVariable Long id, @RequestBody AfterSaleOrdeRequestDTO record);

    /**
     * 售后单号生成规则： S+YYMMDD+3位自增数字
     */
     String generateOrderCode();

    /**
     * 售后单审核
     * @param id
     * @param dto
     * @return
     */
    Boolean examine(Long id, AfterSaleOrdeRequestDTO dto);
}
