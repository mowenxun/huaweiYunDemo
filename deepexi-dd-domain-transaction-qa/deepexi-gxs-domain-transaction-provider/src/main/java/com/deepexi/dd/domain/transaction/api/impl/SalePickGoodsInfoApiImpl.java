package com.deepexi.dd.domain.transaction.api.impl;

import com.deepexi.dd.domain.transaction.api.SalePickGoodsInfoApi;
import com.deepexi.dd.domain.transaction.domain.dto.*;
import com.deepexi.dd.domain.transaction.domain.dto.examine.ExaminePickGoodsInfoRequestDTO;
import com.deepexi.dd.domain.transaction.domain.dto.examine.ExamineRejectInfoRequestDTO;
import com.deepexi.dd.domain.transaction.domain.dto.offlinePayInfo.OfflinePayInfoRequestDTO;
import com.deepexi.dd.domain.transaction.domain.dto.offlinePayInfo.SalePickOrderPayRequestDTO;
import com.deepexi.dd.domain.transaction.domain.dto.operation.center.SalePickGoodsInfoDetailResponseDTO;
import com.deepexi.dd.domain.transaction.domain.dto.salePickDelivery.SalePickDeliveryInfoRequestDTO;
import com.deepexi.dd.domain.transaction.domain.dto.operation.center.SalePickGoodsInfoOperationResponseDTO;
import com.deepexi.dd.domain.transaction.domain.dto.salePickDelivery.SalePickDeliveryInfoRequestDTO;
import com.deepexi.dd.domain.transaction.domain.dto.salePickDelivery.SalePickGoodsDetailInfoResponseDTO;
import com.deepexi.dd.domain.transaction.domain.dto.salePickDelivery.SalePickGoodsItemInfoResponseDTO;
import com.deepexi.dd.domain.transaction.domain.query.SalePickGoodsInfoReqtQuery;
import com.deepexi.dd.domain.transaction.domain.dto.saleOutTask.SaleOutTaskSignRequestDTO;
import com.deepexi.dd.domain.transaction.service.SaleOrderInfoService;
import com.deepexi.dd.domain.transaction.service.SalePickGoodsInfoService;
import com.deepexi.dd.middle.order.domain.dto.SalePickGoodsOrderSkuResponseDTO;
import com.deepexi.dd.middle.order.domain.query.SalePickGoodsInfoRequestQuery;
import com.deepexi.dd.middle.order.domain.query.SalePickGoodsOrderSkuRequestQuery;
import com.deepexi.dd.middle.order.domain.query.operation.center.SalePickGoodsInfoOperationRequestQuery;
import com.deepexi.dd.middle.order.domain.query.SalePickGoodsOrderSkuRequestQuery;
import com.deepexi.util.CollectionUtil;
import com.deepexi.util.config.Payload;
import com.deepexi.util.pageHelper.PageBean;
import com.deepexi.util.pojo.ObjectCloneUtils;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * SalePickGoodsInfoApiImpl
 *
 * @author linpbin
 * @version 1.0
 * @date 2019-12-24 15:32
 */
@RestController
public class SalePickGoodsInfoApiImpl implements SalePickGoodsInfoApi {

    @Autowired
    private SalePickGoodsInfoService salePickGoodsInfoService;

    @Autowired
    private SaleOrderInfoService saleOrderInfoService;

    @Override
    @ApiOperation("新增并提交提货单据表管理以及明细")
    public Payload<SalePickGoodsInfoResponseDTO> createPickGoods(@RequestBody @Valid SalePickGoodsPlanInfoRequestDTO record) throws Exception {
        Payload<SalePickGoodsInfoResponseDTO> payload = salePickGoodsInfoService.createPickGoods(record);
        return payload;
    }

    @Override
    @ApiOperation("在线订购运营端列表")
    public Payload<PageBean<SalePickGoodsInfoResponseDTO>> listPage(@RequestBody SalePickGoodsInfoReqtQuery query) throws Exception {
        PageBean<SalePickGoodsInfoResponseDTO> pageBean = salePickGoodsInfoService.listPage(query.clone(SalePickGoodsInfoRequestQuery.class));
        return new Payload<>(pageBean);
    }

    @Override
    @ApiOperation("修改提货计划")
    public Payload<SalePickGoodsInfoResponseDTO> editPickGoods(@RequestBody SalePickGoodsPlanInfoRequestDTO record) throws Exception {
        Payload<SalePickGoodsInfoResponseDTO> payload = salePickGoodsInfoService.editPickGoods(record);
        return payload;
    }

    @Override
    @ApiOperation("取消提货计划")
    public Payload<Boolean> cancelPickGoods(@RequestBody SalePickGoodsPlanInfoRequestDTO record) throws Exception {
        Payload<Boolean> payload = salePickGoodsInfoService.cancelPickGoods(record);
        return payload;
    }

    @Override
    @ApiOperation("关闭提货计划")
    public Payload<Boolean> closePickGoods(@RequestBody SalePickGoodsPlanInfoRequestDTO record) throws Exception {
        Payload<Boolean> payload = salePickGoodsInfoService.cancelPickGoods(record);
        return payload;
    }

    @Override
    @ApiOperation("提货单签收")
    public Payload<Boolean> sign(@RequestBody SaleOutTaskSignRequestDTO dto) throws Exception {
        return new Payload<>(salePickGoodsInfoService.sign(dto));
    }

    @Override
    public Payload<SalePickGoodsInfoDetailResponseDTO> getDetailById(@PathVariable Long id) throws Exception {
        SalePickGoodsInfoDetailResponseDTO responseDTO=  salePickGoodsInfoService.getDetailById(id);
       if(responseDTO!=null && CollectionUtil.isNotEmpty(responseDTO.getItems())){
            for(com.deepexi.dd.domain.transaction.domain.dto.SalePickGoodsOrderRespDTO order: responseDTO.getItems()){
                if(CollectionUtil.isNotEmpty(order.getItems())){
                    for(com.deepexi.dd.domain.transaction.domain.dto.SalePickGoodsOrderSkuRespDTO sku:order.getItems()){
                        sku.setDeliveryQuantityed(getLong(sku.getDeliveryQuantity())-getLong(sku.getWaitSendNum()));
                    }
                }
            }
       }
        return new Payload<>(responseDTO);
    }

    private Long getLong(Long v){
        return v==null ? 0L:v;
    }

    @Override
    public Payload<SalePickGoodsInfoSkuDTO> getSkuByInfoId(@PathVariable Long id) throws Exception {
        List<SalePickGoodsOrderSkuResponseDTO> skuArr = salePickGoodsInfoService.getSkuByInfoId(id);
        if(skuArr == null){
            skuArr = new ArrayList<SalePickGoodsOrderSkuResponseDTO>();
        }
        List<SalePickGoodsOrderSkuRequestDTO> skus = ObjectCloneUtils.convertList(skuArr, SalePickGoodsOrderSkuRequestDTO.class);
        SalePickGoodsInfoSkuDTO dto = new SalePickGoodsInfoSkuDTO();
        dto.setSkus(skus);
        return new Payload<SalePickGoodsInfoSkuDTO>(dto);
    }

    @Override
    public Payload<SaleOrderPayResponseDTO> salePickPaySaleOrder(@RequestBody @Valid SalePickOrderPayRequestDTO saleOrderPayRequestDTO) throws Exception {
        SaleOrderPayResponseDTO saleOrderPayResponseDTO = saleOrderInfoService.salePickPaySaleOrder(saleOrderPayRequestDTO.clone(com.deepexi.dd.domain.transaction.domain.dto.SalePickOrderPayRequestDTO.class));
        if (saleOrderPayResponseDTO == null) {
            return new Payload<>();
        }
        return new Payload<>(saleOrderPayResponseDTO.clone(SaleOrderPayResponseDTO.class));
    }

    @Override
    public Payload<Boolean> saveSalePickOfflinePayInfo(@RequestBody @Valid OfflinePayInfoRequestDTO dto) throws Exception {
        Boolean saveSalePickOfflinePayInfo = saleOrderInfoService.saveSalePickOfflinePayInfo(dto.clone(OfflinePayInfoRequestDTO.class));
        return new Payload<>(saveSalePickOfflinePayInfo);
    }

    @Override
    public Payload<PaymentCompletedResponseDTO> paymentSalePickInfo(@PathVariable("id") Long salePickId) {
        PaymentCompletedResponseDTO paymentCompletedResponseDTO = saleOrderInfoService.paymentSalePickInfo(salePickId);
        if (paymentCompletedResponseDTO == null) {
            return new Payload<>();
        }
        return new Payload<>(paymentCompletedResponseDTO);
    }

    @Override
    public Payload<PageBean<SalePickGoodsOrderSkuResponseDTO>> searchPickGoodsList(@PathVariable Long saleOrderId, SalePickGoodsOrderSkuRequestQuery salePickGoodsOrderSkuRequestQuery) throws Exception {
        return new Payload<>(salePickGoodsInfoService.searchPickGoodsList(saleOrderId,
                salePickGoodsOrderSkuRequestQuery));
    }

    @Override
    public Payload<PageBean<SalePickGoodsInfoOperationResponseDTO>> listOperationPage(@RequestBody SalePickGoodsInfoOperationRequestQuery query)throws Exception {
        PageBean<SalePickGoodsInfoOperationResponseDTO> pageBean = salePickGoodsInfoService.listOperationPage(query);
        return new Payload<>(pageBean);
    }

    /**
     * @param dto
     * @Description: 提货单发货.
     * @Param: [dto]
     * @return: com.deepexi.util.config.Payload<java.lang.Boolean>
     * @Author: SongTao
     * @Date: 2020/8/28
     */
    @Override
    public Payload<Boolean> pickPlanDeliveryGoods(@RequestBody @Valid SalePickDeliveryInfoRequestDTO dto) throws Exception {
        return new Payload<>(salePickGoodsInfoService.pickPlanDeliveryGoods(dto));
    }

    @Override
    public Payload<Boolean> examinePass(@RequestBody @Valid ExaminePickGoodsInfoRequestDTO requestDTO) throws Exception {
        return new Payload<>(salePickGoodsInfoService.examinePass(requestDTO));
    }

    @Override
    public Payload<Boolean> examineReject(@Valid @RequestBody ExamineRejectInfoRequestDTO requestDTO) throws Exception {
        return new Payload<>(salePickGoodsInfoService.examineReject(requestDTO));
    }

    @Override
    public Payload<Boolean> judgeGetAll() throws Exception {
        return new Payload<>(salePickGoodsInfoService.judgeGetAll());
    }

    @Override
    public Payload<List<SalePickGoodsDetailInfoResponseDTO>> searchSkuDetailList(@PathVariable Long salePickGoodsId) throws Exception {
        List<SalePickGoodsDetailInfoResponseDTO> result=  salePickGoodsInfoService.searchSkuDetailList(salePickGoodsId);
        if(CollectionUtil.isNotEmpty(result)){
            for(SalePickGoodsDetailInfoResponseDTO responseDTO:result){
                if(CollectionUtil.isNotEmpty(responseDTO.getSalePickGoodsItemInfoList())){
                    for(SalePickGoodsItemInfoResponseDTO item:responseDTO.getSalePickGoodsItemInfoList()){
                        item.setDeliveryQuantityed(getLong(item.getDeliveryQuantity())-getLong(item.getWaitSendNum()));
                    }
                }
            }
        }
        return new Payload<>(result);
    }

    @Override
    public Payload<PickGoodStoreHouseResponseDTO> getStorehouseList(@RequestBody PickGoodStoreHouseReq dto) throws Exception {
        return new Payload<>(salePickGoodsInfoService.getStorehouseList(dto));
    }


}
