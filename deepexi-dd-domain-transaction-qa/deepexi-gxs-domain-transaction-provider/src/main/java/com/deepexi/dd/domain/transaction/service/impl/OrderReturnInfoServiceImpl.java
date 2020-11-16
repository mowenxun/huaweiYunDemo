package com.deepexi.dd.domain.transaction.service.impl;

import com.deepexi.clientiam.model.GetUserIdGroupResultVO;
import com.deepexi.dd.domain.common.enums.IdentifierTypeEnum;
import com.deepexi.dd.domain.common.extension.AppRuntimeEnv;
import com.deepexi.dd.domain.common.service.IdentifierGenerator;
import com.deepexi.dd.domain.common.util.GeneralConvertUtils;
import com.deepexi.dd.domain.transaction.domain.dto.OrderExpenseInfoRequestDTO;
import com.deepexi.dd.domain.transaction.domain.dto.OrderReturnInfoDTO;
import com.deepexi.dd.domain.transaction.domain.dto.OrderReturnInfoRequestDTO;
import com.deepexi.dd.domain.transaction.domain.dto.add.OrderReturnItemAddRequestDTO;
import com.deepexi.dd.domain.transaction.domain.query.OrderReturnInfoQuery;
import com.deepexi.dd.domain.transaction.enums.ResultEnum;
import com.deepexi.dd.domain.transaction.remote.order.OrderReturnInfoClient;
import com.deepexi.dd.domain.transaction.remote.order.SaleOrderInfoClient;
import com.deepexi.dd.domain.transaction.remote.order.SaleOrderItemClient;
import com.deepexi.dd.domain.transaction.service.IamUserService;
import com.deepexi.dd.domain.transaction.service.OrderReturnInfoService;
import com.deepexi.dd.middle.order.domain.dto.*;
import com.deepexi.dd.middle.order.domain.query.OrderReturnInfoRequestQuery;
import com.deepexi.dd.middle.order.domain.query.SaleOrderItemMiddleRequestQuery;
import com.deepexi.sdk.commodity.api.ShopItemOpenApi;
import com.deepexi.util.CollectionUtil;
import com.deepexi.util.config.Payload;
import com.deepexi.util.extension.ApplicationException;
import com.deepexi.util.pageHelper.PageBean;
import com.github.pagehelper.page.PageMethod;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class OrderReturnInfoServiceImpl implements OrderReturnInfoService {

    private static final Integer isActivity=0;

    @Autowired
    IdentifierGenerator identifierGenerator;

    @Autowired
    OrderReturnInfoClient orderReturnInfoClient;

    @Autowired
    SaleOrderInfoClient saleOrderInfoClient;

    @Autowired
    SaleOrderItemClient saleOrderItemClient;

    private ShopItemOpenApi shopItemOpenApi;

    @Autowired
    private IamUserService iamUserService;

    @Autowired
    private AppRuntimeEnv appRuntimeEnv;

    private static final Integer SUBMIT=1;
    private static final Integer DRAFT=0;

    @Override
    public PageBean<com.deepexi.dd.domain.transaction.domain.dto.OrderReturnInfoResponseDTO> listOrderReturnInfosPage(OrderReturnInfoQuery query) throws Exception{
        PageMethod.startPage(query.getPage(), query.getSize());
        OrderReturnInfoRequestQuery orderReturnInfoRequestQuery=query.clone(OrderReturnInfoRequestQuery.class);
        GetUserIdGroupResultVO groupResultVO = iamUserService.getUserGroupInfo();
        if(CollectionUtil.isNotEmpty(groupResultVO.getGroupInfo())){
            String idPath = groupResultVO.getGroupInfo().get(0).getIdPath();
            String[] idArray = idPath.split("\\/");
            if(idArray.length>2){
                String orgId = idArray[2];
                orderReturnInfoRequestQuery.setSellerId(Long.valueOf(orgId));
            }
        }
        Payload<PageBean<OrderReturnInfoResponseDTO>> payload = orderReturnInfoClient.listOrderReturnInfosPage(orderReturnInfoRequestQuery);
        PageBean<com.deepexi.dd.domain.transaction.domain.dto.OrderReturnInfoResponseDTO> result = GeneralConvertUtils.convert2PageBean(payload.getPayload(),com.deepexi.dd.domain.transaction.domain.dto.OrderReturnInfoResponseDTO.class);
        return result;
    }

    @Override
    public PageBean<com.deepexi.dd.domain.transaction.domain.dto.OrderReturnInfoResponseDTO> listOrderReturnInfosPageForApi(OrderReturnInfoQuery query) throws Exception{
        PageMethod.startPage(query.getPage(), query.getSize());
        OrderReturnInfoRequestQuery orderReturnInfoRequestQuery=query.clone(OrderReturnInfoRequestQuery.class);
        orderReturnInfoRequestQuery.setBuyerId(appRuntimeEnv.getUserId());
        Payload<PageBean<OrderReturnInfoResponseDTO>> payload = orderReturnInfoClient.listOrderReturnInfosPage(orderReturnInfoRequestQuery);
        PageBean<com.deepexi.dd.domain.transaction.domain.dto.OrderReturnInfoResponseDTO> result = GeneralConvertUtils.convert2PageBean(payload.getPayload(),com.deepexi.dd.domain.transaction.domain.dto.OrderReturnInfoResponseDTO.class);
        return result;
    }

    @Override
    public com.deepexi.dd.domain.transaction.domain.dto.OrderReturnInfoResponseDTO selectById(Long id) throws Exception {
        if (id == null) {
            log.error("查询退货单,传入ID为空");

        }
        com.deepexi.dd.domain.transaction.domain.dto.OrderReturnInfoResponseDTO result = GeneralConvertUtils.conv(orderReturnInfoClient.selectById(id).getPayload(),com.deepexi.dd.domain.transaction.domain.dto.OrderReturnInfoResponseDTO.class);
        if(result==null){
            throw new ApplicationException(ResultEnum.ORDER_NOT_FOUND);
        }
        else {
            return result;
        }

    }



    /**
     * 创建退货订单
     * @param orderReturnInfoRequestDTO
     * @return
     */
    @Override
    public OrderReturnInfoDTO insert(OrderReturnInfoRequestDTO orderReturnInfoRequestDTO) throws Exception {

        //检查客户是否被禁用
        if(iamUserService.getUserInfo()!=null){
            if(!isActivity.equals(iamUserService.getUserInfo().getStatus())){
                throw new ApplicationException(ResultEnum.USER_NOT_ACTIVITY);
            }
        }


        //检查仓库是否被禁用


        OrderReturnInfoDTO orderReturnInfoDTO = GeneralConvertUtils.conv(orderReturnInfoRequestDTO,OrderReturnInfoDTO.class);

        //其他费用
        BigDecimal totalExpense=getOrderExpenseTotalAmount(orderReturnInfoRequestDTO.getOrderExpenseInfo());


        if(orderReturnInfoDTO.getReturnMode().equals(0)){  //按单退货

            //查出销售订单对象
            SaleOrderInfoResponseDTO saleOrder = GeneralConvertUtils.conv(saleOrderInfoClient.selectById(orderReturnInfoDTO.getSaleOrderId()).getPayload(),SaleOrderInfoResponseDTO.class);

            if(saleOrder==null){
                throw new ApplicationException(ResultEnum.ORDER_NOT_FOUND);
            }

            if(saleOrder.getStatus().equals("cancel")){
                throw new ApplicationException(ResultEnum.ORDER_IS_CANCELLED);
            }

            //查退货表是否已经存在该销售单的退货信息
            OrderReturnInfoRequestQuery query = new OrderReturnInfoRequestQuery();
            query.setSaleOrderId(orderReturnInfoRequestDTO.getSaleOrderId());
            List<OrderReturnInfoResponseDTO> existReturnList =GeneralConvertUtils.convert2List(orderReturnInfoClient.listOrderReturnInfos(query).getPayload(),OrderReturnInfoResponseDTO.class);
            if(existReturnList.size()>0){
                throw new ApplicationException(ResultEnum.ORDER_IS_RETURNING);
            }


            orderReturnInfoDTO.setSaleOrderCode(saleOrder.getCode());
            orderReturnInfoDTO.setSellerId(saleOrder.getSellerId());
            orderReturnInfoDTO.setSellerName(saleOrder.getSellerName());
            orderReturnInfoDTO.setBuyerName(saleOrder.getBuyerName());
            orderReturnInfoDTO.setShippingType(saleOrder.getShippingType());
            orderReturnInfoDTO.setBuyerType(saleOrder.getBuyerType());

            //退货总数量
            Long returnQuantity = 0L;
            for(SaleOrderItemMiddleResponseDTO saleOrderItemResponseDTO : saleOrder.getItems()){
                returnQuantity += saleOrderItemResponseDTO.getSkuQuantity();
            }
            orderReturnInfoDTO.setReturnQuantity(returnQuantity);

            //总商品金额
            BigDecimal totalAmount = saleOrder.getTotalAmount();
            //促销优惠金额
            BigDecimal discountAmount = saleOrder.getDiscountAmount();

            //退还金额 = 总商品金额-促销优惠金额+其他费用
            orderReturnInfoDTO.setCanReturnAmount(totalAmount.subtract(discountAmount).add(totalExpense));

        }
        else{  //直接退商品

            orderReturnInfoDTO.setBuyerType("mall");

            //退货总数量
            Long returnQuantity = 0L;

            //总商品金额
            BigDecimal returnAmount = new BigDecimal(0);

            for(OrderReturnItemAddRequestDTO item : orderReturnInfoRequestDTO.getItems()){
                returnAmount = returnAmount.add(item.getPrice().multiply(new BigDecimal(item.getReturnQuantity())));
                returnQuantity += item.getReturnQuantity();
            }

            orderReturnInfoDTO.setReturnQuantity(returnQuantity);

            orderReturnInfoDTO.setCanReturnAmount(returnAmount.add(totalExpense));
            orderReturnInfoDTO.setBuyerName(appRuntimeEnv.getUsername());
        }

        //生成退货编号
        String code=  identifierGenerator.getIdentifier(IdentifierTypeEnum.ORDERRETURN);
        orderReturnInfoDTO.setCode(code);

        orderReturnInfoDTO.setItems(getItems(orderReturnInfoDTO));

        Date now = new Date();
        orderReturnInfoDTO.setCreatedBy(appRuntimeEnv.getUserId().toString());
        orderReturnInfoDTO.setUpdatedBy(appRuntimeEnv.getUserId().toString());
        orderReturnInfoDTO.setCreatedTime(now);
        orderReturnInfoDTO.setUpdatedTime(now);

        if(DRAFT.equals(orderReturnInfoRequestDTO.getType())){
            //初始状态为草稿
            orderReturnInfoDTO.setStatus(0);
        }
        if(SUBMIT.equals(orderReturnInfoRequestDTO.getType())){
            //初始状态为审核中
            orderReturnInfoDTO.setStatus(0);
        }



        OrderReturnInfoAddRequestDTO dto=orderReturnInfoDTO.clone(OrderReturnInfoAddRequestDTO.class);
        Payload<OrderReturnInfoResponseDTO> orderReturnInfoResponseDTO = orderReturnInfoClient.insert(dto);

        OrderReturnInfoResponseDTO result = GeneralConvertUtils.conv(orderReturnInfoResponseDTO.getPayload(),OrderReturnInfoResponseDTO.class);
        orderReturnInfoDTO.setId(result.getId());

        return orderReturnInfoDTO;
    }


    /**
     * 获取其他费用合计
     * @param orderExpenseInfoList
     * @return
     */
    private BigDecimal getOrderExpenseTotalAmount(List<OrderExpenseInfoRequestDTO> orderExpenseInfoList){
        BigDecimal orderExpenseTotalAmount=BigDecimal.valueOf(0);
        if(CollectionUtil.isNotEmpty(orderExpenseInfoList)){
            for(OrderExpenseInfoRequestDTO item : orderExpenseInfoList){
                orderExpenseTotalAmount = orderExpenseTotalAmount.add(item.getAmount());
            }
        }
        return orderExpenseTotalAmount;
    }

    /**
     * 获取商品明细信息,暂时模拟
     * @param orderReturnInfoDTO
     */
    private List<OrderReturnItemRequestDTO> getItems(OrderReturnInfoDTO orderReturnInfoDTO) {

        List<Long> skuIds = new ArrayList<>();

        if(orderReturnInfoDTO.getReturnMode().equals(0)){  //按单退货
            SaleOrderItemMiddleRequestQuery query = new SaleOrderItemMiddleRequestQuery();
            query.setSaleOrderId(orderReturnInfoDTO.getSaleOrderId());
            List<SaleOrderItemMiddleResponseDTO> list = saleOrderItemClient.listSaleOrderItems(query);
//            skuIds=list.stream().map(SaleOrderItemResponseDTO::getSkuId).collect(Collectors.toList());

            List<OrderReturnItemRequestDTO> orderReturnItemRequestDTOS=new ArrayList<>();

            list.forEach(i->{
                OrderReturnItemRequestDTO item =new OrderReturnItemRequestDTO();
                item.setSkuId(i.getSkuId());
                item.setUnitId(i.getUnitId());
                item.setSkuName(i.getSkuName());
                item.setCostPrice(i.getCostPrice());
                item.setPrice(i.getPrice());
                item.setPricePolicyId(i.getPricePolicyId());
                item.setSkuCode(i.getSkuCode());
                item.setSkuFormat(i.getSkuFormat());
                item.setSkuQuantity(i.getSkuQuantity());
                item.setTaxRate(i.getTaxRate());
                item.setReturnQuantity(i.getSkuQuantity());
                item.setReturnQuantity(i.getSkuQuantity());
                orderReturnItemRequestDTOS.add(item);
            });
            return orderReturnItemRequestDTOS;
        }
        else {
//            skuIds=orderReturnInfoDTO.getItems().stream().map(OrderReturnItemDTO::getSkuId).collect(Collectors.toList());

            List<OrderReturnItemRequestDTO> orderReturnItemRequestDTOS=orderReturnInfoDTO.getItems();
            if (CollectionUtil.isNotEmpty(orderReturnItemRequestDTOS)) {
                for(OrderReturnItemRequestDTO item : orderReturnItemRequestDTOS){

                    item.setUnitId(1L);
                    item.setSkuName("11111");
                    item.setCostPrice(BigDecimal.valueOf(0));
                    item.setSkuCode("223332");
                    item.setSkuFormat("testbb");
                }
            }
            return orderReturnItemRequestDTOS;
        }
//        ListShopItemUpShelfRequestDTO requestDTO=new ListShopItemUpShelfRequestDTO();
//        requestDTO.setAppId(1194084922566901768L);
//        requestDTO.setTenantId("a60fc3614d5441b4b3f9908d9c957243");
//        requestDTO.setIds(skuIds);
//        shopItemOpenApi.listShopItemUpShelf(requestDTO);

    }
}