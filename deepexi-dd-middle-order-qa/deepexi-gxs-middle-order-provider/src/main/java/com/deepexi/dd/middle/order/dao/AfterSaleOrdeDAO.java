package com.deepexi.dd.middle.order.dao;


import com.deepexi.dd.middle.order.domain.AfterSaleOrdeDO;
import com.deepexi.dd.middle.order.domain.query.AfterSaleOrdeQuery;

import java.util.List;


/**
 * AfterSaleOrdeDAO
 *
 * @author admin
 * @date Fri Oct 16 14:17:12 CST 2020
 * @version 1.0
 */
public interface AfterSaleOrdeDAO {

    /**
     * 查询售后申请单列表}
     *
     * @return
     */
    List<AfterSaleOrdeDO> listAfterSaleOrdes(AfterSaleOrdeQuery query);

    /**
     * 根据ID删除售后申请单
     *
     * @param id
     * @return
     */
    int deleteByIdIn(List<Long> id);

    /**
     * 新增售后申请单
     *
     * @param record
     * @return
     */
    int insert(AfterSaleOrdeDO record);

    /**
     * 查询售后申请单详情
     *
     * @param id
     * @return
     */
    AfterSaleOrdeDO selectById(Long id);

    /**
     * 根据ID修改售后申请单
     *
     * @param record
     * @return
     */
    int updateById(AfterSaleOrdeDO record);

}