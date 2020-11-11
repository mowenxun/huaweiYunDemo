package com.deepexi.dd.middle.order.api.impl;

import com.deepexi.dd.middle.order.api.OrderExpenseInfoApi;
import com.deepexi.dd.middle.order.domain.dto.OrderExpenseInfoDTO;
import com.deepexi.dd.middle.order.domain.dto.OrderExpenseInfoRequestDTO;
import com.deepexi.dd.middle.order.domain.dto.OrderExpenseInfoResponseDTO;
import com.deepexi.dd.middle.order.domain.query.OrderExpenseInfoQuery;
import com.deepexi.dd.middle.order.domain.query.OrderExpenseInfoRequestQuery;
import com.deepexi.dd.middle.order.service.OrderExpenseInfoService;
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
 * OrderExpenseInfoApiImpl
 *
 * @author linpbin
 * @version 1.0
 * @date 2019-12-24 15:32
 */
@RestController
public class OrderExpenseInfoApiImpl implements OrderExpenseInfoApi {

    @Autowired
    private OrderExpenseInfoService orderExpenseInfoService;

    @Override
    @ApiOperation("查询销售订单的费用信息表列表")
    public List<OrderExpenseInfoResponseDTO> listOrderExpenseInfos(OrderExpenseInfoRequestQuery query) {
        return ObjectCloneUtils
                .convertList(orderExpenseInfoService
                    .listOrderExpenseInfos(query.clone(OrderExpenseInfoQuery.class, CloneDirection.OPPOSITE)),
                                    OrderExpenseInfoResponseDTO.class, CloneDirection.OPPOSITE);
    }

    @Override
    @ApiOperation("分页查询销售订单的费用信息表列表")
    public PageBean<OrderExpenseInfoResponseDTO> listOrderExpenseInfosPage(OrderExpenseInfoRequestQuery query) {
        return ObjectCloneUtils
                .convertPageBean(orderExpenseInfoService
                    .listOrderExpenseInfosPage(query.clone(OrderExpenseInfoQuery.class, CloneDirection.OPPOSITE)),
                        OrderExpenseInfoResponseDTO.class, CloneDirection.OPPOSITE);
    }

    @Override
    @ApiOperation("批量删除销售订单的费用信息表")
    public Boolean deleteByIdIn(@RequestBody List<Long> id) {
        return orderExpenseInfoService.deleteByIdIn(id);
    }

    @Override
    @ApiOperation("新增销售订单的费用信息表")
    public OrderExpenseInfoResponseDTO insert(@RequestBody OrderExpenseInfoRequestDTO record) {
        return orderExpenseInfoService.insert(record.clone(OrderExpenseInfoDTO.class, CloneDirection.OPPOSITE))
                .clone(OrderExpenseInfoResponseDTO.class, CloneDirection.OPPOSITE);
    }

    @Override
    @ApiOperation("根据ID查询销售订单的费用信息表")
    public OrderExpenseInfoResponseDTO selectById(@PathVariable Long id) {
        OrderExpenseInfoDTO result = orderExpenseInfoService.selectById(id);
        return result != null ? result.clone(OrderExpenseInfoResponseDTO.class, CloneDirection.OPPOSITE) : null;
    }

    @Override
    @ApiOperation("根据ID更新销售订单的费用信息表")
    public Boolean updateById(@PathVariable Long id,@RequestBody OrderExpenseInfoRequestDTO record) {
        return orderExpenseInfoService.updateById(id, record.clone(OrderExpenseInfoDTO.class, CloneDirection.OPPOSITE));
    }
}
