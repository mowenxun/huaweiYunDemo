package com.deepexi.dd.middle.order.dao;

import com.baomidou.mybatisplus.extension.service.IService;
import com.deepexi.dd.middle.order.domain.ActivitySkuOrderQuantityDO;
import com.deepexi.dd.middle.order.domain.dto.ActivitySkuOrderQuantityDTO;

import java.util.List;

/**
 * @author zhangjian
 * @version 1.0
 * @date 2020-08-21 19:13
 */
public interface ActivitySkuOrderQuantityDao extends IService<ActivitySkuOrderQuantityDO> {

    /**
     * 批量插入
     * @param activitySkuOrderQuantityDTOList
     */
    void batchInsert(List<ActivitySkuOrderQuantityDTO> activitySkuOrderQuantityDTOList);

    /***
     * 逻辑删除下单商品
     * @param id
     */
    void updateActivitySkuOrder(Long id);
}
