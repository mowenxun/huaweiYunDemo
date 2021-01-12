package com.deepexi.dd.system.mall.controller;

import com.deepexi.dd.domain.transaction.domain.dto.OrderReturnInfoAddResponseDTO;
import com.deepexi.dd.domain.transaction.domain.dto.OrderReturnInfoResponseDTO;
import com.deepexi.dd.domain.transaction.domain.query.OrderReturnInfoRequestQuery;
import com.deepexi.dd.system.mall.domain.dto.*;
import com.deepexi.dd.system.mall.domain.query.OrderReturnInfoAppRequestQuery;
import com.deepexi.dd.system.mall.remote.order.OrderReturnInfoRemote;
import com.deepexi.dd.system.mall.service.OrderReturnInfoService;
import com.deepexi.dd.system.mall.util.GeneralConvertUtils;
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
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * @author chenqili
 * @version 1.0
 * @date 2020-07-07 16:42
 */
@RestController
@Api(tags = "退货订单接口")
@RequestMapping("/admin-api/v1/domain/transaction/orderReturnInfo")
public class OrderReturnInfoController {
    @Autowired
    private OrderReturnInfoService orderReturnInfoService;

//    @ApiOperation("查询退货单表列表")
//    @GetMapping("/list")
//    public Payload<OrderReturnInfoAppAddResponseDTO> listSaleOrderInfos(@RequestBody @Valid OrderReturnInfoRequestQuery query) {
//
//        return new Payload();
//    }

    @ApiOperation("分页查询退货单表列表")
    @GetMapping("/page")
    public Payload<PageBean<OrderReturnInfoAppAddResponseDTO>> listOrderReturnInfosPage(@Valid OrderReturnInfoAppRequestQuery query){

        OrderReturnInfoRequestQuery model = query.clone(OrderReturnInfoRequestQuery.class);
        try{
            PageBean<OrderReturnInfoResponseDTO> result = orderReturnInfoService.listOrderReturnInfosPage(model);
            return new Payload<>(GeneralConvertUtils.convert2PageBean(result, OrderReturnInfoAppAddResponseDTO.class));
        }
        catch (Exception e){
            return new Payload<>(null,"500",e.getMessage());
        }

    }

    @ApiOperation("根据ID查询退货单")
    @GetMapping("/{id}")
    public Payload<OrderReturnInfoAppResponseDTO> selectById(@PathVariable("id") Long id)  {
        try {
            OrderReturnInfoResponseDTO orderReturnInfoResponseDTO = orderReturnInfoService.selectById(id);
            return new Payload<>(GeneralConvertUtils.conv(orderReturnInfoResponseDTO,OrderReturnInfoAppResponseDTO.class));
        }
        catch (ApplicationException e){
            return new Payload<>(null,e.getCode(),e.getMessage());
        }
        catch (Exception e){
            return new Payload<>(null,"500",e.getMessage());
        }
    }

    @ApiOperation("批量删除退货单表")
    @DeleteMapping("")
    public Payload<Boolean>  deleteByIdIn(@RequestBody List<Long> id) {
        return new Payload<Boolean>();
    }

    @ApiOperation("新增退货单表")
    @PostMapping("")
    public Payload<OrderReturnInfoAppAddResponseDTO> insert(@RequestBody @Valid OrderReturnInfoAppAddRequestDTO record){
        if(0==record.getReturnMode()&& record.getSaleOrderId()==null){
            return new Payload(null,"6003","销售订单不能为空");
        }
        if(1==record.getReturnMode()&& CollectionUtil.isEmpty(record.getItems())){
            return new Payload(null,"6006","退货明细不能为空");
        }
        try {
            OrderReturnInfoAppAddResponseDTO reponseDTO=orderReturnInfoService.createOrderReturn(record);
            return new Payload<>(reponseDTO);
        }
        catch (ApplicationException e){
            return new Payload<>(null,e.getCode(),e.getMessage());
        }
        catch (Exception e){
            return new Payload<>(null,"500",e.getMessage());
        }

    }
}
