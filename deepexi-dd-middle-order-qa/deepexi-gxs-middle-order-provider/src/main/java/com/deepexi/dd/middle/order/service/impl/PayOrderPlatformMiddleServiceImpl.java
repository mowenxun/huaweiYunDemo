package com.deepexi.dd.middle.order.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.deepexi.dd.middle.order.dao.PayOrderPlatformDao;
import com.deepexi.dd.middle.order.domain.PayOrderPlatformDO;
import com.deepexi.dd.middle.order.domain.dto.PayOrderPlatformDTO;
import com.deepexi.dd.middle.order.domain.dto.PayOrderPlatformRequestDTO;
import com.deepexi.dd.middle.order.domain.query.PayOrderPlatformPageDTO;
import com.deepexi.dd.middle.order.service.PayOrderPlatformMiddleService;
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
public class PayOrderPlatformMiddleServiceImpl implements PayOrderPlatformMiddleService {

    @Autowired
    PayOrderPlatformDao PayOrderPlatformDao;


    @Override
    public PayOrderPlatformDTO insert(PayOrderPlatformDTO dto) {
        PayOrderPlatformDO clone = dto.clone(PayOrderPlatformDO.class);
        PayOrderPlatformDao.save(clone);
        dto.setId(clone.getId());
        return dto;
    }

    @Override
    public boolean createBatch(List<PayOrderPlatformRequestDTO> list) {
        return PayOrderPlatformDao.saveBatch(ObjectCloneUtils.convertList(list, PayOrderPlatformDO.class));
    }

    @Override
    public boolean deleteByIds(List<Long> ids) {
        return PayOrderPlatformDao.removeByIds(ids);
    }

    @Override
    public boolean deleteById(Long id) {
        return PayOrderPlatformDao.removeById(id);
    }

    @Override
    public List<PayOrderPlatformRequestDTO> listPage(PayOrderPlatformPageDTO query) {
        PageMethod.startPage(query.getPage(), query.getSize());
        return ObjectCloneUtils.convertList(PayOrderPlatformDao.findByQuery(query), PayOrderPlatformRequestDTO.class);
    }

    @Override
    public List<PayOrderPlatformRequestDTO> list(PayOrderPlatformRequestDTO query) {
        QueryWrapper<PayOrderPlatformDO> qw = new QueryWrapper<>(query.clone(PayOrderPlatformDO.class, 1));
        return ObjectCloneUtils.convertList(PayOrderPlatformDao.list(qw), PayOrderPlatformRequestDTO.class);
    }

    @Override
    public PayOrderPlatformDTO selectById(Long id) {
        PayOrderPlatformDO obj = PayOrderPlatformDao.getById(id);
        return obj == null ? null : obj.clone(PayOrderPlatformDTO.class, FORWARD);
    }

    @Override
    public boolean updateById(PayOrderPlatformDTO dto) {
        return PayOrderPlatformDao.updateById(dto.clone(PayOrderPlatformDO.class, FORWARD));
    }

}
