package com.deepexi.dd.middle.order.api;
import com.deepexi.dd.middle.order.domain.dto.SaleOrderPayNotifyResponseDTO;
import com.deepexi.dd.middle.order.domain.query.SaleOrderCollectionNotifySearchQuery;
import com.deepexi.dd.middle.order.domain.query.SaleOrderPayNotifySearchQuery;
import com.deepexi.util.config.Payload;
import com.deepexi.util.pageHelper.PageBean;
import org.springframework.web.bind.annotation.*;
import com.deepexi.dd.middle.order.domain.dto.SaleOrderCollectionNotifyRequestDTO;
import com.deepexi.dd.middle.order.domain.dto.SaleOrderCollectionNotifyResponseDTO;
import com.deepexi.dd.middle.order.domain.query.SaleOrderCollectionNotifyRequestQuery;
import java.util.List;
import io.swagger.annotations.Api;

/**
 * SaleOrderCollectionNotifyApi
 *
 * @author admin
 * @date Fri Jul 24 14:50:09 CST 2020
 * @version 1.0
 */
@Api(value = "按单收款消息表管理")
@RequestMapping("/middle/v1/saleOrderCollectionNotifys")
public interface SaleOrderCollectionNotifyApi {
    /**
     * 查询按单收款消息表列表
     *
     * @return
     */
    @GetMapping("/list")
    List<SaleOrderCollectionNotifyResponseDTO> listSaleOrderCollectionNotifys(SaleOrderCollectionNotifyRequestQuery query);

    /**
     * 分页查询按单收款消息表列表
     *
     * @return
     */
    @GetMapping("/page")
    PageBean<SaleOrderCollectionNotifyResponseDTO> listSaleOrderCollectionNotifysPage(SaleOrderCollectionNotifyRequestQuery query);


    /**
     * 根据ID删除按单收款消息表
     *
     * @param id
     * @return
     */
    @DeleteMapping("")
    Boolean deleteByIdIn(@RequestBody List<Long> id);

    /**
     * 新增按单收款消息表
     *
     * @param record
     * @return
     */
    @PostMapping("")
    SaleOrderCollectionNotifyResponseDTO insert(@RequestBody SaleOrderCollectionNotifyRequestDTO record);

    /**
     * 查询按单收款消息表详情
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    SaleOrderCollectionNotifyResponseDTO selectById(@PathVariable Long id);


    /**
     * 根据ID修改按单收款消息表
     *
     * @param id
     * @param record
     * @return
     */
    @PutMapping("/{id}")
    Boolean updateById(@PathVariable Long id, @RequestBody SaleOrderCollectionNotifyRequestDTO record);

    /**
     * 根据订单ID和支付单号查询订单支付通知
     * @param query
     * @return
     */
    @PostMapping("/querySaleOrderNotify")
    Payload<List<SaleOrderCollectionNotifyResponseDTO>> querySaleOrderCollectionNotify(@RequestBody SaleOrderCollectionNotifySearchQuery query);
}