package com.deepexi.dd.middle.order.api;
import com.deepexi.util.pageHelper.PageBean;
import org.springframework.web.bind.annotation.*;
import com.deepexi.dd.middle.order.domain.dto.ShopOrderRequestDTO;
import com.deepexi.dd.middle.order.domain.dto.ShopOrderResponseDTO;
import com.deepexi.dd.middle.order.domain.query.ShopOrderRequestQuery;
import java.util.List;
import io.swagger.annotations.Api;

/**
 * ShopOrderApi
 *
 * @author admin
 * @date Tue Oct 13 14:53:15 CST 2020
 * @version 1.0
 */
@Api(value = "店铺订单管理")
@RequestMapping("/shopOrders")
public interface ShopOrderApi {
    /**
     * 查询店铺订单列表
     *
     * @return
     */
    @GetMapping("/list")
    List<ShopOrderResponseDTO> listShopOrders(ShopOrderRequestQuery query);

    /**
     * 分页查询店铺订单列表
     *
     * @return
     */
    @GetMapping("/page")
    PageBean<ShopOrderResponseDTO> listShopOrdersPage(ShopOrderRequestQuery query);


    /**
     * 根据ID删除店铺订单
     *
     * @param id
     * @return
     */
    @DeleteMapping("")
    Boolean deleteByIdIn(@RequestBody List<Long> id);

    /**
     * 新增店铺订单
     *
     * @param record
     * @return
     */
    @PostMapping("")
    ShopOrderResponseDTO insert(@RequestBody ShopOrderRequestDTO record);

    /**
     * 查询店铺订单详情
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    ShopOrderResponseDTO selectById(@PathVariable Long id);


    /**
     * 根据ID修改店铺订单
     *
     * @param id
     * @param record
     * @return
     */
    @PutMapping("/{id}")
    Boolean updateById(@PathVariable Long id, @RequestBody ShopOrderRequestDTO record);

    /**
     * 根据code修改
     * @param record
     * @param record
     * @return
     */
    @PutMapping("/updateByCode")
    Boolean updateByCode(@RequestBody List<ShopOrderRequestDTO> record);
}