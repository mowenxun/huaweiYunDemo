package com.deepexi.dd.system.mall.service.impl.gxs;/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: mowenxun
 * @Date: 2020/10/19/20:46
 * @Description:
 */

import com.deepexi.dd.domain.transaction.domain.query.gxs.SupplerOrderDomainQuery;
import com.deepexi.dd.domain.transaction.domain.responseDto.gxs.SupplerOrderDomainResponseDTO;
import com.deepexi.dd.system.mall.remote.shop.order.ReceiptOrderSysRemote;
import com.deepexi.dd.system.mall.service.gxs.ReceiptOrderSysService;
import com.deepexi.util.pageHelper.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName ReceiptOrderSysServiceImpl
 * @Description: TODO
 * @Author mowenxun
 * @Date 2020/10/19
 * @Version V1.0
 **/
@Service
public class ReceiptOrderSysServiceImpl implements ReceiptOrderSysService {

    @Autowired
    private ReceiptOrderSysRemote receiptOrderSysRemote;

    @Override
    public PageBean<SupplerOrderDomainResponseDTO> listPageSupplerOrderResponseDTO(SupplerOrderDomainQuery supplerOrderDomainQuery) throws Exception {
        return receiptOrderSysRemote.listPageSupplerOrderResponseDTO(supplerOrderDomainQuery);
    }

    @Override
    public SupplerOrderDomainResponseDTO getSupplierDetails(Long id) throws Exception {
        return receiptOrderSysRemote.getSupplierDetails(id);
    }

    @Override
    public Boolean signOrderById(Long id) throws Exception {
        return receiptOrderSysRemote.signOrderById(id);
    }
}
