package com.deepexi.dd.system.mall.service.impl;/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: mowenxun
 * @Date: 2020/10/13/19:13
 * @Description:
 */

import com.deepexi.dd.domain.business.domain.dto.CommoditySkuResponseApiDTO;
import com.deepexi.dd.domain.business.domain.query.CommodityAdminApiQuery;
import com.deepexi.dd.domain.common.extension.AppRuntimeEnv;
import com.deepexi.dd.domain.finance.domain.dto.FinanceCreditPaymentApiRequestDTO;
import com.deepexi.dd.domain.finance.domain.dto.FinanceCreditPaymentApiResponseDTO;
import com.deepexi.dd.domain.transaction.domain.dto.gxs.order.ShopOrderAddDTO;
import com.deepexi.dd.domain.transaction.domain.dto.gxs.order.ShopOrderDetailVO;
import com.deepexi.dd.domain.transaction.domain.query.ShopOrderDomainRequestQuery;
import com.deepexi.dd.domain.transaction.domain.responseDto.ShopOrderDomainResponseDTO;
import com.deepexi.dd.domain.transaction.domain.responseDto.gxs.PlatformDistributionDomainRequestDTO;
import com.deepexi.dd.domain.transaction.domain.responseDto.gxs.PlatformDistributionViewDomainResponseDTO;
import com.deepexi.dd.domain.transaction.domain.responseDto.gxs.PlatformShopOrderDomainResponseDTO;
import com.deepexi.dd.middle.finance.domain.enums.CreditDetailTypeEnum;
import com.deepexi.dd.middle.order.domain.dto.ShopOrderItemRequestDTO;
import com.deepexi.dd.middle.order.domain.dto.ShopOrderItemResponseDTO;
import com.deepexi.dd.system.mall.domain.dto.gxs.order.PayBackVO;
import com.deepexi.dd.system.mall.domain.dto.gxs.order.ShopOrderSysAddDTO;
import com.deepexi.dd.system.mall.enums.GxsSysResultEnum;
import com.deepexi.dd.system.mall.remote.business.BusinessSkuPriceOpenClient;
import com.deepexi.dd.system.mall.remote.customer.CompanyInfoClient;
import com.deepexi.dd.system.mall.remote.finance.gxs.FinanceSysCreditOpenApiRemote;
import com.deepexi.dd.system.mall.remote.shop.order.ShopOrderSysRemote;
import com.deepexi.dd.system.mall.service.ShopOrderSysService;
import com.deepexi.dd.system.mall.util.GeneralConvertUtils;
import com.deepexi.util.config.Payload;
import com.deepexi.util.extension.ApplicationException;
import com.deepexi.util.pageHelper.PageBean;
import com.deepexi.util.pojo.ObjectCloneUtils;
import domain.dto.CompanyInfoResponseApiDTO;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @ClassName ShopOrderSysServiceImpl
 * @Description: TODO
 * @Author mowenxun
 * @Date 2020/10/13
 * @Version V1.0
 **/
@Service
public class ShopOrderSysServiceImpl implements ShopOrderSysService {

    @Autowired
    private AppRuntimeEnv appRuntimeEnv;

    @Autowired
    private ShopOrderSysRemote shopOrderSysRemote;

    @Autowired
    private FinanceSysCreditOpenApiRemote financeSysCreditOpenApiRemote;

    @Autowired
    private BusinessSkuPriceOpenClient businessSkuPriceOpenClient;

    @Autowired
    private CompanyInfoClient companyInfoClient;

    @Override
    public PageBean<ShopOrderDomainResponseDTO> listShopOrdersPage(ShopOrderDomainRequestQuery query) throws Exception {
        return shopOrderSysRemote.listShopOrdersPage(query);
    }

    @Override
    public List<ShopOrderAddDTO> addShopOrder(List<ShopOrderSysAddDTO> shopOrderSysAddDTOS) throws Exception {
        //每次供应商查询一次，要不然会把所有供应商的商品全部查出来
        shopOrderSysAddDTOS.forEach(item -> {
            try {
                List<Long> skuIds =
                        item.getItem().stream().map(ShopOrderItemRequestDTO::getSkuId).collect(Collectors.toList());
                //查询价格
                CommodityAdminApiQuery query = new CommodityAdminApiQuery();
                query.setAppId(appRuntimeEnv.getAppId());
                query.setTenantId(appRuntimeEnv.getTenantId());
                query.setSkuIds(skuIds);
                query.setSize(5000);
                //根据供应商id获得供应对应的组织id
                Payload<CompanyInfoResponseApiDTO> payload = companyInfoClient.selectById(item.getSellerId());
                if (payload != null && payload.getPayload() != null) {
                    CompanyInfoResponseApiDTO companyInfoResponse =
                            GeneralConvertUtils.conv(payload.getPayload(), CompanyInfoResponseApiDTO.class);
                    query.setIsolationId(String.valueOf(companyInfoResponse.getCompanyId()));
                } else {
                    throw new ApplicationException(GxsSysResultEnum.SUPPLIER_ID_NOT_EXIST);
                }

                Payload<PageBean<CommoditySkuResponseApiDTO>> pageBeanPayload =
                        businessSkuPriceOpenClient.listCommodity(query);

                PageBean<CommoditySkuResponseApiDTO> pageBean =
                        GeneralConvertUtils.convert2PageBean(pageBeanPayload.getPayload(),
                                CommoditySkuResponseApiDTO.class);
                //设置价格
                if (pageBean != null && CollectionUtils.isNotEmpty(pageBean.getContent())) {
                    List<CommoditySkuResponseApiDTO> list = pageBean.getContent();
                    Map<Long, CommoditySkuResponseApiDTO> map =
                            list.stream().collect(Collectors.toMap(CommoditySkuResponseApiDTO::getSkuId,
                                    Function.identity(), (key1, key2) -> key1));
                    shopOrderSysAddDTOS.forEach(order -> {
                        order.getItem().forEach(sku -> {
                            sku.setCostPrice(map.get(sku.getSkuId()).getPurchesPrice());
                            sku.setProposePrice(map.get(sku.getSkuId()).getPrice());
                            sku.setCollectPurchasePrice(map.get(sku.getSkuId()).getCentralizedPrice());
                            sku.setCountyPrice(map.get(sku.getSkuId()).getCountyPrice());
                            sku.setTownPrice(map.get(sku.getSkuId()).getTownPrice());
                            sku.setVillagePrice(map.get(sku.getSkuId()).getVillagePrice());
                            sku.setGroupPurchasePrice(map.get(sku.getSkuId()).getGroupPrice());
                        });
                    });
                }
            } catch (Exception e) {
                e.printStackTrace();
            }


        });


        return shopOrderSysRemote.addShopOrder(ObjectCloneUtils.convertList(shopOrderSysAddDTOS,
                ShopOrderAddDTO.class));
    }

    @Override
    public boolean cancelShopOrderById(Long id) throws Exception {
        return shopOrderSysRemote.cancelShopOrderById(id);
    }

    @Override
    public ShopOrderDetailVO getShopOrderDetailById(Long id) throws Exception {
        return shopOrderSysRemote.getShopOrderDetailById(id);
    }

    @Override
    public PageBean<PlatformShopOrderDomainResponseDTO> listPlatformShopOrdersPage(ShopOrderDomainRequestQuery query) throws Exception {
        return shopOrderSysRemote.listPlatformShopOrdersPage(query);
    }

    @Override
    public List<PlatformDistributionViewDomainResponseDTO> distributeView(List<Long> ids) throws Exception {
        return shopOrderSysRemote.distributeView(ids);
    }

    @Override
    public Boolean distributeOrder(List<PlatformDistributionDomainRequestDTO> list) throws Exception {
        return shopOrderSysRemote.distributeOrder(list);
    }

    @Override
    public PayBackVO paySuccessUpdateStatus(List<Long> ids) throws Exception {
        PayBackVO payBackVO = new PayBackVO();
        AtomicReference<Long> totalCommodityNum = new AtomicReference<>(0l);
        AtomicReference<Integer> type = new AtomicReference<>(0);
        AtomicReference<BigDecimal> payMoney = new AtomicReference<>(new BigDecimal(0));
        ShopOrderDetailVO shopOrderDetailVO1 = shopOrderSysRemote.getShopOrderDetailById(ids.get(0));
        List<String> orderCodes = new ArrayList<>();
        ids.forEach(item -> {
            try {
                ShopOrderDetailVO shopOrderDetailVO = shopOrderSysRemote.getShopOrderDetailById(item);
                orderCodes.add(shopOrderDetailVO.getOrderCode());
                // shopOrderDetailVO1.set(shopOrderDetailVO);
                payBackVO.setArriveDate(shopOrderDetailVO.getArriveDate());
                payBackVO.setOrderConsigneeAddressResponseDTO(shopOrderDetailVO.getOrderConsigneeAddressResponseDTO());
                payMoney.updateAndGet(v -> v.add(shopOrderDetailVO.getTotalAmount()));
                totalCommodityNum.updateAndGet(v -> v + shopOrderDetailVO.getItem().stream().mapToLong(ShopOrderItemResponseDTO::getSkuQuantity).sum());
                type.updateAndGet(v -> v + shopOrderDetailVO.getItem().size());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        payBackVO.setTotalCommodityNum(totalCommodityNum.get());
        payBackVO.setType(Long.valueOf(type.get()));
        payBackVO.setPayMoney(payMoney.get());
        //1调用资金域接口
        FinanceCreditPaymentApiRequestDTO financeCreditPaymentApiRequestDTO = new FinanceCreditPaymentApiRequestDTO();
        financeCreditPaymentApiRequestDTO.setAppId(shopOrderDetailVO1.getAppId());
        financeCreditPaymentApiRequestDTO.setTenantId(shopOrderDetailVO1.getTenantId());
        financeCreditPaymentApiRequestDTO.setType(CreditDetailTypeEnum.ORDER.getCode());
        financeCreditPaymentApiRequestDTO.setCustomerId(shopOrderDetailVO1.getShopId());
        financeCreditPaymentApiRequestDTO.setCustomerName(shopOrderDetailVO1.getShopName());
        financeCreditPaymentApiRequestDTO.setOrderId(shopOrderDetailVO1.getId());
        financeCreditPaymentApiRequestDTO.setHappenLimit(payMoney.get());
        financeCreditPaymentApiRequestDTO.setOrderCodes(orderCodes);
        financeCreditPaymentApiRequestDTO.setIsolationId(appRuntimeEnv.getUserOrganization().getId().toString());
        financeCreditPaymentApiRequestDTO.setOrgId(appRuntimeEnv.getUserOrganization().getId());
        Payload<FinanceCreditPaymentApiResponseDTO> paymentApiResponseDTOPayload =
                financeSysCreditOpenApiRemote.creditPayment(financeCreditPaymentApiRequestDTO);
        if (paymentApiResponseDTOPayload.getCode().equals("500")) {
            throw new ApplicationException(paymentApiResponseDTOPayload.getMsg());
        }
        FinanceCreditPaymentApiResponseDTO financeCreditPaymentApiResponseDTO =
                GeneralConvertUtils.conv(paymentApiResponseDTOPayload.getPayload(),
                        FinanceCreditPaymentApiResponseDTO.class);
        if (financeCreditPaymentApiResponseDTO == null) {
            throw new ApplicationException(GxsSysResultEnum.SHOP_FINACE_NOT_EXIST);
        }
        payBackVO.setPayOrderCode(financeCreditPaymentApiResponseDTO.getCode());
        //2 修改订单
        shopOrderSysRemote.paySuccessUpdateStatus(ids);
        payBackVO.setIsSuccess(true);
        payBackVO.setIds(ids);
        payBackVO.setMessage("成功");
        return payBackVO;
    }

    @Override
    public Boolean signById(Long id) throws Exception {
        return shopOrderSysRemote.signById(id);
    }

    @Override
    public List<PlatformDistributionViewDomainResponseDTO> againDistributeView(Long id) throws Exception {
        return shopOrderSysRemote.againDistributeView(id);
    }

    @Override
    public Boolean againDistributeOrder(PlatformDistributionDomainRequestDTO platformDistributionDomainRequestDTO) throws Exception {
        return shopOrderSysRemote.againDistributeOrder(platformDistributionDomainRequestDTO);
    }

   /* @Override
    public void exportShopOrderToExcel(HttpServletResponse response, ShopOrderDomainRequestQuery query) throws
    Exception {
        query.setResponse(response);
        shopOrderSysRemote.exportShopOrderToExcel(query);
    }*/
}
