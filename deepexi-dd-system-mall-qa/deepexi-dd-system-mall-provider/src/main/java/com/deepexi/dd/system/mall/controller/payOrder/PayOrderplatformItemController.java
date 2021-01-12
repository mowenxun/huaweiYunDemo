package com.deepexi.dd.system.mall.controller.payOrder;

import com.deepexi.dd.middle.order.domain.dto.PayOrderItemRequestDTO;
import com.deepexi.dd.middle.order.domain.dto.PayOrderItemResponseDTO;
import com.deepexi.dd.middle.order.domain.dto.PayOrderPlatformItemRequestDTO;
import com.deepexi.dd.middle.order.domain.dto.PayOrderPlatformItemResponseDTO;
import com.deepexi.dd.middle.order.domain.query.PayOrderItemRequestPageQuery;
import com.deepexi.dd.middle.order.domain.query.PayOrderPlatformItemRequestPageQuery;
import com.deepexi.dd.system.mall.service.PayOrderItemService;
import com.deepexi.dd.system.mall.service.PayOrderPlatformItemService;
import com.deepexi.util.config.Payload;
import com.deepexi.util.pageHelper.PageBean;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author JeremyLian
 * @date 2020/10/24 21:27
 */
@RestController
@Api(tags = "付款单明细接口")
@RequestMapping("/admin-api/v1/domain/transaction/payOrderItemController")
public class PayOrderplatformItemController {


    @Autowired
    PayOrderPlatformItemService    payOrderPlatformItemService;

    /**
     * 查询付款单明细
     * @param query
     * @return
     */
    @GetMapping("/list")
    public List<PayOrderPlatformItemResponseDTO> list(PayOrderPlatformItemRequestDTO query) {
        return payOrderPlatformItemService.list(query);
    }

    /**
     * 分页查询
     * @param query
     * @return
     */
    @GetMapping("/page")
    public PageBean<PayOrderPlatformItemResponseDTO> listPage(PayOrderPlatformItemRequestPageQuery query) {
        return payOrderPlatformItemService.listPage(query);
    }

    /**
     * 插入明细
     * @param record
     * @return
     */
    @PostMapping("/insert")
    public Payload<PayOrderPlatformItemResponseDTO> insert(@RequestBody PayOrderPlatformItemRequestDTO record) {
        return new Payload<>(payOrderPlatformItemService.insert(record));

    }

    /**
     * 更新
     * @param record
     * @return
     */
    @PutMapping("/update")
    public boolean updateById(@RequestBody PayOrderPlatformItemRequestDTO record) {
        return payOrderPlatformItemService.updateById(record);
    }
}
