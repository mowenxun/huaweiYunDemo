package com.deepexi.dd.middle.order.dao;

import com.deepexi.dd.middle.order.domain.OrderRefundReasonDO;
import com.deepexi.dd.middle.order.domain.dto.OrderRefundReasonFindDTO;
import com.deepexi.dd.middle.order.domain.dto.OrderRefundReasonRequestDTO;
import com.deepexi.dd.middle.order.domain.query.OrderRefundReasonQuery;

import java.util.List;


/**
 * OrderRefundReasonDAO
 *
 * @author admin
 * @version 1.0
 * @date Wed Aug 19 16:00:27 CST 2020
 */
public interface OrderRefundReasonDAO {

    /**
     * 校验编码
     *
     * @param query
     * @return
     */
    List<OrderRefundReasonDO> CheckCode(OrderRefundReasonQuery query);

    /**
     * 查询列表}
     *
     * @return
     */
    List<OrderRefundReasonDO> listOrderRefundReasons(OrderRefundReasonQuery query);

    /**
     * 根据ID删除
     *
     * @param id
     * @return
     */
    int deleteByIdIn(List<Long> id);

    /**
     * 新增
     *
     * @param record
     * @return
     */
    int insert(OrderRefundReasonDO record);

    /**
     * 查询详情
     *
     * @param id
     * @return
     */
    OrderRefundReasonDO selectById(Long id);

    /**
     * 根据ID修改
     *
     * @param record
     * @return
     */
    int updateById(OrderRefundReasonDO record);

    /**
     * 批量修改状态
     *
     * @param record
     * @return
     */
    int updateStatus(OrderRefundReasonRequestDTO record);

    List<OrderRefundReasonFindDTO> findOrderRefundReason(List<String> orderCodeList);
}