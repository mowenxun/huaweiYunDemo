package com.deepexi.dd.domain.transaction.api.impl.gxs;/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: mowenxun
 * @Date: 2020/10/19/20:37
 * @Description:
 */

import com.deepexi.dd.domain.transaction.api.gxs.ReceiptOrderApi;
import com.deepexi.dd.domain.transaction.domain.dto.SupplerOrderResponseDTO;
import com.deepexi.dd.domain.transaction.domain.query.SupplerOrderQuery;
import com.deepexi.dd.domain.transaction.domain.query.gxs.SupplerOrderDomainQuery;
import com.deepexi.dd.domain.transaction.domain.responseDto.gxs.SupplerOrderDomainResponseDTO;
import com.deepexi.dd.domain.transaction.enums.gxs.GxsSupplierOrderStatusEnum;
import com.deepexi.dd.domain.transaction.service.ShopOrderDomainService;
import com.deepexi.dd.domain.transaction.service.SupplierOrderService;
import com.deepexi.dd.domain.transaction.service.impl.commonServiceImpl;
import com.deepexi.dd.middle.order.domain.dto.SupplerOrderRequestDTO;
import com.deepexi.middle.merchant.domain.dto.GxsManagementResponseDTO;
import com.deepexi.util.pageHelper.PageBean;
import com.deepexi.util.pojo.ObjectCloneUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName ReceiptOrderApiImpl
 * @Description: TODO
 * @Author mowenxun
 * @Date 2020/10/19
 * @Version V1.0
 **/
@RestController
public class ReceiptOrderApiImpl implements ReceiptOrderApi {

    @Autowired
    private SupplierOrderService supplierOrderService;

    @Autowired
    private ShopOrderDomainService shopOrderDomainService;

    @Autowired
    private commonServiceImpl commonService;

    @Override
    public PageBean<SupplerOrderDomainResponseDTO> listPageSupplerOrderResponseDTO(SupplerOrderDomainQuery supplerOrderDomainQuery) throws Exception {
        SupplerOrderQuery supplerOrderQuery =
                supplerOrderDomainQuery.clone(SupplerOrderQuery.class);
        GxsManagementResponseDTO gxsManagementResponseDTO = commonService.getShop();
        supplerOrderQuery.setReceiveDistributionId(gxsManagementResponseDTO.getId());
        return ObjectCloneUtils.convertPageBean(supplierOrderService.listPageSupplerOrderResponseDTO(supplerOrderQuery),
                SupplerOrderDomainResponseDTO.class);
    }

    @Override
    public SupplerOrderDomainResponseDTO getSupplierDetails(@PathVariable Long id) throws Exception {
        SupplerOrderResponseDTO supplerOrderResponseDTO = supplierOrderService.getSupplierDetails(id);
        return supplerOrderResponseDTO.clone(SupplerOrderDomainResponseDTO.class);
    }

    @Override
    public Boolean signOrderById(@PathVariable Long id) throws Exception {
        SupplerOrderRequestDTO supplerOrderRequestDTO = new SupplerOrderRequestDTO();
        supplerOrderRequestDTO.setId(id);
        supplerOrderRequestDTO.setStatus(GxsSupplierOrderStatusEnum.SIGNED.getCode());
        if (supplierOrderService.updateById(supplerOrderRequestDTO)) {
            //同步更新对应的门店订单为代签收
            if (shopOrderDomainService.updateSign(id)) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}
