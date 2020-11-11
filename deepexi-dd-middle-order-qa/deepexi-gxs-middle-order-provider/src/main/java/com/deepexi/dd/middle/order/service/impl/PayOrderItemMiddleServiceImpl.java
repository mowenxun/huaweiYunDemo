package com.deepexi.dd.middle.order.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.deepexi.dd.middle.order.dao.PayOrderItemDao;
import com.deepexi.dd.middle.order.domain.PayOrderItemDO;
import com.deepexi.dd.middle.order.domain.dto.PayOrderItemDTO;
import com.deepexi.dd.middle.order.domain.query.PayOrderItemPageDTO;
import com.deepexi.dd.middle.order.service.PayOrderItemMiddleService;
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
public class PayOrderItemMiddleServiceImpl implements PayOrderItemMiddleService {

    @Autowired
    PayOrderItemDao payOrderItemDao;


    @Override
    public PayOrderItemDTO insert(PayOrderItemDTO dto) {
        PayOrderItemDO clone = dto.clone(PayOrderItemDO.class);
        payOrderItemDao.save(clone);
        dto.setId(clone.getId());
        return dto;
    }

    @Override
    public boolean createBatch(List<PayOrderItemDTO> list) {
        return payOrderItemDao.saveBatch(ObjectCloneUtils.convertList(list, PayOrderItemDO.class));
    }

    @Override
    public boolean deleteByIds(List<Long> ids) {
        return payOrderItemDao.removeByIds(ids);
    }

    @Override
    public boolean deleteById(Long id) {
        return payOrderItemDao.removeById(id);
    }

    @Override
    public List<PayOrderItemDTO> listPage(PayOrderItemPageDTO query) {
        PageMethod.startPage(query.getPage(), query.getSize());
        return ObjectCloneUtils.convertList(payOrderItemDao.findByQuery(query), PayOrderItemDTO.class);
    }

    @Override
    public List<PayOrderItemDTO> list(PayOrderItemDTO query) {
        QueryWrapper<PayOrderItemDO> qw = new QueryWrapper<>(query.clone(PayOrderItemDO.class, 1));
        return ObjectCloneUtils.convertList(payOrderItemDao.list(qw), PayOrderItemDTO.class);
    }

    @Override
    public PayOrderItemDTO selectById(Long id) {
        PayOrderItemDO obj = payOrderItemDao.getById(id);
        return obj == null ? null : obj.clone(PayOrderItemDTO.class, FORWARD);
    }

    @Override
    public boolean updateById(PayOrderItemDTO dto) {
        return payOrderItemDao.updateById(dto.clone(PayOrderItemDO.class, FORWARD));
    }

}
