package com.deepexi.dd.middle.order.api.impl;

import com.deepexi.dd.middle.order.api.PayVoucherApi;
import com.deepexi.dd.middle.order.domain.dto.PayVoucherDTO;
import com.deepexi.dd.middle.order.domain.dto.PayVoucherRequestDTO;
import com.deepexi.dd.middle.order.domain.dto.PayVoucherResponseDTO;
import com.deepexi.dd.middle.order.domain.query.PayVoucherPageDTO;
import com.deepexi.dd.middle.order.domain.query.PayVoucherRequestPageQuery;
import com.deepexi.dd.middle.order.service.PayVoucherMiddleService;
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
public class PayVoucherController implements PayVoucherApi {

    @Autowired
    private PayVoucherMiddleService payVoucherService;

    @Override
    @ApiOperation("查询付款凭证表列表")
    public List<PayVoucherResponseDTO> list(PayVoucherRequestDTO query) {
        return ObjectCloneUtils
                .convertList(payVoucherService
                    .list(query.clone(PayVoucherDTO.class, CloneDirection.OPPOSITE)),
                                    PayVoucherResponseDTO.class, CloneDirection.OPPOSITE);
    }

    @Override
    @ApiOperation("分页查询付款凭证表列表")
    public PageBean<PayVoucherResponseDTO> listPage(PayVoucherRequestPageQuery query) {
        List<PayVoucherResponseDTO> rs = ObjectCloneUtils
                .convertList(payVoucherService
                                .listPage(query.clone(PayVoucherPageDTO.class, CloneDirection.OPPOSITE)),
                        PayVoucherResponseDTO.class, CloneDirection.OPPOSITE);
        return new PageBean<>(rs);
    }

    @Override
    @ApiOperation("批量删除付款凭证表")
    public boolean deleteByIds(@RequestBody List<Long> id) {
        return payVoucherService.deleteByIds(id);
    }

    @Override
    @ApiOperation("新增付款凭证表")
    public PayVoucherResponseDTO insert(@RequestBody PayVoucherRequestDTO record) {
        return payVoucherService.insert(record.clone(PayVoucherDTO.class, CloneDirection.OPPOSITE))
                .clone(PayVoucherResponseDTO.class, CloneDirection.OPPOSITE);
    }

    @Override
    @ApiOperation("根据ID查询付款凭证表")
    public PayVoucherResponseDTO selectById(@PathVariable Long id) {
        PayVoucherDTO result = payVoucherService.selectById(id);
        return result != null ? result.clone(PayVoucherResponseDTO.class, CloneDirection.OPPOSITE) : null;
    }


    @Override
    @ApiOperation("根据ID更新付款凭证表")
    public boolean updateById(@RequestBody PayVoucherRequestDTO record) {
        return payVoucherService.updateById(record.clone(PayVoucherDTO.class, CloneDirection.OPPOSITE));
    }
}
