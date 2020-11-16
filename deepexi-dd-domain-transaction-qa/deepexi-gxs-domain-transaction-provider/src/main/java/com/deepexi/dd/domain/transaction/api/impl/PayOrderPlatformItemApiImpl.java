package com.deepexi.dd.domain.transaction.api.impl;

import com.deepexi.dd.domain.transaction.api.PayOrderPlatformItemApi;
import com.deepexi.dd.domain.transaction.service.PayOrderItemService;
import com.deepexi.dd.domain.transaction.service.PayOrderPlatformItemService;
import com.deepexi.dd.domain.transaction.service.PayOrderPlatformService;
import com.deepexi.dd.middle.order.domain.dto.PayOrderPlatformItemRequestDTO;
import com.deepexi.dd.middle.order.domain.dto.PayOrderPlatformItemResponseDTO;
import com.deepexi.dd.middle.order.domain.query.PayOrderPlatformItemRequestPageQuery;
import com.deepexi.util.pageHelper.PageBean;
import com.deepexi.util.pojo.CloneDirection;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;


/**
 */
@RestController
public class PayOrderPlatformItemApiImpl implements PayOrderPlatformItemApi {


    @Autowired
    private PayOrderPlatformItemService payOrderPlatformItemService;


    @Override
    @ApiOperation("查询付款凭证表列表")
    public List<PayOrderPlatformItemResponseDTO> list(PayOrderPlatformItemRequestDTO query) {
        return payOrderPlatformItemService.list(query);
    }

    @Override
    @ApiOperation("分页查询付款凭证表列表")
    public PageBean<PayOrderPlatformItemResponseDTO> listPage(PayOrderPlatformItemRequestPageQuery query) {
        return payOrderPlatformItemService.listPage(query);
    }

    @Override
    @ApiOperation("批量删除付款凭证表")
    public boolean deleteByIds(@RequestBody List<Long> id) {
        return payOrderPlatformItemService.deleteByIds(id);
    }

    @Override
    @ApiOperation("新增付款凭证表")
    public PayOrderPlatformItemResponseDTO insert(@RequestBody @Valid PayOrderPlatformItemRequestDTO record) {
        return payOrderPlatformItemService.insert(record);
    }

    @Override
    @ApiOperation("根据ID查询付款凭证表")
    public PayOrderPlatformItemResponseDTO selectById(@PathVariable Long id) {
        PayOrderPlatformItemResponseDTO result = payOrderPlatformItemService.selectById(id);
        return result != null ? result.clone(PayOrderPlatformItemResponseDTO.class, CloneDirection.OPPOSITE) : null;
    }


    @Override
    @ApiOperation("根据ID更新付款凭证表")
    public boolean updateById(@RequestBody PayOrderPlatformItemRequestDTO record) {
        return payOrderPlatformItemService.updateById(record);
    }
}
