package com.deepexi.dd.middle.order.api;
import com.deepexi.util.pageHelper.PageBean;
import org.springframework.web.bind.annotation.*;
import com.deepexi.dd.middle.order.domain.dto.SupplerOrderRequestDTO;
import com.deepexi.dd.middle.order.domain.dto.SupplerOrderResponseDTO;
import com.deepexi.dd.middle.order.domain.query.SupplerOrderRequestQuery;
import java.util.List;
import io.swagger.annotations.Api;

/**
 * SupplerOrderApi
 *
 * @author admin
 * @date Tue Oct 13 15:15:23 CST 2020
 * @version 1.0
 */
@Api(value = "已分发订单管理")
@RequestMapping("/supplerOrders")
public interface SupplerOrderApi {
    /**
     * 查询已分发订单列表
     *
     * @return
     */
    @GetMapping("/list")
    List<SupplerOrderResponseDTO> listSupplerOrders(SupplerOrderRequestQuery query);

    /**
     * 分页查询已分发订单列表
     *
     * @return
     */
    @GetMapping("/page")
    PageBean<SupplerOrderResponseDTO> listSupplerOrdersPage(SupplerOrderRequestQuery query);


    /**
     * 根据ID删除已分发订单
     *
     * @param id
     * @return
     */
    @DeleteMapping("")
    Boolean deleteByIdIn(@RequestBody List<Long> id);

    /**
     * 新增已分发订单
     *
     * @param record
     * @return
     */
    @PostMapping("")
    SupplerOrderResponseDTO insert(@RequestBody SupplerOrderRequestDTO record);

    /**
     * 查询已分发订单详情
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    SupplerOrderResponseDTO selectById(@PathVariable Long id);


    /**
     * 根据ID修改已分发订单
     *
     * @param id
     * @param record
     * @return
     */
    @PutMapping("/{id}")
    Boolean updateById(@PathVariable Long id, @RequestBody SupplerOrderRequestDTO record);

}