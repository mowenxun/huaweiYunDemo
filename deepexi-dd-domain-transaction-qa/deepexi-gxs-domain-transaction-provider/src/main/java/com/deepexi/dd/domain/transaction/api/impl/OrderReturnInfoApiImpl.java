package com.deepexi.dd.domain.transaction.api.impl;

import com.deepexi.dd.domain.common.util.GeneralConvertUtils;
import com.deepexi.dd.domain.transaction.api.OrderReturnInfoApi;
import com.deepexi.dd.domain.transaction.domain.dto.*;
import com.deepexi.dd.domain.transaction.domain.query.OrderReturnInfoQuery;
import com.deepexi.dd.domain.transaction.domain.query.OrderReturnInfoRequestQuery;
import com.deepexi.dd.domain.transaction.service.OrderReturnInfoService;
import com.deepexi.util.CollectionUtil;
import com.deepexi.util.config.Payload;
import com.deepexi.util.extension.ApplicationException;
import com.deepexi.util.pageHelper.PageBean;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

/**
 * @author chenqili
 * @version 1.0
 * @date 2020-07-08 20:14
 */
@RestController
public class OrderReturnInfoApiImpl implements OrderReturnInfoApi {
    @Autowired
    OrderReturnInfoService orderReturnInfoService;

    @Override
    @ApiOperation("分页查询销售订单表列表")
    public Payload<PageBean<OrderReturnInfoResponseDTO>> listOrderReturnInfosPage(@RequestBody @Valid OrderReturnInfoRequestQuery query) throws Exception {
        OrderReturnInfoQuery model = query.clone(OrderReturnInfoQuery.class);
        return new Payload<>(GeneralConvertUtils.convert2PageBean(orderReturnInfoService.listOrderReturnInfosPageForApi(model),OrderReturnInfoResponseDTO.class));
    }

    @Override
    @ApiOperation("新增退货订单表")
    public Payload<OrderReturnInfoAddResponseDTO> createOrderReturn(@RequestBody @Valid OrderReturnInfoRequestDTO record) throws Exception {
        if(0==record.getReturnMode()&& StringUtils.isEmpty(record.getSaleOrderId())){
            return new Payload(null,"6003","销售订单为空");
        }
        if(1==record.getReturnMode()&& CollectionUtil.isEmpty(record.getItems())){
            return new Payload(null,"6006","退货明细为空");
        }
        OrderReturnInfoDTO result = orderReturnInfoService.insert(record);
        return new Payload<>(result.clone(OrderReturnInfoAddResponseDTO.class));
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
