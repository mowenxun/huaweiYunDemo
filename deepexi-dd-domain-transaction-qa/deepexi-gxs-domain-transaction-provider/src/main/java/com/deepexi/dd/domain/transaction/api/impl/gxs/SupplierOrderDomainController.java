package com.deepexi.dd.domain.transaction.api.impl.gxs;

import com.deepexi.clientiam.model.GroupResultVO;
import com.deepexi.dd.domain.common.extension.AppRuntimeEnv;
import com.deepexi.dd.domain.transaction.api.gxs.SupplierOrderDomainApi;
import com.deepexi.dd.domain.transaction.api.gxs.domain.*;
import com.deepexi.dd.domain.transaction.remote.gxs.PayVoucherRemote;
import com.deepexi.dd.domain.transaction.remote.gxs.ShopSupplerRelationRemote;
import com.deepexi.dd.domain.transaction.remote.gxs.SupplierOrderDomainRemote;
import com.deepexi.dd.domain.transaction.service.SendGoodsInfoService;
import com.deepexi.dd.domain.transaction.service.SupplierOrderService;
import com.deepexi.dd.domain.transaction.service.common.CommonService;
import com.deepexi.dd.domain.transaction.util.ServiceUtil;
import com.deepexi.dd.middle.order.domain.dto.PayVoucherRequestDTO;
import com.deepexi.dd.middle.order.domain.dto.PayVoucherResponseDTO;
import com.deepexi.dd.middle.order.domain.query.SupplerOrderRequestQuery;
import com.deepexi.util.pageHelper.PageBean;
import com.deepexi.util.pojo.ObjectCloneUtils;
import domain.dto.CompanyInfoResponseApiDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 供销社，供应商端 订单controller
 *
 * @author huanghuai
 * @date 2020/10/13
 */
@Slf4j
@RestController
@Api(value = "SupplierOrderController", tags = "供销社-供应商端-订单管理")
public class SupplierOrderDomainController implements SupplierOrderDomainApi {

    @Autowired
    ShopSupplerRelationRemote shopSupplerRelationRemote;
    @Autowired
    SupplierOrderDomainRemote supplierOrderDomainRemote;
    @Autowired
    SupplierOrderService supplierOrderService;
    @Autowired
    SendGoodsInfoService sendGoodsInfoService;
    @Autowired
    PayVoucherRemote payVoucherRemote;
    @Autowired
    AppRuntimeEnv appRuntimeEnv;
    @Autowired
    CommonService commonService;

    /**
     * 供应商订单列表展示,查询
     */
    @GetMapping("/listSupplierOrders")
    @ApiOperation(value = "供应商订单列表查询展示", notes = "供应商订单列表查询展示")
    public PageBean<ListSupplierOrderResponseDTO> listSupplierOrdersPage(ListSupplierOrderRequestDTO vo) {
        GroupResultVO org = appRuntimeEnv.getUserOrganization();
        CompanyInfoResponseApiDTO sellerInfo = commonService.queryCurrentLoginSeller(org.getId());
        ServiceUtil.assertNull(sellerInfo, "当前登录组织id：" + org.getId() + " 没有查询到供应商信息");
        SupplerOrderRequestQuery clone = vo.clone(SupplerOrderRequestQuery.class, 1);
        clone.setSellerId(sellerInfo.getId());
        return supplierOrderService.listSupplerOrdersPage(clone);
    }

    /**
     * 供应商接单, 将订单状态由 ORDER_TO_BE_RECEIVED -->> TO_BE_DELIVERED  2.门店订单 改状态 待发货
     */
    @GetMapping("/receiveOrder/{id}")
    @ApiOperation(value = "供应商接单", notes = "供应商接单")
    public Boolean receiveOrder(@PathVariable("id") Long id) {
        return supplierOrderService.receiveOrder(id);
    }

    /**
     * 供应商拒单, 将订单状态改为 REJECTED
     */
    @GetMapping("/rejectOrder/{id}")
    @ApiOperation(value = "供应商拒单", notes = "供应商拒单")
    public Boolean rejectOrder(@PathVariable("id") Long id,
                               @ApiParam(value = "拒单原因", required = true) @RequestParam("reason") String reason) {
        return supplierOrderService.rejectOrder(id, reason);
    }

    /**
     * 查看供应商订单详情
     */
    @GetMapping("/orderDetails/{id}")
    @ApiOperation(value = "通过订单id查看供应商订单详情", notes = "通过订单id查看供应商订单详情")
    public SupplierOrderDetailResponseDTO orderDetailsById(@PathVariable("id") Long id) {
        SupplierOrderDetailDTO dto = supplierOrderService.orderDetailsById(id);
        return dto == null ? null : dto.clone(SupplierOrderDetailResponseDTO.class, 1);
    }
    @GetMapping("/orderDetailsByCode/{code}")
    @ApiOperation(value = "通过订单code查看供应商订单详情", notes = "通过订单code查看供应商订单详情")
    @Override
    public SupplierOrderDetailResponseDTO orderDetailsByCode(@PathVariable("code")String code) {
        SupplierOrderDetailDTO dto = supplierOrderService.orderDetailsByCode(code);
        return dto == null ? null : dto.clone(SupplierOrderDetailResponseDTO.class, 1);
    }

    /**
     * 订单发货
     */
    @PostMapping("/deliverGoods")
    @ApiOperation(value = "订单发货", notes = "订单发货")
    public Boolean deliverGoods(@RequestBody @Valid SupplierDeliverGoodsRequestDTO vo) {
        return sendGoodsInfoService.deliverGoods(vo);
    }

    /**
     * 查看付款凭证
     */
    @GetMapping("/queryPayVoucher/{id}")
    @ApiOperation(value = "查看付款凭证", notes = "查看付款凭证")
    public QueryPayVoucherResponseDTO queryPayVoucher(
            @ApiParam(name = "id", value = "供应商订单主键id") @PathVariable("id") Long id) {
        PayVoucherRequestDTO query = new PayVoucherRequestDTO();
        query.setOrderId(id);
        List<PayVoucherResponseDTO> dtos = payVoucherRemote.list(query);
        if (dtos.isEmpty()) {
            return null;
        }

        QueryPayVoucherResponseDTO responseVO = dtos.get(0).clone(QueryPayVoucherResponseDTO.class, 1);
        responseVO.setPayVouchers(ObjectCloneUtils.convertList(dtos, QueryPayVoucherResponseDTO.PayVoucher.class, 1));
        return responseVO;
    }


}
