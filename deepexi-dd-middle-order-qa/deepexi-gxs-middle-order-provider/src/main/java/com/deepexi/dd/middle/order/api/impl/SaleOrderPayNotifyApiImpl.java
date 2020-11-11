package com.deepexi.dd.middle.order.api.impl;

import com.deepexi.dd.middle.order.api.SaleOrderPayNotifyApi;
import com.deepexi.dd.middle.order.domain.SaleOrderPayNotifyDTO;
import com.deepexi.dd.middle.order.domain.SaleOrderPayNotifyQuery;
import com.deepexi.dd.middle.order.domain.dto.SaleOrderPayNotifyRequestDTO;
import com.deepexi.dd.middle.order.domain.dto.SaleOrderPayNotifyResponseDTO;
import com.deepexi.dd.middle.order.domain.query.SaleOrderPayNotifySearchQuery;
import com.deepexi.dd.middle.order.domain.query.SaleOrderPayNotifyRequestQuery;
import com.deepexi.dd.middle.order.service.SaleOrderPayNotifyService;
import com.deepexi.util.config.Payload;
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
 * SaleOrderPayNotifyApiImpl
 *
 * @author linpbin
 * @version 1.0
 * @date 2019-12-24 15:32
 */
@RestController
public class SaleOrderPayNotifyApiImpl implements SaleOrderPayNotifyApi {

    @Autowired
    private SaleOrderPayNotifyService saleOrderPayNotifyService;

    @Override
    @ApiOperation("查询列表")
    public Payload<List<SaleOrderPayNotifyResponseDTO>> listSaleOrderPayNotifys(SaleOrderPayNotifyRequestQuery query) {
        List<SaleOrderPayNotifyResponseDTO> list= ObjectCloneUtils
                .convertList(saleOrderPayNotifyService
                    .listSaleOrderPayNotifys(query.clone(SaleOrderPayNotifyQuery.class, CloneDirection.OPPOSITE)),
                                    SaleOrderPayNotifyResponseDTO.class, CloneDirection.OPPOSITE);
        return new Payload<>(list);
    }

    @Override
    @ApiOperation("分页查询列表")
    public Payload< PageBean<SaleOrderPayNotifyResponseDTO>>listSaleOrderPayNotifysPage(SaleOrderPayNotifyRequestQuery query) {
        PageBean<SaleOrderPayNotifyResponseDTO> page= ObjectCloneUtils
                .convertPageBean(saleOrderPayNotifyService
                    .listSaleOrderPayNotifysPage(query.clone(SaleOrderPayNotifyQuery.class, CloneDirection.OPPOSITE)),
                        SaleOrderPayNotifyResponseDTO.class, CloneDirection.OPPOSITE);
        return new Payload(page);
    }


    @Override
    @ApiOperation("批量删除")
    public Payload<Boolean> deleteByIdIn(@RequestBody List<Long> id) {
        return new Payload<>(saleOrderPayNotifyService.deleteByIdIn(id));
    }

    @Override
    @ApiOperation("新增")
    public Payload<SaleOrderPayNotifyResponseDTO> insert(@RequestBody SaleOrderPayNotifyRequestDTO record) {
        return new Payload<>(saleOrderPayNotifyService.insert(record.clone(SaleOrderPayNotifyDTO.class, CloneDirection.OPPOSITE))
                .clone(SaleOrderPayNotifyResponseDTO.class, CloneDirection.OPPOSITE));
    }

    @Override
    @ApiOperation("根据ID查询")
    public Payload<SaleOrderPayNotifyResponseDTO> selectById(@PathVariable Long id) {
        SaleOrderPayNotifyDTO result = saleOrderPayNotifyService.selectById(id);
        return result != null ? new Payload<>(result.clone(SaleOrderPayNotifyResponseDTO.class, CloneDirection.OPPOSITE)) : null;
    }

    @Override
    @ApiOperation("根据ID更新")
    public Payload<Boolean> updateById(@PathVariable Long id,@RequestBody SaleOrderPayNotifyRequestDTO record) {
        return new Payload<>(saleOrderPayNotifyService.updateById(id, record.clone(SaleOrderPayNotifyDTO.class, CloneDirection.OPPOSITE)));
    }


    @Override
    @ApiOperation(("根据支付单号和订单ID查询通知"))
    public Payload<List<SaleOrderPayNotifyResponseDTO>> querySaleOrderNotify(SaleOrderPayNotifySearchQuery query) {
        com.deepexi.dd.middle.order.domain.SaleOrderPayNotifyQuery saleOrderPayNotifyQuery=new com.deepexi.dd.middle.order.domain.SaleOrderPayNotifyQuery();
        saleOrderPayNotifyQuery.setPayNo(query.getPayNo());
        saleOrderPayNotifyQuery.setOrderId(query.getOrderId());
        List<SaleOrderPayNotifyResponseDTO> list= saleOrderPayNotifyService.querySaleOrderNotify(saleOrderPayNotifyQuery);
        return new Payload<>(list);
    }

}
