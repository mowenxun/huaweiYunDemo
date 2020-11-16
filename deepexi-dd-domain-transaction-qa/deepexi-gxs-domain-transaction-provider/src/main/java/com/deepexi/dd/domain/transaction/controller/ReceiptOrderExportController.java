package com.deepexi.dd.domain.transaction.controller;/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: mowenxun
 * @Date: 2020/10/20/14:50
 * @Description:
 */

import com.alibaba.fastjson.JSONObject;
import com.deepexi.dd.domain.common.extension.AppRuntimeEnv;
import com.deepexi.dd.domain.transaction.domain.query.SupplerOrderQuery;
import com.deepexi.dd.domain.transaction.export.SupplierOrderExportService;
import com.deepexi.dd.domain.transaction.service.impl.commonServiceImpl;
import com.deepexi.middle.merchant.domain.dto.GxsManagementResponseDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName ReceiptOrderExportController
 * @Description: TODO
 * @Author mowenxun
 * @Date 2020/10/20
 * @Version V1.0
 **/
@RestController
@Api(tags = "门店端-收货单-导出接口")
@RequestMapping("/admin-api/v1/shop/receipt/order/domain")
@Slf4j
public class ReceiptOrderExportController {

    @Autowired
    private SupplierOrderExportService supplierOrderExportService;

    @Autowired
    private AppRuntimeEnv appRuntimeEnv;

    @Autowired
    private HttpServletResponse response;

    @Autowired
    private commonServiceImpl commonService;

    /**
     * 导出已分发订单列表
     *
     * @param supplerOrderQuery
     */
    @GetMapping("/export")
    @ApiOperation("导出已分发订单列表")
    public void exportSupplierOrder(SupplerOrderQuery supplerOrderQuery) throws Exception {
        log.info("appRuntimeEnv=={}", JSONObject.toJSONString(appRuntimeEnv));
        GxsManagementResponseDTO gxsManagementResponseDTO = commonService.getShop();
        supplerOrderQuery.setReceiveDistributionId(gxsManagementResponseDTO.getId());
        supplierOrderExportService.export(response, supplerOrderQuery);
    }

}
