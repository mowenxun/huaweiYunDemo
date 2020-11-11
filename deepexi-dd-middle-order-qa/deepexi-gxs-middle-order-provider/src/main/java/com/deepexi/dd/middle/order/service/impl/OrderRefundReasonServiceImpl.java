package com.deepexi.dd.middle.order.service.impl;

import com.deepexi.dd.middle.order.dao.OrderOperationRecordDAO;
import com.deepexi.dd.middle.order.dao.OrderRefundReasonDAO;
import com.deepexi.dd.middle.order.domain.OrderOperationRecordDO;
import com.deepexi.dd.middle.order.domain.OrderRefundReasonDO;
import com.deepexi.dd.middle.order.domain.dto.*;
import com.deepexi.dd.middle.order.domain.query.OrderRefundReasonQuery;
import com.deepexi.dd.middle.order.service.OrderRefundReasonService;
import com.deepexi.dd.middle.order.util.DomainUtils;
import com.deepexi.util.CollectionUtil;
import com.deepexi.util.pageHelper.PageBean;
import com.deepexi.util.pojo.ObjectCloneUtils;
import com.github.pagehelper.page.PageMethod;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * OrderRefundReasonServiceImpl
 *
 * @author admin
 * @version 1.0
 * @date Wed Aug 19 16:00:27 CST 2020
 */
@Service
@Slf4j
public class OrderRefundReasonServiceImpl implements OrderRefundReasonService {

    @Autowired
    private OrderRefundReasonDAO orderRefundReasonDao;

    @Autowired
    private OrderOperationRecordDAO orderOperationRecordDAO;

    @Override
    public List<OrderRefundReasonDTO> CheckCode(OrderRefundReasonQuery query) {
        return ObjectCloneUtils.convertList(orderRefundReasonDao.CheckCode(query), OrderRefundReasonDTO.class);
    }

    @Override
    public List<OrderRefundReasonDTO> listOrderRefundReasons(OrderRefundReasonQuery query) {
        return ObjectCloneUtils.convertList(orderRefundReasonDao.listOrderRefundReasons(query), OrderRefundReasonDTO.class);
    }


    @Override
    public PageBean<OrderRefundReasonDTO> listOrderRefundReasonsPage(OrderRefundReasonQuery query) {
        PageMethod.startPage(query.getPage(), query.getSize());
        return ObjectCloneUtils.convertPageBean(new PageBean<>(orderRefundReasonDao.listOrderRefundReasons(query)), OrderRefundReasonDTO.class);
    }

    @Override
    public Boolean deleteByIdIn(List<Long> id) {
        if (CollectionUtil.isEmpty(id)) {
            log.error("删除,传入ID为空");
            return false;
        }
        return orderRefundReasonDao.deleteByIdIn(id) == id.size();
    }

    @Override
    public OrderRefundReasonDTO insert(OrderRefundReasonDTO record) {
        if (record == null) {
            log.error("新增,传入参数为空");
            return null;
        }
        OrderRefundReasonDO orderRefundReasonDo = record.clone(OrderRefundReasonDO.class);
        DomainUtils.initDomainCreatedInfo(orderRefundReasonDo);
        if (orderRefundReasonDao.insert(orderRefundReasonDo) > 0) {
            //保存创建记录
//            OrderOperationRecordDO recordDO = getOrderOperationRecordDO(record);
//            recordDO.setTenantId(record.getTenantId());
//            recordDO.setAppId(record.getAppId());
//            orderOperationRecordDAO.insert(recordDO);
            record.setId(orderRefundReasonDo.getId());
            return record;
        }
        log.error("新增失败");
        return null;
    }


    private OrderOperationRecordDO getOrderOperationRecordDO(OrderRefundReasonDTO record) {
        OrderOperationRecordDO recordDO = new OrderOperationRecordDO();
        DomainUtils.initDomainCreatedInfo(recordDO);
        recordDO.setCreatedBy(record.getCreatedBy());
        recordDO.setOperationType(3);
        recordDO.setOrderId(record.getOrderId());
        recordDO.setOrderCode(record.getOrderCode());
        recordDO.setOperation("创建");
        recordDO.setRemark(record.getRemark());
        return recordDO;
    }

    @Override
    public OrderRefundReasonDTO selectById(Long id) {
        if (id == null) {
            log.error("查询,传入ID为空");
            return null;
        }
        OrderRefundReasonDO orderRefundReasonDo = orderRefundReasonDao.selectById(id);
        if (orderRefundReasonDo == null) {
            return null;
        }
        return orderRefundReasonDo.clone(OrderRefundReasonDTO.class);
    }

    @Override
    public Boolean updateById(Long id, OrderRefundReasonDTO record) {
        if (id == null) {
            log.error("修改,传入ID为空");
            return false;
        }
        record.setId(id);
        OrderRefundReasonDO orderRefundReasonDo = record.clone(OrderRefundReasonDO.class);
        DomainUtils.initUpdateTimeInfo(orderRefundReasonDo);
        return orderRefundReasonDao.updateById(orderRefundReasonDo) > 0;
    }

    private OrderOperationRecordDO getOrderOperationRecordDO(OrderRefundReasonRequestDTO record) {
        OrderOperationRecordDO recordDO = new OrderOperationRecordDO();
        DomainUtils.initDomainCreatedInfo(recordDO);
        recordDO.setCreatedBy(record.getOperator());
        recordDO.setOperationType(3);
        recordDO.setOrderId(record.getOrderId());
        recordDO.setOperation(record.getType() ? "退款成功" : "已驳回");
        recordDO.setRemark(record.getAuditReason());
        recordDO.setTenantId(record.getTenantId());
        recordDO.setAppId(record.getAppId());
        return recordDO;
    }

    @Override
    public boolean updateStatus(OrderRefundReasonRequestDTO record) {
        if (Objects.isNull(record)) {
            log.error("批量修改状态,传参为空");
            return false;
        }
        if (record.getType()) {
            record.setRefundStatus(30);
        } else {
            record.setRefundStatus(5);
        }

        record.setRefundDate(new Date());

        int num = orderRefundReasonDao.updateStatus(record);
        //保存创建记录
//        OrderOperationRecordDO recordDO = getOrderOperationRecordDO(record);
//        orderOperationRecordDAO.insert(recordDO);

        insertOperationRecord(record,record.getType() ? "退款成功" : "已驳回");

        return true;
    }

    private void insertOperationRecord(OrderRefundReasonRequestDTO record, String operation){

        OrderOperationRecordDO recordDO = new OrderOperationRecordDO();
        recordDO.setAppId(record.getAppId());
        recordDO.setTenantId(record.getTenantId());
        recordDO.setVersion(1);
        recordDO.setDeleted(false);
        recordDO.setCreatedTime(new Date());
        recordDO.setUpdatedTime(new Date());
        recordDO.setOrderId(record.getOrderId());
        //recordDO.setOrderCode(record.getOrderCode());
        recordDO.setOrderCode(record.getRefundCode());
        recordDO.setOperation(operation);
        recordDO.setOperationType(3);
        recordDO.setCreatedBy(record.getOperator());
        recordDO.setRemark(record.getAuditReason());

        orderOperationRecordDAO.insert(recordDO);
    }

    @Override
    public Boolean cancel(Long id) {
        OrderRefundReasonDO orderRefundReasonDO=new OrderRefundReasonDO();
        orderRefundReasonDO.setId(id);
        orderRefundReasonDO.setRefundStatus(33);

        int result = orderRefundReasonDao.updateById(orderRefundReasonDO);

        if(result<=0){
            return false;
        }

        return true;
    }

    @Override
    public List<OrderRefundReasonFindDTO> findOrderRefundReason(List<String> orderCodeList) {
        return orderRefundReasonDao.findOrderRefundReason(orderCodeList);
    }
}