package com.deepexi.dd.middle.order.api;
import com.deepexi.dd.middle.order.domain.dto.SaleOrderItemMiddleRequestDTO;
import com.deepexi.dd.middle.order.domain.dto.SaleOrderItemMiddleResponseDTO;
import com.deepexi.dd.middle.order.domain.query.SaleOrderItemMiddleRequestQuery;
import com.deepexi.util.pageHelper.PageBean;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * SaleOrderItemApi
 *
 * @author admin
 * @date Tue Jun 23 19:44:58 CST 2020
 * @version 1.0
 */
@Api(value = "销售订单明细表管理")
@RequestMapping("/saleOrderItems")
public interface SaleOrderItemApi {
    /**
     * 查询销售订单明细表列表
     *
     * @return
     */
    @GetMapping("/list")
    List<SaleOrderItemMiddleResponseDTO> listSaleOrderItems(SaleOrderItemMiddleRequestQuery query);

    /**
     * @Description:  分页查询销售订单明细表列表.
     * @Param: [query]
     * @return: com.deepexi.util.pageHelper.PageBean<com.deepexi.dd.middle.order.domain.dto.SaleOrderItemResponseDTO>
     * @Author: SongTao
     * @Date: 2020/7/7
     */
    @GetMapping("/page")
    PageBean<SaleOrderItemMiddleResponseDTO> listSaleOrderItemsPage(SaleOrderItemMiddleRequestQuery query) throws Exception;


    /**
     * 根据ID删除销售订单明细表
     *
     * @param id
     * @return
     */
    @DeleteMapping("")
    Boolean deleteByIdIn(@RequestBody List<Long> id);

    /**
     * 新增销售订单明细表
     *
     * @param record
     * @return
     */
    @PostMapping("")
    SaleOrderItemMiddleResponseDTO insert(@RequestBody SaleOrderItemMiddleRequestDTO record);

    /**
     * 查询销售订单明细表详情
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    SaleOrderItemMiddleResponseDTO selectById(@PathVariable Long id);


    /**
     * 根据ID修改销售订单明细表
     *
     * @param id
     * @param record
     * @return
     */
    @PutMapping("/{id}")
    Boolean updateById(@PathVariable Long id, @RequestBody SaleOrderItemMiddleRequestDTO record);


    /**
     * 根据ID修改销售订单明细表
     * @return
     */
    @PutMapping("/updatebatchById")
    public Boolean updateBatchById(@RequestBody List<SaleOrderItemMiddleRequestDTO> records);

}