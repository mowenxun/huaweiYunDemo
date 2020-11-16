package com.deepexi.dd.domain.transaction.controller;

import com.deepexi.dd.domain.common.util.GeneralConvertUtils;
import com.deepexi.dd.domain.transaction.domain.dto.*;
import com.deepexi.dd.domain.transaction.domain.query.OrderReturnInfoQuery;
import com.deepexi.dd.domain.transaction.domain.query.OrderReturnInfoRequestQuery;
import com.deepexi.dd.domain.transaction.service.OrderRefundReasonService;
import com.deepexi.dd.domain.transaction.service.OrderReturnInfoService;
import com.deepexi.dd.middle.order.domain.query.OrderRefundSkuRequestQuery;
import com.deepexi.util.CollectionUtil;
import com.deepexi.util.config.Payload;
import com.deepexi.util.extension.ApplicationException;
import com.deepexi.util.pageHelper.PageBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @ClassName : OrderReturnInfoController
 * @Description : 退货单
 * @Author : chenqili
 * @Date: 2020-07-07 11:47
 */
@RestController
@Api(tags = "退货订单接口")
@RequestMapping("/admin-api/v1/domain/transaction/orderReturnInfo")
public class OrderReturnInfoController {
    @Autowired
    private OrderReturnInfoService orderReturnInfoService;
    @Autowired
    private OrderRefundReasonService orderRefundReasonService;

    @ApiOperation("新增退货订单表")
    @PostMapping("")
    public Payload<OrderReturnInfoAddResponseDTO> insert(@RequestBody @Valid OrderReturnInfoRequestDTO record) throws Exception {
        if(0==record.getReturnMode()&& StringUtils.isEmpty(record.getSaleOrderId())){
            return new Payload(null,"6003","销售订单为空");
        }
        if(1==record.getReturnMode()&& CollectionUtil.isEmpty(record.getItems())){
            return new Payload(null,"6006","退货明细为空");
        }
        OrderReturnInfoDTO result = orderReturnInfoService.insert(record);
        return new Payload<>(result.clone(OrderReturnInfoAddResponseDTO.class));
    }

    @ApiOperation("分页查询退货单列表")
    @GetMapping("/page")
    public Payload<PageBean<OrderReturnInfoResponseDTO>> listOrderReturnInfosPage(@Valid OrderReturnInfoRequestQuery query) throws Exception {
        OrderReturnInfoQuery model = query.clone(OrderReturnInfoQuery.class);
        return new Payload<>(GeneralConvertUtils.convert2PageBean(orderReturnInfoService.listOrderReturnInfosPage(model),OrderReturnInfoResponseDTO.class));
    }


    @ApiOperation("根据ID查询退货单商品明细")
    @GetMapping("/queryReturnSku")
    public Payload<OrderReturnInfoResponseDTO> queryReturnSku(OrderRefundSkuRequestQuery query) throws Exception {
        try{
            return new Payload(orderRefundReasonService.queryOrderRefundSku(query));
        }
        catch (ApplicationException e){
            e.printStackTrace();
            return new Payload<>(null,e.getCode(),e.getMessage());
        }
    }

    @ApiOperation("根据ID查询退货单")
    @GetMapping("/{id}")
    public Payload<OrderReturnInfoResponseDTO> selectById(@PathVariable("id") Long id) throws Exception {
        try{
            return new Payload(orderReturnInfoService.selectById(id));
        }
        catch (ApplicationException e){
            return new Payload<>(null,e.getCode(),e.getMessage());
        }
    }

}
