package com.deepexi.dd.middle.order.api.impl;

import com.deepexi.dd.middle.order.api.PayOrderApi;
import com.deepexi.dd.middle.order.domain.dto.PayOrderDTO;
import com.deepexi.dd.middle.order.domain.dto.PayOrderRequestDTO;
import com.deepexi.dd.middle.order.domain.dto.PayOrderResponseDTO;
import com.deepexi.dd.middle.order.domain.query.PayOrderPageDTO;
import com.deepexi.dd.middle.order.domain.query.PayOrderRequestPageQuery;
import com.deepexi.dd.middle.order.service.PayOrderMiddleService;
import com.deepexi.util.pageHelper.PageBean;
import com.deepexi.util.pojo.CloneDirection;
import com.deepexi.util.pojo.ObjectCloneUtils;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 */
@RestController
public class PayOrderController implements PayOrderApi {

    @Autowired
    private PayOrderMiddleService payOrderMiddleService;

    @Override
    @ApiOperation("查询付款凭证表列表")
    public List<PayOrderResponseDTO> list(PayOrderRequestDTO query) {
        return ObjectCloneUtils
                .convertList(payOrderMiddleService
                    .list(query.clone(PayOrderRequestDTO.class, CloneDirection.OPPOSITE)),
                                    PayOrderResponseDTO.class, CloneDirection.OPPOSITE);
    }

    @Override
    @ApiOperation("分页查询付款凭证表列表")
    public PageBean<PayOrderResponseDTO> listPage(PayOrderRequestPageQuery query) {
        List<PayOrderResponseDTO> rs = ObjectCloneUtils
                .convertList(payOrderMiddleService
                                .listPage(query.clone(PayOrderPageDTO.class, CloneDirection.OPPOSITE)),
                        PayOrderResponseDTO.class, CloneDirection.OPPOSITE);
        return new PageBean<>(rs);
    }

    @Override
    @ApiOperation("批量删除付款凭证表")
    public boolean deleteByIds(@RequestBody List<Long> id) {
        return payOrderMiddleService.deleteByIds(id);
    }

    @Override
    @ApiOperation("新增付款凭证表")
    public PayOrderResponseDTO insert(@RequestBody PayOrderRequestDTO record) {
        return payOrderMiddleService.insert(record.clone(PayOrderDTO.class, CloneDirection.OPPOSITE))
                .clone(PayOrderResponseDTO.class, CloneDirection.OPPOSITE);
    }

    @Override
    @ApiOperation("根据ID查询付款凭证表")
    public PayOrderResponseDTO selectById(@PathVariable Long id) {
        PayOrderDTO result = payOrderMiddleService.selectById(id);
        return result != null ? result.clone(PayOrderResponseDTO.class, CloneDirection.OPPOSITE) : null;
    }


    @Override
    @ApiOperation("根据ID更新付款凭证表")
    public boolean updateById(@RequestBody PayOrderRequestDTO record) {
        return payOrderMiddleService.updateById(record.clone(PayOrderDTO.class, CloneDirection.OPPOSITE));
    }
}
