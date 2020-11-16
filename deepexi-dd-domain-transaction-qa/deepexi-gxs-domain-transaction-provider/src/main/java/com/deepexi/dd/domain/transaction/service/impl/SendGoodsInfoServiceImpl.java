package com.deepexi.dd.domain.transaction.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.deepexi.dd.domain.common.extension.AppRuntimeEnv;
import com.deepexi.dd.domain.transaction.api.gxs.domain.SupplierDeliverGoodsRequestDTO;
import com.deepexi.dd.domain.transaction.enums.gxs.GxsPayOrderCollectionStatusEnum;
import com.deepexi.dd.domain.transaction.remote.gxs.SendGoodsInfoRemoteService;
import com.deepexi.dd.domain.transaction.remote.gxs.ShopOrderDomainRemote;
import com.deepexi.dd.domain.transaction.remote.gxs.ShopSupplerRelationRemote;
import com.deepexi.dd.domain.transaction.service.*;
import com.deepexi.dd.domain.transaction.util.ServiceUtil;
import com.deepexi.dd.middle.order.domain.dto.PayOrderPlatformRequestDTO;
import com.deepexi.dd.middle.order.domain.dto.PayOrderRequestDTO;
import com.deepexi.dd.middle.order.domain.dto.SendGoodsInfoRequestDTO;
import com.deepexi.dd.middle.order.domain.dto.SendGoodsInfoResponseDTO;
import com.deepexi.dd.middle.order.domain.query.SendGoodsInfoRequestQuery;
import com.deepexi.util.DateUtils;
import com.deepexi.util.pageHelper.PageBean;
import com.deepexi.util.pojo.CloneDirection;
import com.deepexi.util.pojo.ObjectCloneUtils;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.redisson.api.RAtomicLong;
import org.redisson.api.RLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author huanghuai
 * @date 2020/10/13
 */
@Service
@Slf4j
public class SendGoodsInfoServiceImpl implements SendGoodsInfoService {

    @Autowired
    Redisson redisson;
    private static final String YYYYMMDD = "yyyyMMdd";
    @Autowired
    AppRuntimeEnv appRuntimeEnv;
    @Autowired
    SendGoodsInfoRemoteService sendGoodsInfoRemoteService;
    @Autowired
    ShopOrderDomainRemote shopOrderDomainRemote;
    @Autowired
    SupplierOrderService supplierOrderService;
    @Autowired
    ShopSupplerRelationRemote shopSupplerRelationRemote;
    @Autowired
    ShopSupplerRelationDomainService shopSupplerRelationDomainService;
    @Autowired
    ShopOrderDomainService shopOrderDomainService;
    @Autowired
    PayOrderDomainService payOrderDomainService;
    @Autowired
    PayOrderPlatformService payOrderPlatformService;

    @Override
    @ApiOperation("查询发货表列表")
    public List<SendGoodsInfoResponseDTO> listSendGoodsInfos(SendGoodsInfoRequestQuery query) {
        return ObjectCloneUtils
                .convertList(sendGoodsInfoRemoteService
                                .listSendGoodsInfos(query),
                        SendGoodsInfoResponseDTO.class, CloneDirection.OPPOSITE);
    }

    @Override
    @ApiOperation("分页查询发货表列表")
    public PageBean<SendGoodsInfoResponseDTO> listSendGoodsInfosPage(SendGoodsInfoRequestQuery query) {
        return ObjectCloneUtils
                .convertPageBean(sendGoodsInfoRemoteService
                                .listSendGoodsInfosPage(query),
                        SendGoodsInfoResponseDTO.class, CloneDirection.OPPOSITE);
    }

    @Override
    @ApiOperation("批量删除发货表")
    public Boolean deleteByIdIn(@RequestBody List<Long> id) {
        return sendGoodsInfoRemoteService.deleteByIdIn(id);
    }

    @Override
    @ApiOperation("新增发货表")
    public SendGoodsInfoResponseDTO insert(@RequestBody SendGoodsInfoRequestDTO record) {
        return sendGoodsInfoRemoteService.insert(record)
                .clone(SendGoodsInfoResponseDTO.class, CloneDirection.OPPOSITE);
    }

    @Override
    @ApiOperation("根据ID查询发货表")
    public SendGoodsInfoResponseDTO selectById(@PathVariable Long id) {
        SendGoodsInfoResponseDTO result = sendGoodsInfoRemoteService.selectById(id);
        return result != null ? result.clone(SendGoodsInfoResponseDTO.class, CloneDirection.OPPOSITE) : null;
    }

    @Override
    @ApiOperation("根据ID更新发货表")
    public Boolean updateById(@PathVariable Long id, @RequestBody SendGoodsInfoRequestDTO record) {
        return sendGoodsInfoRemoteService.updateById(id, record);
    }

    public String generateOrderCode() {
        String code = DateUtils.format(new Date(), "yyMMdd");
        long num = getDailyIncNum("GxsAfterYFDCode" + DateUtils.format(new Date(), "yyMMdd"),
                1, 9999);
        if (num < 10) {
            log.info("Generate OrderCode:{}", code + "00" + num);
            return code + "00" + num;
        } else if (num < 100) {
            log.info("Generate OrderCode:{}", code + "0" + num);
            return code + "0" + num;
        } else {
            log.info("Generate OrderCode:{}", code + num);
            return code + num;
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

    /**
     * 发货，1.在发货表插入一条数据，2.更新供应商订单状态为已发货3.创建一个应收单 4.更新门店订单状态 已发货
     *
     * @param vo
     * @return
     */
    @Override
    public boolean deliverGoods(SupplierDeliverGoodsRequestDTO vo) {
        String requestId = appRuntimeEnv.getRequestId();
        log.info("[{}][Deliver Goods]Input params:{}", requestId, vo);
        // 查询订单是否已发货
        SendGoodsInfoRequestQuery query = new SendGoodsInfoRequestQuery();
        query.setOrderId(vo.getOrderId());
        query.setOrderCode(vo.getOrderCode());
        List<SendGoodsInfoResponseDTO> goodsInfo = sendGoodsInfoRemoteService.listSendGoodsInfos(query);
        ServiceUtil.assertTrue(!goodsInfo.isEmpty(), "订单：{} 已经发货，不能重复发货", vo.getOrderId());
        //generateOrderCode()编码后三位
        String generateOrderCode = generateOrderCode();
        String sourOrderCode = vo.getOrderCode();//销售单CODE
        // 创建发货信息
        sendGoodsInfoRemoteService.insert(vo.clone(SendGoodsInfoRequestDTO.class, 1));
        // 创建应收单
        PayOrderRequestDTO payOrder = vo.clone(PayOrderRequestDTO.class, 1);
        payOrder.setOrderCode("YSD" + generateOrderCode);
        payOrder.setCollectionStatus(Integer.parseInt(GxsPayOrderCollectionStatusEnum.TO_BE_RECEIVED.getCode()));
        payOrder.setSourceOrderCode(sourOrderCode);
        payOrder.setOrderTime(new Date());
        payOrder.setPaidMoney(BigDecimal.ZERO);
        payOrder.setUnpayMoney(payOrder.getTotalAmount());
        log.info("[{}][Deliver Goods]Ready to Create Pay Order:{}", requestId, payOrder);
        payOrderDomainService.insert(payOrder);
        log.info("[{}][Deliver Goods]Create Pay Order success", requestId);
        // 创建付款单
        PayOrderPlatformRequestDTO payOrderPlatformRequestDTO = vo.clone(PayOrderPlatformRequestDTO.class, 1);
        payOrderPlatformRequestDTO.setOrderCode("YFD" + generateOrderCode);
        payOrderPlatformRequestDTO.setCollectionStatus(Integer.parseInt(GxsPayOrderCollectionStatusEnum.TO_BE_RECEIVED.getCode()));
        payOrderPlatformRequestDTO.setSourceOrderCode(sourOrderCode);
        payOrderPlatformRequestDTO.setOrderTime(new Date());
        payOrderPlatformRequestDTO.setPaidMoney(BigDecimal.ZERO);
        payOrderPlatformRequestDTO.setUnpayMoney(payOrderPlatformRequestDTO.getTotalAmount());
        log.info("[{}][Deliver Goods]Ready to Create Pay Order Platform:{}", requestId, payOrderPlatformRequestDTO);
        payOrderPlatformService.insert(payOrderPlatformRequestDTO);
        log.info("[{}][Deliver Goods]Create Pay Order Platform success", requestId);

        // 待发货才能变为已发货
        boolean success = supplierOrderService.sendGoods(vo.getOrderId());
        // 改门店订单状态 已发货
        shopSupplerRelationDomainService.listBySupplierOrderId(vo.getOrderId()).forEach(relation -> {
            try {
                shopOrderDomainService.sendGoods(relation.getShopOrderId());
            } catch (Exception e) {
                log.error(StrUtil.format("门店{}发货失败:{}", relation.getShopOrderId(), e.getMessage()), e);
            }
        });
        return success;
    }
}
