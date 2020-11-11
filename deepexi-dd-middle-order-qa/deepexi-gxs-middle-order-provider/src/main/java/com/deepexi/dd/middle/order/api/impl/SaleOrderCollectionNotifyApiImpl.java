package com.deepexi.dd.middle.order.api.impl;

import com.deepexi.dd.middle.order.api.SaleOrderCollectionNotifyApi;
import com.deepexi.dd.middle.order.domain.SaleOrderCollectionNotifyDTO;
import com.deepexi.dd.middle.order.domain.dto.SaleOrderCollectionNotifyRequestDTO;
import com.deepexi.dd.middle.order.domain.dto.SaleOrderCollectionNotifyResponseDTO;
import com.deepexi.dd.middle.order.domain.SaleOrderCollectionNotifyQuery;
import com.deepexi.dd.middle.order.domain.dto.SaleOrderPayNotifyResponseDTO;
import com.deepexi.dd.middle.order.domain.query.SaleOrderCollectionNotifyRequestQuery;
import com.deepexi.dd.middle.order.domain.query.SaleOrderCollectionNotifySearchQuery;
import com.deepexi.dd.middle.order.service.SaleOrderCollectionNotifyService;
import com.deepexi.util.CollectionUtil;
import com.deepexi.util.config.Payload;
import com.deepexi.util.pageHelper.PageBean;
import com.deepexi.util.pojo.ObjectCloneUtils;
import com.deepexi.util.pojo.CloneDirection;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


/**
 * SaleOrderCollectionNotifyApiImpl
 *
 * @author linpbin
 * @version 1.0
 * @date 2019-12-24 15:32
 */
@RestController
public class SaleOrderCollectionNotifyApiImpl implements SaleOrderCollectionNotifyApi {

    @Autowired
    private SaleOrderCollectionNotifyService saleOrderCollectionNotifyService;

    @Override
    @ApiOperation("查询按单收款消息表列表")
    public List<SaleOrderCollectionNotifyResponseDTO> listSaleOrderCollectionNotifys(SaleOrderCollectionNotifyRequestQuery query) {
        return ObjectCloneUtils
                .convertList(saleOrderCollectionNotifyService
                    .listSaleOrderCollectionNotifys(query.clone(SaleOrderCollectionNotifyQuery.class, CloneDirection.OPPOSITE)),
                                    SaleOrderCollectionNotifyResponseDTO.class, CloneDirection.OPPOSITE);
    }

    @Override
    @ApiOperation("分页查询按单收款消息表列表")
    public PageBean<SaleOrderCollectionNotifyResponseDTO> listSaleOrderCollectionNotifysPage(SaleOrderCollectionNotifyRequestQuery query) {
        return ObjectCloneUtils
                .convertPageBean(saleOrderCollectionNotifyService
                    .listSaleOrderCollectionNotifysPage(query.clone(SaleOrderCollectionNotifyQuery.class, CloneDirection.OPPOSITE)),
                        SaleOrderCollectionNotifyResponseDTO.class, CloneDirection.OPPOSITE);
    }

    @Override
    @ApiOperation("批量删除按单收款消息表")
    public Boolean deleteByIdIn(@RequestBody List<Long> id) {
        return saleOrderCollectionNotifyService.deleteByIdIn(id);
    }

    @Override
    @ApiOperation("新增按单收款消息表")
    public SaleOrderCollectionNotifyResponseDTO insert(@RequestBody SaleOrderCollectionNotifyRequestDTO record) {
        return saleOrderCollectionNotifyService.insert(record.clone(SaleOrderCollectionNotifyDTO.class, CloneDirection.OPPOSITE))
                .clone(SaleOrderCollectionNotifyResponseDTO.class, CloneDirection.OPPOSITE);
    }

    @Override
    @ApiOperation("根据ID查询按单收款消息表")
    public SaleOrderCollectionNotifyResponseDTO selectById(@PathVariable Long id) {
        SaleOrderCollectionNotifyDTO result = saleOrderCollectionNotifyService.selectById(id);
        return result != null ? result.clone(SaleOrderCollectionNotifyResponseDTO.class, CloneDirection.OPPOSITE) : null;
    }

    @Override
    @ApiOperation("根据ID更新按单收款消息表")
    public Boolean updateById(@PathVariable Long id,@RequestBody SaleOrderCollectionNotifyRequestDTO record) {
        return saleOrderCollectionNotifyService.updateById(id, record.clone(SaleOrderCollectionNotifyDTO.class, CloneDirection.OPPOSITE));
    }

    @Override
    public Payload<List<SaleOrderCollectionNotifyResponseDTO>> querySaleOrderCollectionNotify(@RequestBody SaleOrderCollectionNotifySearchQuery query) {
        SaleOrderCollectionNotifyQuery saleOrderPayNotifyQuery=new SaleOrderCollectionNotifyQuery();
        saleOrderPayNotifyQuery.setPayNo(query.getPayNo());
        saleOrderPayNotifyQuery.setOrderId(query.getOrderId());
        List<SaleOrderCollectionNotifyDTO> list= saleOrderCollectionNotifyService.querySaleOrderCollectionNotify(saleOrderPayNotifyQuery);
        List<SaleOrderCollectionNotifyResponseDTO> result=new ArrayList<>();
        if(CollectionUtil.isNotEmpty(list)){
            for(SaleOrderCollectionNotifyDTO dto:list){
                result.add(dto.clone(SaleOrderCollectionNotifyResponseDTO.class));
            }
        }
        return new Payload<>(result);
    }
}
