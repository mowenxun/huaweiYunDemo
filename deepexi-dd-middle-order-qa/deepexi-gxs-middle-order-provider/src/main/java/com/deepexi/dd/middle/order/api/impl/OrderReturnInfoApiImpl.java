package com.deepexi.dd.middle.order.api.impl;

import com.deepexi.dd.middle.order.api.OrderReturnInfoApi;
import com.deepexi.dd.middle.order.domain.dto.OrderReturnInfoAddRequestDTO;
import com.deepexi.dd.middle.order.domain.dto.OrderReturnInfoDTO;
import com.deepexi.dd.middle.order.domain.dto.OrderReturnInfoRequestDTO;
import com.deepexi.dd.middle.order.domain.dto.OrderReturnInfoResponseDTO;
import com.deepexi.dd.middle.order.domain.query.OrderReturnInfoQuery;
import com.deepexi.dd.middle.order.domain.query.OrderReturnInfoRequestQuery;
import com.deepexi.dd.middle.order.service.OrderReturnInfoService;
import com.deepexi.util.config.Payload;
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
 * OrderReturnInfoApiImpl
 *
 * @author linpbin
 * @version 1.0
 * @date 2019-12-24 15:32
 */
@RestController
public class OrderReturnInfoApiImpl implements OrderReturnInfoApi {

    @Autowired
    private OrderReturnInfoService orderReturnInfoService;

    @Override
    @ApiOperation("查询销售订单退货单信息表列表")
    public Payload<List<OrderReturnInfoResponseDTO>> listOrderReturnInfos(OrderReturnInfoRequestQuery query) {
        return new Payload<>(ObjectCloneUtils
                .convertList(orderReturnInfoService
                    .listOrderReturnInfos(query.clone(OrderReturnInfoQuery.class, CloneDirection.OPPOSITE)),
                                    OrderReturnInfoResponseDTO.class, CloneDirection.OPPOSITE));
    }

    @Override
    @ApiOperation("分页查询销售订单退货单信息表列表")
    public Payload<PageBean<OrderReturnInfoResponseDTO>> listOrderReturnInfosPage(OrderReturnInfoRequestQuery query) {
        return new Payload<>(ObjectCloneUtils
                .convertPageBean(orderReturnInfoService
                    .listOrderReturnInfosPage(query.clone(OrderReturnInfoQuery.class, CloneDirection.OPPOSITE)),
                        OrderReturnInfoResponseDTO.class, CloneDirection.OPPOSITE));
    }

    @Override
    @ApiOperation("批量删除销售订单退货单信息表")
    public Payload<Boolean> deleteByIdIn(@RequestBody List<Long> id) {
        return new Payload<>(orderReturnInfoService.deleteByIdIn(id));
    }

    @Override
    @ApiOperation("新增销售订单退货单信息表")
    public Payload<OrderReturnInfoResponseDTO> insert(@RequestBody OrderReturnInfoAddRequestDTO record) {
        return new Payload<>(orderReturnInfoService.insert(record)
                .clone(OrderReturnInfoResponseDTO.class, CloneDirection.OPPOSITE));
    }

    @Override
    @ApiOperation("根据ID查询销售订单退货单信息表")
    public Payload<OrderReturnInfoResponseDTO> selectById(@PathVariable Long id) {
        OrderReturnInfoDTO result = orderReturnInfoService.selectById(id);
        return result != null ? new Payload<>(result.clone(OrderReturnInfoResponseDTO.class, CloneDirection.OPPOSITE)) : new Payload<>(null);
    }

    @Override
    @ApiOperation("根据ID更新销售订单退货单信息表")
    public Payload<Boolean> updateById(@PathVariable Long id,@RequestBody OrderReturnInfoRequestDTO record) {
        return new Payload<>(orderReturnInfoService.updateById(id, record.clone(OrderReturnInfoDTO.class, CloneDirection.OPPOSITE)));
    }
}
