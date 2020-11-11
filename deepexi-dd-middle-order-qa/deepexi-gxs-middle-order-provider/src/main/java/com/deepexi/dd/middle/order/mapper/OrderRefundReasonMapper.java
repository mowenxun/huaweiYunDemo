package com.deepexi.dd.middle.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.deepexi.dd.middle.order.domain.OrderRefundReasonDO;
import com.deepexi.dd.middle.order.domain.dto.OrderRefundReasonFindDTO;
import com.deepexi.dd.middle.order.domain.dto.OrderRefundReasonRequestDTO;
import com.deepexi.dd.middle.order.domain.query.OrderRefundReasonQuery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * OrderRefundReasonMapper
 *
 * @author admin
 * @version 1.0
 * @date Wed Aug 19 16:00:27 CST 2020
 */
@Mapper
public interface OrderRefundReasonMapper extends BaseMapper<OrderRefundReasonDO> {

    /**
     * 查询
     *
     * @param query
     * @return
     */
    List<OrderRefundReasonDO> findAll(OrderRefundReasonQuery query);

    /**
     * 删除
     *
     * @param ids
     * @return
     */
    int deleteByIdIn(@Param("ids") List<Long> ids);

    /**
     * 批量更新
     *
     * @param list
     * @return
     */
    int updateBatch(List<OrderRefundReasonDO> list);

    /**
     * 批量新增
     *
     * @param list
     * @return
     */
    int batchInsert(@Param("list") List<OrderRefundReasonDO> list);

    /**
     * 校验编码
     *
     * @param query
     * @return
     */
    List<OrderRefundReasonDO> CheckCode(OrderRefundReasonQuery query);

    /**
     * 批量修改状态
     *
     * @param record
     * @return
     */
    int updateStatus(OrderRefundReasonRequestDTO record);

    List<OrderRefundReasonFindDTO> findOrderRefundReason(@Param("orderCodeList") List<String> orderCodeList);
}