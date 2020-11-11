package com.deepexi.dd.middle.order.api.impl;

import com.deepexi.dd.middle.order.api.OrderConsigneeInfoApi;
import com.deepexi.dd.middle.order.domain.dto.OrderConsigneeInfoDTO;
import com.deepexi.dd.middle.order.domain.dto.OrderConsigneeInfoRequestDTO;
import com.deepexi.dd.middle.order.domain.dto.OrderConsigneeInfoResponseDTO;
import com.deepexi.dd.middle.order.domain.query.OrderConsigneeInfoQuery;
import com.deepexi.dd.middle.order.domain.query.OrderConsigneeInfoRequestQuery;
import com.deepexi.dd.middle.order.service.OrderConsigneeInfoService;
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
 * OrderConsigneeInfoApiImpl
 *
 * @author linpbin
 * @version 1.0
 * @date 2019-12-24 15:32
 */
@RestController
public class OrderConsigneeInfoApiImpl implements OrderConsigneeInfoApi {

    @Autowired
    private OrderConsigneeInfoService orderConsigneeInfoService;

    @Override
    @ApiOperation("查询收货地址信息表列表")
    public List<OrderConsigneeInfoResponseDTO> listOrderConsigneeInfos(OrderConsigneeInfoRequestQuery query) {
        return ObjectCloneUtils
                .convertList(orderConsigneeInfoService
                    .listOrderConsigneeInfos(query.clone(OrderConsigneeInfoQuery.class, CloneDirection.OPPOSITE)),
                                    OrderConsigneeInfoResponseDTO.class, CloneDirection.OPPOSITE);
    }

    @Override
    @ApiOperation("分页查询收货地址信息表列表")
    public PageBean<OrderConsigneeInfoResponseDTO> listOrderConsigneeInfosPage(OrderConsigneeInfoRequestQuery query) {
        return ObjectCloneUtils
                .convertPageBean(orderConsigneeInfoService
                    .listOrderConsigneeInfosPage(query.clone(OrderConsigneeInfoQuery.class, CloneDirection.OPPOSITE)),
                        OrderConsigneeInfoResponseDTO.class, CloneDirection.OPPOSITE);
    }

    @Override
    @ApiOperation("批量删除收货地址信息表")
    public Boolean deleteByIdIn(@RequestBody List<Long> id) {
        return orderConsigneeInfoService.deleteByIdIn(id);
    }

    @Override
    @ApiOperation("新增收货地址信息表")
    public OrderConsigneeInfoResponseDTO insert(@RequestBody OrderConsigneeInfoRequestDTO record) {
        return orderConsigneeInfoService.insert(record.clone(OrderConsigneeInfoDTO.class, CloneDirection.OPPOSITE))
                .clone(OrderConsigneeInfoResponseDTO.class, CloneDirection.OPPOSITE);
    }

    @Override
    @ApiOperation("根据ID查询收货地址信息表")
    public OrderConsigneeInfoResponseDTO selectById(@PathVariable Long id) {
        OrderConsigneeInfoDTO result = orderConsigneeInfoService.selectById(id);
        return result != null ? result.clone(OrderConsigneeInfoResponseDTO.class, CloneDirection.OPPOSITE) : null;
    }

    @Override
    @ApiOperation("根据ID更新收货地址信息表")
    public Boolean updateById(@PathVariable Long id,@RequestBody OrderConsigneeInfoRequestDTO record) {
        return orderConsigneeInfoService.updateById(id, record.clone(OrderConsigneeInfoDTO.class, CloneDirection.OPPOSITE));
    }
}
