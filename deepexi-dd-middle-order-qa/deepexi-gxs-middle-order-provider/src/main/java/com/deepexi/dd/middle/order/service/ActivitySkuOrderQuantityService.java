package com.deepexi.dd.middle.order.service;

import com.deepexi.dd.middle.order.domain.dto.ActivitySkuOrderQuantityDTO;
import com.deepexi.dd.middle.order.domain.query.ActivitySkuOrderQuantityQuery;
import com.deepexi.util.config.Payload;

import java.util.List;

/**
 * @author zhangjian
 * @version 1.0
 * @date 2020-08-21 19:10
 */
public interface ActivitySkuOrderQuantityService {

    /**
     * 批量插入下单商品
     *
     * @return
     */
    void batchInsert(List<ActivitySkuOrderQuantityDTO> activitySkuOrderQuantityDTOList);

    /**
     * 逻辑删除下单商品
     * @param id
     */
    void updateActivitySkuOrder(Long id);

    /**
     * 查询已参加活动的商品
     * @param query
     * @return
     */
    List<ActivitySkuOrderQuantityDTO> queryActivitySku(ActivitySkuOrderQuantityQuery query);
}
