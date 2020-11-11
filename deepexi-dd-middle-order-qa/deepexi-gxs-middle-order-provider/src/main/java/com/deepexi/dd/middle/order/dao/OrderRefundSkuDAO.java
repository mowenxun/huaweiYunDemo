package com.deepexi.dd.middle.order.dao;

import com.baomidou.mybatisplus.extension.service.IService;
import com.deepexi.dd.middle.order.domain.OrderRefundSkuDO;
import com.deepexi.dd.middle.order.domain.query.OrderRefundSkuQuery;

import java.util.List;


/**
 * OrderRefundSkuDAO
 *
 * @author admin
 * @version 1.0
 * @date Wed Aug 19 16:31:13 CST 2020
 */
public interface OrderRefundSkuDAO extends IService<OrderRefundSkuDO> {

    /**
     * 查询列表}
     *
     * @return
     */
    List<OrderRefundSkuDO> listOrderRefundSkus(OrderRefundSkuQuery query);

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
    int insert(OrderRefundSkuDO record);

    /**
     * 查询详情
     *
     * @param id
     * @return
     */
    OrderRefundSkuDO selectById(Long id);

    /**
     * 根据ID修改
     *
     * @param record
     * @return
     */
    boolean updateById(OrderRefundSkuDO record);

}