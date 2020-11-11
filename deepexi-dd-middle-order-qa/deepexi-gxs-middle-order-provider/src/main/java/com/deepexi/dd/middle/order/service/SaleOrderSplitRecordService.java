package com.deepexi.dd.middle.order.service;

import com.deepexi.dd.middle.order.domain.SaleOrderSplitRecordDTO;
import com.deepexi.dd.middle.order.domain.SaleOrderSplitRecordQuery;
import com.deepexi.dd.middle.order.domain.dto.SaleOrderAmountStatuEditDTO;
import com.deepexi.dd.middle.order.domain.dto.SaleOrderSplitRecordResponseDTO;
import com.deepexi.util.pageHelper.PageBean;

import java.util.List;
/**
 * SaleOrderSplitRecordService
 *
 * @author admin
 * @date Wed Aug 12 20:24:31 CST 2020
 * @version 1.0
 */
public interface SaleOrderSplitRecordService {


    /**
     * 查询销售订单拆单记录表列表
     *
     * @return
     */
    List<SaleOrderSplitRecordDTO> listSaleOrderSplitRecords(SaleOrderSplitRecordQuery query);

    /**
     * 分页查询销售订单拆单记录表列表
     *
     * @return
     */
    PageBean<SaleOrderSplitRecordDTO> listSaleOrderSplitRecordsPage(SaleOrderSplitRecordQuery query);

    /**
     * 根据ID删除销售订单拆单记录表
     *
     * @param id
     * @return
     */
    Boolean deleteByIdIn(List<Long> id);

    /**
     * 新增销售订单拆单记录表
     *
     * @param record
     * @return
     */
    SaleOrderSplitRecordDTO insert(SaleOrderSplitRecordDTO record);

    /**
     * 查询销售订单拆单记录表详情
     *
     * @param id
     * @return
     */
    SaleOrderSplitRecordDTO selectById(Long id);


    /**
     * 根据ID修改销售订单拆单记录表
     *
     * @param id
     * @param record
     * @return
     */
    Boolean updateById(Long id, SaleOrderSplitRecordDTO record);

    /**
     * 更新父单和子单的支付信息
     * @param editDto
     * @return
     */
     Boolean updateOrderAmountAndStatus(SaleOrderAmountStatuEditDTO editDto);

    /**
     * 根据code查询父订单信息
     * @param code
     * @return
     */
    public SaleOrderSplitRecordResponseDTO selectByCode(String code);

}