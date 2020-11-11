package com.deepexi.dd.middle.order.dao;

import com.baomidou.mybatisplus.extension.service.IService;
import com.deepexi.dd.middle.order.domain.OrderExpenseInfoDO;
import com.deepexi.dd.middle.order.domain.query.OrderExpenseInfoQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * OrderExpenseInfoDAO
 *
 * @author admin
 * @date Wed Jun 24 09:42:04 CST 2020
 * @version 1.0
 */
public interface OrderExpenseInfoDAO extends IService<OrderExpenseInfoDO> {

    /**
     * 查询销售订单的费用信息表列表}
     *
     * @return
     */
    List<OrderExpenseInfoDO> listOrderExpenseInfos(OrderExpenseInfoQuery query);

    /**
     * 根据ID删除销售订单的费用信息表
     *
     * @param id
     * @return
     */
    int deleteByIdIn(List<Long> id);

    /**
     * 新增销售订单的费用信息表
     *
     * @param record
     * @return
     */
    int insert(OrderExpenseInfoDO record);

    /**
     * 查询销售订单的费用信息表详情
     *
     * @param id
     * @return
     */
    OrderExpenseInfoDO selectById(Long id);

    /**
     * 根据ID修改销售订单的费用信息表
     *
     * @param record
     * @return
     */
//    int updateById(OrderExpenseInfoDO record);

    /**
     * 批量新增销售订单的费用信息表
     *
     * @param list
     * @return
     */
    int batchInsert(@Param("list") List<OrderExpenseInfoDO> list);

}