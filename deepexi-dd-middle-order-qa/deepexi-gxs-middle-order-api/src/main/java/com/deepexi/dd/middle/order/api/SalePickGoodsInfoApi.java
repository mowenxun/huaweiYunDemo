package com.deepexi.dd.middle.order.api;

import com.deepexi.dd.middle.order.domain.dto.*;
import com.deepexi.dd.middle.order.domain.dto.SalePickGoodsInfoRequestDTO;
import com.deepexi.dd.middle.order.domain.dto.SalePickGoodsInfoResponseDTO;
import com.deepexi.dd.middle.order.domain.dto.SalePickGoodsOrderSkuResponseDTO;
import com.deepexi.dd.middle.order.domain.query.SalePickGoodsInfoRequestQuery;
import com.deepexi.util.config.Payload;
import com.deepexi.util.pageHelper.PageBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * SalePickGoodsInfoApi
 *
 * @author admin
 * @date Wed Aug 12 19:18:27 CST 2020
 * @version 1.0
 */
@Api(value = "提货单据表管理")
@RequestMapping("/middle/salePickGoodsInfos")
public interface SalePickGoodsInfoApi {
    /**
     * 查询提货单据表列表
     *
     * @return
     */
    @GetMapping("/list")
    List<SalePickGoodsInfoResponseDTO> listSalePickGoodsInfos(SalePickGoodsInfoRequestQuery query);

    /**
     * 分页查询提货单据表列表
     *
     * @return
     */
    @PostMapping("/page")
    PageBean<SalePickGoodsInfoResponseDTO> listSalePickGoodsInfosPage(@RequestBody SalePickGoodsInfoRequestQuery query);


    /**
     * 根据ID删除提货单据表
     *
     * @param id
     * @return
     */
    @DeleteMapping("/")
    Boolean deleteByIdIn(@RequestBody List<Long> id);

    /**
     * 新增提货单据表
     *
     * @param record
     * @return
     */
    @PostMapping("")
    SalePickGoodsInfoResponseDTO insert(@RequestBody SalePickGoodsInfoRequestDTO record);

    /**
     * 查询提货单据表详情
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    SalePickGoodsInfoResponseDTO selectById(@PathVariable Long id);


    /**
     * 根据提货单号获取提货计划信息
     * @param code
     * @return
     */
    @GetMapping("/getSalePickGoodsInfoByCode/{code}")
    Payload<SalePickGoodsInfoResponseDTO> getSalePickGoodsInfoByCode(@PathVariable String code);


    /**
     * 根据ID修改提货单据表
     *
     * @param id
     * @param record
     * @return
     */
    @PutMapping("/{id}")
    Boolean updateById(@PathVariable Long id, @RequestBody SalePickGoodsInfoRequestDTO record);


    /**
     * 新增提货单据表、订单明细、sku明细
     *
     * @param record
     * @return
     */
    @PostMapping("/saveSalePickGoodsInfo")
    SalePickGoodsInfoResponseDTO savePickGoods(@RequestBody SalePickGoodsInfoRequestDTO record);



    /**
     * @Description: 审批通过 .
     * @Param: [dto]
     * @return: com.deepexi.util.config.Payload<java.lang.Boolean>
     * @Author: SongTao
     * @Date: 2020/8/14
     */
    @PostMapping("/examinePass")
    Payload<Boolean> examinePass(@RequestBody SalePickGoodsInfoRequestDTO dto) throws Exception;
    /**
     * @Description:  审批驳回.
     * @Param: [dto]
     * @return: com.deepexi.util.config.Payload<java.lang.Boolean>
     * @Author: SongTao
     * @Date: 2020/8/30
     */
    @PostMapping("/examineReject")
    Payload<Boolean> examineReject(@RequestBody SalePickGoodsInfoRequestDTO dto) throws Exception;



    @ApiOperation("更新订单状态")
    @PostMapping("/updateOrderStatus")
     Payload<Boolean> updateOrderStatus(@RequestBody SaleOrderInfoStatusEditRequestDTO saleOrderInfoStatusEditRequestDTO);



    /**
     * 更新提货计划金额
     * @param salePickGoodsInfoAmountEditDTO
     * @return
     */
    @PostMapping("/updateOrderAmount")
    Payload<Boolean> updateOrderAmount(@RequestBody SalePickGoodsInfoAmountEditDTO salePickGoodsInfoAmountEditDTO);



    @GetMapping("/searchPickGoodsList/{saleOrderId}")
    Payload<PageBean<SalePickGoodsOrderSkuResponseDTO>> searchPickGoodsList(@PathVariable Long saleOrderId, @RequestParam("page") Integer page, @RequestParam("size") Integer size);
    /**
     * @Description:  提货计划的发货.
     * @Param: [dto]
     * @return: com.deepexi.util.config.Payload<java.lang.Boolean>
     * @Author: SongTao
     * @Date: 2020/8/15
     */
    @PostMapping("/pickPlanDeliveryGoods")
    Payload<Boolean> pickPlanDeliveryGoods(@RequestBody SalePickDeliveryInfoMiddleRequestDTO dto) throws Exception;



    /**
     * @Description:  提货单的出库单签收.
     * @Param: [saleOutTaskMiddleRequestDTO]
     * @return: java.lang.Boolean
     * @Author: SongTao
     * @Date: 2020/8/18
     */
    @PostMapping("/sign")
    Payload<Boolean> sign(@RequestBody SaleOutTaskMiddleRequestDTO saleOutTaskMiddleRequestDTO) throws Exception;



    /**
     * 更新提货单
     * @param dateDTO
     * @return
     */
    @PostMapping("/editPickGoods")
    SalePickGoodsInfoResponseDTO editPickGoods(@RequestBody SalePickGoodsInfoTransferDTO dateDTO);



    /**
     * 取消提货单
     * @param dateDTO
     * @return
     */
    @PostMapping("/doCancelPickGoods")
    SalePickGoodsInfoResponseDTO doCancelPickGoods(@RequestBody SalePickGoodsInfoTransferDTO dateDTO);

    /**
     * @Description:  根据提货单ID查询商品sku列表.
     * @Param: [id]
     * @return: com.deepexi.util.config.Payload<java.util.List<com.deepexi.dd.middle.order.domain.dto.SalePickGoodsDetailInfoMiddleResponseDTO>>
     * @Author: SongTao
     * @Date: 2020/8/21
     */
    @GetMapping("/searchSkuDetailList/{id}")
    Payload<List<SalePickGoodsDetailInfoMiddleResponseDTO>> searchSkuDetailList(@PathVariable Long id) throws Exception;

    @GetMapping("/selectByPickCode")
    SalePickGoodsInfoResponseDTO selectByPickCode(@RequestBody SalePickGoodsInfoRequestQuery query);
}