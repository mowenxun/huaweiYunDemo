package com.deepexi.dd.middle.order.api.impl;

import com.deepexi.dd.middle.order.api.OrderInvoiceApi;
import com.deepexi.dd.middle.order.domain.OrderInvoiceDTO;
import com.deepexi.dd.middle.order.domain.dto.OrderInvoiceRequestDTO;
import com.deepexi.dd.middle.order.domain.dto.OrderInvoiceResponseDTO;
import com.deepexi.dd.middle.order.domain.OrderInvoiceQuery;
import com.deepexi.dd.middle.order.domain.query.OrderInvoiceRequestQuery;
import com.deepexi.dd.middle.order.service.OrderInvoiceService;
import com.deepexi.util.pageHelper.PageBean;
import com.deepexi.util.pojo.ObjectCloneUtils;
import com.deepexi.util.pojo.CloneDirection;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


/**
 * OrderInvoiceApiImpl
 *
 * @author linpbin
 * @version 1.0
 * @date 2019-12-24 15:32
 */
@RestController
public class OrderInvoiceApiImpl implements OrderInvoiceApi {

    @Autowired
    private OrderInvoiceService orderInvoiceService;

    @Override
    @ApiOperation("查询订单的开票信息表列表")
    public List<OrderInvoiceResponseDTO> listOrderInvoices(OrderInvoiceRequestQuery query) {
        return ObjectCloneUtils
                .convertList(orderInvoiceService
                    .listOrderInvoices(query.clone(OrderInvoiceQuery.class, CloneDirection.OPPOSITE)),
                                    OrderInvoiceResponseDTO.class, CloneDirection.OPPOSITE);
    }

    @Override
    @ApiOperation("分页查询订单的开票信息表列表")
    public PageBean<OrderInvoiceResponseDTO> listOrderInvoicesPage(OrderInvoiceRequestQuery query) {
        return ObjectCloneUtils
                .convertPageBean(orderInvoiceService
                    .listOrderInvoicesPage(query.clone(OrderInvoiceQuery.class, CloneDirection.OPPOSITE)),
                        OrderInvoiceResponseDTO.class, CloneDirection.OPPOSITE);
    }

    @Override
    @ApiOperation("批量删除订单的开票信息表")
    public Boolean deleteByIdIn(@RequestBody List<Long> id) {
        return orderInvoiceService.deleteByIdIn(id);
    }

    @Override
    @ApiOperation("新增订单的开票信息表")
    public OrderInvoiceResponseDTO insert(@RequestBody OrderInvoiceRequestDTO record) {
        return orderInvoiceService.insert(record.clone(OrderInvoiceDTO.class, CloneDirection.OPPOSITE))
                .clone(OrderInvoiceResponseDTO.class, CloneDirection.OPPOSITE);
    }

    @Override
    @ApiOperation("根据ID查询订单的开票信息表")
    public OrderInvoiceResponseDTO selectById(@PathVariable Long id) {
        OrderInvoiceDTO result = orderInvoiceService.selectById(id);
        return result != null ? result.clone(OrderInvoiceResponseDTO.class, CloneDirection.OPPOSITE) : null;
    }

    @Override
    @ApiOperation("根据ID更新订单的开票信息表")
    public Boolean updateById(@PathVariable Long id,@RequestBody OrderInvoiceRequestDTO record) {
        return orderInvoiceService.updateById(id, record.clone(OrderInvoiceDTO.class, CloneDirection.OPPOSITE));
    }
}
