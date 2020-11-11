package com.deepexi.dd.middle.order.dao;

import com.baomidou.mybatisplus.extension.service.IService;
import com.deepexi.dd.middle.order.domain.SaleOrderInfoDO;
import com.deepexi.dd.middle.order.domain.SaleOrderPayNotifyDO;
import com.deepexi.dd.middle.order.domain.SaleOrderPayNotifyQuery;

import java.util.List;


/**
 * SaleOrderPayNotifyDAO
 *
 * @author admin
 * @date Thu Jul 23 18:17:38 CST 2020
 * @version 1.0
 */
public interface SaleOrderPayNotifyDAO extends IService<SaleOrderPayNotifyDO> {

    /**
     * 查询列表}
     *
     * @return
     */
    List<SaleOrderPayNotifyDO> listSaleOrderPayNotifys(SaleOrderPayNotifyQuery query);

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
    int insert(SaleOrderPayNotifyDO record);

    /**
     * 查询详情
     *
     * @param id
     * @return
     */
    SaleOrderPayNotifyDO selectById(Long id);

    /**
     * 根据ID修改
     *
     * @param record
     * @return
     */
    @Override
    boolean updateById(SaleOrderPayNotifyDO record);

}