package com.deepexi.dd.domain.transaction.api.impl;/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: mowenxun
 * @Date: 2020/10/13/17:51
 * @Description:
 */

import com.deepexi.dd.domain.transaction.api.ShopOrderDomainApi;
import com.deepexi.dd.domain.transaction.domain.dto.gxs.order.ShopOrderAddDTO;
import com.deepexi.dd.domain.transaction.domain.dto.gxs.order.ShopOrderDetailVO;
import com.deepexi.dd.domain.transaction.domain.query.ShopOrderDomainRequestQuery;
import com.deepexi.dd.domain.transaction.domain.responseDto.ShopOrderDomainResponseDTO;
import com.deepexi.dd.domain.transaction.domain.responseDto.gxs.PlatformDistributionDomainRequestDTO;
import com.deepexi.dd.domain.transaction.domain.responseDto.gxs.PlatformDistributionViewDomainResponseDTO;
import com.deepexi.dd.domain.transaction.domain.responseDto.gxs.PlatformShopOrderDomainResponseDTO;
import com.deepexi.dd.domain.transaction.export.gxs.ShopOrderExportService;
import com.deepexi.dd.domain.transaction.service.ShopOrderDomainService;
import com.deepexi.dd.domain.transaction.service.impl.commonServiceImpl;
import com.deepexi.middle.merchant.domain.dto.GxsManagementResponseDTO;
import com.deepexi.util.pageHelper.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

/**
 * @ClassName ShopOrderDomainApiImpl
 * @Description: TODO
 * @Author mowenxun
 * @Date 2020/10/13
 * @Version V1.0
 **/
@RestController
public class ShopOrderDomainApiImpl implements ShopOrderDomainApi {

    @Autowired
    private ShopOrderDomainService shopOrderDomainService;

    @Autowired
    private ShopOrderExportService shopOrderExportService;

    @Autowired
    private commonServiceImpl commonService;

    @Override
    public PageBean<ShopOrderDomainResponseDTO> listShopOrdersPage(ShopOrderDomainRequestQuery query) throws Exception {
        GxsManagementResponseDTO gxsManagementResponseDTO = commonService.getShop();
        query.setShopId(gxsManagementResponseDTO.getId());
        return shopOrderDomainService.listShopOrdersPage(query);
    }

    @Override
    public List<ShopOrderAddDTO> addShopOrder(@RequestBody List<ShopOrderAddDTO> shopOrderAddDTOS) throws Exception {
        return shopOrderDomainService.addShopOrder(shopOrderAddDTOS);
    }

    @Override
    public boolean cancelShopOrderById(@PathVariable Long id) throws Exception {
        return shopOrderDomainService.cancelShopOrder(id);
    }

    @Override
    public ShopOrderDetailVO getShopOrderDetailById(@PathVariable Long id) throws Exception {
        return shopOrderDomainService.getShopOrderDetailById(id);
    }

    @Override
    public PageBean<PlatformShopOrderDomainResponseDTO> listPlatformShopOrdersPage(ShopOrderDomainRequestQuery query) throws Exception {
        //管辖门店订单查询特殊处理：根据parentShopId是否为空，判断调用方是查询管辖门店订单，替换本门店id至parentShopId
        if (query.getParentShopId() != null) {
            GxsManagementResponseDTO gxsManagementResponseDTO = commonService.getShop();
            query.setParentShopId(gxsManagementResponseDTO.getId());
        }
        return shopOrderDomainService.listPlatformShopOrdersPage(query);
    }

    @Override
    public List<PlatformDistributionViewDomainResponseDTO> distributeView(@RequestBody List<Long> ids) throws Exception {
        return shopOrderDomainService.distributeView(ids);
    }

    @Override
    public Boolean distributeOrder(@RequestBody List<PlatformDistributionDomainRequestDTO> list) throws Exception {
        return shopOrderDomainService.distributeOrder(list);
    }

    @Override
    public Boolean paySuccessUpdateStatus(@RequestBody List<Long> ids) throws Exception {
        return shopOrderDomainService.paySuccessUpdateStatus(ids);
    }

    @Override
    public Boolean signById(@PathVariable Long id) throws Exception {
        return shopOrderDomainService.signById(id);
    }

    @Override
    public List<PlatformDistributionViewDomainResponseDTO> againDistributeView(@PathVariable Long id) throws Exception {
        return shopOrderDomainService.againDistributeView(id);
    }

    @Override
    public Boolean againDistributeOrder(@RequestBody PlatformDistributionDomainRequestDTO platformDistributionDomainRequestDTO) throws Exception {
        return shopOrderDomainService.againDistributeOrder(platformDistributionDomainRequestDTO);
    }

  /*  @Override
    public void exportShopOrderToExcel(ShopOrderDomainRequestQuery query) throws Exception {
        shopOrderExportService.export(query.getResponse(), query);
    }*/
}
