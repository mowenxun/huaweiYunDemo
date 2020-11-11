package com.deepexi.dd.middle.order.service.impl;

import com.deepexi.dd.middle.order.dao.OrderRefundSkuDAO;
import com.deepexi.dd.middle.order.domain.OrderRefundSkuDO;
import com.deepexi.dd.middle.order.domain.dto.OrderRefundSkuDTO;
import com.deepexi.dd.middle.order.domain.dto.OrderRefundSkuRequestDTO;
import com.deepexi.dd.middle.order.domain.dto.OrderRefundSkuResponseDTO;
import com.deepexi.dd.middle.order.domain.query.OrderRefundSkuQuery;
import com.deepexi.dd.middle.order.service.OrderRefundSkuService;
import com.deepexi.dd.middle.order.util.DomainUtils;
import com.deepexi.util.CollectionUtil;
import com.deepexi.util.pageHelper.PageBean;
import com.deepexi.util.pojo.ObjectCloneUtils;
import com.github.pagehelper.page.PageMethod;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * OrderRefundSkuServiceImpl
 *
 * @author admin
 * @date Wed Aug 19 16:31:13 CST 2020
 * @version 1.0
 */
@Service
@Slf4j
public class OrderRefundSkuServiceImpl implements OrderRefundSkuService {

    @Autowired
    private OrderRefundSkuDAO orderRefundSkuDao;

    @Override
    public List<OrderRefundSkuDTO> listOrderRefundSkus(OrderRefundSkuQuery query) {
        return ObjectCloneUtils.convertList(orderRefundSkuDao.listOrderRefundSkus(query), OrderRefundSkuDTO.class);
    }


    @Override
    public PageBean<OrderRefundSkuDTO> listOrderRefundSkusPage(OrderRefundSkuQuery query) {
        PageMethod.startPage(query.getPage(), query.getSize());
        return ObjectCloneUtils.convertPageBean(new PageBean<>(orderRefundSkuDao.listOrderRefundSkus(query)), OrderRefundSkuDTO.class);
    }

    @Override
    public Boolean deleteByIdIn(List<Long> id) {
        if (CollectionUtil.isEmpty(id)) {
            log.error("删除,传入ID为空");
            return false;
        }
        return orderRefundSkuDao.deleteByIdIn(id) == id.size();
    }

    @Override
    public OrderRefundSkuDTO insert(OrderRefundSkuDTO record) {
        if (record == null) {
            log.error("新增,传入参数为空");
            return null;
        }
        OrderRefundSkuDO orderRefundSkuDo = record.clone(OrderRefundSkuDO.class);
        DomainUtils.initDomainCreatedInfo(orderRefundSkuDo);
        if (orderRefundSkuDao.insert(orderRefundSkuDo) > 0) {
            record.setId(orderRefundSkuDo.getId());
            return record;
        }
        log.error("新增失败");
        return null;
    }

    @Override
    public OrderRefundSkuDTO selectById(Long id) {
        if (id == null) {
            log.error("查询,传入ID为空");
            return null;
        }
        OrderRefundSkuDO orderRefundSkuDo = orderRefundSkuDao.selectById(id);
        if (orderRefundSkuDo == null) {
            return null;
        }
        return orderRefundSkuDo.clone(OrderRefundSkuDTO.class);
    }

    @Override
    public Boolean updateById(Long id, OrderRefundSkuDTO record) {
        if (id == null) {
            log.error("修改,传入ID为空");
            return false;
        }
        record.setId(id);
        OrderRefundSkuDO orderRefundSkuDo = record.clone(OrderRefundSkuDO.class);
        DomainUtils.initUpdateTimeInfo(orderRefundSkuDo);
        return orderRefundSkuDao.updateById(orderRefundSkuDo);
    }

    @Override
    public List<OrderRefundSkuResponseDTO> batchInsert(List<OrderRefundSkuRequestDTO> list) {
        if (CollectionUtil.isEmpty(list)) {
            log.error("批量新增，传入参数为空");
            return Lists.newArrayList();
        }
        List<OrderRefundSkuDO> result = ObjectCloneUtils.convertList(list, OrderRefundSkuDO.class);
        result.forEach(DomainUtils::initDomainCreatedInfo);
        if (orderRefundSkuDao.saveBatch(result)) {
            return ObjectCloneUtils.convertList(list, OrderRefundSkuResponseDTO.class);
        }
        log.error("批量新增失败");
        return Lists.newArrayList();
    }

}