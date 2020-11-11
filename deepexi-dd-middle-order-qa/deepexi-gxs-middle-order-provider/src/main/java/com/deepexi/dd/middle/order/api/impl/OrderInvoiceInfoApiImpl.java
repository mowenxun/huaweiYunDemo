package com.deepexi.dd.middle.order.api.impl;

import com.deepexi.dd.middle.order.api.OrderInvoiceInfoApi;
import com.deepexi.dd.middle.order.domain.dto.OrderInvoiceInfoDTO;
import com.deepexi.dd.middle.order.domain.dto.OrderInvoiceInfoRequestDTO;
import com.deepexi.dd.middle.order.domain.dto.OrderInvoiceInfoResponseDTO;
import com.deepexi.dd.middle.order.domain.query.OrderInvoiceInfoQuery;
import com.deepexi.dd.middle.order.domain.query.OrderInvoiceInfoRequestQuery;
import com.deepexi.dd.middle.order.service.OrderInvoiceInfoService;
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
 * OrderInvoiceInfoApiImpl
 *
 * @author linpbin
 * @version 1.0
 * @date 2019-12-24 15:32
 */
@RestController
public class OrderInvoiceInfoApiImpl implements OrderInvoiceInfoApi {

    @Autowired
    private OrderInvoiceInfoService orderInvoiceInfoService;

    @Override
    @ApiOperation("查询订单的开票信息表列表")
    public List<OrderInvoiceInfoResponseDTO> listOrderInvoiceInfos(OrderInvoiceInfoRequestQuery query) {
        return ObjectCloneUtils
                .convertList(orderInvoiceInfoService
                    .listOrderInvoiceInfos(query.clone(OrderInvoiceInfoQuery.class, CloneDirection.OPPOSITE)),
                                    OrderInvoiceInfoResponseDTO.class, CloneDirection.OPPOSITE);
    }

    @Override
    @ApiOperation("分页查询订单的开票信息表列表")
    public PageBean<OrderInvoiceInfoResponseDTO> listOrderInvoiceInfosPage(OrderInvoiceInfoRequestQuery query) {
        return ObjectCloneUtils
                .convertPageBean(orderInvoiceInfoService
                    .listOrderInvoiceInfosPage(query.clone(OrderInvoiceInfoQuery.class, CloneDirection.OPPOSITE)),
                        OrderInvoiceInfoResponseDTO.class, CloneDirection.OPPOSITE);
    }

    @Override
    @ApiOperation("批量删除订单的开票信息表")
    public Boolean deleteByIdIn(@RequestBody List<Long> id) {
        return orderInvoiceInfoService.deleteByIdIn(id);
    }

    @Override
    @ApiOperation("新增订单的开票信息表")
    public OrderInvoiceInfoResponseDTO insert(@RequestBody OrderInvoiceInfoRequestDTO record) {
        return orderInvoiceInfoService.insert(record.clone(OrderInvoiceInfoDTO.class, CloneDirection.OPPOSITE))
                .clone(OrderInvoiceInfoResponseDTO.class, CloneDirection.OPPOSITE);
    }

    @Override
    @ApiOperation("根据ID查询订单的开票信息表")
    public OrderInvoiceInfoResponseDTO selectById(@PathVariable Long id) {
        OrderInvoiceInfoDTO result = orderInvoiceInfoService.selectById(id);
        return result != null ? result.clone(OrderInvoiceInfoResponseDTO.class, CloneDirection.OPPOSITE) : null;
    }

    @Override
    @ApiOperation("根据ID更新订单的开票信息表")
    public Boolean updateById(@PathVariable Long id,@RequestBody OrderInvoiceInfoRequestDTO record) {
        return orderInvoiceInfoService.updateById(id, record.clone(OrderInvoiceInfoDTO.class, CloneDirection.OPPOSITE));
    }
}
