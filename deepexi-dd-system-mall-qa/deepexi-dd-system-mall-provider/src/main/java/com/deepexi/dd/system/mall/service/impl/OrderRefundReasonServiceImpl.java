package com.deepexi.dd.system.mall.service.impl;

import com.deepexi.dd.domain.transaction.domain.dto.*;
import com.deepexi.dd.domain.transaction.domain.dto.operation.center.SalePickGoodsInfoDetailResponseDTO;
import com.deepexi.dd.domain.transaction.domain.dto.saleOrderItem.SaleOrderItemResponseDTO;
import com.deepexi.dd.domain.transaction.domain.query.RefundReasonRequestQuery;
import com.deepexi.dd.middle.finance.domain.dto.FinancePaymentRecordsResponseDTO;
import com.deepexi.dd.middle.finance.domain.query.FinancePaymentRecordsRequestQuery;
import com.deepexi.dd.middle.order.domain.dto.OrderOperationRecordRequestDTO;
import com.deepexi.dd.middle.order.domain.dto.OrderOperationRecordResponseDTO;
import com.deepexi.dd.system.mall.domain.vo.saleorder.RefundDetailsInfoVO;
import com.deepexi.dd.system.mall.remote.finance.FinancePaymentRecordsClient;
import com.deepexi.dd.system.mall.remote.order.OrderRefundReasonRemote;
import com.deepexi.dd.system.mall.remote.order.SaleOrderInfoRemote;
import com.deepexi.dd.system.mall.remote.order.SalePickGoodsInfoRemote;
import com.deepexi.dd.system.mall.service.OrderRefundReasonService;
import com.deepexi.dd.system.mall.service.SaleOrderInfoService;
import com.deepexi.dd.system.mall.util.ConverterUtil;
import com.deepexi.dd.system.mall.util.GeneralConvertUtils;
import com.deepexi.util.CollectionUtil;
import com.deepexi.util.config.Payload;
import com.deepexi.util.pageHelper.PageBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Slf4j
public class OrderRefundReasonServiceImpl implements OrderRefundReasonService {

    @Autowired
    private OrderRefundReasonRemote orderRefundReasonRemote;

    @Autowired
    private SaleOrderInfoService saleOrderInfoService;

    @Autowired
    private SalePickGoodsInfoRemote salePickGoodsInfoRemote;

    @Autowired
    private FinancePaymentRecordsClient financePaymentRecordsClient;

    @Autowired
    private SaleOrderInfoRemote saleOrderInfoRemote;

    @Override
    public RefundReasonResponseDTO listOrderRefundMoney(RefundReasonRequestQuery query) throws Exception {
        SaleOrderInfoResponseDTO sale = saleOrderInfoService.selectById(query.getOrderId(), query.getAppId());
        if (Objects.isNull(sale)) {
            log.error("根据订单编号查询为空 :" + query.getOrderId());
            return null;
        }
        RefundReasonResponseDTO result = new RefundReasonResponseDTO();
        BigDecimal outMoney = new BigDecimal(0);
        for (SaleOrderItemResponseDTO s : sale.getItems()) {
            // 出库总数为空，就是还没有出库
            if (Objects.nonNull(s.getSkuTotalQuantity())) {
                // 出库总数 * 单价 = 已出库金额
                outMoney = outMoney.add(new BigDecimal(s.getSkuTotalQuantity()).multiply(s.getPrice()));
            }
        }
        // 已付金额 - 已出库金额 = 可退金额
        result.setApplyAmount(sale.getPayAmount().subtract(outMoney));
        result.setOrderCode(sale.getCode());
        return result;
    }

    @Override
    public RefundReasonResponseDTO listSkuByInfoId(RefundReasonRequestQuery query) throws Exception {
        SalePickGoodsInfoSkuDTO pickGoods = GeneralConvertUtils.conv(
                salePickGoodsInfoRemote.getSkuByInfoId(query.getOrderId()).getPayload(), SalePickGoodsInfoSkuDTO.class);
        if (Objects.isNull(pickGoods)) {
            log.error("根据提货单号查询退款金额为空 :" + query.getOrderId());
            return null;
        }
        RefundReasonResponseDTO result = new RefundReasonResponseDTO();
        BigDecimal outMoney = new BigDecimal(0);
        for (SalePickGoodsOrderSkuRequestDTO s : pickGoods.getSkus()) {
            // 出库总数为空，就是还没有出库
            if (Objects.nonNull(s.getWaitSendNum())) {
                // 出库总数 * 单价 = 已出库金额
                outMoney = outMoney.add(new BigDecimal(s.getPickNum()).multiply(s.getPrice()));
            }
        }
        SalePickGoodsInfoDetailResponseDTO goodsDetail = GeneralConvertUtils.conv(
                salePickGoodsInfoRemote.getDetailById(query.getOrderId()).getPayload(), SalePickGoodsInfoDetailResponseDTO.class);
        // 已付金额 - 已出库金额 = 可退金额
        result.setApplyAmount(goodsDetail.getPayAmount().subtract(outMoney));
        return result;
    }

    @Override
    public Boolean cancel(Long id) throws Exception {
        return orderRefundReasonRemote.cancel(id).getPayload();
    }

    @Override
    public FinancePaymentRecordsResponseDTO findOriginOrderRefundPaymentRecord(FinancePaymentRecordsRequestQuery query) throws Exception {
        return GeneralConvertUtils.conv(orderRefundReasonRemote.findOriginOrderRefundPaymentRecord(query).getPayload(), FinancePaymentRecordsResponseDTO.class);
    }

    @Override
    public RefundDetailsInfoVO findRefundInfoById(Long id) throws Exception {
        RefundDetailsInfoVO refundDetailsInfoVO = new RefundDetailsInfoVO();
        //退款单基本信息
        refundDetailsInfoVO.setRefundReasonInfo(GeneralConvertUtils.conv(orderRefundReasonRemote.selectById(id).getPayload(), RefundReasonResponseDTO.class));
        //退款单动态

        return null;
    }

    @Override
    public List<RefundReasonResponseDTO> listOrderRefundReasons(RefundReasonRequestQuery query) throws Exception {
        List<RefundReasonResponseDTO> result = GeneralConvertUtils.convert2List(
                orderRefundReasonRemote.listOrderRefundReasons(query).getPayload(), RefundReasonResponseDTO.class);
        if (CollectionUtil.isEmpty(result)) {
            log.error("查询退款原因为空");
            return null;
        }
        return result;
    }

    @Override
    public PageBean<RefundReasonResponseDTO> listOrderRefundReasonsPage(RefundReasonRequestQuery query) throws Exception {
        PageBean<RefundReasonResponseDTO> result = GeneralConvertUtils.convert2PageBean(
                orderRefundReasonRemote.listOrderRefundReasonsPage(query).getPayload(), RefundReasonResponseDTO.class);
//        if (CollectionUtil.isEmpty(result.getContent())) {
//            log.error("查询退款原因为空");
//            return null;
//        }
//        //设置退款商品总数量
        result.getContent().forEach(responseDTO -> responseDTO.getRefundSku().forEach(skuInfo -> {
            responseDTO.setRefundSkuSum((skuInfo.getSkuQuantity() - skuInfo.getSkuTotalQuantity()) + ConverterUtil.toInt(responseDTO.getRefundSkuSum()));
        }));
        return result;
    }

    @Override
    public Boolean deleteByIdIn(@RequestBody List<Long> id) throws Exception {
        return orderRefundReasonRemote.deleteByIdIn(id).getPayload();
    }

    @Override
    public RefundReasonResponseDTO insert(@RequestBody RefundReasonRequestDTO record) throws Exception {
        return GeneralConvertUtils.conv(orderRefundReasonRemote.insert(record).getPayload(), RefundReasonResponseDTO.class);
    }

    @Override
    public RefundReasonResponseDTO selectById(@PathVariable Long id) throws Exception {
        return GeneralConvertUtils.conv(orderRefundReasonRemote.selectById(id).getPayload(), RefundReasonResponseDTO.class);
    }

    @Override
    public Boolean updateById(@PathVariable Long id, @RequestBody RefundReasonRequestDTO record) throws Exception {
        return orderRefundReasonRemote.updateById(id, record).getPayload();
    }

    @Override
    public boolean updateStatus(@RequestBody RefundReasonRequestDTO record) throws Exception {
        return orderRefundReasonRemote.updateStatus(record).getPayload();
    }

    @Override
    public String getRefundPayCode(String refundCode) throws Exception {

        com.deepexi.dd.domain.finance.domain.query.FinancePaymentRecordsRequestQuery query =new com.deepexi.dd.domain.finance.domain.query.FinancePaymentRecordsRequestQuery();
        query.setOrderCode(refundCode);

        Payload<PageBean<com.deepexi.dd.domain.finance.domain.dto.FinancePaymentRecordsResponseDTO>> financePaymentRecordsPagePayload =financePaymentRecordsClient.listFinancePaymentRecordsPage(query);

        PageBean<com.deepexi.dd.domain.finance.domain.dto.FinancePaymentRecordsResponseDTO> financePaymentRecords = GeneralConvertUtils.convert2PageBean(
                financePaymentRecordsPagePayload.getPayload(), com.deepexi.dd.domain.finance.domain.dto.FinancePaymentRecordsResponseDTO.class);

        if(CollectionUtil.isEmpty(financePaymentRecords.getContent())){
            return "";
        }

        com.deepexi.dd.domain.finance.domain.dto.FinancePaymentRecordsResponseDTO single =financePaymentRecords.getContent().get(0);

        return single.getCode();

    }

    @Override
    public List<OrderOperationRecordResponseDTO> getRefundRecord(OrderOperationRecordRequestDTO query) throws Exception {
        query.setPage(1);
        query.setSize(100);
        query.setOperationType(3);//退款

        PageBean<OrderOperationRecordResponseDTO> result = GeneralConvertUtils.convert2PageBean(saleOrderInfoRemote.getOrderOperationRecord(query).getPayload(),OrderOperationRecordResponseDTO.class);

        if(CollectionUtil.isNotEmpty(result.getContent())){
            List<OrderOperationRecordResponseDTO> operationRecordResponseDTOS=result.getContent().stream().sorted(Comparator.comparing(OrderOperationRecordResponseDTO::getCreatedTime).reversed()).collect(Collectors.toList());

            return operationRecordResponseDTOS;
        }

        return null;
    }
}
