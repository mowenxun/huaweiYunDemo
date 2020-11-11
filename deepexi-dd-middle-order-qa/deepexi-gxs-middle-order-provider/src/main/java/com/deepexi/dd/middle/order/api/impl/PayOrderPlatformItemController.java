package com.deepexi.dd.middle.order.api.impl;

import com.deepexi.dd.middle.order.api.PayOrderPlatformItemApi;
import com.deepexi.dd.middle.order.domain.dto.PayOrderPlatformItemDTO;
import com.deepexi.dd.middle.order.domain.dto.PayOrderPlatformItemRequestDTO;
import com.deepexi.dd.middle.order.domain.dto.PayOrderPlatformItemResponseDTO;
import com.deepexi.dd.middle.order.domain.query.PayOrderPlatformItemPageDTO;
import com.deepexi.dd.middle.order.domain.query.PayOrderPlatformItemRequestPageQuery;
import com.deepexi.dd.middle.order.service.PayOrderPlatformItemMiddleService;
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
public class PayOrderPlatformItemController implements PayOrderPlatformItemApi {

    @Autowired
    private PayOrderPlatformItemMiddleService PayOrderPlatformItemMiddleService;

    @Override
    @ApiOperation("查询付款凭证表列表")
    public List<PayOrderPlatformItemResponseDTO> list(PayOrderPlatformItemRequestDTO query) {
        return ObjectCloneUtils
                .convertList(PayOrderPlatformItemMiddleService
                    .list(query.clone(PayOrderPlatformItemDTO.class, CloneDirection.OPPOSITE)),
                                    PayOrderPlatformItemResponseDTO.class, CloneDirection.OPPOSITE);
    }

    @Override
    @ApiOperation("分页查询付款凭证表列表")
    public PageBean<PayOrderPlatformItemResponseDTO> listPage(PayOrderPlatformItemRequestPageQuery query) {
        List<PayOrderPlatformItemResponseDTO> rs = ObjectCloneUtils
                .convertList(PayOrderPlatformItemMiddleService
                                .listPage(query.clone(PayOrderPlatformItemPageDTO.class, CloneDirection.OPPOSITE)),
                        PayOrderPlatformItemResponseDTO.class, CloneDirection.OPPOSITE);
        return new PageBean<>(rs);
    }

    @Override
    @ApiOperation("批量删除付款凭证表")
    public boolean deleteByIds(@RequestBody List<Long> id) {
        return PayOrderPlatformItemMiddleService.deleteByIds(id);
    }

    @Override
    @ApiOperation("新增付款凭证表")
    public PayOrderPlatformItemResponseDTO insert(@RequestBody @Valid PayOrderPlatformItemRequestDTO record) {
        return PayOrderPlatformItemMiddleService.insert(record.clone(PayOrderPlatformItemDTO.class, CloneDirection.OPPOSITE))
                .clone(PayOrderPlatformItemResponseDTO.class, CloneDirection.OPPOSITE);
    }

    @Override
    @ApiOperation("根据ID查询付款凭证表")
    public PayOrderPlatformItemResponseDTO selectById(@PathVariable Long id) {
        PayOrderPlatformItemDTO result = PayOrderPlatformItemMiddleService.selectById(id);
        return result != null ? result.clone(PayOrderPlatformItemResponseDTO.class, CloneDirection.OPPOSITE) : null;
    }


    @Override
    @ApiOperation("根据ID更新付款凭证表")
    public boolean updateById(@RequestBody PayOrderPlatformItemRequestDTO record) {
        return PayOrderPlatformItemMiddleService.updateById(record.clone(PayOrderPlatformItemDTO.class, CloneDirection.OPPOSITE));
    }
}
