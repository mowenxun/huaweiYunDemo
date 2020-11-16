package com.deepexi.dd.domain.transaction.controller;

import com.deepexi.dd.domain.common.util.GeneralConvertUtils;
import com.deepexi.dd.domain.transaction.domain.query.ShopOrderDomainRequestQuery;
import com.deepexi.dd.domain.transaction.domain.query.SubordinateShopOrderRequestQuery;
import com.deepexi.dd.domain.transaction.enums.ResultEnum;
import com.deepexi.dd.domain.transaction.export.gxs.ShopOrderExportService;
import com.deepexi.dd.domain.transaction.remote.gxs.CompanyInfoDomainClient;
import com.deepexi.dd.domain.transaction.service.common.CommonService;
import com.deepexi.util.config.Payload;
import com.deepexi.util.extension.ApplicationException;
import domain.dto.CompanyInfoResponseApiDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName SubordinateShopOrderController(excel导出在交易域)
 * @Description: 下属门店的订单管理类（导出管理）
 * @Author Leon Wong
 * @Date 2020/10/16
 * @Version V1.0
 **/
@RestController
@Api(tags = "门店订购-下属店铺订单接口")
@RequestMapping("/admin-api/v1/sys/subordinate/shop/order")
public class SubordinateShopOrderController {

    @Autowired
    private HttpServletResponse response;

    @Autowired
    private ShopOrderExportService shopOrderExportService;

    @Autowired
    private CommonService commonService;

    @Autowired
    private CompanyInfoDomainClient companyInfoClient;

    @ApiOperation("下属门店订单导出")
    @GetMapping("/export")
    public void exportSubordinateShopOrder(@Valid SubordinateShopOrderRequestQuery query) throws Exception {
        //前端把组织id当做供应商id传进来，转一遍
        if (query.getSellerId() != null && query.getSellerId() != 0) {
            List<Long> ids = new ArrayList<>();
            ids.add(query.getSellerId());
            Payload<List<CompanyInfoResponseApiDTO>> payload = companyInfoClient.listCompanyInfosByCompanyIds(ids);
            if (payload == null || CollectionUtils.isEmpty(payload.getPayload())) {
                throw new ApplicationException(ResultEnum.GROUP_ID_NOT_SUPPLIER);
            }
            List<CompanyInfoResponseApiDTO> list = GeneralConvertUtils.convert2List(payload.getPayload(),
                    CompanyInfoResponseApiDTO.class);
            query.setSellerId(list.get(0).getId());
        }
        //获取当前门店的shopId，设置为下属门店的上级门店的parentShopId，
        query.setParentShopId(commonService.queryOrgThrowIfNotFound(query.getParentShopId()).getId());
        shopOrderExportService.export(response,query.clone(ShopOrderDomainRequestQuery.class));
    }
}
