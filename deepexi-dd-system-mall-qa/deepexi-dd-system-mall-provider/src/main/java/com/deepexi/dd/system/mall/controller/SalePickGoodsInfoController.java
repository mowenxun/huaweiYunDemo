package com.deepexi.dd.system.mall.controller;

import com.deepexi.dd.domain.common.util.CommonUtils;
import com.deepexi.dd.domain.transaction.domain.dto.PickGoodStoreHouseReq;
import com.deepexi.dd.domain.transaction.domain.dto.PickGoodStoreHouseResponseDTO;
import com.deepexi.dd.domain.transaction.domain.dto.SalePickGoodsInfoResponseDTO;
import com.deepexi.dd.domain.transaction.domain.dto.SalePickGoodsPlanInfoRequestDTO;
import com.deepexi.dd.domain.transaction.domain.dto.examine.ExaminePickGoodsInfoRequestDTO;
import com.deepexi.dd.domain.transaction.domain.dto.examine.ExamineRejectInfoRequestDTO;
import com.deepexi.dd.domain.transaction.domain.dto.operation.center.SalePickGoodsInfoDetailResponseDTO;
import com.deepexi.dd.domain.transaction.domain.dto.salePickDelivery.SalePickDeliveryInfoRequestDTO;
import com.deepexi.dd.domain.transaction.domain.dto.operation.center.SalePickGoodsInfoOperationResponseDTO;
import com.deepexi.dd.domain.transaction.domain.dto.salePickDelivery.SalePickDeliveryInfoRequestDTO;
import com.deepexi.dd.domain.transaction.domain.dto.salePickDelivery.SalePickGoodsDetailInfoResponseDTO;
import com.deepexi.dd.domain.transaction.domain.query.SalePickGoodsInfoReqtQuery;
import com.deepexi.dd.middle.order.domain.dto.SalePickGoodsOrderSkuResponseDTO;
import com.deepexi.dd.middle.order.domain.query.SalePickGoodsInfoRequestQuery;
import com.deepexi.dd.middle.order.domain.query.SalePickGoodsOrderSkuRequestQuery;
import com.deepexi.dd.middle.order.domain.query.operation.center.SalePickGoodsInfoOperationRequestQuery;
import com.deepexi.dd.middle.order.domain.query.SalePickGoodsOrderSkuRequestQuery;
import com.deepexi.dd.system.mall.domain.dto.SaleOutTaskInfoSignRequestDTO;
import com.deepexi.dd.system.mall.domain.dto.saleorder.ExaminePickGoodsInfoAdminRequestDTO;
import com.deepexi.dd.system.mall.domain.dto.saleorder.RejectPickGoodsInfoAdminRequestDTO;
import com.deepexi.dd.system.mall.domain.dto.saleorder.SalePickDeliveryInfoAdminRequestDTO;
import com.deepexi.dd.system.mall.domain.query.saleorder.SalePickGoodsInfoOperationAdminReqeustQuery;
import com.deepexi.dd.system.mall.domain.vo.pickorder.SalePickGoodsOrderGroupResponesDTO;
import com.deepexi.dd.system.mall.service.SalePickGoodsInfoService;
import com.deepexi.dd.system.mall.util.GeneralConvertUtils;
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
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * @Description : 提货单据
 * @Author : wujinhua
 */
@RestController
@Api(tags = "提货计划管理")
@RequestMapping("admin-api/v1/domain/transaction/salePickGoodsInfos")
public class SalePickGoodsInfoController {

    @Autowired
    private SalePickGoodsInfoService salePickGoodsInfoService;

    @ApiOperation("新增提货单据表管理以及明细")
    @PostMapping("/createPickGoods")
    public Payload<SalePickGoodsInfoResponseDTO> createPickGoods(@RequestBody @Valid SalePickGoodsPlanInfoRequestDTO record) throws Exception {
        Payload<SalePickGoodsInfoResponseDTO> payload = salePickGoodsInfoService.createPickGoods(record);
        return payload;
    }


    @ApiOperation("分页查询提货单据订单列表")
    @PostMapping("/page")
    public Payload<PageBean<SalePickGoodsInfoResponseDTO>> listPage(@RequestBody SalePickGoodsInfoReqtQuery query) throws Exception {
        Payload<PageBean<SalePickGoodsInfoResponseDTO>> payload = salePickGoodsInfoService.listPage(query);
        return payload;
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
        Payload<Boolean> payload = salePickGoodsInfoService.closePickGoods(record);
        return payload;
    }


    @ApiOperation("签收")
    @PostMapping("/sign")
    public Payload<Boolean> sign(@RequestBody @Valid SaleOutTaskInfoSignRequestDTO dto) throws Exception {
        return new Payload<>(salePickGoodsInfoService.sign(dto));
    }

    @ApiOperation("发货")
    @PostMapping("/pickPlanDeliveryGoods")
    public Payload<Boolean> pickPlanDeliveryGoods(@RequestBody @Valid SalePickDeliveryInfoRequestDTO dto) throws Exception {
        return new Payload<>(salePickGoodsInfoService.pickPlanDeliveryGoods(dto));
    }

    @ApiOperation("根据订单获取提货单详情")
    @GetMapping("/{id}")
    public Payload<SalePickGoodsInfoDetailResponseDTO> getDetailById(@PathVariable Long id) throws Exception {
        return new Payload<>(salePickGoodsInfoService.getDetailById(id));
    }

    @ApiOperation("通过订单Id查询订单的提货计划列表")
    @GetMapping("/getPickGoodsGroupList/{saleOrderId}")
    public Payload<List<SalePickGoodsOrderGroupResponesDTO>> searchPickGoodsGroupList(@PathVariable Long saleOrderId,
                                                                                      SalePickGoodsOrderSkuRequestQuery salePickGoodsOrderSkuRequestQuery) throws Exception {
        salePickGoodsOrderSkuRequestQuery.setSize(899);
        Payload<PageBean<SalePickGoodsOrderSkuResponseDTO>> pageBeanPayload =
                salePickGoodsInfoService.searchPickGoodsList(saleOrderId,
                        salePickGoodsOrderSkuRequestQuery);
        PageBean<SalePickGoodsOrderSkuResponseDTO>listPage =
                GeneralConvertUtils.convert2PageBean(pageBeanPayload.getPayload(),
                        SalePickGoodsOrderSkuResponseDTO.class);
        if (CollectionUtil.isEmpty(listPage.getContent())) {
            new Payload<>(new ArrayList());
        }

        List<SalePickGoodsOrderGroupResponesDTO> result = new ArrayList<>();

        Map<String, List<SalePickGoodsOrderSkuResponseDTO>> map =
                listPage.getContent().stream().collect(Collectors.groupingBy(SalePickGoodsOrderSkuResponseDTO::getPickGoodsCode));

        for (Map.Entry<String, List<SalePickGoodsOrderSkuResponseDTO>> item : map.entrySet()) {
            SalePickGoodsOrderGroupResponesDTO dto = new SalePickGoodsOrderGroupResponesDTO();
            dto.setPickGoodsCode(item.getKey());
            dto.setCreatedTime(item.getValue().get(0).getCreatedTime());
            dto.setItems(item.getValue());
            result.add(dto);
        }

        return new Payload<>(result);
    }

    @ApiOperation("运营中心分页查询提货单据订单列表")
    @GetMapping("/operation/center/page")
    public Payload<PageBean<SalePickGoodsInfoOperationResponseDTO>> listOperationPage(SalePickGoodsInfoOperationAdminReqeustQuery query) throws Exception {
        if (CommonUtils.isNotEmpty(query.getExceptStatus())) {
            query.setExceptStatusList(Arrays.asList(query.getExceptStatus().split(",")).stream().map(Integer::valueOf).collect(Collectors.toList()));
            query.setExceptStatus(null);
        }
        PageBean<SalePickGoodsInfoOperationResponseDTO> pageBean = salePickGoodsInfoService.listOperationPage(query.clone(SalePickGoodsInfoOperationRequestQuery.class));
        return new Payload<>(pageBean);
    }

    @ApiOperation("审批通过")
    @PostMapping("/examinePass")
    public Payload<Boolean> examinePass(@RequestBody @Valid ExaminePickGoodsInfoAdminRequestDTO requestDTO) throws Exception {
        return new Payload<>(salePickGoodsInfoService.examinePass(requestDTO.clone(ExaminePickGoodsInfoRequestDTO.class)));
    }

    @ApiOperation("审批驳回")
    @PostMapping("/examineReject")
    public Payload<Boolean> examineReject(@RequestBody @Valid RejectPickGoodsInfoAdminRequestDTO requestDTO) throws Exception {
        return new Payload<>(salePickGoodsInfoService.examineReject(requestDTO.clone(ExamineRejectInfoRequestDTO.class)));
    }

    /**
     * 用于临时判断当前登陆账号是否能获取所有产品线
     * @return
     * @throws Exception
     */
    @ApiOperation("临时判断当前登陆账号是否能获取所有产品线")
    @GetMapping("/judgeGetAll")
    public Payload<Boolean> judgeGetAll() throws Exception {
        return salePickGoodsInfoService.judgeGetAll();
    }

    @ApiOperation("查看提货单的商品列表")
    @GetMapping("/searchSkuDetailList/{salePickGoodsId}")
    public Payload<List<SalePickGoodsDetailInfoResponseDTO>> searchSkuDetailList(@PathVariable Long salePickGoodsId) throws Exception {
        return new Payload<>(salePickGoodsInfoService.searchSkuDetailList(salePickGoodsId));
    }

    @ApiOperation("获取仓库列表")
    @PostMapping("/getStorehouseList")
    public Payload<PickGoodStoreHouseResponseDTO> getStorehouseList(@RequestBody PickGoodStoreHouseReq dto) throws Exception {
        return salePickGoodsInfoService.getStorehouseList(dto);
    }
}
