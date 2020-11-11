package com.deepexi.dd.middle.order.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.deepexi.dd.middle.order.dao.PayVoucherDao;
import com.deepexi.dd.middle.order.domain.PayVoucherDO;
import com.deepexi.dd.middle.order.domain.dto.PayVoucherDTO;
import com.deepexi.dd.middle.order.domain.query.PayVoucherPageDTO;
import com.deepexi.dd.middle.order.service.PayVoucherMiddleService;
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
public class PayVoucherMiddleServiceImpl implements PayVoucherMiddleService {

    @Autowired
    PayVoucherDao payVoucherDao;


    @Override
    public PayVoucherDTO insert(PayVoucherDTO dto) {
        PayVoucherDO clone = dto.clone(PayVoucherDO.class);
        payVoucherDao.save(clone);
        dto.setId(clone.getId());
        return dto;
    }

    @Override
    public boolean createBatch(List<PayVoucherDTO> list) {
        return payVoucherDao.saveBatch(ObjectCloneUtils.convertList(list, PayVoucherDO.class));
    }

    @Override
    public boolean deleteByIds(List<Long> ids) {
        return payVoucherDao.removeByIds(ids);
    }

    @Override
    public boolean deleteById(Long id) {
        return payVoucherDao.removeById(id);
    }

    @Override
    public List<PayVoucherDTO> listPage(PayVoucherPageDTO query) {
        PageMethod.startPage(query.getPage(), query.getSize());
        return ObjectCloneUtils.convertList(payVoucherDao.findByQuery(query), PayVoucherDTO.class);
    }

    @Override
    public List<PayVoucherDTO> list(PayVoucherDTO query) {
        QueryWrapper<PayVoucherDO> qw = new QueryWrapper<>(query.clone(PayVoucherDO.class, 1));
        return ObjectCloneUtils.convertList(payVoucherDao.list(qw), PayVoucherDTO.class);
    }

    @Override
    public PayVoucherDTO selectById(Long id) {
        PayVoucherDO obj = payVoucherDao.getById(id);
        return obj == null ? null : obj.clone(PayVoucherDTO.class, FORWARD);
    }

    @Override
    public boolean updateById(PayVoucherDTO dto) {
        return payVoucherDao.updateById(dto.clone(PayVoucherDO.class, FORWARD));
    }

}
