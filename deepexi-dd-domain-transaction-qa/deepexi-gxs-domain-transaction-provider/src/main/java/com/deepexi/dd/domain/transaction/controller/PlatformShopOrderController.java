package com.deepexi.dd.domain.transaction.controller;/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: mowenxun
 * @Date: 2020/10/16/11:05
 * @Description:
 */

import com.deepexi.dd.domain.common.util.GeneralConvertUtils;
import com.deepexi.dd.domain.transaction.domain.query.ShopOrderDomainRequestQuery;
import com.deepexi.dd.domain.transaction.enums.gxs.GxsResultEnum;
import com.deepexi.dd.domain.transaction.export.gxs.ShopOrderExportService;
import com.deepexi.dd.domain.transaction.remote.gxs.CompanyInfoDomainClient;
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
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName PlatformShopOrderController
 * @Description: TODO
 * @Author mowenxun
 * @Date 2020/10/16
 * @Version V1.0
 **/
@RestController
@Api(tags = "平台端-domain-门店订单接口")
@RequestMapping("/admin-api/v1/platform/shop/order")
public class PlatformShopOrderController {

    @Autowired
    private HttpServletResponse response;

    @Autowired
    private ShopOrderExportService shopOrderExportService;

    @Autowired
    private CompanyInfoDomainClient companyInfoDomainClient;


    @ApiOperation(value = "平台—门店订单导出", nickname = "export")
    @GetMapping("/export")
    public void exportSaleOrder(ShopOrderDomainRequestQuery query) throws Exception {
        query.setShopId(null);
        query.setParentShopId(null);
        //前端把组织id当做供应商id传进来，转一遍
        if (query.getSellerId() != null && query.getSellerId() != 0) {
            List<Long> ids = new ArrayList<>();
            ids.add(query.getSellerId());
            Payload<List<CompanyInfoResponseApiDTO>> payload = companyInfoDomainClient.listCompanyInfosByCompanyIds(ids);
            if (payload == null || CollectionUtils.isEmpty(payload.getPayload())) {
                throw new ApplicationException(GxsResultEnum.GROUP_ID_NOT_SUPPLIER);
            }
            List<CompanyInfoResponseApiDTO> list = GeneralConvertUtils.convert2List(payload.getPayload(),
                    CompanyInfoResponseApiDTO.class);
            query.setSellerId(list.get(0).getId());
        }
        shopOrderExportService.export(response, query);
    }

}
