package com.deepexi.dd.middle.order.api;
import com.deepexi.util.pageHelper.PageBean;
import org.springframework.web.bind.annotation.*;
import com.deepexi.dd.middle.order.domain.dto.ShopOrderItemRequestDTO;
import com.deepexi.dd.middle.order.domain.dto.ShopOrderItemResponseDTO;
import com.deepexi.dd.middle.order.domain.query.ShopOrderItemRequestQuery;
import java.util.List;
import io.swagger.annotations.Api;

/**
 * ShopOrderItemApi
 *
 * @author admin
 * @date Tue Oct 13 15:15:24 CST 2020
 * @version 1.0
 */
@Api(value = "店铺订单明细表管理")
@RequestMapping("/shopOrderItems")
public interface ShopOrderItemApi {
    /**
     * 查询店铺订单明细表列表
     *
     * @return
     */
    @GetMapping("/list")
    List<ShopOrderItemResponseDTO> listShopOrderItems(ShopOrderItemRequestQuery query);

    /**
     * 分页查询店铺订单明细表列表
     *
     * @return
     */
    @GetMapping("/page")
    PageBean<ShopOrderItemResponseDTO> listShopOrderItemsPage(ShopOrderItemRequestQuery query);


    /**
     * 根据ID删除店铺订单明细表
     *
     * @param id
     * @return
     */
    @DeleteMapping("")
    Boolean deleteByIdIn(@RequestBody List<Long> id);

    /**
     * 新增店铺订单明细表
     *
     * @param record
     * @return
     */
    @PostMapping("")
    ShopOrderItemResponseDTO insert(@RequestBody ShopOrderItemRequestDTO record);

    /**
     * 查询店铺订单明细表详情
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    ShopOrderItemResponseDTO selectById(@PathVariable Long id);


    /**
     * 根据ID修改店铺订单明细表
     *
     * @param id
     * @param record
     * @return
     */
    @PutMapping("/{id}")
    Boolean updateById(@PathVariable Long id, @RequestBody ShopOrderItemRequestDTO record);

    @PostMapping("/saveBatch")
    boolean saveBatch(@RequestBody List<ShopOrderItemRequestDTO> list);

}