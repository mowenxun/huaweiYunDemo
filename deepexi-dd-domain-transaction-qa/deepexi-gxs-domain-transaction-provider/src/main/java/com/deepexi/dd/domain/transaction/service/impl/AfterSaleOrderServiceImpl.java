package com.deepexi.dd.domain.transaction.service.impl;

import cn.hutool.core.date.DateUtil;
import com.deepexi.dd.domain.common.service.IdentifierGenerator;
import com.deepexi.dd.domain.transaction.enums.gxs.GxsAfterSaleOrderStatusEnum;
import com.deepexi.dd.domain.transaction.remote.gxs.AfterSaleOrderRemoteService;
import com.deepexi.dd.domain.transaction.service.AfterSaleOrderService;
import com.deepexi.dd.domain.transaction.util.ServiceUtil;
import com.deepexi.dd.middle.order.domain.dto.AfterSaleOrdeRequestDTO;
import com.deepexi.dd.middle.order.domain.dto.AfterSaleOrdeResponseDTO;
import com.deepexi.dd.middle.order.domain.dto.PayOrderItemResponseDTO;
import com.deepexi.dd.middle.order.domain.query.AfterSaleOrdeRequestQuery;
import com.deepexi.util.DateUtils;
import com.deepexi.util.extension.ApplicationException;
import com.deepexi.util.pageHelper.PageBean;
import com.deepexi.util.pojo.CloneDirection;
import com.deepexi.util.pojo.ObjectCloneUtils;
import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.redisson.api.RAtomicLong;
import org.redisson.api.RLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author huanghuai
 * @date 2020/10/13
 */
@Service
@Slf4j
public class AfterSaleOrderServiceImpl implements AfterSaleOrderService {

    @Autowired
    Redisson redisson;
    @Autowired
    AfterSaleOrderRemoteService afterSaleOrderRemoteService;
    @Autowired
    IdentifierGenerator identifierGenerator;

    @Override
    public List<PayOrderItemResponseDTO> listAfterSaleOrdes(AfterSaleOrdeRequestQuery query) {
        return ObjectCloneUtils
                .convertList(afterSaleOrderRemoteService.listAfterSaleOrdes(query),
                        PayOrderItemResponseDTO.class, CloneDirection.OPPOSITE);
    }

    @Override
    public PageBean<AfterSaleOrdeResponseDTO> listAfterSaleOrdesPage(AfterSaleOrdeRequestQuery query) {
        return ObjectCloneUtils
                .convertPageBean(afterSaleOrderRemoteService.listAfterSaleOrdesPage(query),
                        AfterSaleOrdeResponseDTO.class, CloneDirection.OPPOSITE);
    }

    @Override
    public Boolean deleteByIdIn(List<Long> id) {
        return afterSaleOrderRemoteService.deleteByIdIn(id);
    }

    @Override
    public AfterSaleOrdeResponseDTO insert(@RequestBody AfterSaleOrdeRequestDTO record) {
        record.setOrderCode(generateOrderCode());
        return afterSaleOrderRemoteService.insert(record)
                .clone(AfterSaleOrdeResponseDTO.class, CloneDirection.OPPOSITE);
    }

    @Override
    public AfterSaleOrdeResponseDTO selectById(@PathVariable Long id) {
        AfterSaleOrdeResponseDTO result = afterSaleOrderRemoteService.selectById(id);
        return result != null ? result.clone(AfterSaleOrdeResponseDTO.class, CloneDirection.OPPOSITE) : null;
    }

    @Override
    public Boolean updateById(Long id, AfterSaleOrdeRequestDTO record) {
        return afterSaleOrderRemoteService.updateById(id, record);
    }

    @Override
    public String generateOrderCode() {
        String incr = getDailyIncNum(ORDER_CODE_GENERATE_TYPE
                        + DateUtils.format(new Date(), "yyMMdd"), 1, 9999)+"";
        incr = ServiceUtil.preFill(incr, 3, "0");
        String code = "S" + DateUtils.format(new Date(), "yyMMdd")
                + incr;
        log.info("Generate AfterSaleOrder Code:{}", code);
        return code;
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
    public Boolean examine(Long id, AfterSaleOrdeRequestDTO dto) {
        if (dto.getStatus() == null) {
            throw new ApplicationException("请传入审核状态");
        }
        AfterSaleOrdeRequestDTO afterSaleOrdeRequestDTO = new AfterSaleOrdeRequestDTO();
        if (dto.getStatus().equals(GxsAfterSaleOrderStatusEnum.APPROVED.getCode())) {
            afterSaleOrdeRequestDTO.setStatus(dto.getStatus());
            return afterSaleOrderRemoteService.updateById(id, dto);
        }
        if (dto.getStatus().equals(GxsAfterSaleOrderStatusEnum.REJECTED.getCode())) {
            afterSaleOrdeRequestDTO.setStatus(dto.getStatus());
            afterSaleOrdeRequestDTO.setRemark(dto.getRemark());//驳回原因  存remake
            return afterSaleOrderRemoteService.updateById(id, dto);
        }
        return Boolean.FALSE;
    }
}
