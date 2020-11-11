package com.deepexi.dd.middle.order.dao;

import com.baomidou.mybatisplus.extension.service.IService;
import com.deepexi.dd.middle.order.domain.OrderConsigneeInfoDO;
import com.deepexi.dd.middle.order.domain.query.OrderConsigneeInfoQuery;

import java.util.List;


/**
 * OrderConsigneeInfoDAO
 *
 * @author admin
 * @date Wed Jun 24 09:42:05 CST 2020
 * @version 1.0
 */
public interface OrderConsigneeInfoDAO extends IService<OrderConsigneeInfoDO> {

    /**
     * 查询收货地址信息表列表}
     *
     * @return
     */
    List<OrderConsigneeInfoDO> listOrderConsigneeInfos(OrderConsigneeInfoQuery query);

    /**
     * 根据ID删除收货地址信息表
     *
     * @param id
     * @return
     */
    int deleteByIdIn(List<Long> id);

    /**
     * 新增收货地址信息表
     *
     * @param record
     * @return
     */
    int insert(OrderConsigneeInfoDO record);

    /**
     * 查询收货地址信息表详情
     *
     * @param id
     * @return
     */
    OrderConsigneeInfoDO selectById(Long id);

    /**
     * 根据ID修改收货地址信息表
     *
     * @param record
     * @return
     */
//    int updateById(OrderConsigneeInfoDO record);

}