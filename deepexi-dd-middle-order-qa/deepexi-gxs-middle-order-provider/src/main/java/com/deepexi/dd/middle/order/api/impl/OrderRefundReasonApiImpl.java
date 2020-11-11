package com.deepexi.dd.middle.order.api.impl;

import com.deepexi.dd.middle.order.api.OrderRefundReasonApi;
import com.deepexi.dd.middle.order.domain.dto.OrderRefundReasonDTO;
import com.deepexi.dd.middle.order.domain.dto.OrderRefundReasonFindResponseDTO;
import com.deepexi.dd.middle.order.domain.dto.OrderRefundReasonRequestDTO;
import com.deepexi.dd.middle.order.domain.dto.OrderRefundReasonResponseDTO;
import com.deepexi.dd.middle.order.domain.query.OrderRefundReasonQuery;
import com.deepexi.dd.middle.order.domain.query.OrderRefundReasonRequestQuery;
import com.deepexi.dd.middle.order.service.OrderRefundReasonService;
import com.deepexi.util.config.Payload;
import com.deepexi.util.pageHelper.PageBean;
import com.deepexi.util.pojo.CloneDirection;
import com.deepexi.util.pojo.ObjectCloneUtils;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * OrderRefundReasonApiImpl
 *
 * @author linpbin
 * @version 1.0
 * @date 2019-12-24 15:32
 */
@RestController
public class OrderRefundReasonApiImpl implements OrderRefundReasonApi {

    @Autowired
    private OrderRefundReasonService orderRefundReasonService;

    @Override
    public Payload<List<OrderRefundReasonResponseDTO>> CheckCode(OrderRefundReasonRequestQuery query) {
        return new Payload<>(ObjectCloneUtils.convertList(
                orderRefundReasonService.CheckCode(query.clone(OrderRefundReasonQuery.class, CloneDirection.OPPOSITE)),
                OrderRefundReasonResponseDTO.class, CloneDirection.OPPOSITE));
    }

    @Override
    @ApiOperation("查询列表")
    public Payload<List<OrderRefundReasonResponseDTO>> listOrderRefundReasons(OrderRefundReasonRequestQuery query) {
        return new Payload<>(ObjectCloneUtils
                .convertList(orderRefundReasonService
                                .listOrderRefundReasons(query.clone(OrderRefundReasonQuery.class, CloneDirection.OPPOSITE)),
                        OrderRefundReasonResponseDTO.class, CloneDirection.OPPOSITE));
    }

    @Override
    @ApiOperation("分页查询列表")
    public Payload<PageBean<OrderRefundReasonResponseDTO>> listOrderRefundReasonsPage(OrderRefundReasonRequestQuery query) {
        return new Payload<>(ObjectCloneUtils
                .convertPageBean(orderRefundReasonService
                                .listOrderRefundReasonsPage(query.clone(OrderRefundReasonQuery.class, CloneDirection.OPPOSITE)),
                        OrderRefundReasonResponseDTO.class, CloneDirection.OPPOSITE));
    }

    @Override
    @ApiOperation("批量删除")
    public Payload<Boolean> deleteByIdIn(@RequestBody List<Long> id) {
        return new Payload<>(orderRefundReasonService.deleteByIdIn(id));
    }

    @Override
    @ApiOperation("新增")
    public Payload<OrderRefundReasonResponseDTO> insert(@RequestBody OrderRefundReasonRequestDTO record) {
        return new Payload<>(orderRefundReasonService.insert(record.clone(OrderRefundReasonDTO.class, CloneDirection.OPPOSITE))
                .clone(OrderRefundReasonResponseDTO.class, CloneDirection.OPPOSITE));
    }

    @Override
    @ApiOperation("根据ID查询")
    public Payload<OrderRefundReasonResponseDTO> selectById(@PathVariable Long id) {
        OrderRefundReasonDTO result = orderRefundReasonService.selectById(id);
        return new Payload<>(result != null ? result.clone(OrderRefundReasonResponseDTO.class, CloneDirection.OPPOSITE) : null);
    }

    @Override
    @ApiOperation("根据ID更新")
    public Payload<Boolean> updateById(@PathVariable Long id, @RequestBody OrderRefundReasonRequestDTO record) {
        return new Payload<>(orderRefundReasonService.updateById(id, record.clone(OrderRefundReasonDTO.class, CloneDirection.OPPOSITE)));
    }

    @Override
    @ApiOperation("批量修改状态")
    public Payload<Boolean> updateStatus(@RequestBody OrderRefundReasonRequestDTO record) {
        return new Payload<>(orderRefundReasonService.updateStatus(record));
    }

    @Override
    @ApiOperation("取消退款")
    public Payload<Boolean> cancel(@PathVariable Long id) {
        return new Payload<>(orderRefundReasonService.cancel(id));
    }

    @Override
    public Payload<List<OrderRefundReasonFindResponseDTO>> findOrderRefundReason(@RequestBody List<String> orderCodeList) {

        return new Payload<>(ObjectCloneUtils
                .convertList(orderRefundReasonService
                                .findOrderRefundReason(orderCodeList),
                        OrderRefundReasonFindResponseDTO.class, CloneDirection.OPPOSITE));
    }
}
