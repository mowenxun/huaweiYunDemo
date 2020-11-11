package com.deepexi.dd.middle.order.api;

import com.deepexi.dd.middle.order.domain.dto.ActivitySkuOrderQuantityDTO;
import com.deepexi.dd.middle.order.domain.query.ActivitySkuOrderQuantityQuery;
import com.deepexi.util.config.Payload;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author zhangjian
 * @version 1.0
 * @date 2020-08-21 20:33
 */
@Api(value = "下单商品信息表管理")
@RequestMapping("/activitySkuOrderQuantity")
public interface ActivitySkuOrderQuantityApi {

    @PostMapping("")
    Payload batchInsert(@RequestBody List<ActivitySkuOrderQuantityDTO> convert2List);

    /**
     * 逻辑删除下单商品
     * @param id
     */
    @PutMapping("")
    void updateActivitySkuOrder(@RequestBody Long id);

    /**
     * 查询参加活动的商品信息
     * @param query
     * @return
     */
    @PostMapping("/queryActivitySku")
    Payload<List<ActivitySkuOrderQuantityDTO> > queryActivitySku(@RequestBody ActivitySkuOrderQuantityQuery query);
}
