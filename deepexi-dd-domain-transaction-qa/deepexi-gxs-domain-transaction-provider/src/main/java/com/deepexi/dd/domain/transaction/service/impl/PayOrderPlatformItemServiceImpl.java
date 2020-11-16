package com.deepexi.dd.domain.transaction.service.impl;

import cn.hutool.core.date.DateUtil;
import com.deepexi.dd.domain.transaction.enums.gxs.GxsPayOrderCollectionStatusEnum;
import com.deepexi.dd.domain.transaction.remote.gxs.PayOrderPlatformItemRemoteService;
import com.deepexi.dd.domain.transaction.remote.gxs.PayOrderPlatformRemoteService;
import com.deepexi.dd.domain.transaction.service.PayOrderPlatformItemService;
import com.deepexi.dd.middle.order.domain.dto.*;
import com.deepexi.dd.middle.order.domain.query.PayOrderPlatformItemRequestPageQuery;
import com.deepexi.util.DateUtils;
import com.deepexi.util.pageHelper.PageBean;
import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.redisson.api.RAtomicLong;
import org.redisson.api.RLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author JeremyLian
 * @date 2020/10/30 21:08
 */
@Service
@Slf4j
public class PayOrderPlatformItemServiceImpl implements PayOrderPlatformItemService {

    @Autowired
    Redisson redisson;

    @Autowired
    PayOrderPlatformItemRemoteService payOrderPlatformItemRemoteService;

    @Autowired
    PayOrderPlatformRemoteService payOrderPlatformRemoteService;

    @Override
    public List<PayOrderPlatformItemResponseDTO> list(PayOrderPlatformItemRequestDTO query) {
        return payOrderPlatformItemRemoteService.list(query);
    }

    @Override
    public PageBean<PayOrderPlatformItemResponseDTO> listPage(PayOrderPlatformItemRequestPageQuery query) {
        return payOrderPlatformItemRemoteService.listPage(query);
    }

    @Override
    public boolean deleteByIds(List<Long> ids) {
        return payOrderPlatformItemRemoteService.deleteByIds(ids);
    }

    public String generateOrderCode() {
        String code = DateUtils.format(new Date(), "yyMMdd");
        long num = getDailyIncNum("GxsAfterYFDCode" + DateUtils.format(new Date(), "yyMMdd"),
                1, 9999);
        if(num<10){
            log.info("Generate OrderCode:{}", code+"00"+num);
            return  code+"00"+num;
        }else if(num<100){
            log.info("Generate OrderCode:{}", code+"0"+num);
            return code+"0"+num;
        }else{
            log.info("Generate OrderCode:{}", code+num);
            return code+num;
        }
    }
    /**
     * 获取每天自增数字     lowerLimit <= num <= upperLimit
     */
    public long getDailyIncNum(String namespace, long lowerLimit, long upperLimit) {
        RLock lock = redisson.getLock(namespace);
        try {
            lock.lock(10, TimeUnit.SECONDS);
            RAtomicLong atomicLong = redisson.getAtomicLong(namespace + "_");
            atomicLong.expireAt(DateUtil.endOfDay(new Date()));
            long num = atomicLong.incrementAndGet();
            return num;
        } catch (Exception e) {
            log.error("获取redis编号异常:" + e.getMessage(), e);
            throw new IllegalArgumentException("获取redis编号异常，namespace=" + namespace, e);
        } finally {
            lock.unlock();
        }
    }

    @Override
    public PayOrderPlatformItemResponseDTO insert(PayOrderPlatformItemRequestDTO record) {
        PayOrderPlatformResponseDTO payOrder = payOrderPlatformRemoteService.selectById(record.getOrderId());
        // 收款时间取财务手动收款的时间
        record.setPayTime(new Date());
        PayOrderPlatformItemResponseDTO item = payOrderPlatformItemRemoteService.insert(record);
        //System.out.println(DateUtils.format(new Date(), "yyyyMMdd"));
        System.out.println("----------------------------"+generateOrderCode());
        // 修改应收单
        PayOrderPlatformRequestDTO updatePayOrder = new PayOrderPlatformRequestDTO();
        updatePayOrder.setId(record.getOrderId());
        updatePayOrder.setPaidMoney(payOrder.getPaidMoney().add(record.getPayMoney()));
        updatePayOrder.setUnpayMoney(payOrder.getUnpayMoney().subtract(record.getPayMoney()));
        updatePayOrder.setCollectionStatus(Integer.parseInt(GxsPayOrderCollectionStatusEnum.PARTIAL_RECEIVED.getCode()));
        if (updatePayOrder.getUnpayMoney().doubleValue() == 0
                && updatePayOrder.getPaidMoney().doubleValue() == payOrder.getTotalAmount().doubleValue()) {
            updatePayOrder.setCollectionStatus(Integer.parseInt(GxsPayOrderCollectionStatusEnum.RECEIVED.getCode()));
        }
        payOrderPlatformRemoteService.updateById(updatePayOrder);
        return item;
        //return payOrderPlatformItemRemoteService.insert(record);
    }

    @Override
    public PayOrderPlatformItemResponseDTO selectById(Long id) {
        return payOrderPlatformItemRemoteService.selectById(id);
    }

    @Override
    public boolean updateById(PayOrderPlatformItemRequestDTO record) {
        return payOrderPlatformItemRemoteService.updateById(record);
    }
}
