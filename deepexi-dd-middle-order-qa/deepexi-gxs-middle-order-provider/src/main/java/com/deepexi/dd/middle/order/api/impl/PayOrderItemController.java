package com.deepexi.dd.middle.order.api.impl;

import com.deepexi.dd.middle.order.api.PayOrderItemApi;
import com.deepexi.dd.middle.order.domain.dto.PayOrderItemDTO;
import com.deepexi.dd.middle.order.domain.dto.PayOrderItemRequestDTO;
import com.deepexi.dd.middle.order.domain.dto.PayOrderItemResponseDTO;
import com.deepexi.dd.middle.order.domain.query.PayOrderItemPageDTO;
import com.deepexi.dd.middle.order.domain.query.PayOrderItemRequestPageQuery;
import com.deepexi.dd.middle.order.service.PayOrderItemMiddleService;
import com.deepexi.util.pageHelper.PageBean;
import com.deepexi.util.pojo.CloneDirection;
import com.deepexi.util.pojo.ObjectCloneUtils;
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
public class PayOrderItemController implements PayOrderItemApi {

    @Autowired
    private PayOrderItemMiddleService payOrderItemMiddleService;

    @Override
    @ApiOperation("查询付款凭证表列表")
    public List<PayOrderItemResponseDTO> list(PayOrderItemRequestDTO query) {
        return ObjectCloneUtils
                .convertList(payOrderItemMiddleService
                    .list(query.clone(PayOrderItemDTO.class, CloneDirection.OPPOSITE)),
                                    PayOrderItemResponseDTO.class, CloneDirection.OPPOSITE);
    }

    @Override
    @ApiOperation("分页查询付款凭证表列表")
    public PageBean<PayOrderItemResponseDTO> listPage(PayOrderItemRequestPageQuery query) {
        List<PayOrderItemResponseDTO> rs = ObjectCloneUtils
                .convertList(payOrderItemMiddleService
                                .listPage(query.clone(PayOrderItemPageDTO.class, CloneDirection.OPPOSITE)),
                        PayOrderItemResponseDTO.class, CloneDirection.OPPOSITE);
        return new PageBean<>(rs);
    }

    @Override
    @ApiOperation("批量删除付款凭证表")
    public boolean deleteByIds(@RequestBody List<Long> id) {
        return payOrderItemMiddleService.deleteByIds(id);
    }

    @Override
    @ApiOperation("新增付款凭证表")
    public PayOrderItemResponseDTO insert(@RequestBody @Valid PayOrderItemRequestDTO record) {
        return payOrderItemMiddleService.insert(record.clone(PayOrderItemDTO.class, CloneDirection.OPPOSITE))
                .clone(PayOrderItemResponseDTO.class, CloneDirection.OPPOSITE);
    }

    @Override
    @ApiOperation("根据ID查询付款凭证表")
    public PayOrderItemResponseDTO selectById(@PathVariable Long id) {
        PayOrderItemDTO result = payOrderItemMiddleService.selectById(id);
        return result != null ? result.clone(PayOrderItemResponseDTO.class, CloneDirection.OPPOSITE) : null;
    }


    @Override
    @ApiOperation("根据ID更新付款凭证表")
    public boolean updateById(@RequestBody PayOrderItemRequestDTO record) {
        return payOrderItemMiddleService.updateById(record.clone(PayOrderItemDTO.class, CloneDirection.OPPOSITE));
    }
}
