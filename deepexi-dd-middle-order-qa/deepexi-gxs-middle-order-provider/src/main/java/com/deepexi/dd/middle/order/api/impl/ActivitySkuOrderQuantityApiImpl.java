package com.deepexi.dd.middle.order.api.impl;

import com.deepexi.dd.middle.order.api.ActivitySkuOrderQuantityApi;
import com.deepexi.dd.middle.order.domain.dto.ActivitySkuOrderQuantityDTO;
import com.deepexi.dd.middle.order.domain.query.ActivitySkuOrderQuantityQuery;
import com.deepexi.dd.middle.order.service.ActivitySkuOrderQuantityService;
import com.deepexi.util.CollectionUtil;
import com.deepexi.util.config.Payload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author zhangjian
 * @version 1.0
 * @date 2020-08-21 20:03
 */
@RestController
public class ActivitySkuOrderQuantityApiImpl implements ActivitySkuOrderQuantityApi {

    @Autowired
    private ActivitySkuOrderQuantityService activitySkuOrderQuantityService;

    /***
     * 批量插入下单商品
     * @param list
     * @return
     */
    @Override
    public Payload batchInsert(@RequestBody List<ActivitySkuOrderQuantityDTO> list) {
        activitySkuOrderQuantityService.batchInsert(list);
        return new Payload();
    }

    /***
     * 逻辑删除下单商品
     * @param id
     */
    @Override
    public void updateActivitySkuOrder(@RequestBody Long id) {
        activitySkuOrderQuantityService.updateActivitySkuOrder(id);
    }

    @Override
    public Payload<List<ActivitySkuOrderQuantityDTO>> queryActivitySku(@RequestBody ActivitySkuOrderQuantityQuery query) {
        List<ActivitySkuOrderQuantityDTO> list= activitySkuOrderQuantityService.queryActivitySku(query);
        if(CollectionUtil.isNotEmpty(list)){
            return new Payload<>(list);
        }
        return new Payload<>();
    }
}
