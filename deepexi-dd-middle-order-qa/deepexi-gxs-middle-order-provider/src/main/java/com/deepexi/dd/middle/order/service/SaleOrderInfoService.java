package com.deepexi.dd.middle.order.service;

import com.deepexi.dd.middle.order.domain.dto.*;
import com.deepexi.dd.middle.order.domain.query.SaleOrderInfoQuery;
import com.deepexi.dd.middle.order.domain.query.SaleOrderInfoResQuery;
import com.deepexi.util.pageHelper.PageBean;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * SaleOrderInfoService
 *
 * @author admin
 * @version 1.0
 * @date Tue Jun 30 11:43:59 CST 2020
 */
public interface SaleOrderInfoService {


    /**
     * 创建销售订单
     *
     * @param saleOrder
     * @return
     */
    public SaleOrderInfoDTO createOrder(SaleOrderInfoAddRequestDTO saleOrder);

    /**
     * 创建销售订单批量
     *
     * @param saleOrder
     * @return
     */
    List<SaleOrderInfoDTO> createOrders(List<SaleOrderInfoAddRequestDTO> saleOrder);

    /**
     * 查询销售订单表列表
     *
     * @return
     */
    List<SaleOrderInfoDTO> listSaleOrderInfos(SaleOrderInfoQuery query);

    /**
     * 查询销售订单表列表(根据订单ids)
     *
     * @return
     */
    List<SaleOrderInfoDTO> listSaleOrderInfosByIds(List<Long> ids);


    /**
     * 分页查询销售订单表列表
     *
     * @return
     */
    PageBean<SaleOrderInfoResponseDTO> listSaleOrderInfosPage(SaleOrderInfoQuery query);


    PageBean<SaleOrderInfoOLResponseDTO> listSaleOrdersPage(SaleOrderInfoQuery query);

    /**
     * 根据ID删除销售订单表
     *
     * @param id
     * @return
     */
    Boolean deleteByIdIn(List<Long> id);

    /**
     * 新增销售订单表
     *
     * @param record
     * @return
     */
    com.deepexi.dd.middle.order.domain.dto.SaleOrderInfoDTO insert(com.deepexi.dd.middle.order.domain.dto.SaleOrderInfoDTO record);

    /**
     * 查询销售订单表详情
     *
     * @param id
     * @return
     */
    SaleOrderInfoResponseDTO selectById(Long id);

    /**
     * @Description: 根据id和code查询订单信息.
     * @Param: [saleOrderInfoQuery]
     * @return: com.deepexi.dd.middle.order.domain.dto.SaleOrderInfoResponseDTO
     * @Author: SongTao
     * @Date: 2020/8/29
     */
    SaleOrderInfoResponseDTO searchSaleOrderInfoByQuery(SaleOrderInfoQuery saleOrderInfoQuery) throws Exception;

    SaleOrderResponseDTO selectSaleOrder(Long id);

    /**
     * 根据订单编号获取订单信息
     *
     * @param code
     * @return
     */
    SaleOrderResponseDTO getSaleOrderByCode(String code);

    /**
     * 根据父订单编号获取订单信息
     *
     * @param code
     * @return
     */
    List<SaleOrderResponseDTO> getSaleOrderByParentCode(String code);


    /**
     * 根据ID编辑订单信息
     *
     * @param id
     * @param record
     * @return
     */
    Boolean updateById(Long id, SaleOrderInfoAddRequestDTO record);


    /**
     * 根据ID修改主销售订单表
     *
     * @param id
     * @param record
     * @return
     */
    Boolean updateById(Long id, SaleOrderInfoRequestDTO record);

    /**
     * 根据ID修改销售订单表的支付金额
     *
     * @param record
     * @return
     */
    Boolean updatePayAmountById(SaleOrderInfoAddRequestDTO record);

    Boolean updateOrderStatus(SaleOrderInfoStatusEditRequestDTO saleOrderInfoStatusEditRequestDTO);

    /**
     * @Description: 根据订单ID集合查询销售订单表，用于出库单列表查询.
     * @Param: [idList]
     * @return: java.util.List<com.deepexi.dd.middle.order.domain.dto.SaleOrderInfoResponseDTO>
     * @Author: SongTao
     * @Date: 2020/7/6
     */
    List<SaleOrderInfoResponseDTO> searchSaleOrderInfoById(List<Long> idList);

    /**
     * @Description: 根据用户拿到批量订单ID，用于出库单查询.
     * @Param: [buyerName]
     * @return: java.util.List<java.lang.Long>
     * @Author: SongTao
     * @Date: 2020/7/6
     */
    List<Long> getSaleOrderIdListByBuyerName(String buyerName);

    /**
     * @Description: 订单发货.
     * @Param: [requestDTO]
     * @return: java.lang.Boolean
     * @Author: SongTao
     * @Date: 2020/7/15
     */
    Boolean deliveryGoodsOrder(SaleOrderInfoDeliverGoodsMiddleRequestDTO requestDTO) throws Exception;

    /**
     * 更新订单金额和状态
     *
     * @param saleOrderInfoAmountEditDTO
     * @return
     */
    Boolean updateOrderAmount(@RequestBody SaleOrderInfoAmountEditDTO saleOrderInfoAmountEditDTO);

    /**
     * @Description: 根据批量的订单编号查订单信息.
     * @Param: [saleOrderIdList]
     * @return: com.deepexi.util.config.Payload<java.util.List < com.deepexi.dd.middle.order.domain.dto.SaleOrderInfoResponseDTO>>
     * @Author: SongTao
     * @Date: 2020/8/20
     */
    List<SaleOrderInfoResponseDTO> batchSearchSaleOrderInfo(List<String> saleOrderCodeList);

    /**
     * @Description: 根据批量订单id修改.
     * @Param: [list]
     * @return: java.lang.Boolean
     * @Author: SongTao
     * @Date: 2020/8/20
     */
    Boolean updateBatchById(List<SaleOrderInfoRequestDTO> list);


    /**
     * 通过id和code 查询订单
     *
     * @param id
     * @param code
     * @return
     */
    SaleOrderInfoDTO selectSaleOrder(Long id, String code);

    /**
     * 更新支付记录订单编号
     *
     * @param saleOrderInfoPayOrderCodeEditDTO
     * @return
     */
    Boolean updatePayOrderCode(@RequestBody SaleOrderInfoPayOrderCodeEditDTO saleOrderInfoPayOrderCodeEditDTO);

    /**
     * 合同, 项目专用查询列表
     *
     * @param query
     * @return
     */
    List<SaleOrderInfoResponseDTO> findList(SaleOrderInfoResQuery query);

    Boolean closePreMonthPlanOrder();
}