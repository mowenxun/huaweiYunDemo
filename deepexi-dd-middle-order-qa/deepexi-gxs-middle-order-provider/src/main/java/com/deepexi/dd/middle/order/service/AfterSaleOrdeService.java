package com.deepexi.dd.middle.order.service;

import com.deepexi.dd.middle.order.domain.dto.AfterSaleOrdeDTO;
import com.deepexi.dd.middle.order.domain.query.AfterSaleOrdeQuery;
import com.deepexi.util.pageHelper.PageBean;

import java.util.List;
/**
 * AfterSaleOrdeService
 *
 * @author admin
 * @date Fri Oct 16 14:17:12 CST 2020
 * @version 1.0
 */
public interface AfterSaleOrdeService {


    /**
     * 查询售后申请单列表
     *
     * @return
     */
    List<AfterSaleOrdeDTO> listAfterSaleOrdes(AfterSaleOrdeQuery query);

    /**
     * 分页查询售后申请单列表
     *
     * @return
     */
    PageBean<AfterSaleOrdeDTO> listAfterSaleOrdesPage(AfterSaleOrdeQuery query);

    /**
     * 根据ID删除售后申请单
     *
     * @param id
     * @return
     */
    Boolean deleteByIdIn(List<Long> id);

    /**
     * 新增售后申请单
     *
     * @param record
     * @return
     */
    AfterSaleOrdeDTO insert(AfterSaleOrdeDTO record);

    /**
     * 查询售后申请单详情
     *
     * @param id
     * @return
     */
    AfterSaleOrdeDTO selectById(Long id);


    /**
     * 根据ID修改售后申请单
     *
     * @param id
     * @param record
     * @return
     */
    Boolean updateById(Long id, AfterSaleOrdeDTO record);

}