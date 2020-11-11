package com.deepexi.dd.middle.order.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.deepexi.dd.middle.order.dao.PayOrderDao;
import com.deepexi.dd.middle.order.domain.PayOrderDO;
import com.deepexi.dd.middle.order.domain.dto.PayOrderDTO;
import com.deepexi.dd.middle.order.domain.dto.PayOrderRequestDTO;
import com.deepexi.dd.middle.order.domain.query.PayOrderPageDTO;
import com.deepexi.dd.middle.order.service.PayOrderMiddleService;
import com.deepexi.util.pojo.ObjectCloneUtils;
import com.github.pagehelper.page.PageMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.deepexi.util.pojo.CloneDirection.FORWARD;

/**
 * @author huanghuai
 * @since 2020-10-13
 */
@Service
public class PayOrderMiddleServiceImpl implements PayOrderMiddleService {

    @Autowired
    PayOrderDao payOrderDao;


    @Override
    public PayOrderDTO insert(PayOrderDTO dto) {
        PayOrderDO clone = dto.clone(PayOrderDO.class);
        payOrderDao.save(clone);
        dto.setId(clone.getId());
        return dto;
    }

    @Override
    public boolean createBatch(List<PayOrderRequestDTO> list) {
        return payOrderDao.saveBatch(ObjectCloneUtils.convertList(list, PayOrderDO.class));
    }

    @Override
    public boolean deleteByIds(List<Long> ids) {
        return payOrderDao.removeByIds(ids);
    }

    @Override
    public boolean deleteById(Long id) {
        return payOrderDao.removeById(id);
    }

    @Override
    public List<PayOrderRequestDTO> listPage(PayOrderPageDTO query) {
        PageMethod.startPage(query.getPage(), query.getSize());
        return ObjectCloneUtils.convertList(payOrderDao.findByQuery(query), PayOrderRequestDTO.class);
    }

    @Override
    public List<PayOrderRequestDTO> list(PayOrderRequestDTO query) {
        QueryWrapper<PayOrderDO> qw = new QueryWrapper<>(query.clone(PayOrderDO.class, 1));
        return ObjectCloneUtils.convertList(payOrderDao.list(qw), PayOrderRequestDTO.class);
    }

    @Override
    public PayOrderDTO selectById(Long id) {
        PayOrderDO obj = payOrderDao.getById(id);
        return obj == null ? null : obj.clone(PayOrderDTO.class, FORWARD);
    }

    @Override
    public boolean updateById(PayOrderDTO dto) {
        return payOrderDao.updateById(dto.clone(PayOrderDO.class, FORWARD));
    }

}
