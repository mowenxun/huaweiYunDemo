package com.deepexi.dd.middle.order.api;
import com.deepexi.util.pageHelper.PageBean;
import org.springframework.web.bind.annotation.*;
import com.deepexi.dd.middle.order.domain.dto.SalePickGoodsOrderRequestDTO;
import com.deepexi.dd.middle.order.domain.dto.SalePickGoodsOrderResponseDTO;
import com.deepexi.dd.middle.order.domain.query.SalePickGoodsOrderRequestQuery;
import java.util.List;
import io.swagger.annotations.Api;

/**
 * SalePickGoodsOrderApi
 *
 * @author admin
 * @date Wed Aug 12 22:17:35 CST 2020
 * @version 1.0
 */
@Api(value = "提货单据所关联的订单的信息表管理")
@RequestMapping("/middle/salePickGoodsOrders")
public interface SalePickGoodsOrderApi {
    /**
     * 查询提货单据所关联的订单的信息表列表
     *
     * @return
     */
    @GetMapping("/list")
    List<SalePickGoodsOrderResponseDTO> listSalePickGoodsOrders(SalePickGoodsOrderRequestQuery query);

    /**
     * 分页查询提货单据所关联的订单的信息表列表
     *
     * @return
     */
    @GetMapping("/page")
    PageBean<SalePickGoodsOrderResponseDTO> listSalePickGoodsOrdersPage(SalePickGoodsOrderRequestQuery query);


    /**
     * 根据ID删除提货单据所关联的订单的信息表
     *
     * @param id
     * @return
     */
    @DeleteMapping("")
    Boolean deleteByIdIn(@RequestBody List<Long> id);

    /**
     * 新增提货单据所关联的订单的信息表
     *
     * @param record
     * @return
     */
    @PostMapping("")
    SalePickGoodsOrderResponseDTO insert(@RequestBody SalePickGoodsOrderRequestDTO record);

    /**
     * 查询提货单据所关联的订单的信息表详情
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    SalePickGoodsOrderResponseDTO selectById(@PathVariable Long id);


    /**
     * 根据ID修改提货单据所关联的订单的信息表
     *
     * @param id
     * @param record
     * @return
     */
    @PutMapping("/{id}")
    Boolean updateById(@PathVariable Long id, @RequestBody SalePickGoodsOrderRequestDTO record);

}