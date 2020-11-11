package com.deepexi.dd.middle.order.api;
import com.deepexi.dd.middle.order.domain.dto.ShopOrderItemRequestDTO;
import com.deepexi.util.pageHelper.PageBean;
import org.springframework.web.bind.annotation.*;
import com.deepexi.dd.middle.order.domain.dto.SupplerOrderItemRequestDTO;
import com.deepexi.dd.middle.order.domain.dto.SupplerOrderItemResponseDTO;
import com.deepexi.dd.middle.order.domain.query.SupplerOrderItemRequestQuery;
import java.util.List;
import io.swagger.annotations.Api;

/**
 * SupplerOrderItemApi
 *
 * @author admin
 * @date Tue Oct 13 15:15:23 CST 2020
 * @version 1.0
 */
@Api(value = "已分发订单明细表管理")
@RequestMapping("/supplerOrderItems")
public interface SupplerOrderItemApi {
    /**
     * 查询已分发订单明细表列表
     *
     * @return
     */
    @GetMapping("/list")
    List<SupplerOrderItemResponseDTO> listSupplerOrderItems(SupplerOrderItemRequestQuery query);

    /**
     * 分页查询已分发订单明细表列表
     *
     * @return
     */
    @GetMapping("/page")
    PageBean<SupplerOrderItemResponseDTO> listSupplerOrderItemsPage(SupplerOrderItemRequestQuery query);


    /**
     * 根据ID删除已分发订单明细表
     *
     * @param id
     * @return
     */
    @DeleteMapping("")
    Boolean deleteByIdIn(@RequestBody List<Long> id);

    /**
     * 新增已分发订单明细表
     *
     * @param record
     * @return
     */
    @PostMapping("")
    SupplerOrderItemResponseDTO insert(@RequestBody SupplerOrderItemRequestDTO record);

    /**
     * 查询已分发订单明细表详情
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    SupplerOrderItemResponseDTO selectById(@PathVariable Long id);


    /**
     * 根据ID修改已分发订单明细表
     *
     * @param id
     * @param record
     * @return
     */
    @PutMapping("/{id}")
    Boolean updateById(@PathVariable Long id, @RequestBody SupplerOrderItemRequestDTO record);

    @PostMapping("/saveBatch")
    boolean saveBatch(@RequestBody List<SupplerOrderItemRequestDTO> list);

    @PostMapping("/updateBatchById")
    boolean updateBatchById(@RequestBody List<SupplerOrderItemRequestDTO> list);

}