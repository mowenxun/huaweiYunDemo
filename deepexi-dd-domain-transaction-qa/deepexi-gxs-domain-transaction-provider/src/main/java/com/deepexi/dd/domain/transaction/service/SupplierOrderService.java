package com.deepexi.dd.domain.transaction.service;

/**
 * @author zhangjian
 * @version 1.0
 * @date 2020-10-15 14:43
 */

import com.deepexi.dd.domain.transaction.api.gxs.domain.ListSupplierOrderResponseDTO;
import com.deepexi.dd.domain.transaction.api.gxs.domain.SupplierOrderDetailDTO;
import com.deepexi.dd.domain.transaction.domain.dto.DistributionCheckResponseDTO;
import com.deepexi.dd.domain.transaction.domain.dto.PayVoucherRequestDTO;
import com.deepexi.dd.domain.transaction.domain.dto.SupplerOrderResponseDTO;
import com.deepexi.dd.domain.transaction.domain.query.SupplerOrderQuery;
import com.deepexi.dd.middle.order.domain.dto.PayVoucherResponseDTO;
import com.deepexi.dd.middle.order.domain.dto.SupplerOrderRequestDTO;
import com.deepexi.dd.middle.order.domain.query.SupplerOrderRequestQuery;
import com.deepexi.util.pageHelper.PageBean;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/***
 * 已分发订单业务接口
 */
public interface SupplierOrderService {

    /**
     * 分页查询已分发订单列表
     *
     * @param supplerOrderQuery
     * @return
     */
    PageBean<SupplerOrderResponseDTO> listPageSupplerOrderResponseDTO(SupplerOrderQuery supplerOrderQuery) throws Exception;

    /**
     * 查询已分发订单列表
     * @param supplerOrderQuery
     * @return
     * @throws Exception
     */
    List<SupplerOrderResponseDTO> listSupplerOrderResponseDTO(SupplerOrderQuery supplerOrderQuery) throws Exception;

    /**
     * 已分发订单详情
     * @param id
     * @return
     */
    SupplerOrderResponseDTO getSupplierDetails(Long id) throws Exception;

    /**
     * 上传支付凭证
     * @param requestDTO
     * @return
     */
    Boolean uploadPayVoucher(PayVoucherRequestDTO requestDTO);

    /**
     * 撤销分发
     * @return
     */
    Boolean revocationDistribution(Long id);

    /**
     * 根据ID删除已分发订单
     */
    Boolean deleteByIdIn(@RequestBody List<Long> id);

    /**
     * 新增已分发订单
     */
    SupplerOrderResponseDTO insert(@RequestBody SupplerOrderRequestDTO record);

    /**
     * 查询已分发订单详情
     */
    SupplerOrderResponseDTO selectById(@PathVariable Long id);


    /**
     * 根据ID修改已分发订单
     */
    Boolean updateById(@RequestBody SupplerOrderRequestDTO record);

    /**
     * 查询订单详情，包括商品item
     */
    SupplierOrderDetailDTO orderDetailsById(Long id);

    /**
     * 接单
     */
    boolean receiveOrder(Long id);

    /**
     * 拒单
     * @param id
     * @param reason
     * @return
     */
    boolean rejectOrder(Long id, String reason);

    PageBean<ListSupplierOrderResponseDTO> listSupplerOrdersPage(SupplerOrderRequestQuery clone);

    /**
     * 查看配货
     * @return
     */
    List<DistributionCheckResponseDTO> getDistributionCheck(SupplerOrderQuery supplerOrderQuery) throws Exception;

    /**
     * 查询付款凭证
     * @param requestDTO
     * @return
     */
    List<PayVoucherResponseDTO> getPayVoucher(PayVoucherRequestDTO requestDTO);

    SupplierOrderDetailDTO orderDetailsByCode(String code);

    boolean sendGoods(Long id);
}
