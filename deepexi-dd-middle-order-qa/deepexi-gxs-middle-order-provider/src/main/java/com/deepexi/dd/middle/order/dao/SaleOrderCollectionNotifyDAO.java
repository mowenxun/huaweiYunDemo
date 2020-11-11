package com.deepexi.dd.middle.order.dao;

import com.baomidou.mybatisplus.extension.service.IService;
import com.deepexi.dd.middle.order.domain.SaleOrderCollectionNotifyDO;
import com.deepexi.dd.middle.order.domain.SaleOrderCollectionNotifyQuery;
import com.deepexi.dd.middle.order.domain.SaleOrderPayNotifyDO;

import java.util.List;


/**
 * SaleOrderCollectionNotifyDAO
 *
 * @author admin
 * @date Fri Jul 24 14:50:09 CST 2020
 * @version 1.0
 */
public interface SaleOrderCollectionNotifyDAO extends IService<SaleOrderCollectionNotifyDO> {

    /**
     * 查询按单收款消息表列表}
     *
     * @return
     */
    List<SaleOrderCollectionNotifyDO> listSaleOrderCollectionNotifys(SaleOrderCollectionNotifyQuery query);

    /**
     * 根据ID删除按单收款消息表
     *
     * @param id
     * @return
     */
    int deleteByIdIn(List<Long> id);

    /**
     * 新增按单收款消息表
     *
     * @param record
     * @return
     */
    int insert(SaleOrderCollectionNotifyDO record);

    /**
     * 查询按单收款消息表详情
     *
     * @param id
     * @return
     */
    SaleOrderCollectionNotifyDO selectById(Long id);

    /**
     * 根据ID修改按单收款消息表
     *
     * @param record
     * @return
     */
    @Override
    boolean updateById(SaleOrderCollectionNotifyDO record);

}