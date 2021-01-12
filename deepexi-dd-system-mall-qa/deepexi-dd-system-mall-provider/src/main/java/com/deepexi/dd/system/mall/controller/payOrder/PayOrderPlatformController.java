package com.deepexi.dd.system.mall.controller.payOrder;

/**
 * @author JeremyLian
 * @date 2020/10/24 21:27
 */

import com.deepexi.dd.domain.common.util.GeneralConvertUtils;
import com.deepexi.dd.middle.order.domain.dto.PayOrderRequestDTO;
import com.deepexi.dd.system.mall.domain.dto.PayOrderInfoDTO;
import com.deepexi.dd.system.mall.domain.dto.PayOrderInfoResponseDTO;
import com.deepexi.dd.system.mall.domain.query.payOrder.PayOrderRequestQuery;
import com.deepexi.dd.system.mall.service.PayOrderPlatformService;
import com.deepexi.util.config.Payload;
import com.deepexi.util.pageHelper.PageBean;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(tags = "付款单接口")
@RequestMapping("/admin-api/v1/domain/transaction/payOrderController")
public class PayOrderPlatformController {

    @Autowired
    PayOrderPlatformService payOrderPlatformService;
    /**
     * 分页查询付款单表列表
     *
     * @return
     */
    @GetMapping("/page")
    public Payload<PageBean<PayOrderInfoResponseDTO>> listPage(PayOrderRequestQuery query) throws Exception{
        PageBean<PayOrderInfoResponseDTO> result = payOrderPlatformService.listPage(query);
        return new Payload<>(GeneralConvertUtils.convert2PageBean(result,PayOrderInfoResponseDTO.class));
    }

    /**
     * 新增付款单
     *
     * @param payOrderInfoDTO
     * @return
     * @throws Exception
     */
    @PostMapping("/add")
    public Boolean addPayOrder(@RequestBody PayOrderInfoDTO payOrderInfoDTO) throws Exception {
        return payOrderPlatformService.addPayOrder(payOrderInfoDTO);
    }

    /**
     * 更新付款单信息(状态)
     *
     * @param record
     * @return
     */
    @PutMapping("/update")
    public boolean updateById(@RequestBody PayOrderRequestDTO record) throws Exception {
        return payOrderPlatformService.updateById(record);
    }
}
