package com.deepexi.dd.domain.transaction.controller;

import com.deepexi.dd.domain.common.extension.AppRuntimeEnv;
import com.deepexi.dd.domain.transaction.api.gxs.ShopAfterSaleDomainApi;
import com.deepexi.dd.domain.transaction.api.gxs.domain.*;
import com.deepexi.dd.domain.transaction.remote.gxs.SupplerOrderItemRemote;
import com.deepexi.dd.domain.transaction.service.AfterSaleOrderItemDomainService;
import com.deepexi.dd.domain.transaction.service.AfterSaleOrderService;
import com.deepexi.dd.middle.order.domain.dto.*;
import com.deepexi.dd.middle.order.domain.query.AfterSaleOrdeRequestQuery;
import com.deepexi.dd.middle.order.domain.query.AfterSaleOrderItemRequestQuery;
import com.deepexi.dd.middle.order.domain.query.SupplerOrderItemRequestQuery;
import com.deepexi.util.config.Payload;
import com.deepexi.util.pageHelper.PageBean;
import com.deepexi.util.pojo.ObjectCloneUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 售后单
 *
 * @author zhangjian
 * @date 2020/10/14
 */
@Slf4j
@RestController
@RequestMapping("/after/sale/order")
@Api(tags = "售后单")
public class AfterSaleOrderController {


    @Autowired
    AfterSaleOrderService afterSaleOrderService;
    @Autowired
    AfterSaleOrderItemDomainService afterSaleOrderItemService;
    @Autowired
    SupplerOrderItemRemote supplerOrderItemRemote;
    @Autowired
    AppRuntimeEnv appRuntimeEnv;

    /**
     * 查询售后单
     */
    @GetMapping("/list")
    @ApiOperation(value = "售后申请列表展示", notes = "售后申请列表展示")
    public PageBean<ListAfterSaleOrderResponseDTO> list(ListAfterSaleOrderRequestDTO vo){
        log.info("售后单入参:{}",vo);
        SimpleDateFormat simpleDateFormat  = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if(null != vo && null != vo.getCreatedTimeFrom()){
            vo.setCreatedTimeFrom(simpleDateFormat.format(new Date(Long.parseLong(vo.getCreatedTimeFrom()))));
        }
        if(null != vo && null != vo.getCreatedTimeTo()){
            vo.setCreatedTimeTo(simpleDateFormat.format(new Date(Long.parseLong(vo.getCreatedTimeTo()))));
        }
        PageBean<AfterSaleOrdeResponseDTO> page = afterSaleOrderService.listAfterSaleOrdesPage(vo.clone(AfterSaleOrdeRequestQuery.class, 1));
        return ObjectCloneUtils.convertPageBean(page, ListAfterSaleOrderResponseDTO.class, 1);
    }

    /**
     * 查询售后单商品item(创建售后单)
     * 通过商品sku编码和商品名称模糊查询
     */
    @GetMapping("/queryAfterSaleItem")
    @ApiOperation(value = "查询退货商品", notes = "查询退货商品")
    public PageBean<SupplerOrderItemDomainResponseDTO> querySupplierItems(SupplerOrderItemDomainRequestQuery query) {
        PageBean<SupplerOrderItemResponseDTO> page = supplerOrderItemRemote.listSupplerOrderItemsPage(query.clone(SupplerOrderItemRequestQuery.class,1));
        return ObjectCloneUtils.convertPageBean(page, SupplerOrderItemDomainResponseDTO.class, 1);
    }


    /**
     * 售后单详情
     */
    @GetMapping("/afterSaleDetails/{id}")
    @ApiOperation(value = "查询售后单详情", notes = "查询售后单详情")
    public AfterSaleOrderDetailsResponseDTO afterSaleDetails(@ApiParam(value = "售后单的主键id", required = true) @PathVariable("id") Long id) {
        AfterSaleOrdeResponseDTO afterSaleOrder = afterSaleOrderService.selectById(id);
        if (afterSaleOrder == null) {
            return null;
        }
        // 售后单item
        AfterSaleOrderItemRequestQuery query = new AfterSaleOrderItemRequestQuery();
        query.setAfterSaleOrderId(id);
        List<AfterSaleOrderItemResponseDTO> items = afterSaleOrderItemService.listAfterSaleOrderItems(query);

        AfterSaleOrderDetailsResponseDTO rs = afterSaleOrder.clone(AfterSaleOrderDetailsResponseDTO.class, 1);
        rs.setAfterSaleItems(ObjectCloneUtils.convertList(items, AfterSaleOrderDetailsResponseDTO.AfterSaleItem.class, 1));
        return rs;
    }

    /**
     * 修改售后单状态  审核
     */
    @PutMapping("/examine/{id}")
    @ApiOperation(value = "修改售后单状态", notes = "修改售后单状态")
    public Boolean updateStatus(@PathVariable("id") Long id, @RequestBody AfterSaleOrdeRequestDTO dto) {
        return afterSaleOrderService.examine(id, dto);
    }

}
