package com.deepexi.dd.domain.transaction.service.impl;

import com.deepexi.dd.domain.transaction.remote.gxs.PayOrderRemoteService;
import com.deepexi.dd.domain.transaction.service.PayOrderDomainService;
import com.deepexi.dd.middle.order.domain.dto.PayOrderRequestDTO;
import com.deepexi.dd.middle.order.domain.dto.PayOrderResponseDTO;
import com.deepexi.dd.middle.order.domain.query.PayOrderRequestPageQuery;
import com.deepexi.util.pageHelper.PageBean;
import com.deepexi.util.pojo.CloneDirection;
import com.deepexi.util.pojo.ObjectCloneUtils;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * 供应商应收单service
 * @author huanghuai
 * @date 2020/10/13
 */
@Service
@Slf4j
public class PayOrderDomainServiceImpl implements PayOrderDomainService {

    @Autowired
    PayOrderRemoteService payOrderRemoteService;

    @Override
    @ApiOperation("查询应收单")
    public List<PayOrderResponseDTO> list(PayOrderRequestDTO query) {
        return ObjectCloneUtils
                .convertList(payOrderRemoteService.list(query),
                        PayOrderResponseDTO.class, CloneDirection.OPPOSITE);
    }

    @Override
    @ApiOperation("分页查询应收单")
    public PageBean<PayOrderResponseDTO> listPage(PayOrderRequestPageQuery query) {
        return ObjectCloneUtils
                .convertPageBean(payOrderRemoteService.listPage(query),
                        PayOrderResponseDTO.class, CloneDirection.OPPOSITE);
    }

    @Override
    @ApiOperation("批量删除应收单")
    public boolean deleteByIds(@RequestBody List<Long> id) {
        return payOrderRemoteService.deleteByIds(id);
    }

    @Override
    @ApiOperation("新增应收单")
    public PayOrderResponseDTO insert(@RequestBody PayOrderRequestDTO record) {
        return payOrderRemoteService.insert(record)
                .clone(PayOrderResponseDTO.class, CloneDirection.OPPOSITE);
    }

    @Override
    @ApiOperation("根据ID查询应收单")
    public PayOrderResponseDTO selectById(@PathVariable Long id) {
        PayOrderResponseDTO result = payOrderRemoteService.selectById(id);
        return result != null ? result.clone(PayOrderResponseDTO.class, CloneDirection.OPPOSITE) : null;
    }

    @Override
    @ApiOperation("根据ID更新应收单")
    public boolean updateById(@RequestBody PayOrderRequestDTO record) {
        return payOrderRemoteService.updateById(record);
    }

   
}
