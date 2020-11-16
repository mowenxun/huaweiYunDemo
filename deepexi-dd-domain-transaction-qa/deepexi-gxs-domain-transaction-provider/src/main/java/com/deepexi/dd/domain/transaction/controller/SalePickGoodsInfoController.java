package com.deepexi.dd.domain.transaction.controller;


import com.deepexi.dd.domain.transaction.domain.dto.*;
import com.deepexi.dd.domain.transaction.domain.dto.examine.ExaminePickGoodsInfoRequestDTO;
import com.deepexi.dd.domain.transaction.domain.dto.examine.ExamineRejectInfoRequestDTO;
import com.deepexi.dd.domain.transaction.domain.dto.operation.center.SalePickGoodsInfoDetailResponseDTO;
import com.deepexi.dd.domain.transaction.domain.dto.operation.center.SalePickGoodsInfoOperationResponseDTO;
import com.deepexi.dd.domain.transaction.domain.dto.saleOutTask.SaleOutTaskSignRequestDTO;
import com.deepexi.dd.domain.transaction.domain.dto.salePickDelivery.SalePickDeliveryInfoRequestDTO;
import com.deepexi.dd.domain.transaction.domain.dto.salePickDelivery.SalePickGoodsDetailInfoResponseDTO;
import com.deepexi.dd.domain.transaction.mq.producter.PlanOrderSendMqMsg;
import com.deepexi.dd.domain.transaction.service.SalePickGoodsInfoService;
import com.deepexi.dd.domain.transaction.service.impl.send.PlanOrderSendServiceImpl;
import com.deepexi.dd.middle.order.domain.dto.SalePickGoodsOrderSkuResponseDTO;
import com.deepexi.dd.middle.order.domain.query.SalePickGoodsInfoRequestQuery;
import com.deepexi.dd.middle.order.domain.query.SalePickGoodsOrderSkuRequestQuery;
import com.deepexi.dd.middle.order.domain.query.operation.center.SalePickGoodsInfoOperationRequestQuery;
import com.deepexi.util.CollectionUtil;
import com.deepexi.util.config.Payload;
import com.deepexi.util.pageHelper.PageBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * @Description : 提货单据
 * @Author : wujinhua
 */
@RestController
@Api(tags = "提货计划")
@RequestMapping("admin-api/v1/domain/transaction/salePickGoodsInfos")
public class SalePickGoodsInfoController {

    @Autowired
    private SalePickGoodsInfoService salePickGoodsInfoService;

    @Autowired
    private PlanOrderSendServiceImpl planOrderSendService;

    @ApiOperation("新增提货单据表管理以及明细")
    @PostMapping("/createPickGoods")
    public Payload<SalePickGoodsInfoResponseDTO> createPickGoods(@RequestBody @Valid SalePickGoodsPlanInfoRequestDTO record) throws Exception {
        Payload<SalePickGoodsInfoResponseDTO> payload = salePickGoodsInfoService.createPickGoods(record);
        return payload;
    }

    @ApiOperation("在线订购分页查询提货单据订单列表")
    @PostMapping("/page")
    public Payload<PageBean<SalePickGoodsInfoResponseDTO>> listPage(@RequestBody SalePickGoodsInfoRequestQuery query) throws Exception {
        PageBean<SalePickGoodsInfoResponseDTO> pageBean = salePickGoodsInfoService.listPage(query);
        return new Payload<>(pageBean);
    }

    @ApiOperation("运营中心分页查询提货单据订单列表")
    @GetMapping("/operation/center/page")
    public Payload<PageBean<SalePickGoodsInfoOperationResponseDTO>> listOperationPage(SalePickGoodsInfoOperationRequestQuery query) throws Exception {
        PageBean<SalePickGoodsInfoOperationResponseDTO> pageBean = salePickGoodsInfoService.listOperationPage(query);
        return new Payload<>(pageBean);
    }

    @ApiOperation("修改提货计划")
    @PostMapping("/editPickGoods")
    public Payload<SalePickGoodsInfoResponseDTO> editPickGoods(@RequestBody SalePickGoodsPlanInfoRequestDTO record) throws Exception {
        Payload<SalePickGoodsInfoResponseDTO> payload = salePickGoodsInfoService.editPickGoods(record);
        return payload;
    }

    @ApiOperation("取消提货计划")
    @PostMapping("/cancelPickGoods")
    public Payload<Boolean> cancelPickGoods(@RequestBody SalePickGoodsPlanInfoRequestDTO record) throws Exception {
        Payload<Boolean> payload = salePickGoodsInfoService.cancelPickGoods(record);
        return payload;
    }

    @ApiOperation("关闭提货计划")
    @PostMapping("/closePickGoods")
    public Payload<Boolean> closePickGoods(@RequestBody SalePickGoodsPlanInfoRequestDTO record) throws Exception {
        Payload<Boolean> payload = salePickGoodsInfoService.cancelPickGoods(record);
        return payload;
    }


    @ApiOperation("通过订单Id查询订单的提货计划列表")
    @GetMapping("/searchPickGoodsList/{saleOrderId}")
    public Payload<PageBean<SalePickGoodsOrderSkuResponseDTO>> searchPickGoodsList(@PathVariable Long saleOrderId,
                                                                                   SalePickGoodsOrderSkuRequestQuery salePickGoodsOrderSkuRequestQuery) throws Exception {
        return new Payload<>(salePickGoodsInfoService.searchPickGoodsList(saleOrderId,
                salePickGoodsOrderSkuRequestQuery));
    }

    @ApiOperation("通过订单Id查询订单的提货计划列表")
    @GetMapping("/searchPickGoodsGroupList/{saleOrderId}")
    public Payload<List<SalePickGoodsOrderGroupResponesDTO>> searchPickGoodsGroupList(@PathVariable Long saleOrderId,
                                                                                   SalePickGoodsOrderSkuRequestQuery salePickGoodsOrderSkuRequestQuery) throws Exception {
        salePickGoodsOrderSkuRequestQuery.setSize(100);
        PageBean<SalePickGoodsOrderSkuResponseDTO> list= salePickGoodsInfoService.searchPickGoodsList(saleOrderId,
                salePickGoodsOrderSkuRequestQuery);

        if(CollectionUtil.isEmpty(list.getContent())){
            new Payload<>(new ArrayList());
        }

        List<SalePickGoodsOrderGroupResponesDTO> result=new ArrayList<>();

        Map<String,List<SalePickGoodsOrderSkuResponseDTO>> map= list.getContent().stream().collect(Collectors.groupingBy(SalePickGoodsOrderSkuResponseDTO::getPickGoodsCode));

        for(Map.Entry<String,List<SalePickGoodsOrderSkuResponseDTO>> item: map.entrySet()){
            SalePickGoodsOrderGroupResponesDTO dto=new SalePickGoodsOrderGroupResponesDTO();
            dto.setPickGoodsCode(item.getKey());
            dto.setCreatedTime(item.getValue().get(0).getCreatedTime());
            dto.setItems(item.getValue());
            result.add(dto);
        }

        return new Payload<>(result);
    }

    @ApiOperation("审批通过")
    @PostMapping("/examinePass")
    public Payload<Boolean> examinePass(@RequestBody @Valid ExaminePickGoodsInfoRequestDTO requestDTO) throws Exception {
        return new Payload<>(salePickGoodsInfoService.examinePass(requestDTO));
    }

    @ApiOperation("审批驳回")
    @PostMapping("/examineReject")
    public Payload<Boolean> examineReject(@RequestBody @Valid ExamineRejectInfoRequestDTO requestDTO) throws Exception {
        return new Payload<>(salePickGoodsInfoService.examineReject(requestDTO));
    }

    @ApiOperation("发货")
    @PostMapping("/pickPlanDeliveryGoods")
    public Payload<Boolean> pickPlanDeliveryGoods(@RequestBody @Valid SalePickDeliveryInfoRequestDTO dto) throws Exception {
        return new Payload<>(salePickGoodsInfoService.pickPlanDeliveryGoods(dto));
    }

    @ApiOperation("根据订单获取提货单详情")
    @GetMapping("/{id}")
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

    @ApiOperation("签收")
    @PostMapping("/sign")
    public Payload<Boolean> sign(@RequestBody @Valid SaleOutTaskSignRequestDTO dto) throws Exception {
        return new Payload<>(salePickGoodsInfoService.sign(dto));
    }
    @ApiOperation("查看提货单的商品列表")
    @GetMapping("/searchSkuDetailList/{salePickGoodsId}")
    public Payload<List<SalePickGoodsDetailInfoResponseDTO>> searchSkuDetailList(@PathVariable Long salePickGoodsId) throws Exception {
        return new Payload<>(salePickGoodsInfoService.searchSkuDetailList(salePickGoodsId));
    }

    @ApiOperation("云仓库订单新增测试")
    @PostMapping("/yunOrder")
    public Payload<Boolean> yunOrder(@RequestBody @Valid ExaminePickGoodsInfoRequestDTO requestDTO) throws Exception {
        planOrderSendService.sendMsg(requestDTO);
        return new Payload<>(true);
    }

    @ApiOperation("postman_云仓库订单新增测试_接口")
    @PostMapping("/postOrder")
    public Payload<Boolean>sendOrderMsg(@RequestBody PlanOrderSendMqMsg planOrderSendMqMsg) throws Exception {
        planOrderSendService.sendOrderByBody(planOrderSendMqMsg);
        return new Payload<>(true);
    }

    @ApiOperation("当前账号是否需要获取全部产品线")
    @PostMapping("/judgeGetAll")
    @Deprecated
    public Payload<Boolean> judgeGetAll() throws Exception {
        return new Payload<Boolean>(salePickGoodsInfoService.judgeGetAll());
    }


    @ApiOperation("根据产品线、供应商id、客户等等数据展示仓库列表")
    @PostMapping("/getStorehouseList")
    public Payload<PickGoodStoreHouseResponseDTO> getStorehouseList(@RequestBody PickGoodStoreHouseReq dto) throws Exception {
        return new Payload<PickGoodStoreHouseResponseDTO>(salePickGoodsInfoService.getStorehouseList(dto));
    }


    @ApiOperation("根据token返回当前appRuntimeEnv信息")
    @GetMapping("/getAppRuntimeEnvByToken")
    public Payload<AppRunEnvForPick> getAppRuntimeEnvByToken() throws Exception {
        return new Payload<AppRunEnvForPick>(salePickGoodsInfoService.getAppRuntimeEnvByToken());
    }

}
