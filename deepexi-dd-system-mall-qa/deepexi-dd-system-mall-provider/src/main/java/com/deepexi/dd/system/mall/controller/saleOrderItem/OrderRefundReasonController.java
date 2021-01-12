package com.deepexi.dd.system.mall.controller.saleOrderItem;

import com.deepexi.dd.domain.transaction.domain.dto.RefundReasonRequestDTO;
import com.deepexi.dd.domain.transaction.domain.dto.RefundReasonResponseDTO;
import com.deepexi.dd.domain.transaction.domain.query.RefundReasonRequestQuery;
import com.deepexi.dd.middle.finance.domain.dto.FinancePaymentRecordsResponseDTO;
import com.deepexi.dd.middle.finance.domain.query.FinancePaymentRecordsRequestQuery;
import com.deepexi.dd.middle.order.domain.dto.OrderOperationRecordRequestDTO;
import com.deepexi.dd.middle.order.domain.dto.OrderOperationRecordResponseDTO;
import com.deepexi.dd.system.mall.domain.vo.finance.RefundReasonVO;
import com.deepexi.dd.system.mall.domain.vo.saleorder.RefundDetailsInfoVO;
import com.deepexi.dd.system.mall.domain.vo.saleorder.RefundReasonResponseVO;
import com.deepexi.dd.system.mall.service.OrderRefundReasonService;
import com.deepexi.dd.system.mall.util.GeneralConvertUtils;
import com.deepexi.util.config.Payload;
import com.deepexi.util.pageHelper.PageBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(tags = "退款原因管理")
@RequestMapping("/admin-api/v1/domain/sale/orderRefundReason")
public class OrderRefundReasonController {

    @Autowired
    private OrderRefundReasonService orderRefundReasonService;

    @GetMapping("/listOrderRefundMoney")
    @ApiOperation("根据订单id查询退款金额")
    public Payload<RefundReasonResponseVO> listOrderRefundMoney(RefundReasonRequestQuery query) throws Exception {
        return new Payload<>(GeneralConvertUtils.conv(orderRefundReasonService.listOrderRefundMoney(query), RefundReasonResponseVO.class));
    }

    @GetMapping("/listSkuByInfoId")
    @ApiOperation("根据提货单号查询退款金额")
    public Payload<RefundReasonResponseVO> listSkuByInfoId(RefundReasonRequestQuery query) throws Exception {
        return new Payload<>(GeneralConvertUtils.conv(orderRefundReasonService.listSkuByInfoId(query), RefundReasonResponseVO.class));
    }

    @GetMapping("/list")
    @ApiOperation("查询列表")
    public Payload<List<RefundReasonResponseDTO>> listOrderRefundReasons(RefundReasonRequestQuery query) throws Exception {
        return new Payload<>(orderRefundReasonService.listOrderRefundReasons(query));
    }

    @GetMapping("/page")
    @ApiOperation("分页查询列表")
    public Payload<PageBean<RefundReasonResponseDTO>> listOrderRefundReasonsPage(RefundReasonRequestQuery query) throws Exception {
        return new Payload<>(orderRefundReasonService.listOrderRefundReasonsPage(query));
    }

    @DeleteMapping
    @ApiOperation("根据ID删除")
    public Payload<Boolean> deleteByIdIn(@RequestBody List<Long> id) throws Exception {
        return new Payload<>(orderRefundReasonService.deleteByIdIn(id));
    }

    @PostMapping
    @ApiOperation("新增")
    public Payload<RefundReasonResponseDTO> insert(@RequestBody RefundReasonRequestDTO record) throws Exception {
        return new Payload<>(orderRefundReasonService.insert(record));
    }

    @GetMapping("/{id}")
    @ApiOperation("查询详情")
    public Payload<RefundReasonResponseDTO> selectById(@PathVariable Long id) throws Exception {
        RefundReasonResponseDTO refundReasonResponseDTO = orderRefundReasonService.selectById(id);

        RefundReasonVO refundReasonVO=refundReasonResponseDTO.clone(RefundReasonVO.class);
        refundReasonVO.setRefundPayCode(orderRefundReasonService.getRefundPayCode(refundReasonVO.getRefundCode()));

        return new Payload<>(refundReasonVO);
    }

    @PutMapping("/{id}")
    @ApiOperation("根据ID修改")
    public Payload<Boolean> updateById(@PathVariable Long id, @RequestBody RefundReasonRequestDTO record) throws Exception {
        return new Payload<>(orderRefundReasonService.updateById(id, record));
    }

    @GetMapping("/findRefundInfoById/{id}")
    @ApiOperation("查询退款订单详情")
    public Payload<RefundDetailsInfoVO> findRefundInfoById(@PathVariable Long id) throws Exception {
        return new Payload<>(orderRefundReasonService.findRefundInfoById(id));
    }

    @PutMapping("/cancel/{id}")
    @ApiOperation("取消退款")
    public Payload<Boolean> cancel(@PathVariable Long id) throws Exception {
        return new Payload<>(orderRefundReasonService.cancel(id));
    }

    @GetMapping("/findOriginOrderRefundPaymentRecord")
    @ApiOperation("根据来源单的code查询退款单的退款支付记录")
    public Payload<FinancePaymentRecordsResponseDTO> findOriginOrderRefundPaymentRecord(FinancePaymentRecordsRequestQuery query) throws Exception {
        return new Payload<>(orderRefundReasonService.findOriginOrderRefundPaymentRecord(query));
    }

    @GetMapping("/getRefundRecord")
    @ApiOperation("获取退款记录")
    public Payload<List<OrderOperationRecordResponseDTO>> getRefundRecord(OrderOperationRecordRequestDTO query) throws Exception {
        return new Payload<>(orderRefundReasonService.getRefundRecord(query));
    }
}
