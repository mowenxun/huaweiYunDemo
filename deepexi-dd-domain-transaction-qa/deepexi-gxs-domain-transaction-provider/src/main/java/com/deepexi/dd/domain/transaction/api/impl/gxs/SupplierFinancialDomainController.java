package com.deepexi.dd.domain.transaction.api.impl.gxs;

import cn.hutool.core.collection.CollUtil;
import com.deepexi.clientiam.model.GroupResultVO;
import com.deepexi.dd.domain.common.extension.AppRuntimeEnv;
import com.deepexi.dd.domain.transaction.api.gxs.SupplierFinancialDomainApi;
import com.deepexi.dd.domain.transaction.api.gxs.domain.CreatePayOrderItemRequestDTO;
import com.deepexi.dd.domain.transaction.api.gxs.domain.ListPayOrderRequestDTO;
import com.deepexi.dd.domain.transaction.api.gxs.domain.ListPayOrderResponseDTO;
import com.deepexi.dd.domain.transaction.api.gxs.domain.PayOrderDetailsResponseDTO;
import com.deepexi.dd.domain.transaction.remote.gxs.CompanyInfoDomainClient;
import com.deepexi.dd.domain.transaction.remote.gxs.SupplierOrderDomainRemote;
import com.deepexi.dd.domain.transaction.service.PayOrderDomainService;
import com.deepexi.dd.domain.transaction.service.PayOrderItemService;
import com.deepexi.dd.domain.transaction.service.common.CommonService;
import com.deepexi.dd.domain.transaction.util.ServiceUtil;
import com.deepexi.dd.middle.order.domain.dto.PayOrderItemRequestDTO;
import com.deepexi.dd.middle.order.domain.dto.PayOrderItemResponseDTO;
import com.deepexi.dd.middle.order.domain.dto.PayOrderResponseDTO;
import com.deepexi.dd.middle.order.domain.query.PayOrderRequestPageQuery;
import com.deepexi.dd.middle.order.domain.query.SupplerOrderRequestQuery;
import com.deepexi.util.StringUtil;
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
 * 供销社，供应商端 财务管理api
 *
 * @author huanghuai
 * @date 2020/10/14
 */
@Slf4j
@RestController
@Api(value = "SupplierFinancialDomainController", tags = "供销社-供应商端-财务管理api")
public class SupplierFinancialDomainController implements SupplierFinancialDomainApi {


    @Autowired
    PayOrderDomainService payOrderDomainService;
    @Autowired
    PayOrderItemService payOrderItemService;
    @Autowired
    AppRuntimeEnv appRuntimeEnv;
    @Autowired
    CommonService commonService;
    @Autowired
    CompanyInfoDomainClient companyInfoDomainClient;
    @Autowired
    SupplierOrderDomainRemote supplierOrderDomainRemote;

    /**
     * 查询应收单 ，发货之后才有应收单， 付了钱才会发货
     */
    @GetMapping("/listPayOrders")
    @ApiOperation(value = "应收单列表展示", notes = "应收单列表展示")
    public PageBean<ListPayOrderResponseDTO> listPayOrders(ListPayOrderRequestDTO vo) {
        PayOrderRequestPageQuery clone = vo.clone(PayOrderRequestPageQuery.class, 1);
        GroupResultVO org = appRuntimeEnv.getUserOrganization();
        CompanyInfoResponseApiDTO sellerInfo = commonService.queryCurrentLoginSeller(org.getId());
        ServiceUtil.assertNull(sellerInfo, "当前登录组织id：" + org.getId() + " 没有查询到供应商信息");
        clone.setSellerId(sellerInfo.getId());
        PageBean<PayOrderResponseDTO> listPage = payOrderDomainService.listPage(clone);
        PageBean<ListPayOrderResponseDTO> rs = ObjectCloneUtils.convertPageBean(listPage, ListPayOrderResponseDTO.class, 1);
        rs.getContent().forEach(e -> {
            String code = e.getSourceOrderCode();
            if (StringUtil.isNotEmpty(code)) {
                SupplerOrderRequestQuery q = new SupplerOrderRequestQuery();
                q.setOrderCode(code);
                List<com.deepexi.dd.middle.order.domain.dto.SupplerOrderResponseDTO> dtos = supplierOrderDomainRemote.listSupplerOrders(q);
                if (CollUtil.isNotEmpty(dtos)) {
                    e.setSourceOrderId(dtos.get(0).getId());
                }
            }
        });
        return rs;
    }

    /**
     * 供应商财务收款<br>
     * 1.创建收款单
     * 2.修改应收单的已收和待收金额
     */
    @PostMapping("/supplierCollection")
    @ApiOperation(value = "供应商财务收款", notes = "供应商财务收款")
    public Boolean supplierCollection(@RequestBody @Valid CreatePayOrderItemRequestDTO vo) {
        log.info("[{}][Supplier Collection]Input params:{}", appRuntimeEnv.getRequestId(), vo);
        payOrderItemService.insert(vo.clone(PayOrderItemRequestDTO.class, 1));
        return Boolean.TRUE;
    }


    /**
     * 查询应收单详情. <br>
     * 1.查应收单 pay_order
     * 2.查应收单item pay_order_item
     */
    @GetMapping("/payOrdersDetails/{id}")
    @ApiOperation(value = "查询应收单详情", notes = "查询应收单详情")
    public PayOrderDetailsResponseDTO payOrdersDetails(@ApiParam(value = "应收单的主键id", required = true) @PathVariable("id") Long id) {
        PayOrderResponseDTO payOrder = payOrderDomainService.selectById(id);
        if (payOrder == null) {
            return null;
        }
        // 付款单item
        PayOrderItemRequestDTO query = new PayOrderItemRequestDTO();
        query.setOrderId(id);
        List<PayOrderItemResponseDTO> items = payOrderItemService.list(query);

        PayOrderDetailsResponseDTO rs = payOrder.clone(PayOrderDetailsResponseDTO.class, 1);
        rs.setPayOrderItemInfos(ObjectCloneUtils.convertList(items, PayOrderDetailsResponseDTO.PayOrderItemInfo.class, 1));

        return rs;
    }

}
