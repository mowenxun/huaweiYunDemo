package com.deepexi.dd.middle.order.api;
import com.deepexi.dd.middle.order.domain.dto.SaleOutTaskDetailInfoRequestDTO;
import com.deepexi.util.pageHelper.PageBean;
import org.springframework.web.bind.annotation.*;
import com.deepexi.dd.middle.order.domain.dto.SalePickGoodsOrderSkuRequestDTO;
import com.deepexi.dd.middle.order.domain.dto.SalePickGoodsOrderSkuResponseDTO;
import com.deepexi.dd.middle.order.domain.query.SalePickGoodsOrderSkuRequestQuery;
import java.util.List;
import io.swagger.annotations.Api;

/**
 * SalePickGoodsOrderSkuApi
 *
 * @author admin
 * @date Thu Aug 13 07:37:26 CST 2020
 * @version 1.0
 */
@Api(value = "提货单据所关联的订单所关联的sku信息表管理")
@RequestMapping("/middle/salePickGoodsOrderSkus")
public interface SalePickGoodsOrderSkuApi {
    /**
     * 查询提货单据所关联的订单所关联的sku信息表列表
     *
     * @return
     */
    @PostMapping("/list")
    List<SalePickGoodsOrderSkuResponseDTO> listSalePickGoodsOrderSkus(@RequestBody SalePickGoodsOrderSkuRequestQuery query);

    /**
     * 分页查询提货单据所关联的订单所关联的sku信息表列表
     *
     * @return
     */
    @GetMapping("/page")
    PageBean<SalePickGoodsOrderSkuResponseDTO> listSalePickGoodsOrderSkusPage(SalePickGoodsOrderSkuRequestQuery query);


    /**
     * 根据ID删除提货单据所关联的订单所关联的sku信息表
     *
     * @param id
     * @return
     */
    @DeleteMapping("")
    Boolean deleteByIdIn(@RequestBody List<Long> id);

    /**
     * 新增提货单据所关联的订单所关联的sku信息表
     *
     * @param record
     * @return
     */
    @PostMapping("")
    SalePickGoodsOrderSkuResponseDTO insert(@RequestBody SalePickGoodsOrderSkuRequestDTO record);

    /**
     * 查询提货单据所关联的订单所关联的sku信息表详情
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    SalePickGoodsOrderSkuResponseDTO selectById(@PathVariable Long id);


    /**
     * 根据ID修改提货单据所关联的订单所关联的sku信息表
     *
     * @param id
     * @param record
     * @return
     */
    @PutMapping("/{id}")
    Boolean updateById(@PathVariable Long id, @RequestBody SalePickGoodsOrderSkuRequestDTO record);

    @GetMapping("/selectBySkuId")
    SalePickGoodsOrderSkuResponseDTO selectByRowCode(@RequestParam String rowCode);


    /**
     * 根据销售单明细获取出库单明细
     */
    @PostMapping("/getOutItemBySaleItemId")
    List<SaleOutTaskDetailInfoRequestDTO> getOutItemBySaleItemId(@RequestBody List<Long> saleItemIds);




}