package com.deepexi.dd.middle.order.dao;

import com.baomidou.mybatisplus.extension.service.IService;
import com.deepexi.dd.middle.order.domain.OrderCouponInfoDO;
import com.deepexi.dd.middle.order.domain.query.OrderCouponInfoQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * OrderCouponInfoDAO
 *
 * @author admin
 * @date Wed Jun 24 09:42:05 CST 2020
 * @version 1.0
 */
public interface OrderCouponInfoDAO extends IService<OrderCouponInfoDO> {

    /**
     * 查询优惠信息表列表}
     *
     * @return
     */
    List<OrderCouponInfoDO> listOrderCouponInfos(OrderCouponInfoQuery query);

    /**
     * 根据ID删除优惠信息表
     *
     * @param id
     * @return
     */
    int deleteByIdIn(List<Long> id);

    /**
     * 新增优惠信息表
     *
     * @param record
     * @return
     */
    int insert(OrderCouponInfoDO record);

    /**
     * 查询优惠信息表详情
     *
     * @param id
     * @return
     */
    OrderCouponInfoDO selectById(Long id);

    /**
     * 根据ID修改优惠信息表
     *
     * @param record
     * @return
     */
//    int updateById(OrderCouponInfoDO record);

    /**
     * 批量新增优惠信息表
     *
     * @param list
     * @return
     */
    int batchInsert(@Param("list") List<OrderCouponInfoDO> list);

}