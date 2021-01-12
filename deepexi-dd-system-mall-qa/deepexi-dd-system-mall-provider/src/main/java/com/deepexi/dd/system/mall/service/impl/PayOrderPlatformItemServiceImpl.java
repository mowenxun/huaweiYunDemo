package com.deepexi.dd.system.mall.service.impl;

import com.deepexi.dd.middle.order.domain.dto.PayOrderItemRequestDTO;
import com.deepexi.dd.middle.order.domain.dto.PayOrderItemResponseDTO;
import com.deepexi.dd.middle.order.domain.dto.PayOrderPlatformItemRequestDTO;
import com.deepexi.dd.middle.order.domain.dto.PayOrderPlatformItemResponseDTO;
import com.deepexi.dd.middle.order.domain.query.PayOrderItemRequestPageQuery;
import com.deepexi.dd.middle.order.domain.query.PayOrderPlatformItemRequestPageQuery;
import com.deepexi.dd.system.mall.remote.order.PayOrderPlatformItemRemote;
import com.deepexi.dd.system.mall.service.PayOrderItemService;
import com.deepexi.dd.system.mall.service.PayOrderPlatformItemService;
import com.deepexi.util.pageHelper.PageBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author JeremyLian
 * @date 2020/10/24 23:00
 */
@Service
@Slf4j
public class PayOrderPlatformItemServiceImpl implements PayOrderPlatformItemService {

    @Autowired
    PayOrderPlatformItemRemote payOrderPlatformItemRemote;

    @Override
    public List<PayOrderPlatformItemResponseDTO> list(PayOrderPlatformItemRequestDTO query) {
        return payOrderPlatformItemRemote.list(query);
    }

    @Override
    public PageBean<PayOrderPlatformItemResponseDTO> listPage(PayOrderPlatformItemRequestPageQuery query) {
        return payOrderPlatformItemRemote.listPage(query);
    }

    @Override
    public boolean deleteByIds(List<Long> ids) {
        return payOrderPlatformItemRemote.deleteByIds(ids);
    }

    @Override
    public PayOrderPlatformItemResponseDTO insert(PayOrderPlatformItemRequestDTO record) {
        return payOrderPlatformItemRemote.insert(record);
    }

    @Override
    public PayOrderPlatformItemResponseDTO selectById(Long id) {
        return payOrderPlatformItemRemote.selectById(id);
    }

    @Override
    public boolean updateById(PayOrderPlatformItemRequestDTO record) {
        return payOrderPlatformItemRemote.updateById(record);
    }
}
