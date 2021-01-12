package com.deepexi.dd.system.mall.controller.gxs.order;/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: mowenxun
 * @Date: 2020/10/16/11:05
 * @Description:
 */

import com.deepexi.dd.domain.transaction.domain.dto.gxs.order.ShopOrderDetailVO;
import com.deepexi.dd.domain.transaction.domain.query.SalePickGoodsOrderSkuQuery;
import com.deepexi.dd.domain.transaction.domain.query.ShopOrderDomainRequestQuery;
import com.deepexi.dd.domain.transaction.domain.responseDto.ShopOrderDomainResponseDTO;
import com.deepexi.dd.domain.transaction.domain.responseDto.gxs.PlatformDistributionDomainRequestDTO;
import com.deepexi.dd.domain.transaction.domain.responseDto.gxs.PlatformDistributionViewDomainResponseDTO;
import com.deepexi.dd.domain.transaction.domain.responseDto.gxs.PlatformShopOrderDomainResponseDTO;
import com.deepexi.dd.system.mall.domain.dto.gxs.order.DistributeViewQuery;
import com.deepexi.dd.system.mall.enums.GxsSysResultEnum;
import com.deepexi.dd.system.mall.remote.customer.CompanyInfoClient;
import com.deepexi.dd.system.mall.service.ShopOrderSysService;
import com.deepexi.dd.system.mall.util.GeneralConvertUtils;
import com.deepexi.dd.system.mall.util.ValidableList;
import com.deepexi.util.config.Payload;
import com.deepexi.util.extension.ApplicationException;
import com.deepexi.util.pageHelper.PageBean;
import domain.dto.CompanyInfoResponseApiDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
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
@Api(tags = "平台端-门店订单接口")
@RequestMapping("/admin-api/v1/platform/shop/order")
public class PlatformShopOrderController {

    @Autowired
    private HttpServletResponse response;

    @Autowired
    private ShopOrderSysService shopOrderSysService;

    @Autowired
    private CompanyInfoClient companyInfoClient;

    @ApiOperation("列表接口")
    @GetMapping("/page")
    public Payload<PageBean<PlatformShopOrderDomainResponseDTO>> listShopOrdersPage(ShopOrderDomainRequestQuery query) throws Exception {
        query.setShopId(null);
        query.setParentShopId(null);
        //前端把组织id当做供应商id传进来，转一遍
        if (query.getSellerId() != null && query.getSellerId() != 0) {
            List<Long> ids = new ArrayList<>();
            ids.add(query.getSellerId());
            Payload<List<CompanyInfoResponseApiDTO>> payload = companyInfoClient.listCompanyInfosByCompanyIds(ids);
            if (payload == null || CollectionUtils.isEmpty(payload.getPayload())) {
                throw new ApplicationException(GxsSysResultEnum.GROUP_ID_NOT_SUPPLIER);
            }
            List<CompanyInfoResponseApiDTO> list = GeneralConvertUtils.convert2List(payload.getPayload(),
                    CompanyInfoResponseApiDTO.class);
            query.setSellerId(list.get(0).getId());
        }
        return new Payload<>(shopOrderSysService.listPlatformShopOrdersPage(query));
    }

    @ApiOperation("根据订单id查看详情")
    @GetMapping("/{id}")
    public Payload<ShopOrderDetailVO> getDetailById(@PathVariable Long id) throws Exception {
        return new Payload<>(shopOrderSysService.getShopOrderDetailById(id));
    }

/*    @ApiOperation(value = "平台—门店订单导出", nickname = "export")
    @GetMapping("/export")
    public void exportSaleOrder(ShopOrderDomainRequestQuery query) throws Exception {
        shopOrderSysService.exportShopOrderToExcel(response,query);
    }*/

    @ApiOperation("平台-分发预览视图接口")
    @PostMapping("/distributeView")
    public Payload<List<PlatformDistributionViewDomainResponseDTO>> distributeView(@RequestBody @Valid DistributeViewQuery distributeViewQuery) throws Exception {
        return new Payload<>(shopOrderSysService.distributeView(distributeViewQuery.getIds()));
    }

    @PostMapping("/distributeOrder")
    @ApiOperation("平台端-确认分发接口")
    public Payload<Boolean> distributeOrder(@RequestBody ValidableList<PlatformDistributionDomainRequestDTO> list) throws Exception {
        return new Payload<>(shopOrderSysService.distributeOrder(list));
    }


    @ApiOperation("平台-重新分发预览视图接口")
    @GetMapping("/againDistributeView/{id}")
    public Payload<List<PlatformDistributionViewDomainResponseDTO>> distributeView(@PathVariable Long id) throws Exception {
        return new Payload<>(shopOrderSysService.againDistributeView(id));
    }

    @PostMapping("/againDistributeOrder")
    @ApiOperation("平台端-重新分发确认接口（修改已分发订单）")
    public Payload<Boolean> againDistributeOrder(@RequestBody @Valid PlatformDistributionDomainRequestDTO platformDistributionDomainRequestDTO) throws Exception {
        return new Payload<>(shopOrderSysService.againDistributeOrder(platformDistributionDomainRequestDTO));
    }
}
