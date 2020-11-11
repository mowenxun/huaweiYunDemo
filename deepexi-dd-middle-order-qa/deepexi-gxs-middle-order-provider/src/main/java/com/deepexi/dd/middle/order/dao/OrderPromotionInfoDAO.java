package com.deepexi.dd.middle.order.dao;

import com.baomidou.mybatisplus.extension.service.IService;
import com.deepexi.dd.middle.order.domain.OrderPromotionInfoDO;
import com.deepexi.dd.middle.order.domain.query.OrderPromotionInfoQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * OrderPromotionInfoDAO
 *
 * @author admin
 * @date Wed Jun 24 09:42:06 CST 2020
 * @version 1.0
 */
public interface OrderPromotionInfoDAO extends IService<OrderPromotionInfoDO> {

    /**
     * 查询销售订单的促销信息表列表}
     *
     * @return
     */
    List<OrderPromotionInfoDO> listOrderPromotionInfos(OrderPromotionInfoQuery query);

    /**
     * 根据ID删除销售订单的促销信息表
     *
     * @param id
     * @return
     */
    int deleteByIdIn(List<Long> id);

    /**
     * 新增销售订单的促销信息表
     *
     * @param record
     * @return
     */
    int insert(OrderPromotionInfoDO record);

    /**
     * 查询销售订单的促销信息表详情
     *
     * @param id
     * @return
     */
    OrderPromotionInfoDO selectById(Long id);

    /**
     * 根据ID修改销售订单的促销信息表
     *
     * @param record
     * @return
     */
//    int updateById(OrderPromotionInfoDO record);

    /**
     * 批量新增销售订单的促销信息表
     *
     * @param list
     * @return
     */
    int batchInsert(@Param("list") List<OrderPromotionInfoDO> list);
}