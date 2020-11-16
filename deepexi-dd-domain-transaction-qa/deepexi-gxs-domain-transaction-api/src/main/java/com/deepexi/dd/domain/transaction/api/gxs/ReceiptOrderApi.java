package com.deepexi.dd.domain.transaction.api.gxs;/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: mowenxun
 * @Date: 2020/10/19/20:27
 * @Description:
 */

import com.deepexi.dd.domain.transaction.domain.query.gxs.SupplerOrderDomainQuery;
import com.deepexi.dd.domain.transaction.domain.responseDto.gxs.SupplerOrderDomainResponseDTO;
import com.deepexi.util.pageHelper.PageBean;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName ReceiptOrderApi
 * @Description: TODO
 * @Author mowenxun
 * @Date 2020/10/19
 * @Version V1.0
 **/
@Api(value = "门店订单-收货单")
@RequestMapping("/open-api/v1/sys/receipt/order")
public interface ReceiptOrderApi {

    /**
     * 分页查询已分发订单列表
     *
     * @param supplerOrderDomainQuery
     * @return
     */
    @GetMapping("/page")
    PageBean<SupplerOrderDomainResponseDTO> listPageSupplerOrderResponseDTO(SupplerOrderDomainQuery supplerOrderDomainQuery) throws Exception;

    /**
     * 已分发订单详情
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    SupplerOrderDomainResponseDTO getSupplierDetails(@PathVariable Long id) throws Exception;

    @GetMapping("/sign/{id}")
    Boolean signOrderById(@PathVariable Long id)throws Exception;
}
