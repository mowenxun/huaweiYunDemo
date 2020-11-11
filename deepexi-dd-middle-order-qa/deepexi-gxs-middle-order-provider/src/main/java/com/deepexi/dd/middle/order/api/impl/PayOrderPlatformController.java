package com.deepexi.dd.middle.order.api.impl;

import com.deepexi.dd.middle.order.api.PayOrderPlatformApi;
import com.deepexi.dd.middle.order.domain.dto.PayOrderPlatformDTO;
import com.deepexi.dd.middle.order.domain.dto.PayOrderPlatformRequestDTO;
import com.deepexi.dd.middle.order.domain.dto.PayOrderPlatformResponseDTO;
import com.deepexi.dd.middle.order.domain.query.PayOrderPlatformPageDTO;
import com.deepexi.dd.middle.order.domain.query.PayOrderPlatformRequestPageQuery;
import com.deepexi.dd.middle.order.service.PayOrderPlatformMiddleService;
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
public class PayOrderPlatformController implements PayOrderPlatformApi {

    @Autowired
    private PayOrderPlatformMiddleService PayOrderPlatformMiddleService;

    @Override
    @ApiOperation("查询付款凭证表列表")
    public List<PayOrderPlatformResponseDTO> list(PayOrderPlatformRequestDTO query) {
        return ObjectCloneUtils
                .convertList(PayOrderPlatformMiddleService
                    .list(query.clone(PayOrderPlatformRequestDTO.class, CloneDirection.OPPOSITE)),
                                    PayOrderPlatformResponseDTO.class, CloneDirection.OPPOSITE);
    }

    @Override
    @ApiOperation("分页查询付款凭证表列表")
    public PageBean<PayOrderPlatformResponseDTO> listPage(PayOrderPlatformRequestPageQuery query) {
        List<PayOrderPlatformResponseDTO> rs = ObjectCloneUtils
                .convertList(PayOrderPlatformMiddleService
                                .listPage(query.clone(PayOrderPlatformPageDTO.class, CloneDirection.OPPOSITE)),
                        PayOrderPlatformResponseDTO.class, CloneDirection.OPPOSITE);
        return new PageBean<>(rs);
    }

    @Override
    @ApiOperation("批量删除付款凭证表")
    public boolean deleteByIds(@RequestBody List<Long> id) {
        return PayOrderPlatformMiddleService.deleteByIds(id);
    }

    @Override
    @ApiOperation("新增付款凭证表")
    public PayOrderPlatformResponseDTO insert(@RequestBody PayOrderPlatformRequestDTO record) {
        return PayOrderPlatformMiddleService.insert(record.clone(PayOrderPlatformDTO.class, CloneDirection.OPPOSITE))
                .clone(PayOrderPlatformResponseDTO.class, CloneDirection.OPPOSITE);
    }

    @Override
    @ApiOperation("根据ID查询付款凭证表")
    public PayOrderPlatformResponseDTO selectById(@PathVariable Long id) {
        PayOrderPlatformDTO result = PayOrderPlatformMiddleService.selectById(id);
        return result != null ? result.clone(PayOrderPlatformResponseDTO.class, CloneDirection.OPPOSITE) : null;
    }


    @Override
    @ApiOperation("根据ID更新付款凭证表")
    public boolean updateById(@RequestBody PayOrderPlatformRequestDTO record) {
        return PayOrderPlatformMiddleService.updateById(record.clone(PayOrderPlatformDTO.class, CloneDirection.OPPOSITE));
    }
}
