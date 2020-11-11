package com.deepexi.dd.middle.order.service.impl;

import com.deepexi.dd.middle.order.dao.OrderOperationRecordDAO;
import com.deepexi.dd.middle.order.domain.OrderOperationRecordDO;
import com.deepexi.dd.middle.order.domain.dto.OrderOperationRecordDTO;
import com.deepexi.dd.middle.order.domain.query.OrderOperationRecordQuery;
import com.deepexi.dd.middle.order.service.OrderOperationRecordService;
import com.deepexi.dd.middle.order.util.DomainUtils;
import com.deepexi.util.CollectionUtil;
import com.deepexi.util.pageHelper.PageBean;
import com.deepexi.util.pojo.ObjectCloneUtils;
import com.github.pagehelper.page.PageMethod;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * OrderOperationRecordServiceImpl
 *
 * @author admin
 * @date Wed Jul 29 15:12:50 CST 2020
 * @version 1.0
 */
@Service
@Slf4j
public class OrderOperationRecordServiceImpl implements OrderOperationRecordService {

    @Autowired
    private OrderOperationRecordDAO orderOperationRecordDao;

    @Override
    public List<OrderOperationRecordDTO> listOrderOperationRecords(OrderOperationRecordQuery query) {
        PageMethod.orderBy("created_time desc");
        return ObjectCloneUtils.convertList(orderOperationRecordDao.listOrderOperationRecords(query), OrderOperationRecordDTO.class);
    }


    @Override
    public PageBean<OrderOperationRecordDTO> listOrderOperationRecordsPage(OrderOperationRecordQuery query) {
        PageMethod.startPage(query.getPage(), query.getSize());
        return ObjectCloneUtils.convertPageBean(new PageBean<>(orderOperationRecordDao.listOrderOperationRecords(query)), OrderOperationRecordDTO.class);
    }

    @Override
    public Boolean deleteByIdIn(List<Long> id) {
        if (CollectionUtil.isEmpty(id)) {
            log.error("删除操作记录表,传入ID为空");
            return false;
        }
        return orderOperationRecordDao.deleteByIdIn(id) == id.size();
    }

    @Override
    public OrderOperationRecordDTO insert(OrderOperationRecordDTO record) {
        if (record == null) {
            log.error("新增操作记录表,传入参数为空");
            return null;
        }
        OrderOperationRecordDO orderOperationRecordDo = record.clone(OrderOperationRecordDO.class);
        DomainUtils.initDomainCreatedInfo(orderOperationRecordDo);
        if (orderOperationRecordDao.insert(orderOperationRecordDo) > 0) {
            record.setId(orderOperationRecordDo.getId());
            return record;
        }
        log.error("新增操作记录表失败");
        return null;
    }

    @Override
    public OrderOperationRecordDTO selectById(Long id) {
        if (id == null) {
            log.error("查询操作记录表,传入ID为空");
            return null;
        }
        OrderOperationRecordDO orderOperationRecordDo = orderOperationRecordDao.selectById(id);
        if (orderOperationRecordDo == null) {
            return null;
        }
        return orderOperationRecordDo.clone(OrderOperationRecordDTO.class);
    }

    @Override
    public Boolean updateById(Long id, OrderOperationRecordDTO record) {
        if (id == null) {
            log.error("修改操作记录表,传入ID为空");
            return false;
        }
        record.setId(id);
        OrderOperationRecordDO orderOperationRecordDo = record.clone(OrderOperationRecordDO.class);
        DomainUtils.initUpdateTimeInfo(orderOperationRecordDo);
        return orderOperationRecordDao.updateById(orderOperationRecordDo);
    }

    @Override
    public Boolean batchInsert(List<OrderOperationRecordDTO> records) {
        List<OrderOperationRecordDO> orderOperationRecords =ObjectCloneUtils.convertList(records,OrderOperationRecordDO.class);
        orderOperationRecordDao.saveBatch(orderOperationRecords);
        return true;
    }
}