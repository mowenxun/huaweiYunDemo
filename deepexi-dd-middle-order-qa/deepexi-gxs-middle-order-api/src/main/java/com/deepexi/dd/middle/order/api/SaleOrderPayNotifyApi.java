package com.deepexi.dd.middle.order.api;
import com.deepexi.dd.middle.order.domain.query.SaleOrderPayNotifySearchQuery;
import com.deepexi.util.config.Payload;
import com.deepexi.util.pageHelper.PageBean;
import org.springframework.web.bind.annotation.*;
import com.deepexi.dd.middle.order.domain.dto.SaleOrderPayNotifyRequestDTO;
import com.deepexi.dd.middle.order.domain.dto.SaleOrderPayNotifyResponseDTO;
import com.deepexi.dd.middle.order.domain.query.SaleOrderPayNotifyRequestQuery;
import java.util.List;
import io.swagger.annotations.Api;

/**
 * SaleOrderPayNotifyApi
 *
 * @author admin
 * @date Thu Jul 23 18:17:38 CST 2020
 * @version 1.0
 */
@Api(value = "管理")
@RequestMapping("/middle/v1/saleOrderPayNotifys")
public interface SaleOrderPayNotifyApi {
    /**
     * 查询列表
     *
     * @return
     */
    @GetMapping("/list")
    Payload<List<SaleOrderPayNotifyResponseDTO>> listSaleOrderPayNotifys(SaleOrderPayNotifyRequestQuery query);

    /**
     * 分页查询列表
     *
     * @return
     */
    @GetMapping("/page")
    Payload<PageBean<SaleOrderPayNotifyResponseDTO>> listSaleOrderPayNotifysPage(SaleOrderPayNotifyRequestQuery query);


    /**
     * 根据ID删除
     *
     * @param id
     * @return
     */
    @DeleteMapping("")
    Payload<Boolean> deleteByIdIn(@RequestBody List<Long> id);

    /**
     * 新增
     *
     * @param record
     * @return
     */
    @PostMapping("")
    Payload<SaleOrderPayNotifyResponseDTO> insert(@RequestBody SaleOrderPayNotifyRequestDTO record);

    /**
     * 查询详情
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    Payload<SaleOrderPayNotifyResponseDTO> selectById(@PathVariable Long id);


    /**
     * 根据ID修改
     *
     * @param id
     * @param record
     * @return
     */
    @PutMapping("/{id}")
    Payload<Boolean> updateById(@PathVariable Long id, @RequestBody SaleOrderPayNotifyRequestDTO record);

    /**
     * 根据订单ID和支付单号查询订单支付通知
     * @param query
     * @return
     */
    @PostMapping("/querySaleOrderNotify")
    Payload<List<SaleOrderPayNotifyResponseDTO>> querySaleOrderNotify(@RequestBody SaleOrderPayNotifySearchQuery query);
}