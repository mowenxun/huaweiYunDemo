package com.deepexi.dd.domain.transaction.service;

import com.deepexi.dd.middle.order.domain.dto.AfterSaleOrderItemRequestDTO;
import com.deepexi.dd.middle.order.domain.dto.AfterSaleOrderItemResponseDTO;
import com.deepexi.dd.middle.order.domain.query.AfterSaleOrderItemRequestQuery;
import com.deepexi.util.pageHelper.PageBean;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @author huanghuai
 * @date 2020/10/13
 */
public interface AfterSaleOrderItemDomainService {

    /**
     * 查询售后订单明细表列表
     *
     * @return
     */
    List<AfterSaleOrderItemResponseDTO> listAfterSaleOrderItems(AfterSaleOrderItemRequestQuery query);

    /**
     * 分页查询售后订单明细表列表
     *
     * @return
     */
    PageBean<AfterSaleOrderItemResponseDTO> listAfterSaleOrderItemsPage(AfterSaleOrderItemRequestQuery query);


    /**
     * 根据ID删除售后订单明细表
     *
     * @param id
     * @return
     */
    Boolean deleteByIdIn(@RequestBody List<Long> id);

    /**
     * 新增售后订单明细表
     *
     * @param record
     * @return
     */
    AfterSaleOrderItemResponseDTO insert(@RequestBody AfterSaleOrderItemRequestDTO record);

    /**
     * 查询售后订单明细表详情
     *
     * @param id
     * @return
     */
    AfterSaleOrderItemResponseDTO selectById(@PathVariable Long id);


    /**
     * 根据ID修改售后订单明细表
     *
     * @param id
     * @param record
     * @return
     */
    Boolean updateById(@PathVariable Long id, @RequestBody AfterSaleOrderItemRequestDTO record);

}
