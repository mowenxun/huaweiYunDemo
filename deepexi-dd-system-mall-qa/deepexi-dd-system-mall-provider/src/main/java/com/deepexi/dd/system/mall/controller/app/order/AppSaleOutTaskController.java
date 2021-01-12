package com.deepexi.dd.system.mall.controller.app.order;

import com.deepexi.dd.system.mall.domain.dto.SaleOutTaskDetailInfoResponseDTO;
import com.deepexi.dd.system.mall.domain.dto.SaleOutTaskInfoSignRequestDTO;
import com.deepexi.dd.system.mall.service.SaleOutTaskService;
import com.deepexi.util.config.Payload;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @Author: mumu
 * @Description: 销售出库单
 * @Datetime: 2020/9/1   17:20
 * @version: v1.0
 */
@RestController
@Api(tags = "app销售出库单接口")
@RequestMapping("/admin-api/v1/app/transaction/salesOutTask")
public class AppSaleOutTaskController {

    @Autowired
    SaleOutTaskService saleOutTaskService;

    /**
     * @Description: 签收.
     * @Param: [dto]
     * @return: com.deepexi.util.config.Payload<java.lang.Boolean>
     */
    @ApiOperation("签收")
    @PostMapping("/sign")
    public Payload<Boolean> sign(@RequestBody @Valid SaleOutTaskInfoSignRequestDTO dto) throws Exception {
        return new Payload<>(saleOutTaskService.sign(dto));
    }

    /**
     * 根据订单ID及签收状态查询销售出库单（出库任务单）
     *
     * @param id
     * @param signStatus
     * @return
     * @throws Exception
     */
    @ApiOperation("根据订单ID及签收状态查询销售出库单（出库任务单）")
    @RequestMapping(value = "/searchSaleOutTaskInfoBySaleOrderId/{id}/{signStatus}", method = RequestMethod.GET)
    public Payload<List<SaleOutTaskDetailInfoResponseDTO>> searchSaleOutTaskInfoBySaleOrderId(@PathVariable Long id, @PathVariable Integer signStatus) throws Exception {
        List<SaleOutTaskDetailInfoResponseDTO> list = saleOutTaskService.searchSaleOutTaskInfoBySaleOrderId(id, signStatus);
        return new Payload<>(list);
    }


}
