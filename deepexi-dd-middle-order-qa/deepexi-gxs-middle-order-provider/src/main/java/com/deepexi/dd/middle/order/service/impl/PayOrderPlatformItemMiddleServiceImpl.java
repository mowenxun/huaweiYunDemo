package com.deepexi.dd.middle.order.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.deepexi.dd.middle.order.dao.PayOrderPlatformItemDao;
import com.deepexi.dd.middle.order.domain.PayOrderPlatformItemDO;
import com.deepexi.dd.middle.order.domain.dto.PayOrderPlatformItemDTO;
import com.deepexi.dd.middle.order.domain.query.PayOrderPlatformItemPageDTO;
import com.deepexi.dd.middle.order.service.PayOrderPlatformItemMiddleService;
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
public class PayOrderPlatformItemMiddleServiceImpl implements PayOrderPlatformItemMiddleService {

    @Autowired
    PayOrderPlatformItemDao PayOrderPlatformItemDao;


    @Override
    public PayOrderPlatformItemDTO insert(PayOrderPlatformItemDTO dto) {
        PayOrderPlatformItemDO clone = dto.clone(PayOrderPlatformItemDO.class);
        PayOrderPlatformItemDao.save(clone);
        dto.setId(clone.getId());
        return dto;
    }

    @Override
    public boolean createBatch(List<PayOrderPlatformItemDTO> list) {
        return PayOrderPlatformItemDao.saveBatch(ObjectCloneUtils.convertList(list, PayOrderPlatformItemDO.class));
    }

    @Override
    public boolean deleteByIds(List<Long> ids) {
        return PayOrderPlatformItemDao.removeByIds(ids);
    }

    @Override
    public boolean deleteById(Long id) {
        return PayOrderPlatformItemDao.removeById(id);
    }

    @Override
    public List<PayOrderPlatformItemDTO> listPage(PayOrderPlatformItemPageDTO query) {
        PageMethod.startPage(query.getPage(), query.getSize());
        return ObjectCloneUtils.convertList(PayOrderPlatformItemDao.findByQuery(query), PayOrderPlatformItemDTO.class);
    }

    @Override
    public List<PayOrderPlatformItemDTO> list(PayOrderPlatformItemDTO query) {
        QueryWrapper<PayOrderPlatformItemDO> qw = new QueryWrapper<>(query.clone(PayOrderPlatformItemDO.class, 1));
        return ObjectCloneUtils.convertList(PayOrderPlatformItemDao.list(qw), PayOrderPlatformItemDTO.class);
    }

    @Override
    public PayOrderPlatformItemDTO selectById(Long id) {
        PayOrderPlatformItemDO obj = PayOrderPlatformItemDao.getById(id);
        return obj == null ? null : obj.clone(PayOrderPlatformItemDTO.class, FORWARD);
    }

    @Override
    public boolean updateById(PayOrderPlatformItemDTO dto) {
        return PayOrderPlatformItemDao.updateById(dto.clone(PayOrderPlatformItemDO.class, FORWARD));
    }

}
