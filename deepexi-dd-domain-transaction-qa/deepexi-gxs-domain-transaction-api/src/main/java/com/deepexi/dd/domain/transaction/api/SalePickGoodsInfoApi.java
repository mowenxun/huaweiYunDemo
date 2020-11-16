package com.deepexi.dd.domain.transaction.api;

import com.deepexi.dd.domain.transaction.domain.dto.*;
import com.deepexi.dd.domain.transaction.domain.dto.examine.ExaminePickGoodsInfoRequestDTO;
import com.deepexi.dd.domain.transaction.domain.dto.examine.ExamineRejectInfoRequestDTO;
import com.deepexi.dd.domain.transaction.domain.dto.offlinePayInfo.OfflinePayInfoRequestDTO;
import com.deepexi.dd.domain.transaction.domain.dto.offlinePayInfo.SalePickOrderPayRequestDTO;
import com.deepexi.dd.domain.transaction.domain.dto.operation.center.SalePickGoodsInfoDetailResponseDTO;
import com.deepexi.dd.domain.transaction.domain.dto.operation.center.SalePickGoodsInfoOperationResponseDTO;
import com.deepexi.dd.domain.transaction.domain.dto.saleOutTask.SaleOutTaskSignRequestDTO;
import com.deepexi.dd.domain.transaction.domain.dto.salePickDelivery.SalePickDeliveryInfoRequestDTO;
import com.deepexi.dd.domain.transaction.domain.dto.salePickDelivery.SalePickGoodsDetailInfoResponseDTO;
import com.deepexi.dd.domain.transaction.domain.query.SalePickGoodsInfoReqtQuery;
import com.deepexi.dd.middle.order.domain.dto.SalePickGoodsOrderSkuResponseDTO;
import com.deepexi.dd.middle.order.domain.query.SalePickGoodsOrderSkuRequestQuery;
import com.deepexi.dd.middle.order.domain.dto.SalePickGoodsOrderSkuResponseDTO;
import com.deepexi.dd.middle.order.domain.query.SalePickGoodsOrderSkuRequestQuery;
import com.deepexi.dd.middle.order.domain.query.operation.center.SalePickGoodsInfoOperationRequestQuery;
import com.deepexi.util.config.Payload;
import com.deepexi.util.pageHelper.PageBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * SalePickGoodsInfoApi
 *
 * @author admin
 * @version 1.0
 * @date Wed Aug 12 19:18:27 CST 2020
 */
@Api(value = "提货API")
@RequestMapping("/salePickGoodsInfos")
public interface SalePickGoodsInfoApi {

    /**
     * 创建提货单据
     *
     * @param record
     * @return
     */
    @ApiOperation("创建提货并提交单据")
    @PostMapping("/createPickGoods")
    Payload<SalePickGoodsInfoResponseDTO> createPickGoods(@RequestBody SalePickGoodsPlanInfoRequestDTO record) throws Exception;

    @ApiOperation("分页查询提货单据订单列表")
    @PostMapping("/page")
    public Payload<PageBean<SalePickGoodsInfoResponseDTO>> listPage(@RequestBody SalePickGoodsInfoReqtQuery query) throws Exception;

    @ApiOperation("编辑并提交提货订单")
    @PostMapping("/editPickGoods")
    public Payload<SalePickGoodsInfoResponseDTO> editPickGoods(@RequestBody SalePickGoodsPlanInfoRequestDTO record) throws Exception;

    @ApiOperation("取消提货计划")
    @PostMapping("/cancelPickGoods")
    public Payload<Boolean> cancelPickGoods(@RequestBody SalePickGoodsPlanInfoRequestDTO record) throws Exception;


    @ApiOperation("关闭提货计划")
    @PostMapping("/closePickGoods")
    public Payload<Boolean> closePickGoods(@RequestBody SalePickGoodsPlanInfoRequestDTO record) throws Exception;

    /**
     * @Description: 提货单签收.
     * @Param: [record]
     * @return: com.deepexi.util.config.Payload<com.deepexi.dd.domain.transaction.domain.dto.SalePickGoodsInfoResponseDTO>
     * @Author: SongTao
     * @Date: 2020/8/19
     */
    @ApiOperation("签收")
    @PostMapping("/sign")
    Payload<Boolean> sign(@RequestBody SaleOutTaskSignRequestDTO dto) throws Exception;

    /**
     * 查看详情
     *
     * @param id
     * @return
     * @throws Exception
     */
    @ApiOperation("根据id获得详情")
    @PostMapping("/{id}")
    Payload<SalePickGoodsInfoDetailResponseDTO> getDetailById(@PathVariable Long id) throws Exception;

    /**
     * 根据提货单id获取与之关联的提货单sku
     */
    @ApiOperation("根据提货单id获取与之关联的提货单sku")
    @PostMapping("/getSkuByInfoId/{id}")
    Payload<SalePickGoodsInfoSkuDTO> getSkuByInfoId(@PathVariable Long id) throws Exception;

    @ApiOperation("线上订单支付(中台预下单)【提货单】")
    @PostMapping("/salePickPay")
    Payload<SaleOrderPayResponseDTO> salePickPaySaleOrder(@RequestBody @Valid SalePickOrderPayRequestDTO saleOrderPayRequestDTO) throws Exception;

    @ApiOperation("线下支付【提货单】")
    @PostMapping("/saveSalePickOfflinePayInfo")
    Payload<Boolean> saveSalePickOfflinePayInfo(@RequestBody @Valid OfflinePayInfoRequestDTO dto) throws Exception;

    @ApiOperation("支付成功页面【提货单】")
    @GetMapping("/salePickPaymentCompleted/{id}")
    Payload<PaymentCompletedResponseDTO> paymentSalePickInfo(@PathVariable("id") Long salePickId);

    @ApiOperation("通过订单Id查询订单的提货计划列表")
    @GetMapping("/searchPickGoodsList/{saleOrderId}")
    Payload<PageBean<SalePickGoodsOrderSkuResponseDTO>> searchPickGoodsList(@PathVariable Long saleOrderId,
                                                                            SalePickGoodsOrderSkuRequestQuery salePickGoodsOrderSkuRequestQuery) throws Exception;

    @ApiOperation("运营中心分页查询提货单据订单列表")
    @PostMapping("/operation/center/page")
    Payload<PageBean<SalePickGoodsInfoOperationResponseDTO>> listOperationPage(@RequestBody SalePickGoodsInfoOperationRequestQuery query) throws Exception;

    @ApiOperation("发货")
    @PostMapping("/pickPlanDeliveryGoods")
    Payload<Boolean> pickPlanDeliveryGoods(@RequestBody @Valid SalePickDeliveryInfoRequestDTO dto) throws Exception;

    @ApiOperation("审批通过")
    @PostMapping("/examinePass")
    Payload<Boolean> examinePass(@RequestBody @Valid ExaminePickGoodsInfoRequestDTO requestDTO) throws Exception;

    @ApiOperation("审批驳回")
    @PostMapping("/examineReject")
    Payload<Boolean> examineReject(@RequestBody @Valid ExamineRejectInfoRequestDTO requestDTO) throws Exception;

    @ApiOperation("当前账号是否需要获取全部产品线")
    @PostMapping("/judgeGetAll")
    Payload<Boolean> judgeGetAll() throws Exception;

    @ApiOperation("查看提货单的商品列表")
    @GetMapping("/searchSkuDetailList/{salePickGoodsId}")
    Payload<List<SalePickGoodsDetailInfoResponseDTO>> searchSkuDetailList(@PathVariable Long salePickGoodsId) throws Exception;

    @ApiOperation("获取提货单的仓库")
    @PostMapping("/getStorehouseList")
    public Payload<PickGoodStoreHouseResponseDTO> getStorehouseList(@RequestBody PickGoodStoreHouseReq dto) throws Exception;


}