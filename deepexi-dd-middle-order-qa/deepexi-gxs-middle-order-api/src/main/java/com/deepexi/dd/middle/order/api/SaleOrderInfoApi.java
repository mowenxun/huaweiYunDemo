package com.deepexi.dd.middle.order.api;

import com.deepexi.dd.middle.order.domain.dto.*;
import com.deepexi.dd.middle.order.domain.query.SaleOrderInfoRequestQuery;
import com.deepexi.dd.middle.order.domain.query.SaleOrderInfoResQuery;
import com.deepexi.util.config.Payload;
import com.deepexi.util.pageHelper.PageBean;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * SaleOrderInfoApi
 *
 * @author admin
 * @version 1.0
 * @date Tue Jun 30 11:43:59 CST 2020
 */
@Api(value = "销售订单表管理")
@RequestMapping("/middle/v1/saleOrderInfos")
public interface SaleOrderInfoApi {

    /**
     * 合同, 项目专用查询列表
     *
     * @return
     */
    @PostMapping("/findList")
    Payload<List<SaleOrderInfoResponseDTO>> findList(@RequestBody SaleOrderInfoResQuery query);

    /**
     * 查询销售订单表列表
     *
     * @return
     */
    @GetMapping("/list")
    Payload<List<SaleOrderInfoResponseDTO>> listSaleOrderInfos(SaleOrderInfoRequestQuery query);

    /**
     * 查询销售订单表列表 (根据订单IDs)
     *
     * @return
     */
    @PostMapping("/listByIds")
    Payload<List<SaleOrderInfoResponseDTO>> listSaleOrderInfosByIds(@RequestBody List<Long> ids);

    /**
     * 分页查询销售订单表列表
     *
     * @return
     */
    @GetMapping("/page")
    Payload<PageBean<SaleOrderInfoResponseDTO>> listSaleOrderInfosPage(@Valid SaleOrderInfoRequestQuery query);

    /**
     * 分页查询销售订单表列表（只返回订单主表和明细）
     *
     * @return
     */
    @GetMapping("/pageAndItem")
    Payload<PageBean<SaleOrderInfoOLResponseDTO>> listSaleOrdersPage(@Valid SaleOrderInfoRequestQuery query);

    /**
     * 根据ID删除销售订单表
     *
     * @param id
     * @return
     */
    @DeleteMapping("")
    Payload<Boolean> deleteByIdIn(@RequestBody List<Long> id);

    /**
     * 新增销售订单表
     *
     * @param record
     * @return
     */
    @PostMapping("")
    Payload<SaleOrderInfoResponseDTO> insert(@RequestBody SaleOrderInfoAddRequestDTO record);

    /**
     * 新增销售订单批量
     *
     * @param record
     * @return
     */
    @PostMapping("/adds")
    Payload<List<SaleOrderInfoResponseDTO>> inserts(@RequestBody List<SaleOrderInfoAddRequestDTO> record);

    /**
     * 查询销售订单表详情
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    Payload<SaleOrderInfoResponseDTO> selectById(@PathVariable Long id);

    /**
     * 查询销售订单表详情(只返回订单表信息)
     *
     * @param id
     * @return
     */
    @GetMapping("/getSaleOrder/{id}")
    Payload<SaleOrderResponseDTO> selectSaleOrder(@PathVariable Long id);

    /**
     * 查询销售订单表详情(只返回订单表信息)
     *
     * @param code 订单编号
     * @return
     */
    @GetMapping("/getSaleOrderByCode/{code}")
    Payload<SaleOrderResponseDTO> selectSaleOrder(@PathVariable String code);

    /**
     * 根据父订单编号查询销售订单表详情(只返回订单表信息)
     *
     * @param code 订单编号
     * @return
     */
    @GetMapping("/getSaleOrderByParentCode/{code}")
    Payload<List<SaleOrderResponseDTO>> selectSaleOrderByParentCode(@PathVariable String code);

    /**
     * 编辑订单
     *
     * @param id
     * @param record
     * @return
     */
    @PostMapping("/{id}")
    Payload<Boolean> updateById(@PathVariable Long id, @RequestBody SaleOrderInfoAddRequestDTO record);

    /**
     * 根据ID修改销售主订单
     *
     * @param id
     * @param record
     * @return
     */
    @PostMapping("/updateMainOrder/{id}")
    Payload<Boolean> updateMainOrderById(@PathVariable Long id, @RequestBody SaleOrderInfoRequestDTO record);

    @PutMapping("/updatePayAmount")
    Payload<Boolean> updatePayAmountById(@RequestBody SaleOrderInfoAddRequestDTO record);

    @PutMapping("/updateOrderStatus")
    Payload<Boolean> updateOrderStatus(@RequestBody SaleOrderInfoStatusEditRequestDTO saleOrderInfoStatusEditRequestDTO);

    /**
     * @Description: 订单发货.
     * @Param: [requestDTO]
     * @return: com.deepexi.util.config.Payload<java.lang.Boolean>
     * @Author: SongTao
     * @Date: 2020/7/15
     */
    @PostMapping("/deliveryGoodsOrder")
    Payload<Boolean> deliveryGoodsOrder(@RequestBody SaleOrderInfoDeliverGoodsMiddleRequestDTO requestDTO) throws Exception;

    /**
     * 订单金额更新
     *
     * @param saleOrderInfoAmountEditDTO
     * @return
     */
    @PostMapping("/updateOrderAmount")
    Payload<Boolean> updateOrderAmount(@RequestBody SaleOrderInfoAmountEditDTO saleOrderInfoAmountEditDTO);

    /**
     * 更新支付记录订单编号
     *
     * @param saleOrderInfoPayOrderCodeEditDTO
     * @return
     */
    @PostMapping("/updatePayOrderCode")
    Payload<Boolean> updatePayOrderCode(@RequestBody SaleOrderInfoPayOrderCodeEditDTO saleOrderInfoPayOrderCodeEditDTO);

    /**
     * @Description: 根据批量的订单编号查订单信息.
     * @Param: [saleOrderIdList]
     * @return: com.deepexi.util.config.Payload<java.util.List < com.deepexi.dd.middle.order.domain.dto.SaleOrderInfoResponseDTO>>
     * @Author: SongTao
     * @Date: 2020/8/20
     */
    @PostMapping("/batchSearchSaleOrderInfo")
    Payload<List<SaleOrderInfoResponseDTO>> batchSearchSaleOrderInfo(@RequestBody List<String> saleOrderCodeList) throws Exception;

    /**
     * @Description: 根据批量订单id修改.
     * @Param: [list]
     * @return: com.deepexi.util.config.Payload<java.lang.Boolean>
     * @Author: SongTao
     * @Date: 2020/8/20
     */
    @PostMapping("/updateBatchById")
    Payload<Boolean> updateBatchById(@RequestBody List<SaleOrderInfoRequestDTO> list) throws Exception;

    @PostMapping("/selectSaleOrderByIdAndCode")
    Payload<SaleOrderResponseDTO> selectSaleOrder(@RequestBody SaleOrderInfoRequestQuery query);

    /**
     * @Description: 根据id和code查询订单信息.
     * @Param: [saleOrderInfoRequestQuery]
     * @return: com.deepexi.util.config.Payload<com.deepexi.dd.middle.order.domain.dto.SaleOrderInfoResponseDTO>
     * @Author: SongTao
     * @Date: 2020/8/29
     */
    @PostMapping("/searchSaleOrderInfoByQuery")
    Payload<SaleOrderInfoResponseDTO> searchSaleOrderInfoByQuery(@RequestBody SaleOrderInfoRequestQuery saleOrderInfoRequestQuery) throws Exception;

    @PostMapping("/closePreMonthPlanOrder")
    Payload<Boolean> closePreMonthPlanOrder();
}