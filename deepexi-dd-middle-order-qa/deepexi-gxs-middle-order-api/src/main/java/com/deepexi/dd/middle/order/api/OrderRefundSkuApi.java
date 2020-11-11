package com.deepexi.dd.middle.order.api;

import com.deepexi.dd.middle.order.domain.dto.OrderRefundSkuRequestDTO;
import com.deepexi.dd.middle.order.domain.dto.OrderRefundSkuResponseDTO;
import com.deepexi.dd.middle.order.domain.query.OrderRefundSkuRequestQuery;
import com.deepexi.util.config.Payload;
import com.deepexi.util.pageHelper.PageBean;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * OrderRefundSkuApi
 *
 * @author admin
 * @version 1.0
 * @date Wed Aug 19 16:31:13 CST 2020
 */
@Api(value = "退款原因明细管理")
@RequestMapping("/open-api-middle-order/middle/orderRefundSku")
public interface OrderRefundSkuApi {
    /**
     * 查询列表
     *
     * @return
     */
    @GetMapping("/list")
    Payload<List<OrderRefundSkuResponseDTO>> listOrderRefundSkus(OrderRefundSkuRequestQuery query);

    /**
     * 分页查询列表
     *
     * @return
     */
    @GetMapping("/page")
    PageBean<OrderRefundSkuResponseDTO> listOrderRefundSkusPage(OrderRefundSkuRequestQuery query);


    /**
     * 根据ID删除
     *
     * @param id
     * @return
     */
    @DeleteMapping("")
    Boolean deleteByIdIn(@RequestBody List<Long> id);

    /**
     * 新增
     *
     * @param record
     * @return
     */
    @PostMapping("")
    OrderRefundSkuResponseDTO insert(@RequestBody OrderRefundSkuRequestDTO record);

    /**
     * 查询详情
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    OrderRefundSkuResponseDTO selectById(@PathVariable Long id);


    /**
     * 根据ID修改
     *
     * @param id
     * @param record
     * @return
     */
    @PutMapping("/{id}")
    Boolean updateById(@PathVariable Long id, @RequestBody OrderRefundSkuRequestDTO record);

    /**
     * 批量新增
     *
     * @param list
     * @return
     */
    @PostMapping("/batchInsert")
    Payload<List<OrderRefundSkuResponseDTO>> batchInsert(@RequestBody List<OrderRefundSkuRequestDTO> list);

}