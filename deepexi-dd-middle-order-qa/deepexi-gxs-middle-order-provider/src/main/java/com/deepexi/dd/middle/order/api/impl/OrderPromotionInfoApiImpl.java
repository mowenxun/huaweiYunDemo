package com.deepexi.dd.middle.order.api.impl;

import com.deepexi.dd.middle.order.api.OrderPromotionInfoApi;
import com.deepexi.dd.middle.order.domain.dto.OrderPromotionInfoDTO;
import com.deepexi.dd.middle.order.domain.dto.OrderPromotionInfoRequestDTO;
import com.deepexi.dd.middle.order.domain.dto.OrderPromotionInfoResponseDTO;
import com.deepexi.dd.middle.order.domain.query.OrderPromotionInfoQuery;
import com.deepexi.dd.middle.order.domain.query.OrderPromotionInfoRequestQuery;
import com.deepexi.dd.middle.order.service.OrderPromotionInfoService;
import com.deepexi.util.pageHelper.PageBean;
import com.deepexi.util.pojo.ObjectCloneUtils;
import com.deepexi.util.pojo.CloneDirection;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * OrderPromotionInfoApiImpl
 *
 * @author linpbin
 * @version 1.0
 * @date 2019-12-24 15:32
 */
@RestController
public class OrderPromotionInfoApiImpl implements OrderPromotionInfoApi {

    @Autowired
    private OrderPromotionInfoService orderPromotionInfoService;

    @Override
    @ApiOperation("查询销售订单的促销信息表列表")
    public List<OrderPromotionInfoResponseDTO> listOrderPromotionInfos(OrderPromotionInfoRequestQuery query) {
        return ObjectCloneUtils
                .convertList(orderPromotionInfoService
                    .listOrderPromotionInfos(query.clone(OrderPromotionInfoQuery.class, CloneDirection.OPPOSITE)),
                                    OrderPromotionInfoResponseDTO.class, CloneDirection.OPPOSITE);
    }

    @Override
    @ApiOperation("分页查询销售订单的促销信息表列表")
    public PageBean<OrderPromotionInfoResponseDTO> listOrderPromotionInfosPage(OrderPromotionInfoRequestQuery query) {
        return ObjectCloneUtils
                .convertPageBean(orderPromotionInfoService
                    .listOrderPromotionInfosPage(query.clone(OrderPromotionInfoQuery.class, CloneDirection.OPPOSITE)),
                        OrderPromotionInfoResponseDTO.class, CloneDirection.OPPOSITE);
    }

    @Override
    @ApiOperation("批量删除销售订单的促销信息表")
    public Boolean deleteByIdIn(@RequestBody List<Long> id) {
        return orderPromotionInfoService.deleteByIdIn(id);
    }

    @Override
    @ApiOperation("新增销售订单的促销信息表")
    public OrderPromotionInfoResponseDTO insert(@RequestBody OrderPromotionInfoRequestDTO record) {
        return orderPromotionInfoService.insert(record.clone(OrderPromotionInfoDTO.class, CloneDirection.OPPOSITE))
                .clone(OrderPromotionInfoResponseDTO.class, CloneDirection.OPPOSITE);
    }

    @Override
    @ApiOperation("根据ID查询销售订单的促销信息表")
    public OrderPromotionInfoResponseDTO selectById(@PathVariable Long id) {
        OrderPromotionInfoDTO result = orderPromotionInfoService.selectById(id);
        return result != null ? result.clone(OrderPromotionInfoResponseDTO.class, CloneDirection.OPPOSITE) : null;
    }

    @Override
    @ApiOperation("根据ID更新销售订单的促销信息表")
    public Boolean updateById(@PathVariable Long id,@RequestBody OrderPromotionInfoRequestDTO record) {
        return orderPromotionInfoService.updateById(id, record.clone(OrderPromotionInfoDTO.class, CloneDirection.OPPOSITE));
    }
}
