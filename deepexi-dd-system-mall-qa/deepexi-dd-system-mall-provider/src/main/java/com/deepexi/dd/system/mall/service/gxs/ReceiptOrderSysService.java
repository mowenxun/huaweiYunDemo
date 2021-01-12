package com.deepexi.dd.system.mall.service.gxs;

import com.deepexi.dd.domain.transaction.domain.query.gxs.SupplerOrderDomainQuery;
import com.deepexi.dd.domain.transaction.domain.responseDto.gxs.SupplerOrderDomainResponseDTO;
import com.deepexi.util.pageHelper.PageBean;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: mowenxun
 * @Date: 2020/10/19/20:45
 * @Description:
 */
public interface ReceiptOrderSysService {

    PageBean<SupplerOrderDomainResponseDTO> listPageSupplerOrderResponseDTO(SupplerOrderDomainQuery supplerOrderDomainQuery) throws Exception;

    /**
     * 已分发订单详情
     * @param id
     * @return
     */
    SupplerOrderDomainResponseDTO getSupplierDetails(Long id) throws Exception;

    /**
     * 签收
     * @param id
     * @return
     * @throws Exception
     */
    Boolean signOrderById(Long id) throws Exception;

}
