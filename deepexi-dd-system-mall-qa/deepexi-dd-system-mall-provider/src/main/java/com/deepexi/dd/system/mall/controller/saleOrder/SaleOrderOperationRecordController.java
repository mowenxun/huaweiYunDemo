package com.deepexi.dd.system.mall.controller.saleOrder;/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: mowenxun
 * @Date: 2020/09/11/19:12
 * @Description:
 */

import com.deepexi.dd.middle.order.domain.dto.OrderOperationRecordRequestDTO;
import com.deepexi.dd.middle.order.domain.dto.OrderOperationRecordResponseDTO;
import com.deepexi.dd.system.mall.service.SaleOrderOperationRecordService;
import com.deepexi.util.config.Payload;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName SaleOrderOperationRecordController
 * @Description: TODO
 * @Author mowenxun
 * @Date 2020/9/11
 * @Version V1.0
 **/
@RestController
@Api(tags = "订单操作记录")
@RequestMapping("admin-api/v1/domain/sale/operation")
public class SaleOrderOperationRecordController {

    @Autowired
    private SaleOrderOperationRecordService saleOrderOperationRecordService;

    @GetMapping("/getOrderRejectOperationRecord")
    @ApiOperation("根据订单号查询对应的驳回信息")
    public Payload<OrderOperationRecordResponseDTO> getOrderRejectOperationRecord(OrderOperationRecordRequestDTO orderOperationRecordRequestDTO) {
        return saleOrderOperationRecordService.getOrderRejectOperationRecord(orderOperationRecordRequestDTO);
    }
}
